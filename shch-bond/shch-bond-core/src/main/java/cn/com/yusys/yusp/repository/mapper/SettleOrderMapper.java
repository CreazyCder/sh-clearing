/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.repository.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import cn.com.yusys.yusp.domain.SettleOrder;
import  cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.*;
import cn.com.yusys.yusp.commons.fee.common.enums.*;
/**
 * @项目名称: shch-bond-core模块
 * @类名称: SettleOrderMapper
 * @类描述: #Dao类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:54
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@CataLog(nodeType=ActionNodeType.MAPPER, value = "mapper/SettleOrderMapper", lableType=LableType.DB)
public interface SettleOrderMapper {

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