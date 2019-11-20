package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.msg.revoke.DVPRevokeReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 异步结算通知
 */
@FeignClient(name = "${application.service.shch-clear:shch-clear}", fallback = SettleNotifyFallback.class)
public interface SettleNotifyClient {
    /**
     * 薄记异步结算通知
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/notify/bond/asynRsp")
    ResultDto bondRsp(@RequestBody BondSettleNotifyReq req);

    /**
     * 资金异步结算通知
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/notify/cash/asynRsp")
    ResultDto cashRsp(@RequestBody CashSettleNotifyReq req);
    
    /**
     * 交易撤消
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/settleorder/revoke")
    ResultDto<Integer> revoke(@RequestBody DVPRevokeReq req) ;
}
