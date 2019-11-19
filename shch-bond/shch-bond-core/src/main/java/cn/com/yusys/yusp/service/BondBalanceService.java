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
import cn.com.yusys.yusp.domain.BondBalance;
import cn.com.yusys.yusp.repository.mapper.BondBalanceMapper;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.*;
import cn.com.yusys.yusp.commons.fee.common.enums.*;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondBalanceService
 * @类描述: #服务类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:23:21
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@Service
@Transactional
@CataLog(nodeType=ActionNodeType.MAPPER, value = "service/BondBalanceService", lableType=LableType.MS)
public class BondBalanceService {

    @Autowired
    private BondBalanceMapper bondBalanceMapper;
	
    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public BondBalance selectByPrimaryKey(String memCode, String holderAccount, String bondCode, String titleCode) {
        return bondBalanceMapper.selectByPrimaryKey(memCode, holderAccount, bondCode, titleCode);
    }
	
	/**
     * @方法名称: selectAll
     * @方法描述: 查询所有数据
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Transactional(readOnly=true)
    public List<BondBalance> selectAll(QueryModel model) {
        List<BondBalance> records = (List<BondBalance>) bondBalanceMapper.selectByModel(model);
        return records;
    }

    /**
     * @方法名称: selectByModel
     * @方法描述: 条件查询 - 查询进行分页
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_BALANCEService分页查询", transaction = true)
    public List<BondBalance> selectByModel(@LogicParam(description = "BOND_BALANCE模型实体") QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<BondBalance> list = bondBalanceMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }	
	
    /**
     * @方法名称: insert
     * @方法描述: 插入
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insert(BondBalance record) {
        return bondBalanceMapper.insert(record);
    }

    /**
     * @方法名称: insertSelective
     * @方法描述: 插入 - 只插入非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insertSelective(BondBalance record) {
        return bondBalanceMapper.insertSelective(record);
    }

    /**
     * @方法名称: update
     * @方法描述: 根据主键更新 
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int update(BondBalance record) {
        return bondBalanceMapper.updateByPrimaryKey(record);
    }

    /**
     * @方法名称: updateSelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int updateSelective(BondBalance record) {
        return bondBalanceMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @方法名称: deleteByPrimaryKey
     * @方法描述: 根据主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int deleteByPrimaryKey(String memCode, String holderAccount, String bondCode, String titleCode) {
        return bondBalanceMapper.deleteByPrimaryKey(memCode, holderAccount, bondCode, titleCode);
    }

}
