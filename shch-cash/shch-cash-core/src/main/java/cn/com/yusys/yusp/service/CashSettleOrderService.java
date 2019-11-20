package cn.com.yusys.yusp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import cn.com.yusys.yusp.domain.CashSettleOrder;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import cn.com.yusys.yusp.repository.mapper.CashAccountBalanceMapper;
import cn.com.yusys.yusp.repository.mapper.CashSettleOrderMapper;

@Service
public class CashSettleOrderService {
	private Logger logger = LoggerFactory.getLogger(CashSettleOrderService.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private CashSettleOrderMapper cashSettleOrderMapper;

	@Autowired
	private CashAccountBalanceMapper cashAccountBalanceMapper;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private SettleNotifyClient settleNotifyClient;
	
	public int deleteByPrimaryKey(String serialNum) {
		return cashSettleOrderMapper.deleteByPrimaryKey(serialNum);
	}

	public int insert(CashSettleOrder record) {
		return cashSettleOrderMapper.insert(record);
	}

	public int insertSelective(CashSettleOrder record) {
		return cashSettleOrderMapper.insertSelective(record);
	}

	public CashSettleOrder selectByPrimaryKey(String serialNum) {
		return cashSettleOrderMapper.selectByPrimaryKey(serialNum);
	}

	public int updateByPrimaryKeySelective(CashSettleOrder record) {
		return cashSettleOrderMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(CashSettleOrder record) {
		return cashSettleOrderMapper.updateByPrimaryKey(record);
	}

	public int updateState(String serialNum) {
		return cashSettleOrderMapper.updateState(serialNum);
	}

	public int updateStateReturn(String serialNum) {
		return cashSettleOrderMapper.updateStateReturn(serialNum);
	}

	public List<CashSettleOrder> selectByModel(QueryModel model) {
		PageHelper.startPage(model.getPage(), model.getSize());
		List<CashSettleOrder> list = cashSettleOrderMapper.selectByModel(model);
		PageHelper.clearPage();
		return list;
	}

	public int deleteByIds(String serialNums) {
		return cashSettleOrderMapper.deleteByIds(serialNums);
	}

	@Transactional
	public void updateCashCount(CashDto record) {
		logger.debug("资金DVP结算请求指令开始:" + record);
		try {
			CashSettleOrder cashSettleOrder = new CashSettleOrder();
			BeanUtils.copyProperties(record, cashSettleOrder);

			cashSettleOrder.setCashAccount("ZHa");
			cashSettleOrder.setCashAccountName("账户a");
			cashSettleOrder.setCashProcStatus("0");
			cashSettleOrder.setCashSettleId(("A" + System.currentTimeMillis()).substring(0, 10));
			cashSettleOrder.setSerialNum("B" + System.currentTimeMillis());
			cashSettleOrderMapper.insert(cashSettleOrder);
			logger.debug("等级流水成功" + record);

			CashAccountBalance record2 = new CashAccountBalance();
			record2.setCurrencyAmt(record.getCashSettleAmt());
			record2.setMemCode(record.getDebitMemId());
			record2.setHolderAccount(record.getDebitHolderAccount());
			record2.setTitleCode(record.getCashDebitTitle());
			record2.setCashAccount("0009");

			kouKuan(cashSettleOrder.getSerialNum(), record2);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("资金DVP结算请求指令异常:" + record);
		}
	}

	/* @Async("taskExecutor") */
	public void asynUpdateCashCount(CashDto record) {
		updateCashCount(record);
	}

	/**
	 * 扣款
	 * 
	 * @param serialNum
	 * @param record2
	 * @throws Exception
	 */
	@Transactional
	public void kouKuan(String serialNum, CashAccountBalance record2) throws Exception {
		boolean result = true;
		if (0 == cashAccountBalanceMapper.updateMoney(record2)) {
			logger.error("余额不足:" + record2);
			CashSettleOrder record3 = new CashSettleOrder();
			record3.setSerialNum(serialNum);
			record3.setCashProcStatus("2");
			cashSettleOrderMapper.updateByPrimaryKeySelective(record3);
			result = false;
		} else {
			CashSettleOrder record3 = new CashSettleOrder();
			record3.setSerialNum(serialNum);
			record3.setCashProcStatus("1");
			cashSettleOrderMapper.updateByPrimaryKeySelective(record3);
			record2.setSerialNum(serialNum);
			record2.setMsgType("HVPS.111.001.01");
			// cashSettleOrderMapper.updateStateSuccess(serialNum);
			logger.info("发送Rabbit报文:" + objectMapper.writeValueAsString(record2));
			amqpTemplate.convertAndSend("pay", objectMapper.writeValueAsString(record2));
			logger.info("交易成功:" + serialNum);
			result = true;
		}
		
		if (result) {// 调用资金dep异步结算通知

		}
	}

	public void pay(String serialNum) {
		
		CashSettleOrder cashSettleOrder = new CashSettleOrder();
		
		cashSettleOrder.setSerialNum(serialNum);
		cashSettleOrder.setCashProcStatus("4");
		
		cashSettleOrderMapper.updateByPrimaryKeySelective(cashSettleOrder);

		/**
		 * 通知清算成功
		 */
		cashSettleOrder = selectByPrimaryKey(cashSettleOrder.getSerialNum());
		
		CashSettleNotifyReq cashSettleNotifyReq = new CashSettleNotifyReq();
		
		cashSettleNotifyReq.setCashSettleId(cashSettleOrder.getCashSettleId());
		cashSettleNotifyReq.setSettleOrderId(cashSettleOrder.getSettleOrderId());
		cashSettleNotifyReq.setTradeId(cashSettleOrder.getTradeId());
		cashSettleNotifyReq.setCashProcStatus("3");
		cashSettleNotifyReq.setRetMsg("款项到账");
		
		settleNotifyClient.cashRsp(cashSettleNotifyReq);
	}
}
