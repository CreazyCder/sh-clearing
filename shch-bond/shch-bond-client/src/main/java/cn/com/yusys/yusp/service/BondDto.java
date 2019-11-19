package cn.com.yusys.yusp.service;

import java.io.Serializable;


/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondDto
 * @类描述: 簿记传输对象
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:02
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
public class BondDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 流水号 **/
	private String serialNum;
	
	/** 交易编号 **/
	private String tradeId;
	
	/** 债券结算编号 **/
	private String bondSettleId;
	
	/** 债券代码 **/
	private String bondCode;
	
	/** 债券简称 **/
	private String bondName;
	
	/** 借方参与者代码 **/
	private String debitMemId;
	
	/** 借方参与者简称 **/
	private String debitMemName;
	
	/** 借方持有人账号 **/
	private String debitHolderAccount;
	
	/** 借方持有人账号简称 **/
	private String debitHolderAccountName;
	
	/** 债券借方科目 **/
	private String bondDebitTitle;
	
	/** 贷方参与者代码 **/
	private String creditMemId;
	
	/** 贷方参与者简称 **/
	private String creditMemName;
	
	/** 贷方持有人账号 **/
	private String creditHolderAccount;
	
	/** 贷方持有人账号简称 **/
	private String creditHolderAccountName;
	
	/** 债券贷方科目 **/
	private String bondCreditTitle;
	
	/** 债券面额 **/
	private java.math.BigDecimal bondFaceAmt;
	
	/** 更新时间 **/
	private String updateTm;
	
	/** 备注 **/
	private String remark;
	
	/** 债券处理状态 **/
	private String bondProcStatus;
	
	/** 结算指令编号 **/
	private String settleOrderId;
	
	/** 操作类型**/
	private String opertionType;
	
	/**
	 * @param serialNum
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum == null ? null : serialNum.trim();
	}
	
    /**
     * @return SerialNum
     */	
	public String getSerialNum() {
		return this.serialNum;
	}
	
	/**
	 * @param tradeId
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId == null ? null : tradeId.trim();
	}
	
    /**
     * @return TradeId
     */	
	public String getTradeId() {
		return this.tradeId;
	}
	
	/**
	 * @param bondSettleId
	 */
	public void setBondSettleId(String bondSettleId) {
		this.bondSettleId = bondSettleId == null ? null : bondSettleId.trim();
	}
	
    /**
     * @return BondSettleId
     */	
	public String getBondSettleId() {
		return this.bondSettleId;
	}
	
	/**
	 * @param bondCode
	 */
	public void setBondCode(String bondCode) {
		this.bondCode = bondCode == null ? null : bondCode.trim();
	}
	
    /**
     * @return BondCode
     */	
	public String getBondCode() {
		return this.bondCode;
	}
	
	/**
	 * @param bondName
	 */
	public void setBondName(String bondName) {
		this.bondName = bondName == null ? null : bondName.trim();
	}
	
    /**
     * @return BondName
     */	
	public String getBondName() {
		return this.bondName;
	}
	
	/**
	 * @param debitMemId
	 */
	public void setDebitMemId(String debitMemId) {
		this.debitMemId = debitMemId == null ? null : debitMemId.trim();
	}
	
    /**
     * @return DebitMemId
     */	
	public String getDebitMemId() {
		return this.debitMemId;
	}
	
	/**
	 * @param debitMemName
	 */
	public void setDebitMemName(String debitMemName) {
		this.debitMemName = debitMemName == null ? null : debitMemName.trim();
	}
	
    /**
     * @return DebitMemName
     */	
	public String getDebitMemName() {
		return this.debitMemName;
	}
	
	/**
	 * @param debitHolderAccount
	 */
	public void setDebitHolderAccount(String debitHolderAccount) {
		this.debitHolderAccount = debitHolderAccount == null ? null : debitHolderAccount.trim();
	}
	
    /**
     * @return DebitHolderAccount
     */	
	public String getDebitHolderAccount() {
		return this.debitHolderAccount;
	}
	
	/**
	 * @param debitHolderAccountName
	 */
	public void setDebitHolderAccountName(String debitHolderAccountName) {
		this.debitHolderAccountName = debitHolderAccountName == null ? null : debitHolderAccountName.trim();
	}
	
    /**
     * @return DebitHolderAccountName
     */	
	public String getDebitHolderAccountName() {
		return this.debitHolderAccountName;
	}
	
	/**
	 * @param bondDebitTitle
	 */
	public void setBondDebitTitle(String bondDebitTitle) {
		this.bondDebitTitle = bondDebitTitle == null ? null : bondDebitTitle.trim();
	}
	
    /**
     * @return BondDebitTitle
     */	
	public String getBondDebitTitle() {
		return this.bondDebitTitle;
	}
	
	/**
	 * @param creditMemId
	 */
	public void setCreditMemId(String creditMemId) {
		this.creditMemId = creditMemId == null ? null : creditMemId.trim();
	}
	
    /**
     * @return CreditMemId
     */	
	public String getCreditMemId() {
		return this.creditMemId;
	}
	
	/**
	 * @param creditMemName
	 */
	public void setCreditMemName(String creditMemName) {
		this.creditMemName = creditMemName == null ? null : creditMemName.trim();
	}
	
    /**
     * @return CreditMemName
     */	
	public String getCreditMemName() {
		return this.creditMemName;
	}
	
	/**
	 * @param creditHolderAccount
	 */
	public void setCreditHolderAccount(String creditHolderAccount) {
		this.creditHolderAccount = creditHolderAccount == null ? null : creditHolderAccount.trim();
	}
	
    /**
     * @return CreditHolderAccount
     */	
	public String getCreditHolderAccount() {
		return this.creditHolderAccount;
	}
	
	/**
	 * @param creditHolderAccountName
	 */
	public void setCreditHolderAccountName(String creditHolderAccountName) {
		this.creditHolderAccountName = creditHolderAccountName == null ? null : creditHolderAccountName.trim();
	}
	
    /**
     * @return CreditHolderAccountName
     */	
	public String getCreditHolderAccountName() {
		return this.creditHolderAccountName;
	}
	
	/**
	 * @param bondCreditTitle
	 */
	public void setBondCreditTitle(String bondCreditTitle) {
		this.bondCreditTitle = bondCreditTitle == null ? null : bondCreditTitle.trim();
	}
	
    /**
     * @return BondCreditTitle
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
     * @return BondFaceAmt
     */	
	public java.math.BigDecimal getBondFaceAmt() {
		return this.bondFaceAmt;
	}
	
	/**
	 * @param updateTm
	 */
	public void setUpdateTm(String updateTm) {
		this.updateTm = updateTm == null ? null : updateTm.trim();
	}
	
    /**
     * @return UpdateTm
     */	
	public String getUpdateTm() {
		return this.updateTm;
	}
	
	/**
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	
    /**
     * @return Remark
     */	
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * @param bondProcStatus
	 */
	public void setBondProcStatus(String bondProcStatus) {
		this.bondProcStatus = bondProcStatus == null ? null : bondProcStatus.trim();
	}
	
    /**
     * @return BondProcStatus
     */	
	public String getBondProcStatus() {
		return this.bondProcStatus;
	}

	public String getSettleOrderId() {
		return settleOrderId;
	}

	public void setSettleOrderId(String settleOrderId) {
		this.settleOrderId = settleOrderId;
	}

	public String getOpertionType() {
		return opertionType;
	}

	public void setOpertionType(String opertionType) {
		this.opertionType = opertionType;
	}

	

}