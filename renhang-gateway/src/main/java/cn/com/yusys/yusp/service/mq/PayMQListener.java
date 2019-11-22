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
public class PayMQListener {

	private ObjectMapper objectMapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(PayMQListener.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private CashClient cashClient;

	@RabbitListener(queues = { "payback" })
	@RabbitHandler
	public void receiveQueue(String message) {
		System.out.println(message);
		try {

			/*
			 * Map dd = new HashMap<String,String>(); dd.put("serialNum", "2222");
			 * cashClient.pay(dd);
			 */

			Map data = objectMapper.readValue(message, Map.class);
			logger.info("支付处理接收报文格式化对象成功：" + message);

			if (data.get("msgType").equals("HVPS.111.001.01")) {
				ResultDto<String> result = cashClient.pay(data);
				logger.info("自动化测试平台(人行)返回成功：" + result);
			} else if (data.get("msgType").equals("HVPS.112.001.01")) {//
				// payback塞消息
				//amqpTemplate.convertAndSend("payback", message);

				MoneyDto money = new MoneyDto();
				money.setCashAccount(data.get("cashAccount").toString());
				money.setCurrencyAmt(new BigDecimal(data.get("currencyAmt").toString()));
				money.setHolderAccount(data.get("holderAccount").toString());
				money.setMemCode(data.get("memCode").toString());
				money.setTitleCode(data.get("titleCode").toString());
				cashClient.add(money);
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("支付处理接收报文格式化对象失败：" + message);
		}
	}
}
