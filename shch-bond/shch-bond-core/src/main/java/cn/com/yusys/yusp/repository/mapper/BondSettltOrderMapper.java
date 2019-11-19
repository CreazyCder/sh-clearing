/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.repository.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import cn.com.yusys.yusp.domain.BondSettltOrder;
import  cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.*;
import cn.com.yusys.yusp.commons.fee.common.enums.*;
/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondSettltOrderMapper
 * @类描述: #Dao类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:02
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@CataLog(nodeType=ActionNodeType.MAPPER, value = "mapper/BondSettltOrderMapper", lableType=LableType.DB)
public interface BondSettltOrderMapper {

    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER主键查看", transaction = true)
    BondSettltOrder selectByPrimaryKey(@Param("serialNum") @LogicParam(description = "BOND_SETTLT_ORDER主键ID") String serialNum);

    /**
     * @方法名称: selectByModel
     * @方法描述: 条件列表查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    List<BondSettltOrder> selectByModel(QueryModel model);
	
    /**
     * @方法名称: insert
     * @方法描述: 插入
     * @参数与返回说明:
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER新增", transaction = true)
    int insert(@LogicParam(description = "BOND_SETTLT_ORDER模型实体") BondSettltOrder record);

    /**
     * @方法名称: insertSelective
     * @方法描述: 插入 - 只插入非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER字段可选新增", transaction = true)
    int insertSelective(@LogicParam(description = "BOND_SETTLT_ORDER模型实体") BondSettltOrder record);

    /**
     * @方法名称: updateByPrimaryKey
     * @方法描述: 根据主键更新 
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER修改", transaction = true)
    int updateByPrimaryKey(@LogicParam(description = "BOND_SETTLT_ORDER模型实体") BondSettltOrder record);

    /**
     * @方法名称: updateByPrimaryKeySelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER主键更新模型非空字段", transaction = true)
    int updateByPrimaryKeySelective(@LogicParam(description = "BOND_SETTLT_ORDER模型实体") BondSettltOrder record);

    /**
     * @方法名称: deleteByPrimaryKey
     * @方法描述: 根据主键删除
     * @参数与返回说明: sysId - 主键
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER根据主键删除", transaction = true)
    int deleteByPrimaryKey(@Param("serialNum") @LogicParam(description = "BOND_SETTLT_ORDER主键ID") String serialNum);

    /**
     * @方法名称: deleteByIds
     * @方法描述: 根据多个主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDER根据主键批量删除", transaction = true)
    int deleteByIds(@Param("ids") @LogicParam(description = "BOND_SETTLT_ORDER主键ID") String ids);
   
}