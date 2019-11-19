package cn.com.yusys.yusp.service.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
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
    	logger.info("数据同步定时任务执行：DataCopyJobImpl");
    	Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl","SHCH_POC","SHCH_POC");					
		connection.setAutoCommit(false);
		List<String> task = getTask(connection);	
		List<String> cols = getTableColName(connection,task.get(0));
		String sql = getSelectSql(cols,task.get(0));
		//sql = sql +" where update_tm >= to_date("+task.get(2)+", 'yyyymmddhh24miss') and update_tm < to_date("+task.get(2)+", 'yyyymmddhh24miss') + numtodsinterval(10, 'second')";
		System.out.println(sql);
		List<Map<String, Object>> data = getData(connection,cols,sql);
		closeConnection(connection);
		insertData(data , task.get(1));
		
        return ReturnT.SUCCESS;
    }
	
	/**
	 * 获取数据库连接
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection(String url,String username,String password) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection(url,username,password);
		return connection;
	}
	
	/**
	 * 关闭数据库连接
	 * @param connection
	 */
	private void closeConnection(Connection connection){
		if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	private void select(Connection conn,String sql) throws Exception{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		// 遍历获取表结构信息
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String date = rs.getString(3);
			System.out.println(id+" "+name+" "+date);
		}
		rs.close();
	}
	
	@Test
	public void testInsertData() throws Exception {
		Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl","SHCH_POC","SHCH_POC");					
		connection.setAutoCommit(false);
		List<String> task = getTask(connection);	
		List<String> cols = getTableColName(connection,task.get(0));
		String sql = getSelectSql(cols,task.get(0));
		//sql = sql +" where update_tm >= to_date("+task.get(2)+", 'yyyymmddhh24miss') and update_tm < to_date("+task.get(2)+", 'yyyymmddhh24miss') + numtodsinterval(10, 'second')";
		System.out.println(sql);
		List<Map<String, Object>> data = getData(connection,cols,sql);
		closeConnection(connection);
		insertData(data , task.get(1));
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 */
	
	private List<String> getTask(Connection conn) throws Exception{
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
	/**
	 * 获取表结构
	 * @param connection
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	private List<String> getTableColName(Connection connection,String tableName) throws Exception{
		List<String> colNames = new ArrayList<String>();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT A.table_name,  A.column_name, A.data_type, A.data_length, A.DATA_PRECISION FROM SYS.USER_TAB_COLUMNS A where table_name = '"+tableName+"' ORDER BY column_id asc");
		// 遍历获取表结构信息
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String date = rs.getString(3);
			System.out.println(id+" "+name+" "+date);
			colNames.add(name);
		}
		rs.close();
		return colNames;
	}
	
	private String getSelectSql(List<String> colNames,String table){
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select ");
		for(String col:colNames){
			sqlStr.append(col+",");
		}
		
		String sql = sqlStr.toString();
		sql = sql.substring(0, sql.length()-1);
		sql = sql + " from "+table;	
		System.out.println(sql);
		return sql;
	}
	
	private List<Map<String,Object>> getData(Connection connection,List<String> cols,String sql) throws Exception{
		List<Map<String,Object>> data = new ArrayList();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		// 遍历获取表结构信息
		while (rs.next()) {
			Map<String,Object> map = new HashMap<String,Object>();
			for(String col:cols){
				Object colData = rs.getObject(col);
				map.put(col, colData);
			}
			data.add(map);
		}
		rs.close();
		logger.info("数据量："+data.size());
		return data;
	}
	
	private int insertData(List<Map<String,Object>> data ,String table) throws Exception{
		Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl", "SHCH_SUM", "SHCH_SUM");
		connection.setAutoCommit(false);
		for (Map<String, Object> dataT : data) {
			String sql = generateSql(dataT, table);
			System.out.println(sql);
			try {
				insert(connection, sql);
				logger.info("插入成功：" + sql);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		closeConnection(connection);
		return data.size();
	}
	
	private String generateSql(Map<String,Object> data,String table){
		StringBuilder sql = new StringBuilder();
		sql = sql.append("INSERT INTO "+table+" ");
		StringBuilder key1 = new StringBuilder("(");
		StringBuilder data1 = new StringBuilder("(");
		for(String key:data.keySet()){
			if(null==data.get(key)){
				continue;
			}
			key1 = key1.append(key).append(",");
			data1 = data1.append(dataChange(data.get(key))).append(",");
		}
		String key2 = key1.toString();
		key2 = key2.substring(0, key2.length()-1);
		key2 = key2 + ")";
		
		String data2 = data1.toString();
		data2 = data2.substring(0, data2.length()-1);
		data2 = data2 + ")";
		
		sql.append(key2).append(" values ").append(data2);
		return sql.toString();
	}
	
	private Object dataChange(Object data){
		StringBuffer dd = new StringBuffer();
		if(data instanceof java.sql.Timestamp){
			dd = dd.append("to_timestamp('").append(data).append("', 'yyyy-mm-dd hh24:mi:ss.ff1')");
		}else{
			dd = dd.append("'").append(data).append("'");
		}
		return dd;
	}
	
	private void insert(Connection connection,String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
