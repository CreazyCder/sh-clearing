package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SettleNotifyFallback implements SettleNotifyClient {
    private final Logger logger = LoggerFactory.getLogger(SettleNotifyFallback.class);

    @Override
    public ResultDto bondRsp(BondSettleNotifyReq req) {
        return null;
    }

    @Override
    public ResultDto cashRsp(CashSettleNotifyReq req) {
        return null;
    }
}
