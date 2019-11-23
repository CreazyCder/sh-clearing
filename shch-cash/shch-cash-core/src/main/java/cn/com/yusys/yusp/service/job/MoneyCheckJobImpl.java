package cn.com.yusys.yusp.service.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import cn.com.yusys.yusp.domain.CashSettleOrder;
import cn.com.yusys.yusp.service.CashAccountBalanceService;
import cn.com.yusys.yusp.service.CashSettleOrderService;
import cn.com.yusys.yusp.web.rest.CashResource;

//@JobHandler(value = "MoneyCheckJobImpl")
@Service
public class MoneyCheckJobImpl extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(MoneyCheckJobImpl.class);

    @Autowired
    CashSettleOrderService cashSettleOrderService;
    @Autowired
    CashAccountBalanceService cashAccountBalanceService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        logger.info("定时任务执行：MoneyCheckJobImpl");
        QueryModel model = new QueryModel();
        model.setSize(100);
        model.setPage(1);
        model.addCondition("cashProcStatus", "2");
        List<CashSettleOrder> data = cashSettleOrderService.selectByModel(model);
        for (CashSettleOrder dataT : data) {
            if (1 == cashSettleOrderService.updateState(dataT.getSerialNum())) {
                CashAccountBalance record = new CashAccountBalance();
                record.setCashAccount(dataT.getCashAccount());
                record.setCurrencyAmt(dataT.getCashSettleAmt());
                record.setMemCode(dataT.getDebitMemId());
                record.setTitleCode(dataT.getCashDebitTitle());
                record.setHolderAccount(dataT.getDebitHolderAccount());
                if (1 == cashAccountBalanceService.selectAccountState(record)) {
                    cashSettleOrderService.kouKuan(dataT.getSerialNum(), record);
                    // 通知清算跟新成功
                } else {
                    cashSettleOrderService.updateStateReturn(dataT.getSerialNum());
                }
            }
        }
        return ReturnT.SUCCESS;
    }
}
