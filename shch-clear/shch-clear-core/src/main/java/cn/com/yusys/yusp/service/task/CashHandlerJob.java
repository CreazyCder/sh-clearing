package cn.com.yusys.yusp.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.constant.CashSettleStatusEnum;
import cn.com.yusys.yusp.service.CallService;
import cn.com.yusys.yusp.service.ClearJobService;

/**
 * 资金处理任务.
 *
 * @author wpplu
 * @since 2019/11/18
 */
@JobHandler("cashHandler")
@Service
public class CashHandlerJob extends IJobHandler {
    private Logger log = LoggerFactory.getLogger(CashHandlerJob.class);
    @Autowired
    private CallService callServ;
    @Autowired
    private ClearJobService jobService;

    /**
     * 针对簿记成功，进行资金处理.
     *
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        jobService.findNeedCashSettles(100).forEach(obj -> {
            // 修改为交割中.
            int number = 0;
            try {
                number = jobService.updateCashSettleStatus(obj.getSettleOrderId(), CashSettleStatusEnum.HANDLING.getCode());
            } catch (Throwable e) {
                log.warn("执行数据库操作异常:{}", number);
            }
            if (number != 0) {

                callServ.callCashSettleApply(obj);
            }
        });

        return new ReturnT<>();
    }
}
