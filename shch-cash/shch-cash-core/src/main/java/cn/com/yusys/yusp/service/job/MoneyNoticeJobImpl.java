package cn.com.yusys.yusp.service.job;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import cn.com.yusys.yusp.message.client.MessageClient;
import cn.com.yusys.yusp.service.CashAccountBalanceService;

@JobHandler(value = "MoneyNoticeJobImpl")
@Service
public class MoneyNoticeJobImpl extends IJobHandler {

	private Logger logger = LoggerFactory.getLogger(MoneyNoticeJobImpl.class);
	
    private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MessageClient messageClient;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private CashAccountBalanceService cashAccountBalanceService;

	@Override
	public ReturnT<String> execute(String param2) throws Exception {
		logger.info("余额通知定时任务执行：MoneyNoticeJobImpl");
		Map<String, Object> param = new HashMap<String, Object>();
		QueryModel model = new QueryModel();
		List<CashAccountBalance> data = cashAccountBalanceService.selectByModel(model);
		for(CashAccountBalance cashAccountBalanceT:data){
			amqpTemplate.convertAndSend(cashAccountBalanceT.getMemCode(), objectMapper.writeValueAsString(cashAccountBalanceT));
			if(cashAccountBalanceT.getCurrencyAmt().compareTo(new BigDecimal("800"))<0){
				param.put("channelType", "system");
				param.put("users", "40");// 用户id,多个,隔开
				param.put("content", "余额即将不足了");
				param.put("tittle", "余额通知");// 邮件和系统消息才需要
				//messageClient.sendRealTimeMessageWithOutTemplate(param);// 一般消息发送
                amqpTemplate.convertAndSend("notify_to_client", param);
            }
		}
		
		return ReturnT.SUCCESS;
	}
}
