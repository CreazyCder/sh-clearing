/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.fee.common.annotation.ide.CataLog;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.Logic;
import cn.com.yusys.yusp.commons.fee.common.annotation.ide.LogicParam;
import cn.com.yusys.yusp.commons.fee.common.enums.ActionNodeType;
import cn.com.yusys.yusp.commons.fee.common.enums.LableType;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.BondBalance;
import cn.com.yusys.yusp.domain.BondSettltOrder;
import cn.com.yusys.yusp.repository.mapper.BondBalanceMapper;
import cn.com.yusys.yusp.repository.mapper.BondSettltOrderMapper;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondSettltOrderService
 * @类描述: #服务类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:02
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@Service
@Transactional
@CataLog(nodeType=ActionNodeType.MAPPER, value = "service/BondSettltOrderService", lableType=LableType.MS)
public class BondSettltOrderService {

    @Autowired
    private BondSettltOrderMapper bondSettltOrderMapper;
    @Autowired
    private BondBalanceMapper bondBalanceMapper;//簿记债券余额
    
    /**
     * @方法名称: procCouponBond
     * @方法描述: 债券DVP结算圈存指令异步处理
     * @参数与返回说明: 簿记传输对象,异步处理结果反馈
     * @算法描述: 无
     */
    @Async("taskExecutor")
	@Logic(description="债券DVP结算圈存指令异步处理逻辑",transaction=true)
	public void procCouponBond(BondDto bondDto){
    	//借方：可用-->借方待付
		//数据字典：券足为3、等券为2、失败为F
		String bondProcStatus = "";
		//1.根据参数中借方参与者、借方持有人、债券代码查询债券余额表记录（借方:可用科目）
		BondBalance debitBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getDebitMemId(), bondDto.getDebitHolderAccount(), bondDto.getBondCode(), bondDto.getBondDebitTitle());
		if(debitBalance != null) {
			BigDecimal tempAmt = bondDto.getBondFaceAmt();
			if(debitBalance.getCurrencyAmt().subtract(tempAmt).compareTo(BigDecimal.ZERO) < 0) {
				//券不足，返回等券
				bondProcStatus = "2";
			}else {
				//券足
				//1.1先更新当前余额表信息：可用余额=当前余额-债券面额
				//可用科目减少
				debitBalance.setCurrencyAmt(debitBalance.getCurrencyAmt().subtract(tempAmt));//科目余额 =当前余额 -债券面额
				bondBalanceMapper.updateByPrimaryKeySelective(debitBalance);
				
				//1.2再更新当前余额表信息：待付余额=当前余额+债券面额	
				BondBalance creditBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getDebitMemId(), bondDto.getDebitHolderAccount(), bondDto.getBondCode(), bondDto.getBondCreditTitle());
				if(creditBalance == null) {
					//待付科目当前无记录-->插入
				}else {
					//待付科目记录存在，更新待付科目余额
					creditBalance.setCurrencyAmt(creditBalance.getCurrencyAmt().add(tempAmt));//科目余额 =当前余额+债券面额
					bondBalanceMapper.updateByPrimaryKeySelective(creditBalance);
				}
				
				bondProcStatus = "3";//券足
			}
		}else {
			//这里需要做报错处理
			bondProcStatus = "F";//失败(借方该债券余额信息不存在，流程失败)
		}
		
		//3插入簿记流水表
		bondSettltOrderMapper.insert(recordMatch(bondDto,bondProcStatus));
		
		//4.拼接异步反馈对象，返回异步调用处（给清算系统异步应答）
		//这里要调用clear清算的api,需要另起事务，增加method		
	}
    
    
    
    /**
     * @方法名称: procRecordBond
     * @方法描述: 债券DVP结算记账指令异步处理
     * @参数与返回说明: 簿记传输对象,异步处理结果反馈
     * @算法描述: 无
     */
    @Async("taskExecutor")
	@Logic(description="债券DVP结算记账指令异步处理逻辑",transaction=true)
  	public void procRecordBond(BondDto bondDto) {
    	//借方：待付-->贷方：可用
    	String bondProcStatus = "";
		//1.根据参数中借方参与者、借方持有人、债券代码查询债券余额表记录（借方：待付科目）
		BondBalance debitBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getDebitMemId(), bondDto.getDebitHolderAccount(), bondDto.getBondCode(), bondDto.getBondDebitTitle());
		if(debitBalance != null) {
			BigDecimal tempAmt = bondDto.getBondFaceAmt();
			if(debitBalance.getCurrencyAmt().subtract(tempAmt).compareTo(BigDecimal.ZERO) < 0) {
				//记账时，待付科目余额一定有值，但需判断清算传递的金额不能大于待付科目的余额
				//这里报错，errMsg为清算传递的债券金额大于当前科目余额;
				//正常情况下，记账阶段，待付科目余额一定有，且≥传递的债券面额
			}else {
				//1.1先更新当前余额表信息：待付余额=当前余额-债券面额
				//待付科目减少
				debitBalance.setCurrencyAmt(debitBalance.getCurrencyAmt().subtract(tempAmt));//科目余额 =当前余额 -债券面额
				bondBalanceMapper.updateByPrimaryKeySelective(debitBalance);
				
				//1.2再更新当前余额表信息：贷方 可以余额=当前余额+债券面额	
				BondBalance creditBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getCreditMemId(), bondDto.getCreditHolderAccount(), bondDto.getBondCode(), bondDto.getBondCreditTitle());
				if(creditBalance == null) {
					//贷方可用科目当前无记录-->插入
				}else {
					//贷方可用科目记录存在，更新可用科目余额
					creditBalance.setCurrencyAmt(creditBalance.getCurrencyAmt().add(tempAmt));//科目余额 =当前余额+债券面额
					bondBalanceMapper.updateByPrimaryKeySelective(creditBalance);
				}
				bondProcStatus = "S";//成功
			}
		}else {
			//这里需要做报错处理
			bondProcStatus = "F";//失败(借方该债券待付科目余额信息不存在，流程失败)
		}
		
		//3插入簿记流水表
		bondSettltOrderMapper.insert(recordMatch(bondDto,bondProcStatus));
		
		//4.拼接异步反馈对象，返回异步调用处（给清算系统异步应答）
		//这里要调用clear清算的api,需要另起事务，增加method
  		
  	}
    /**
     * @方法名称: procCouponBond
     * @方法描述: 债券DVP结算撤销指令异步处理
     * @参数与返回说明: 簿记传输对象,异步处理结果反馈
     * @算法描述: 无
     */
    @Async("taskExecutor")
	@Logic(description="债券DVP结算撤销指令异步处理逻辑",transaction=true)
  	public void procCancleBond(BondDto bondDto) {
    	//借方：待付-->借方：可用
    	//数据字典：券足为3、等券为2、失败为F
		String bondProcStatus = "";
		//1.根据参数中借方参与者、借方持有人、债券代码查询债券余额表记录（借方:待付科目）
		BondBalance debitBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getDebitMemId(), bondDto.getDebitHolderAccount(), bondDto.getBondCode(), bondDto.getBondDebitTitle());
		if(debitBalance != null) {
			BigDecimal tempAmt = bondDto.getBondFaceAmt();
			if(debitBalance.getCurrencyAmt().subtract(tempAmt).compareTo(BigDecimal.ZERO) < 0) {
				//记账时，待付科目余额一定有值，但需判断清算传递的金额不能大于待付科目的余额
				//这里报错，errMsg为清算传递的债券金额大于当前科目余额;
				//正常情况下，记账阶段，待付科目余额一定有，且≥传递的债券面额
			}else {
				//1.1先更新当前余额表信息：待付余额=当前余额-债券面额
				//待付科目减少
				debitBalance.setCurrencyAmt(debitBalance.getCurrencyAmt().subtract(tempAmt));//科目余额 =当前余额 -债券面额
				bondBalanceMapper.updateByPrimaryKeySelective(debitBalance);
				
				//1.2再更新当前余额表信息：借方可用余额=当前余额+债券面额	
				BondBalance creditBalance = bondBalanceMapper.selectByPrimaryKey(bondDto.getDebitMemId(), bondDto.getDebitHolderAccount(), bondDto.getBondCode(), bondDto.getBondCreditTitle());
				if(creditBalance == null) {
					//可用科目一定是有记录的
				}else {
					//待付科目记录存在，更新待付科目余额
					creditBalance.setCurrencyAmt(creditBalance.getCurrencyAmt().add(tempAmt));//科目余额 =当前余额+债券面额
					bondBalanceMapper.updateByPrimaryKeySelective(creditBalance);
				}
				
				bondProcStatus = "C";//已撤销
			}
		}else {
			//这里需要做报错处理
			bondProcStatus = "F";//失败(借方该债券余额信息不存在，流程失败)
		}
		
		//3插入簿记流水表
		bondSettltOrderMapper.insert(recordMatch(bondDto,bondProcStatus));
  		
  	}
	
    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public BondSettltOrder selectByPrimaryKey(String serialNum) {
        return bondSettltOrderMapper.selectByPrimaryKey(serialNum);
    }
	
	/**
     * @方法名称: selectAll
     * @方法描述: 查询所有数据
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Transactional(readOnly=true)
    public List<BondSettltOrder> selectAll(QueryModel model) {
        List<BondSettltOrder> records = (List<BondSettltOrder>) bondSettltOrderMapper.selectByModel(model);
        return records;
    }

    /**
     * @方法名称: selectByModel
     * @方法描述: 条件查询 - 查询进行分页
     * @参数与返回说明: 
     * @算法描述: 无
     */
    @Logic(description = "BOND_SETTLT_ORDERService分页查询", transaction = true)
    public List<BondSettltOrder> selectByModel(@LogicParam(description = "BOND_SETTLT_ORDER模型实体") QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<BondSettltOrder> list = bondSettltOrderMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }	
	
    /**
     * @方法名称: insert
     * @方法描述: 插入
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insert(BondSettltOrder record) {
        return bondSettltOrderMapper.insert(record);
    }

    /**
     * @方法名称: insertSelective
     * @方法描述: 插入 - 只插入非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int insertSelective(BondSettltOrder record) {
        return bondSettltOrderMapper.insertSelective(record);
    }

    /**
     * @方法名称: update
     * @方法描述: 根据主键更新 
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int update(BondSettltOrder record) {
        return bondSettltOrderMapper.updateByPrimaryKey(record);
    }

    /**
     * @方法名称: updateSelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int updateSelective(BondSettltOrder record) {
        return bondSettltOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @方法名称: deleteByPrimaryKey
     * @方法描述: 根据主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int deleteByPrimaryKey(String serialNum) {
        return bondSettltOrderMapper.deleteByPrimaryKey(serialNum);
    }

    /**
     * @方法名称: deleteByIds
     * @方法描述: 根据多个主键删除
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public int deleteByIds(String ids) {
        return bondSettltOrderMapper.deleteByIds(ids);
    }
    
    private BondSettltOrder recordMatch(BondDto bondDto,String bondProcStatus) {
    	BondSettltOrder record = new BondSettltOrder();
		//record.setSerialNum(serialNum);//流水号 uuid生成，无需代码传入
		record.setTradeId(bondDto.getTradeId());//交易编号
		//record.setBondSettleId(bondSettleId);//债券结算编号 uuid生成，无需代码出入
		record.setBondCode(bondDto.getBondCode());//债券代码
		record.setBondName(bondDto.getBondName());//债券简称
		record.setDebitMemId(bondDto.getDebitMemId());//借方参与者代码
		record.setDebitMemName(bondDto.getDebitMemName());//借方参与者简称
		record.setDebitHolderAccount(bondDto.getDebitHolderAccount());//借方持有人账号
		record.setDebitHolderAccountName(bondDto.getDebitHolderAccountName());//借方持有人账号简称
		record.setBondDebitTitle(bondDto.getBondDebitTitle());//借方科目
		if("2".equals(bondDto.getOpertionType())) {
			//记账逻辑
			record.setCreditMemId(bondDto.getCreditMemId());//贷方参与者代码
			record.setCreditMemName(bondDto.getCreditMemName());//贷方参与者简称
			record.setCreditHolderAccount(bondDto.getCreditHolderAccount());//贷方持有人账号
			record.setCreditHolderAccountName(bondDto.getCreditHolderAccountName());//贷方持有人账号简称
		}else {
			//圈券逻辑或者撤销逻辑
			record.setCreditMemId(bondDto.getDebitMemId());//贷方参与者代码
			record.setCreditMemName(bondDto.getDebitMemName());//贷方参与者简称
			record.setCreditHolderAccount(bondDto.getDebitHolderAccount());//贷方持有人账号
			record.setCreditHolderAccountName(bondDto.getDebitHolderAccountName());//贷方持有人账号简称
		}
		record.setBondCreditTitle(bondDto.getBondCreditTitle());//贷方科目
		record.setBondFaceAmt(bondDto.getBondFaceAmt());//债券面额
		record.setRemark(bondDto.getRemark());//备注
		record.setBondProcStatus(bondProcStatus);//债券处理状态
		return record;
    }

}
