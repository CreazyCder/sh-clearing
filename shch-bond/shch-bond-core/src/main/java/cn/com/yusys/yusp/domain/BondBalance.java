/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.domain;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;

import java.io.Serializable;
import javax.persistence.*;
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
@Table(name = "BOND_BALANCE")
public class BondBalance extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/** 参与者代码 **/
	@Id
	@Column(name = "MEM_CODE")
	private String memCode;
	
	/** 持有人账号 **/
	@Id
	@Column(name = "HOLDER_ACCOUNT")
	private String holderAccount;
	
	/** 债券代码 **/
	@Id
	@Column(name = "BOND_CODE")
	private String bondCode;
	
	/** 科目代码 **/
	@Id
	@Column(name = "TITLE_CODE")
	private String titleCode;
	
	/** 营业日期 **/
	@Column(name = "BIZ_DATE", unique = false, nullable = true, length = 10)
	private String bizDate;
	
	/** 参与者简称 **/
	@Column(name = "MEM_NAME", unique = false, nullable = true, length = 40)
	private String memName;
	
	/** 持有人账号简称 **/
	@Column(name = "HOLDER_ACCOUNT_NAME", unique = false, nullable = true, length = 40)
	private String holderAccountName;
	
	/** 债券简称 **/
	@Column(name = "BOND_NAME", unique = false, nullable = true, length = 40)
	private String bondName;
	
	/** 债券类型 **/
	@Column(name = "BOND_TYPE", unique = false, nullable = true, length = 4)
	private String bondType;
	
	/** 科目名称 **/
	@Column(name = "TITLE_NAME", unique = false, nullable = true, length = 40)
	private String titleName;
	
	/** 当前余额 **/
	@Column(name = "CURRENCY_AMT", unique = false, nullable = true, length = 15)
	private java.math.BigDecimal currencyAmt;
	
	/** 更新时间 **/
	@Column(name = "UPDATE_TM", unique = false, nullable = true, length = 18)
	private String updateTm;
	
	
	/**
	 * @param bizDate
	 */
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	
    /**
     * @return bizDate
     */
	public String getBizDate() {
		return this.bizDate;
	}
	
	/**
	 * @param memCode
	 */
	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}
	
    /**
     * @return memCode
     */
	public String getMemCode() {
		return this.memCode;
	}
	
	/**
	 * @param memName
	 */
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
    /**
     * @return memName
     */
	public String getMemName() {
		return this.memName;
	}
	
	/**
	 * @param holderAccount
	 */
	public void setHolderAccount(String holderAccount) {
		this.holderAccount = holderAccount;
	}
	
    /**
     * @return holderAccount
     */
	public String getHolderAccount() {
		return this.holderAccount;
	}
	
	/**
	 * @param holderAccountName
	 */
	public void setHolderAccountName(String holderAccountName) {
		this.holderAccountName = holderAccountName;
	}
	
    /**
     * @return holderAccountName
     */
	public String getHolderAccountName() {
		return this.holderAccountName;
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
	 * @param bondType
	 */
	public void setBondType(String bondType) {
		this.bondType = bondType;
	}
	
    /**
     * @return bondType
     */
	public String getBondType() {
		return this.bondType;
	}
	
	/**
	 * @param titleCode
	 */
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	
    /**
     * @return titleCode
     */
	public String getTitleCode() {
		return this.titleCode;
	}
	
	/**
	 * @param titleName
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
    /**
     * @return titleName
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
     * @return currencyAmt
     */
	public java.math.BigDecimal getCurrencyAmt() {
		return this.currencyAmt;
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


}