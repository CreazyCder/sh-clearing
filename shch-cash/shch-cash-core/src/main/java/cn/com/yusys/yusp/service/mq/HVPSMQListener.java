package cn.com.yusys.yusp.service.mq;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.domain.CashSettleOrder;
import cn.com.yusys.yusp.repository.mapper.CashSettleOrderMapper;

@Service
public class HVPSMQListener {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(HVPSMQListener.class);
    
    @Autowired
    private CashSettleOrderMapper cashSettleOrderMapper;

	@RabbitListener(queues = {"pay"})
	@RabbitHandler
	public void receiveQueue(String message) {
		System.out.println(message);
		try {
			Map data = objectMapper.readValue(message, Map.class);
			logger.info("支付处理接收报文格式化对象成功："+message);
			CashSettleOrder record3 = new CashSettleOrder();
			record3.setSerialNum(data.get("serialNum").toString());
			record3.setCashProcStatus("3");
			cashSettleOrderMapper.updateByPrimaryKeySelective(record3);	
			
			/**
			 * 通知清算成功
			 */
		} catch (IOException e) {
			logger.info("支付处理接收报文格式化对象失败："+message);
		}
	}
}
