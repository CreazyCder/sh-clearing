package cn.com.yusys.yusp.service.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;

@JobHandler(value = "DataCopyJobImpl")
@Service
public class DataCopyJobImpl extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(DataCopyJobImpl.class);

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        Connection connection = null;
        try {
            logger.info("数据同步定时任务执行：DataCopyJobImpl");
            connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl", "SHCH_POC", "SHCH_POC");
            connection.setAutoCommit(false);
            List<List<String>> task = getTasks(connection);
            
            for (List<String> t : task) {
            	List<String> cols = getTableColName(connection,t.get(0));
                String sql = getSelectSql(cols, t.get(0));
                System.out.println(sql);
                List<Map<String, Object>> data = getData(connection, cols, sql);
                insertData(data, t.get(1));
			}
            
        } catch (Exception e) {
            logger.error("发生异常: " + e.getMessage());
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }

        return ReturnT.SUCCESS;
    }

    /**
     * 获取数据库连接
     *
     * @param url
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    private Connection getConnection(String url, String username, String password) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     */
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void select(Connection conn, String sql) throws Exception {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        // 遍历获取表结构信息
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String date = rs.getString(3);
            System.out.println(id + " " + name + " " + date);
        }
        rs.close();
    }

    public void testInsertData() throws Exception {
        Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl", "SHCH_POC", "SHCH_POC");
        connection.setAutoCommit(false);
        List<String> task = getTask(connection);
        List<String> cols = getTableColName(connection, task.get(0));
        String sql = getSelectSql(cols, task.get(0));
        //sql = sql +" where update_tm >= to_date("+task.get(2)+", 'yyyymmddhh24miss') and update_tm < to_date("+task.get(2)+", 'yyyymmddhh24miss') + numtodsinterval(10, 'second')";
        System.out.println(sql);
        List<Map<String, Object>> data = getData(connection, cols, sql);
        closeConnection(connection);
        insertData(data, task.get(1));
    }

    /**
     * @throws Exception
     */

    private List<String> getTask(Connection conn) throws Exception {
        List<String> data = new ArrayList<String>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select src_table, dst_table, to_char(last_transx_tm, 'yyyymmddhh24miss') from data_transx_ctrl where last_transx_tm < sysdate - numtodsinterval(20, 'second')");
        // 遍历获取表结构信息
        while (rs.next()) {
            String s = rs.getString(1);
            String t = rs.getString(2);
            String d = rs.getString(3);
            data.add(s);
            data.add(t);
            data.add(d);
            break;
        }
        rs.close();
        return data;
    }

    private List<List<String>> getTasks(Connection conn) throws Exception {
        List<List<String>> data = new ArrayList<List<String>>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select src_table, dst_table, to_char(last_transx_tm, 'yyyymmddhh24miss') from data_transx_ctrl where last_transx_tm < sysdate - numtodsinterval(20, 'second')");
        // 遍历获取表结构信息
        while (rs.next()) {
            String s = rs.getString(1);
            String t = rs.getString(2);
            String d = rs.getString(3);
            
            data.add(Arrays.asList(new String[] {s,t,d}));
        }
        rs.close();
        return data;
    }
    /**
     * 获取表结构
     *
     * @param connection
     * @param tableName
     * @return
     * @throws Exception
     */
    private List<String> getTableColName(Connection connection, String tableName) throws Exception {
        List<String> colNames = new ArrayList<String>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT A.table_name,  A.column_name, A.data_type, A.data_length, A.DATA_PRECISION FROM SYS.USER_TAB_COLUMNS A where table_name = '" + tableName + "' ORDER BY column_id asc");
        // 遍历获取表结构信息
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String date = rs.getString(3);
            System.out.println(id + " " + name + " " + date);
            colNames.add(name);
        }
        rs.close();
        return colNames;
    }
    /**
     * 取得表主键
     * @param connection
     * @param tableName
     * @return
     * @throws Exception
     */
    private List<String> getTableKeyName(Connection connection, String tableName) throws Exception {
        List<String> colNames = new ArrayList<String>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select col.column_name \r\n" + 
        		"from user_constraints con,  user_cons_columns col \r\n" + 
        		"where con.constraint_name = col.constraint_name \r\n" + 
        		"and con.constraint_type='P' \r\n" + 
        		"and col.table_name = '"+tableName+"'");
        // 遍历获取表结构信息
        while (rs.next()) {
            colNames.add(rs.getString(1));
        }
        rs.close();
        return colNames;
    }

    private String getSelectSql(List<String> colNames, String table) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select ");
        for (String col : colNames) {
            sqlStr.append(col + ",");
        }

        String sql = sqlStr.toString();
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " from " + table;
        System.out.println(sql);
        return sql;
    }

    private List<Map<String, Object>> getData(Connection connection, List<String> cols, String sql) throws Exception {
        List<Map<String, Object>> data = new ArrayList();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        // 遍历获取表结构信息
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String col : cols) {
                Object colData = rs.getObject(col);
                map.put(col, colData);
            }
            data.add(map);
        }
        rs.close();
        logger.info("数据量：" + data.size());
        return data;
    }

    private int insertData(List<Map<String, Object>> data, String table) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl", "SHCH_SUM", "SHCH_SUM");
            connection.setAutoCommit(false);
            for (Map<String, Object> dataT : data) {
                String sql = generateSql(dataT, table);
                System.out.println(sql);
                try {
                    insert(connection, sql);
                    logger.info("插入成功：" + sql);
                } catch (Exception e) {
                    //e.printStackTrace();
                    logger.warn("insert 数据失败" + e.getMessage());
                    String updateSql = generateUpdateSqlByKey(connection,dataT, table);
                    insert(connection, updateSql); // update数据
                    logger.info("更新成功：" + updateSql);
                }
            }
        } catch (Exception e) {
            logger.error("发生异常: " + e.getMessage());
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }

        return data.size();
    }

    private String generateUpdateSql(Map<String, Object> data, String table) {

        StringBuilder items = new StringBuilder("");

        String acctDate = null;
        String serialNum = null;

        for (String key : data.keySet()) {
            if (null == data.get(key)) {
                continue;
            }

            String data1 = dataChange(data.get(key)).toString();
            if (key.equals("ACCT_DATE")) {
                acctDate = data1;
                continue;
            }
            if (key.equals("SERIAL_NUM")) {
                serialNum = data1;
                continue;
            }

            String item = " " + key + " = " + data1 + ",";
            items.append(item);
        }

        String updateString = items.toString();
        updateString = updateString.substring(0, updateString.length() - 1);

        String sql = String.format("UPDATE %s SET %s WHERE ACCT_DATE=%s AND SERIAL_NUM=%s", table, updateString, acctDate, serialNum);

        return sql;
    }

    private String generateUpdateSqlByKey(Connection conn,Map<String, Object> data, String table) throws Exception {

    	List<String> keys = getTableKeyName(conn, table);
    	
        StringBuilder items = new StringBuilder("");
        StringBuilder condation = new StringBuilder("");
        
        String acctDate = null;
        String serialNum = null;

        for (String key : data.keySet()) {
            if (null == data.get(key)) {
                continue;
            }

            String data1 = dataChange(data.get(key)).toString();
            
            if (keys.contains(key)) {
                condation.append("AND ").append(key).append(" = ").append(data1);
                continue;
            }

            String item = " " + key + " = " + data1 + ",";
            items.append(item);
        }

        String updateString = items.toString();
        updateString = updateString.substring(0, updateString.length() - 1);

        String sql = String.format("UPDATE %s SET %s WHERE 1=1 %s", table, updateString, condation.toString());

        return sql;
    }
    
    private String generateSql(Map<String, Object> data, String table) {
        StringBuilder sql = new StringBuilder();
        sql = sql.append("INSERT INTO " + table + " ");
        StringBuilder key1 = new StringBuilder("(");
        StringBuilder data1 = new StringBuilder("(");
        for (String key : data.keySet()) {
            if (null == data.get(key)) {
                continue;
            }
            key1 = key1.append(key).append(",");
            data1 = data1.append(dataChange(data.get(key))).append(",");
        }
        String key2 = key1.toString();
        key2 = key2.substring(0, key2.length() - 1);
        key2 = key2 + ")";

        String data2 = data1.toString();
        data2 = data2.substring(0, data2.length() - 1);
        data2 = data2 + ")";

        sql.append(key2).append(" values ").append(data2);
        return sql.toString();
    }


    private Object dataChange(Object data) {
        StringBuffer dd = new StringBuffer();
        if (data instanceof java.sql.Timestamp) {
            dd = dd.append("to_timestamp('").append(data).append("', 'yyyy-mm-dd hh24:mi:ss.ff1')");
        } else if (data instanceof java.sql.Date) {
            dd = dd.append("to_date('").append(data).append("', 'yyyy-mm-dd hh24:mi:ss')");
        } else {
            dd = dd.append("'").append(data).append("'");
        }
        return dd;
    }

    private void insert(Connection connection, String sql) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	/*public static void main(String[] args) throws Exception {
		String ff = "{\"memCode\":\"A000004\",\"serialNum\":100}";
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.readValue(ff, Map.class);
		objectMapper.readValue(ff, Map.class);
	}*/
}
