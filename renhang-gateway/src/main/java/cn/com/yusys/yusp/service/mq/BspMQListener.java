package cn.com.yusys.yusp.service.mq;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.service.CashClient;
import cn.com.yusys.yusp.service.MoneyDto;

@Service
public class BspMQListener {

	private ObjectMapper objectMapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(BspMQListener.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private CashClient cashClient;

	@RabbitListener(queues = { "pay" })
	@RabbitHandler
	public void receiveQueue(String message) {
		try {

			logger.info("收到去人行报文:" + message);
			amqpTemplate.convertAndSend("payback", message);
			logger.info("转发到清算中心" + message);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("转发到清算中心对象失败：" + message);
		}
	}
}
