package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.commons.exception.YuspException;
import cn.com.yusys.yusp.commons.util.DateUtil;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.constant.BondSettleStatusEnum;
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
        SettleOrder order = new SettleOrder();
        order.setSettleOrderId(req.getSettleOrderId());
        order.setBondSettleId(req.getBondSettleId());
        order.setBondSettleStatus(req.getBondProcStatus());
        order.setSettleOrderStatus(
                BondSettleStatusEnum.SUCCESS.getCode().equals(req.getBondProcStatus()) ?
                        BondSettleStatusEnum.SUCCESS.getCode() : "");
        order.setBondSettleStatusUpdateTm(DateUtil.formatDate(DateUtil.PATTERN_DATETIME_COMPACT));
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
        SettleOrder order = new SettleOrder();
        order.setSettleOrderId(req.getSettleOrderId());
        order.setCashSettleId(req.getCashSettleId());
        order.setCashSettleStatus(req.getCashProcStatus());
        order.setCashSettleStatusUpdateTm(DateUtil.formatDate(DateUtil.PATTERN_DATETIME_COMPACT));
        //TODO 清算
        int ret = mapper.updateByPrimaryKeySelective(order);

        if (ret == 1) {
            return new ResultDto("成功");
        } else {
            throw new YuspException("1", "未找到记录");
        }
    }
}
