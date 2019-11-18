package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.domain.SettleOrder;

import java.util.List;

/**
 * 清算任务接口.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public interface ClearJobService {
    /**
     * 查询待簿记的指定条数.
     * <pre>
     *     会同时变更记录状态.
     * </pre>
     *
     * @param size 查询条数.
     * @return 待处理列表.
     */
    List<SettleOrder> findNeedBondSettles(int size);

    /**
     * 查询待资金处理的列表.
     *
     * @param size 指定处理条数.
     * @return 待处理列表.
     */
    List<SettleOrder> findNeedCashSettles(int size);

    /**
     * 变更结算指令状态.
     *
     * @param settleId     清算ID，
     * @param settleStatus 清算状态.
     * @return 更新数量.
     */
    int updateSettleStatus(String settleId, String settleStatus);

    /**
     * 变更簿记结算状态.
     *
     * @param settleId   清算ID，
     * @param bondStatus 簿记状态.
     * @return 更新数量.
     */
    int updateBondSettleStatus(String settleId, String bondStatus);

    /**
     * 变更资金结算状态.
     *
     * @param settleId   结算ID
     * @param cashStatus 资金状态.
     * @return 更新数量
     */
    int updateCashSettleStatus(String settleId, String cashStatus);
}
