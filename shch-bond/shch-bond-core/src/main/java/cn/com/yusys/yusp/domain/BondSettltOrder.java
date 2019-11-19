/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.domain;
import cn.com.yusys.yusp.commons.mapper.annotation.Generated;
import cn.com.yusys.yusp.commons.mapper.annotation.GenerationType;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondSettltOrder
 * @类描述: BOND_SETTLT_ORDER数据实体类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:02
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@Table(name = "BOND_SETTLT_ORDER")
public class BondSettltOrder extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/** 流水号 **/
	@Id
	@Generated(GenerationType.UUID)
	@Column(name = "SERIAL_NUM")
	private String serialNum;
	
	/** 交易编号 **/
	@Column(name = "TRADE_ID", unique = false, nullable = true, length = 32)
	private String tradeId;
	
	/** 债券结算编号 **/
	@Column(name = "BOND_SETTLE_ID", unique = false, nullable = true, length = 10)
	private String bondSettleId;
	
	/** 债券代码 **/
	@Column(name = "BOND_CODE", unique = false, nullable = true, length = 9)
	private String bondCode;
	
	/** 债券简称 **/
	@Column(name = "BOND_NAME", unique = false, nullable = true, length = 40)
	private String bondName;
	
	/** 借方参与者代码 **/
	@Column(name = "DEBIT_MEM_ID", unique = false, nullable = true, length = 7)
	private String debitMemId;
	
	/** 借方参与者简称 **/
	@Column(name = "DEBIT_MEM_NAME", unique = false, nullable = true, length = 40)
	private String debitMemName;
	
	/** 借方持有人账号 **/
	@Column(name = "DEBIT_HOLDER_ACCOUNT", unique = false, nullable = true, length = 7)
	private String debitHolderAccount;
	
	/** 借方持有人账号简称 **/
	@Column(name = "DEBIT_HOLDER_ACCOUNT_NAME", unique = false, nullable = true, length = 40)
	private String debitHolderAccountName;
	
	/** 债券借方科目 **/
	@Column(name = "BOND_DEBIT_TITLE", unique = false, nullable = true, length = 4)
	private String bondDebitTitle;
	
	/** 贷方参与者代码 **/
	@Column(name = "CREDIT_MEM_ID", unique = false, nullable = true, length = 7)
	private String creditMemId;
	
	/** 贷方参与者简称 **/
	@Column(name = "CREDIT_MEM_NAME", unique = false, nullable = true, length = 40)
	private String creditMemName;
	
	/** 贷方持有人账号 **/
	@Column(name = "CREDIT_HOLDER_ACCOUNT", unique = false, nullable = true, length = 7)
	private String creditHolderAccount;
	
	/** 贷方持有人账号简称 **/
	@Column(name = "CREDIT_HOLDER_ACCOUNT_NAME", unique = false, nullable = true, length = 40)
	private String creditHolderAccountName;
	
	/** 债券贷方科目 **/
	@Column(name = "BOND_CREDIT_TITLE", unique = false, nullable = true, length = 4)
	private String bondCreditTitle;
	
	/** 债券面额 **/
	@Column(name = "BOND_FACE_AMT", unique = false, nullable = true, length = 15)
	private java.math.BigDecimal bondFaceAmt;
	
	/** 更新时间 **/
	@Column(name = "UPDATE_TM", unique = false, nullable = true, length = 18)
	private String updateTm;
	
	/** 备注 **/
	@Column(name = "REMARK", unique = false, nullable = true, length = 256)
	private String remark;
	
	/** 债券处理状态 **/
	@Column(name = "BOND_PROC_STATUS", unique = false, nullable = true, length = 4)
	private String bondProcStatus;
	
	
	/**
	 * @param serialNum
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
    /**
     * @return serialNum
     */
	public String getSerialNum() {
		return this.serialNum;
	}
	
	/**
	 * @param tradeId
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	
    /**
     * @return tradeId
     */
	public String getTradeId() {
		return this.tradeId;
	}
	
	/**
	 * @param bondSettleId
	 */
	public void setBondSettleId(String bondSettleId) {
		this.bondSettleId = bondSettleId;
	}
	
    /**
     * @return bondSettleId
     */
	public String getBondSettleId() {
		return this.bondSettleId;
	}
	
	/**
	 * @param bondCode
	 */
	public void setBondCode(String bondCode) {
		this.bondCode = bondCode;
	}
	
    /**
     * @return bondCode
     */
	public String getBondCode() {
		return this.bondCode;
	}
	
	/**
	 * @param bondName
	 */
	public void setBondName(String bondName) {
		this.bondName = bondName;
	}
	
    /**
     * @return bondName
     */
	public String getBondName() {
		return this.bondName;
	}
	
	/**
	 * @param debitMemId
	 */
	public void setDebitMemId(String debitMemId) {
		this.debitMemId = debitMemId;
	}
	
    /**
     * @return debitMemId
     */
	public String getDebitMemId() {
		return this.debitMemId;
	}
	
	/**
	 * @param debitMemName
	 */
	public void setDebitMemName(String debitMemName) {
		this.debitMemName = debitMemName;
	}
	
    /**
     * @return debitMemName
     */
	public String getDebitMemName() {
		return this.debitMemName;
	}
	
	/**
	 * @param debitHolderAccount
	 */
	public void setDebitHolderAccount(String debitHolderAccount) {
		this.debitHolderAccount = debitHolderAccount;
	}
	
    /**
     * @return debitHolderAccount
     */
	public String getDebitHolderAccount() {
		return this.debitHolderAccount;
	}
	
	/**
	 * @param debitHolderAccountName
	 */
	public void setDebitHolderAccountName(String debitHolderAccountName) {
		this.debitHolderAccountName = debitHolderAccountName;
	}
	
    /**
     * @return debitHolderAccountName
     */
	public String getDebitHolderAccountName() {
		return this.debitHolderAccountName;
	}
	
	/**
	 * @param bondDebitTitle
	 */
	public void setBondDebitTitle(String bondDebitTitle) {
		this.bondDebitTitle = bondDebitTitle;
	}
	
    /**
     * @return bondDebitTitle
     */
	public String getBondDebitTitle() {
		return this.bondDebitTitle;
	}
	
	/**
	 * @param creditMemId
	 */
	public void setCreditMemId(String creditMemId) {
		this.creditMemId = creditMemId;
	}
	
    /**
     * @return creditMemId
     */
	public String getCreditMemId() {
		return this.creditMemId;
	}
	
	/**
	 * @param creditMemName
	 */
	public void setCreditMemName(String creditMemName) {
		this.creditMemName = creditMemName;
	}
	
    /**
     * @return creditMemName
     */
	public String getCreditMemName() {
		return this.creditMemName;
	}
	
	/**
	 * @param creditHolderAccount
	 */
	public void setCreditHolderAccount(String creditHolderAccount) {
		this.creditHolderAccount = creditHolderAccount;
	}
	
    /**
     * @return creditHolderAccount
     */
	public String getCreditHolderAccount() {
		return this.creditHolderAccount;
	}
	
	/**
	 * @param creditHolderAccountName
	 */
	public void setCreditHolderAccountName(String creditHolderAccountName) {
		this.creditHolderAccountName = creditHolderAccountName;
	}
	
    /**
     * @return creditHolderAccountName
     */
	public String getCreditHolderAccountName() {
		return this.creditHolderAccountName;
	}
	
	/**
	 * @param bondCreditTitle
	 */
	public void setBondCreditTitle(String bondCreditTitle) {
		this.bondCreditTitle = bondCreditTitle;
	}
	
    /**
     * @return bondCreditTitle
     */
	public String getBondCreditTitle() {
		return this.bondCreditTitle;
	}
	
	/**
	 * @param bondFaceAmt
	 */
	public void setBondFaceAmt(java.math.BigDecimal bondFaceAmt) {
		this.bondFaceAmt = bondFaceAmt;
	}
	
    /**
     * @return bondFaceAmt
     */
	public java.math.BigDecimal getBondFaceAmt() {
		return this.bondFaceAmt;
	}
	
	/**
	 * @param updateTm
	 */
	public void setUpdateTm(String updateTm) {
		this.updateTm = updateTm;
	}
	
    /**
     * @return updateTm
     */
	public String getUpdateTm() {
		return this.updateTm;
	}
	
	/**
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    /**
     * @return remark
     */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * @param bondProcStatus
	 */
	public void setBondProcStatus(String bondProcStatus) {
		this.bondProcStatus = bondProcStatus;
	}
	
    /**
     * @return bondProcStatus
     */
	public String getBondProcStatus() {
		return this.bondProcStatus;
	}


}