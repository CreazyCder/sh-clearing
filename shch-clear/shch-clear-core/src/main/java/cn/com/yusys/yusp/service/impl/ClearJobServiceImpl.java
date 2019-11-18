package cn.com.yusys.yusp.service.impl;

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
     * 查询待簿记的指定条数.
     * <pre>
     *     会同时变更记录状态.
     * </pre>
     *
     * @param size 查询条数.
     * @return 待处理列表.
     */
    @Override
    public List<SettleOrder> findNeedBondSettles(int size) {
        return settleOrderMapper.selectNeedBondSettles(size);
    }

    /**
     * 查询待资金处理的列表.
     *
     * @param size 指定处理条数.
     * @return 待处理列表.
     */
    @Override
    public List<SettleOrder> findNeedCashSettles(int size) {
        return null;
    }
}
