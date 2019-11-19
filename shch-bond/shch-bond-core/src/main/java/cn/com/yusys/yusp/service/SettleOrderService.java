/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.SettleOrder;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.*;
import cn.com.yusys.yusp.commons.fee.common.enums.*;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: SettleOrderService
 * @类描述: #服务类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:54
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@Service
@Transactional
@CataLog(nodeType=ActionNodeType.MAPPER, value = "service/SettleOrderService", lableType=LableType.MS)
public class SettleOrderService {

    @Autowired
    private SettleOrderMapper settleOrderMapper;
	
    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public SettleOrder selectByPrimaryKey(String settleOrderId) {
        return settleOrderMapper.selectByPrimaryKey(settleOrderId);
    }
	
	/**
     * @方法名称: selectAll
     * @方法描述: 查询所有数据
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Transactional(readOnly=true)
    public List<SettleOrder> selectAll(QueryModel model) {
        List<SettleOrder> records = (List<SettleOrder>) settleOrderMapper.selectByModel(model);
        return records;
    }

    /**
     * @方法名称: selectByModel
     * @方法描述: 条件查询 - 查询进行分页
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "SETTLE_ORDERService分页查询", transaction = true)
    public List<SettleOrder> selectByModel(@LogicParam(description = "SETTLE_ORDER模型实体") QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<SettleOrder> list = settleOrderMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }	
	
    /**
     * @方法名称: insert
     * @方法描述: 插入
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insert(SettleOrder record) {
        return settleOrderMapper.insert(record);
    }

    /**
     * @方法名称: insertSelective
     * @方法描述: 插入 - 只插入非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insertSelective(SettleOrder record) {
        return settleOrderMapper.insertSelective(record);
    }

    /**
     * @方法名称: update
     * @方法描述: 根据主键更新 
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int update(SettleOrder record) {
        return settleOrderMapper.updateByPrimaryKey(record);
    }

    /**
     * @方法名称: updateSelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int updateSelective(SettleOrder record) {
        return settleOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @方法名称: deleteByPrimaryKey
     * @方法描述: 根据主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int deleteByPrimaryKey(String settleOrderId) {
        return settleOrderMapper.deleteByPrimaryKey(settleOrderId);
    }

    /**
     * @方法名称: deleteByIds
     * @方法描述: 根据多个主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int deleteByIds(String ids) {
        return settleOrderMapper.deleteByIds(ids);
    }
}
