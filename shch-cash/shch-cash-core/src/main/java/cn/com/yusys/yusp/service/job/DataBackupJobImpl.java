package cn.com.yusys.yusp.service.job;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.commons.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JobHandler(value = "DataBackupJobImpl")
@Service
public class DataBackupJobImpl extends IJobHandler {
	
    private Logger logger = LoggerFactory.getLogger(DataBackupJobImpl.class);
    
	@Override
    public ReturnT<String> execute(String param) throws Exception {
    	logger.info("数据备份定时任务执行：DataBackupJobImpl");
    	Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl","SHCH_POC","SHCH_POC");					
		connection.setAutoCommit(false);
		String date = DateUtil.getCurrDateStr();
		date = date.replaceAll("-", "");
		List<String> task = getTask(connection,date);
		if(null == task || task.isEmpty()){
			logger.info("当天数据已经备份");
		}else{
			List<String> cols = getTableColName(connection,task.get(0));
			String sql = getSelectSql(cols,task.get(0));
			sql = sql +" where "+task.get(1)+" < '" + date +"'";
			List<Map<String, Object>> data = getData(connection,cols,sql);
			for (Map<String, Object> dataT : data) {
				generateData(dataT);
			}
			update(connection,"update DATA_BACKUP_CTRL set LAST_BACKUP_DATE = '"+date+"' where BACKUP_TABLE_NAME = '"+task.get(0)+"' and DATE_COLUMN_NAME = '"+task.get(1)+"' and LAST_BACKUP_DATE = '"+task.get(2)+"'");
			closeConnection(connection);
		}
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
	
	public void testInsertData() throws Exception {
		Connection connection = getConnection("jdbc:oracle:thin:@//192.168.251.166:1521/orcl","SHCH_POC","SHCH_POC");					
		connection.setAutoCommit(false);
		String date = DateUtil.getCurrDateStr();
		date = date.replaceAll("-", "");
		List<String> task = getTask(connection,date);
		if(null == task || task.isEmpty()){
			logger.info("当天数据已经备份");
			return;
		}
		List<String> cols = getTableColName(connection,task.get(0));
		String sql = getSelectSql(cols,task.get(0));
		sql = sql +" where "+task.get(1)+" < '" + date +"'";
		List<Map<String, Object>> data = getData(connection,cols,sql);
		for (Map<String, Object> dataT : data) {
			generateData(dataT);
		}
		update(connection,"update DATA_BACKUP_CTRL set LAST_BACKUP_DATE = '"+date+"' where BACKUP_TABLE_NAME = '"+task.get(0)+"' and DATE_COLUMN_NAME = '"+task.get(1)+"' and LAST_BACKUP_DATE = '"+task.get(2)+"'");
		closeConnection(connection);	
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 */	
	private List<String> getTask(Connection conn,String date) throws Exception{
		List<String> data = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select BACKUP_TABLE_NAME, DATE_COLUMN_NAME, LAST_BACKUP_DATE from DATA_BACKUP_CTRL where LAST_BACKUP_DATE < '"+ date +"'");
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
			//System.out.println(id+" "+name+" "+date);
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
		//System.out.println(sql);
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
	
	private void generateData(Map<String,Object> data){
		StringBuilder sql = new StringBuilder();
		
		StringBuilder data1 = new StringBuilder("(");
		for(String key:data.keySet()){
			data1 = data1.append(dataChange(data.get(key))).append(",");
		}
		
		String data2 = data1.toString();
		data2 = data2.substring(0, data2.length()-1);
		data2 = data2 + ")";
		logger.info("数据备份成功：" + data2);
	}
	
	private Object dataChange(Object data){
		StringBuffer dd = new StringBuffer();
		dd = dd.append("'").append(data).append("'");
		return dd;
	}
	
	private void update(Connection connection,String sql) {
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
