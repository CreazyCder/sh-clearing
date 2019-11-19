package cn.com.yusys.yusp.service;

import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;

@Component
public class SettleNotifyFallback implements SettleNotifyClient {

    @Override
    public ResultDto<String> bondRsp(BondSettleNotifyReq req) {
        return new ResultDto<String>(1,"fail","error");
    }

    @Override
    public ResultDto<String> cashRsp(CashSettleNotifyReq req) {
    	return new ResultDto<String>(1,"fail","error");
    }
}
