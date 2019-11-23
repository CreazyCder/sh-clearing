package cn.com.yusys.yusp.service;

import java.util.List;

import cn.com.yusys.yusp.config.EnvClusterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.exception.YuspException;
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
    EnvClusterConfig envClusterConfig;

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

            QueryModel model = new QueryModel();
            model.setSize(0);
            model.addCondition("memCode", record.getDebitMemId());
            model.addCondition("holderAccount", record.getDebitHolderAccount());
            model.addCondition("titleCode", record.getCashDebitTitle());
            List<CashAccountBalance> rp = cashAccountBalanceMapper.selectByModel(model);
            if (rp.size() <= 0) {
                logger.error("账户记录不存在:" + model.toString());
                return;
            }
            CashAccountBalance re2 = rp.get(0);

            CashSettleOrder cashSettleOrder = new CashSettleOrder();
            BeanUtils.copyProperties(record, cashSettleOrder);
            cashSettleOrder.setCashAccount(re2.getCashAccount());
            cashSettleOrder.setCashAccountName(re2.getCashAccountName());
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
            record2.setTradeId(record.getTradeId());

            record2.setCashAccount(re2.getCashAccount());

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

            CashSettleOrder order = selectByPrimaryKey(serialNum);
            CashSettleNotifyReq cashSettleNotifyReq = new CashSettleNotifyReq();
            cashSettleNotifyReq.setCashSettleId(order.getCashSettleId());
            cashSettleNotifyReq.setSettleOrderId(order.getSettleOrderId());
            cashSettleNotifyReq.setTradeId(order.getTradeId());
            cashSettleNotifyReq.setCashProcStatus("2");
            cashSettleNotifyReq.setRetMsg("等款");

            settleNotifyClient.cashRsp(cashSettleNotifyReq);
            result = false;
        } else {
            CashSettleOrder record3 = new CashSettleOrder();
            record3.setSerialNum(serialNum);
            record3.setCashProcStatus("1");
            cashSettleOrderMapper.updateByPrimaryKeySelective(record3);
            record2.setSerialNum(serialNum);
            record2.setMsgType("HVPS.111.001.01");
            record2.setEnv(envClusterConfig.getEnv());

            // cashSettleOrderMapper.updateStateSuccess(serialNum);
            String data = objectMapper.writeValueAsString(record2);
            logger.info("发送往账报文到MQ:" + data);

            //amqpTemplate.convertAndSend("to_hvps", data);

            byte[] binData = data.getBytes("UTF-8");
            MessageProperties messageProperties = new MessageProperties();
            Message message = new Message(binData, messageProperties);
            amqpTemplate.send("to_hvps", message);

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

    public void enoughMoney(EnoughMoneyDto enoughMoneyDto) throws Exception {
        try {
            logger.info("发送到款数据到Rabbit:" + objectMapper.writeValueAsString(enoughMoneyDto));
            amqpTemplate.convertAndSend("payback", objectMapper.writeValueAsString(enoughMoneyDto));
        } catch (Exception e) {
            throw new YuspException("1", "发送rabbitmq失败" + e.getMessage());
        }
    }
}
