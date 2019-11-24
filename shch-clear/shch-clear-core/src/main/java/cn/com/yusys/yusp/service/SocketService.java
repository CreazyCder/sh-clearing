package cn.com.yusys.yusp.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import cn.com.yusys.yusp.commons.util.NumberUtil;

public class SocketService {
	private static String CHART_SET = "GBK";
	
	private static String ip = "106.13.136.114";
	private static int port = 8088;
	
	public static void socket(String json) throws IOException, Exception{
		Socket socket = new Socket();
		InetSocketAddress socketAddr = new InetSocketAddress(
				InetAddress.getByName(ip), port);
		socket.connect(socketAddr, 50000);
		socket.setKeepAlive(true);
		
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		byte[] reqHeadBytes = json.getBytes(CHART_SET);
		String s =String.format("%08d", reqHeadBytes.length);
		System.out.println(s);
		out.write(s.getBytes(CHART_SET));
		out.write(reqHeadBytes);
		
		out.flush();
		
		socket.setSoTimeout(50000);
		
		DataInputStream dis = new DataInputStream(socket.getInputStream());
        
		byte[] dataLen = new byte[8];
        dis.readFully(dataLen);
		int hlen = NumberUtil.parseToInt(new String(dataLen), 0);
		System.out.println(hlen);
		byte[] rspHeadBytes = new byte[(int) hlen];
		dis.readFully(rspHeadBytes);
		
		String rspHead = new String(rspHeadBytes,CHART_SET);
		System.out.println(rspHead);
	}
	
	public static void main(String[] args) throws Exception,IOException {
		String json ="{\r\n" + 
				"	\"yusys\": {\r\n" + 
				"		\"head\": {\r\n" + 
				"			\"appCode\": \"1003\",\r\n" + 
				"			\"sceneNo\": \"1001\",\r\n" + 
				"			\"chnlCode\": \"0147\",\r\n" + 
				"			\"chnlSeqNo\": \"stm110000120180511001702891\",\r\n" + 
				"			\"tradeName\": \"单据模板样例\",\r\n" + 
				"			\"tradeCode\": \"SC3001\"\r\n" + 
				"		},\r\n" + 
				"		\"body\": {\r\n" + 
				"			\"request\": {\r\n" + 
				"				\"trade_id\":\"DVP201911<br>170000001\",\r\n" + 
				"				\"settle_order_id\":\"DVP2019111<br>000001\",\r\n" + 
				"				\"settle_date\":\"2019/11/17\",\r\n" + 
				"				\"bond_code\":\"011900001\",\r\n" + 
				"				\"bond_name\":\"国债城建001\",\r\n" + 
				"				\"seller_mem_code\":\"A000005\",\r\n" + 
				"				\"seller_mem_name\":\"中国工商银行\",\r\n" + 
				"				\"buyer_mem_code\":\"A000006\",\r\n" + 
				"				\"buyer_mem_name\":\"招商银行\",\r\n" + 
				"				\"bond_face_amt\":\"1\",\r\n" + 
				"				\"settle_order_status_update_tm\":\"2019/11/17 16:41:01\",\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		socket(json);
	}
	
}
