package cn.com.yusys.yusp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import cn.com.yusys.yusp.service.SettleNotifyService;
/**
 * 提供给薄记调用服务
 * @author boip
 *
 */
@RestController
@RequestMapping("/api/notify/")
public class NotifyReqResource {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SettleNotifyService settleServ;
    /**
     * 异步应用接口
     * @return
     */
    @PostMapping("/bond/asynRsp")
    protected ResultDto bondRsp(@RequestBody BondSettleNotifyReq req) {
    	log.info("bondRsp rcv msg info : {}",req);
        return settleServ.bondRsp(req);
    }
    /**
     * 异步应用接口
     * @return
     */
    @PostMapping("/cash/asynRsp")
    protected ResultDto cashRsp(@RequestBody CashSettleNotifyReq req) {
    	log.info("cashRsp rcv msg info : {}",req);
        return settleServ.cashRsp(req);
    }
}
