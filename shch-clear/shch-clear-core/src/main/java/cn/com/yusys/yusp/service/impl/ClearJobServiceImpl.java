package cn.com.yusys.yusp.service.impl;

import cn.com.yusys.yusp.commons.util.DateUtil;
import cn.com.yusys.yusp.constant.BondSettleStatusEnum;
import cn.com.yusys.yusp.constant.CashSettleStatusEnum;
import cn.com.yusys.yusp.constant.SettleStatusEnum;
import cn.com.yusys.yusp.domain.SettleOrder;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import cn.com.yusys.yusp.service.ClearJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 从数据库获取清算任务.
 *
 * @author wpplu
 * @since 2019/11/18
 */
@Service
public class ClearJobServiceImpl implements ClearJobService {
    @Autowired
    private SettleOrderMapper settleOrderMapper;

    /**
     * 查询待簿记圈存的指定条数.
     * <pre>
     *     会同时变更记录状态.
     * </pre>
     *
     * @param size 查询条数.
     * @return 待处理列表.
     */
    @Override
    public List<SettleOrder> findNeedBondSettles(int size) {
        //return settleOrderMapper.selectSettlesByStatus(size, BondSettleStatusEnum.SHOULD_PERFORM.getCode());
        return settleOrderMapper.selectTaskByStatus(size, SettleStatusEnum.SHOULD_PERFORM.getCode(), 
        							BondSettleStatusEnum.SHOULD_PERFORM.getCode(), 
        							CashSettleStatusEnum.SHOULD_PERFORM.getCode());
    }
    /**
     * 查询待簿记记账的指定条数.
     * <pre>
     *     会同时变更记录状态.
     * </pre>
     *
     * @param size 查询条数.
     * @return 待处理列表.
     */
    @Override
    public List<SettleOrder> findNeedBondAccountSettles(int size) {
    	
        return settleOrderMapper.selectTaskByStatus(size, SettleStatusEnum.HANDLING.getCode(), 
				BondSettleStatusEnum.BOND_OK.getCode(), 
				CashSettleStatusEnum.BOND_OK.getCode());
        
    }
    /**
     * 查询待资金处理的列表.
     *
     * @param size 指定处理条数.
     * @return 待处理列表.
     */
    @Override
    public List<SettleOrder> findNeedCashSettles(int size) {
        return settleOrderMapper.selectTaskByStatus(size, SettleStatusEnum.HANDLING.getCode(), 
				BondSettleStatusEnum.BOND_OK.getCode(), 
				CashSettleStatusEnum.SHOULD_PERFORM.getCode());
    }

    /**
     * 变更结算指令状态.
     *
     * @param settleId     清算ID，
     * @param settleStatus 清算状态.
     * @return 更新数量.
     */
    @Override
    public int updateSettleStatus(String settleId, String settleStatus) {
        return this.updateSettleStatus(settleId, settleStatus, null, null);
    }

    /**
     * 默认添加时间.
     *
     * @param settled      清算ID
     * @param settleStatus 清算指令状态
     * @param bondStatus   簿记状态
     * @param cashStatus   资金结算状态.
     * @return 更新数目.
     */
    private int updateSettleStatus(String settled, String settleStatus, String bondStatus, String cashStatus) {
        return settleOrderMapper.updateSettleStatus(settled, settleStatus, bondStatus, cashStatus,
                DateUtil.formatDate(DateUtil.PATTERN_DATETIME_COMPACT));
    }

    /**
     * 变更簿记结算状态.
     *
     * @param settleId   清算ID，
     * @param bondStatus 簿记状态.
     * @return 更新数量.
     */
    @Override
    public int updateBondSettleStatus(String settleId, String bondStatus,String operType) {
    	String stt = SettleStatusEnum.HANDLING.getCode();
    	if("1".equals(operType)) {
    		stt = SettleStatusEnum.TRANSFERING.getCode();
    	}
        return this.updateSettleStatus(settleId, stt, bondStatus, null);
    }

    /**
     * 变更资金结算状态.
     *
     * @param settleId   结算ID
     * @param cashStatus 资金状态.
     * @return 更新数量
     */
    @Override
    public int updateCashSettleStatus(String settleId, String cashStatus) {
        return this.updateSettleStatus(settleId, null, null, cashStatus);
    }
}
