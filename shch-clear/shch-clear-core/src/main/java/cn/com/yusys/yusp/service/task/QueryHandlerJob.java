package cn.com.yusys.yusp.service.task;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import cn.com.yusys.yusp.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 资金处理任务.
 *
 * @author wpplu
 * @since 2019/11/18
 */
@JobHandler("cashHandler")
public class QueryHandlerJob extends IJobHandler {
    @Autowired
    private CallService callServ;
    @Autowired
    private SettleOrderMapper mapper;
    /**
     * 针对簿记成功，进行资金处理.
     *
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        //TODO 查询需要处理的列表
        // TODO 调用接口

        QueryModel model = new QueryModel();
        model.setSize(100);
        mapper.selectPageSettleByParam(model).forEach(obj -> {
            obj.setCashSettleStatus("1");// 交割中

            mapper.updateByPrimaryKeySelective(obj); //TODO 独立事务
            // toSend
            callServ.callCashSettleApply();
        });

        return null;
    }
}
