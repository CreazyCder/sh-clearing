package cn.com.yusys.yusp.service.task;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.constant.BondSettleStatusEnum;
import cn.com.yusys.yusp.service.CallService;
import cn.com.yusys.yusp.service.ClearJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 簿记记账任务.
 *
 * @author wpplu
 * @since 2019/11/18
 */
@JobHandler("registerBondAccount")
@Service
public class AccountBondJob extends IJobHandler {
    private Logger log = LoggerFactory.getLogger(AccountBondJob.class);
    @Autowired
    private CallService callServ;

    @Autowired
    private ClearJobService clearJobService;

    @Value("${application.biz.job.batch.size:100}")
    private int batchSize = 100;

    /**
     * 执行查询需要簿记记账处理的清算指定列表.
     *
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("开始执行需要簿记记账的任务");
        clearJobService.findNeedBondAccountSettles(batchSize).forEach(obj -> {
            int result = 0;
            try {
                // 交割中
                result = clearJobService.updateBondSettleStatus(obj.getSettleOrderId(),
                        BondSettleStatusEnum.HANDLING.getCode(),"1"); //TODO 独立事务
            } catch (Throwable e) {
                log.warn("执行变更状态失败:{}", e);
            }
            if (result != 0) {
                // 调用簿记接口.
                callServ.callBondSettleApply(obj);
            }
        });
        return new ReturnT<>();
    }
}
