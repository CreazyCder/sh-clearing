package cn.com.yusys.yusp.web.rest;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import cn.com.yusys.yusp.service.SettleNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 提供给薄记调用服务
 * @author boip
 *
 */
@RequestMapping("/api/notify/")
public class NotifyReqResource {
    @Autowired
    private SettleNotifyService settleServ;
    /**
     * 异步应用接口
     * @return
     */
    @GetMapping("/bond/asynRsp")
    protected ResultDto bondRsp(BondSettleNotifyReq req) {
        return settleServ.bondRsp(req);
    }
    /**
     * 异步应用接口
     * @return
     */
    @GetMapping("/cash/asynRsp")
    protected ResultDto cashRsp(CashSettleNotifyReq req) {
        return settleServ.cashRsp(req);
    }
}
