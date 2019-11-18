package cn.com.yusys.yusp.service.task;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import cn.com.yusys.yusp.service.CallService;
import cn.com.yusys.yusp.service.ClearJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 簿记注册任务.
 *
 * @author wpplu
 * @since 2019/11/18
 */
@JobHandler("registerBond")
public class RegisterBondJob extends IJobHandler {
    private Logger log = LoggerFactory.getLogger(RegisterBondJob.class);
    @Autowired
    private CallService callServ;
    @Autowired
    private SettleOrderMapper mapper;

    @Autowired
    private ClearJobService clearJobService;

    @Value("${application.biz.job.batch.size:100}")
    private int batchSize = 100;

    /**
     * 执行查询需要簿记处理的清算指定列表.
     *
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String s) throws Exception {


        //TODO 查询满足条件的列表,可配置条数
        // TODO 调用簿记接口，同时传递回调接口和服务实例ID
        clearJobService.findNeedBondSettles(100).forEach(obj -> {
            obj.setBondSettleStatus("1");// 交割中

            try {
                int result = mapper.updateByPrimaryKeySelective(obj); //TODO 独立事务
                if (result != 0) {
                    // 需要处理
                    //TODO 调用簿记接口.
                    callServ.callCashSettleApply();
                }
            } catch (Throwable e) {
                log.warn("执行变更状态失败:{}", e);
            }
        });
        return null;
    }
}
