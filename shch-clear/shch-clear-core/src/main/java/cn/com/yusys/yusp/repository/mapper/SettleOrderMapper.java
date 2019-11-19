/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.repository.mapper;

import cn.com.yusys.yusp.commons.fee.common.annotation.ide.CataLog;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.Logic;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.LogicParam;
import cn.com.yusys.yusp.commons.fee.common.enums.ActionNodeType;
import cn.com.yusys.yusp.commons.fee.common.enums.LableType;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.SettleOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0.0
 * @项目名称: shch-clear-core模块
 * @类名称: SettleOrderMapper
 * @类描述: #Dao类
 * @功能描述:
 * @创建人: boip
 * @创建时间: 2019-11-18 11:32:23
 * @修改备注:
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @Copyright (c) 宇信科技-版权所有
 */
@CataLog(nodeType = ActionNodeType.MAPPER, value = "mapper/SettleOrderMapper", lableType = LableType.DB)
public interface SettleOrderMapper {
    /**
     * 查询需要交割中的记录.·
     *
     * @param size 指定记录.前X条
     * @return 记录.
     */
    List<SettleOrder> selectDeliveringByStatus(@Param("size") int sizes);
    /**
     * 结算分页查询.
     *
     * @param model
     * @return
     */
    @Logic(description = "结算分页查询", transaction = true)
    List<SettleOrder> selectPageSettleByParam(QueryModel model);

    /**
     * 查询需要簿记的记录.
     *
     * @param size 指定记录.
     * @return 记录.
     */
    List<SettleOrder> selectSettlesByStatus(@Param("size") int size,
                                            @Param("bondStatus") String bondStatus);

    /**
     * 更新清算的状态.
     *
     * @param settleId     清算ID
     * @param settleStatus 清算指令状态
     * @param bondStatus   簿记状态
     * @param cashStatus   资金状态
     * @return 更新数目
     */
    int updateSettleStatus(@Param("settleId") String settleId,
                           @Param("settleStatus") String settleStatus,
                           @Param("bondStatus") String bondStatus,
                           @Param("cashStatus") String cashStatus,
                           @Param("updateTime") String updateTime);

    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER主键查看", transaction = true)
    SettleOrder selectByPrimaryKey(@Param("settleOrderId") @LogicParam(description = "SETTLE_ORDER主键ID") String settleOrderId);

    /**
     * @方法名称: selectByModel
     * @方法描述: 条件列表查询
     * @参数与返回说明:
     * @算法描述: 无
     */
    List<SettleOrder> selectByModel(QueryModel model);

    /**
     * @方法名称: insert
     * @方法描述: 插入
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER新增", transaction = true)
    int insert(@LogicParam(description = "SETTLE_ORDER模型实体") SettleOrder record);

    /**
     * @方法名称: insertSelective
     * @方法描述: 插入 - 只插入非空字段
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER字段可选新增", transaction = true)
    int insertSelective(@LogicParam(description = "SETTLE_ORDER模型实体") SettleOrder record);

    /**
     * @方法名称: updateByPrimaryKey
     * @方法描述: 根据主键更新
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER修改", transaction = true)
    int updateByPrimaryKey(@LogicParam(description = "SETTLE_ORDER模型实体") SettleOrder record);

    /**
     * @方法名称: updateByPrimaryKeySelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER主键更新模型非空字段", transaction = true)
    int updateByPrimaryKeySelective(@LogicParam(description = "SETTLE_ORDER模型实体") SettleOrder record);

    /**
     * @方法名称: deleteByPrimaryKey
     * @方法描述: 根据主键删除
     * @参数与返回说明: sysId - 主键
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER根据主键删除", transaction = true)
    int deleteByPrimaryKey(@Param("settleOrderId") @LogicParam(description = "SETTLE_ORDER主键ID") String settleOrderId);

    /**
     * @方法名称: deleteByIds
     * @方法描述: 根据多个主键删除
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDER根据主键批量删除", transaction = true)
    int deleteByIds(@Param("ids") @LogicParam(description = "SETTLE_ORDER主键ID") String ids);
}