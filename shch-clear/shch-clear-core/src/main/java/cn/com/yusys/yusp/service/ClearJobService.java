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
}
