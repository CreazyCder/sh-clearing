package cn.com.yusys.yusp.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondBalance
 * @类描述: BOND_BALANCE数据实体类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:23:21
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
public class BondBalanceDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 参与者代码 **/
	private String memCode;
	/** 持有人账号 **/
	private String holderAccount;
	/** 债券代码 **/
	private String bondCode;
	/** 科目代码 **/
	private String titleCode;
	
	/** 营业日期 **/
	private String bizDate;
	
	/** 参与者简称 **/
	private String memName;
	
	/** 持有人账号简称 **/
	private String holderAccountName;
	
	/** 债券简称 **/
	private String bondName;
	
	/** 债券类型 **/
	private String bondType;
	
	/** 科目名称 **/
	private String titleName;
	
	/** 当前余额 **/
	private java.math.BigDecimal currencyAmt;
	
	/** 更新时间 **/
	private String updateTm;
	
	
	/**
	 * @param bizDate
	 */
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate == null ? null : bizDate.trim();
	}
	
    /**
     * @return BizDate
     */	
	public String getBizDate() {
		return this.bizDate;
	}
	
	/**
	 * @param memCode
	 */
	public void setMemCode(String memCode) {
		this.memCode = memCode == null ? null : memCode.trim();
	}
	
    /**
     * @return MemCode
     */	
	public String getMemCode() {
		return this.memCode;
	}
	
	/**
	 * @param memName
	 */
	public void setMemName(String memName) {
		this.memName = memName == null ? null : memName.trim();
	}
	
    /**
     * @return MemName
     */	
	public String getMemName() {
		return this.memName;
	}
	
	/**
	 * @param holderAccount
	 */
	public void setHolderAccount(String holderAccount) {
		this.holderAccount = holderAccount == null ? null : holderAccount.trim();
	}
	
    /**
     * @return HolderAccount
     */	
	public String getHolderAccount() {
		return this.holderAccount;
	}
	
	/**
	 * @param holderAccountName
	 */
	public void setHolderAccountName(String holderAccountName) {
		this.holderAccountName = holderAccountName == null ? null : holderAccountName.trim();
	}
	
    /**
     * @return HolderAccountName
     */	
	public String getHolderAccountName() {
		return this.holderAccountName;
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
	 * @param bondType
	 */
	public void setBondType(String bondType) {
		this.bondType = bondType == null ? null : bondType.trim();
	}
	
    /**
     * @return BondType
     */	
	public String getBondType() {
		return this.bondType;
	}
	
	/**
	 * @param titleCode
	 */
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode == null ? null : titleCode.trim();
	}
	
    /**
     * @return TitleCode
     */	
	public String getTitleCode() {
		return this.titleCode;
	}
	
	/**
	 * @param titleName
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName == null ? null : titleName.trim();
	}
	
    /**
     * @return TitleName
     */	
	public String getTitleName() {
		return this.titleName;
	}
	
	/**
	 * @param currencyAmt
	 */
	public void setCurrencyAmt(java.math.BigDecimal currencyAmt) {
		this.currencyAmt = currencyAmt;
	}
	
    /**
     * @return CurrencyAmt
     */	
	public java.math.BigDecimal getCurrencyAmt() {
		return this.currencyAmt;
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


}