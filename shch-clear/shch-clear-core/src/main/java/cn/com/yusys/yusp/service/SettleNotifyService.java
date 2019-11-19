package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.commons.exception.YuspException;
import cn.com.yusys.yusp.commons.util.DateUtil;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.SettleOrder;
import cn.com.yusys.yusp.domain.msg.settlenotify.BondSettleNotifyReq;
import cn.com.yusys.yusp.domain.msg.settlenotify.CashSettleNotifyReq;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 结算通知服务
 *
 * @author boip
 */
@Service
public class SettleNotifyService {
    @Autowired
    private SettleOrderMapper mapper;

    /**
     * 薄记应答处理
     *
     * @param req
     * @return
     */
    public ResultDto bondRsp(BondSettleNotifyReq req) {
    	
        SettleOrder order = SettleOrder.builder()
                .settleOrderId(req.getSettleOrderId())
                .bondSettleId(req.getBondSettleId())
                .bondSettleStatus(req.getBondProcStatus())
                .settleOrderStatus("S".equals(req.getBondProcStatus())?"S":"")
                .bondSettleStatusUpdateTm(DateUtil.getCurrDateTimeStr())
                .build();

        int ret = mapper.updateByPrimaryKeySelective(order);
        if (ret == 1) {
            return new ResultDto("成功");
        } else {
            throw new YuspException("1", "未找到记录");
        }
    }

    /**
     * 资金应答处理
     *
     * @param req
     * @return
     */
    public ResultDto cashRsp(CashSettleNotifyReq req) {
        SettleOrder order = SettleOrder.builder()
                .settleOrderId(req.getSettleOrderId())
                .cashSettleId(req.getCashSettleId())
                .cashSettleStatus(req.getCashProcStatus())
                .cashSettleStatusUpdateTm(DateUtil.getCurrDateTimeStr())
                .build();

        //TODO 清算
        int ret = mapper.updateByPrimaryKeySelective(order);

        if (ret == 1) {
            return new ResultDto("成功");
        } else {
            throw new YuspException("1", "未找到记录");
        }
    }
}
