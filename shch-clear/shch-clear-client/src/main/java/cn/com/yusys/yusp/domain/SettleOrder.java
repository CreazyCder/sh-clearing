/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.domain;

import cn.com.yusys.yusp.commons.mapper.annotation.Generated;
import cn.com.yusys.yusp.commons.mapper.annotation.GenerationType;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @项目名称: shch-clear-core模块
 * @类名称: SettleOrder
 * @类描述: SETTLE_ORDER数据实体类
 * @功能描述: 
 * @创建人: boip
 * @创建时间: 2019-11-18 11:32:23
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@Builder
@Table(name = "SETTLE_ORDER")
public class SettleOrder extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/** 结算指令编号 **/
	@Id
	@Generated(GenerationType.UUID)
	@Column(name = "SETTLE_ORDER_ID")
	private String settleOrderId;
	
	/** 营业日期 **/
	@Column(name = "BIZ_DATE", unique = false, nullable = true, length = 10)
	private String bizDate;
	
	/** 交易编号 **/
	@Column(name = "TRADE_ID", unique = false, nullable = true, length = 32)
	private String tradeId;
	
	/** 交易日期 **/
	@Column(name = "TRADE_DATE", unique = false, nullable = true, length = 10)
	private String tradeDate;
	
	/** 债券代码 **/
	@Column(name = "BOND_CODE", unique = false, nullable = false, length = 9)
	private String bondCode;
	
	/** 债券简称 **/
	@Column(name = "BOND_NAME", unique = false, nullable = true, length = 40)
	private String bondName;
	
	/** 债券类型 **/
	@Column(name = "BOND_TYPE", unique = false, nullable = true, length = 4)
	private String bondType;
	
	/** 债券面额 **/
	@Column(name = "BOND_FACE_AMT", unique = false, nullable = true, length = 15)
	private java.math.BigDecimal bondFaceAmt;
	
	/** 结算日期 **/
	@Column(name = "SETTLE_DATE", unique = false, nullable = true, length = 10)
	private String settleDate;
	
	/** 结算方式 **/
	@Column(name = "SETTLE_TYPE", unique = false, nullable = true, length = 4)
	private String settleType;
	
	/** 结算金额 **/
	@Column(name = "SETTLE_AMT", unique = false, nullable = true, length = 15)
	private String settleAmt;
	
	/** 买方参与者代码 **/
	@Column(name = "BUYER_MEM_CODE", unique = false, nullable = true, length = 7)
	private String buyerMemCode;
	
	/** 买方参与者简称 **/
	@Column(name = "BUYER_MEM_NAME", unique = false, nullable = true, length = 40)
	private String buyerMemName;
	
	/** 买方持有人账号 **/
	@Column(name = "BUYER_HOLDER_ACCOUNT", unique = false, nullable = true, length = 7)
	private String buyerHolderAccount;
	
	/** 买方持有人账号简称 **/
	@Column(name = "BUYER_HOLDER_ACCOUNT_NAME", unique = false, nullable = true, length = 40)
	private String buyerHolderAccountName;
	
	/** 卖方参与者代码 **/
	@Column(name = "SELLER_MEM_CODE", unique = false, nullable = true, length = 7)
	private String sellerMemCode;
	
	/** 卖方参与者简称 **/
	@Column(name = "SELLER_MEM_NAME", unique = false, nullable = true, length = 40)
	private String sellerMemName;
	
	/** 卖方持有人账号 **/
	@Column(name = "SELLER_HOLDER_ACCOUNT", unique = false, nullable = true, length = 7)
	private String sellerHolderAccount;
	
	/** 卖方持有人账号简称 **/
	@Column(name = "SELLER_HOLDER_ACCOUNT_NAME", unique = false, nullable = true, length = 40)
	private String sellerHolderAccountName;
	
	/** 债券结算编号 **/
	@Column(name = "BOND_SETTLE_ID", unique = false, nullable = true, length = 10)
	private String bondSettleId;
	
	/** 资金结算编号 **/
	@Column(name = "CASH_SETTLE_ID", unique = false, nullable = true, length = 10)
	private String cashSettleId;
	
	/** 债券结算状态 **/
	@Column(name = "BOND_SETTLE_STATUS", unique = false, nullable = true, length = 4)
	private String bondSettleStatus;
	
	/** 债券结算状态更新时间 **/
	@Column(name = "BOND_SETTLE_STATUS_UPDATE_TM", unique = false, nullable = true, length = 18)
	private String bondSettleStatusUpdateTm;
	
	/** 资金结算状态 **/
	@Column(name = "CASH_SETTLE_STATUS", unique = false, nullable = true, length = 4)
	private String cashSettleStatus;
	
	/** 资金结算状态更新时间 **/
	@Column(name = "CASH_SETTLE_STATUS_UPDATE_TM", unique = false, nullable = true, length = 18)
	private String cashSettleStatusUpdateTm;
	
	/** 结算指令状态 **/
	@Column(name = "SETTLE_ORDER_STATUS", unique = false, nullable = true, length = 4)
	private String settleOrderStatus;
	
	/** 结算指令状态更新时间 **/
	@Column(name = "SETTLE_ORDER_STATUS_UPDATE_TM", unique = false, nullable = true, length = 18)
	private String settleOrderStatusUpdateTm;
	
	/** 资金宽限期 **/
	@Column(name = "CASH_PEROID", unique = false, nullable = true, length = 1)
	private String cashPeroid;
	
	/** 债券宽限期 **/
	@Column(name = "BOND_PEROID", unique = false, nullable = true, length = 1)
	private String bondPeroid;
	
	/** 录入操作员代码 **/
	@Column(name = "INPUT_OPER_ID", unique = false, nullable = true, length = 8)
	private String inputOperId;
	
	/** 录入时间 **/
	@Column(name = "INPUT_TM", unique = false, nullable = true, length = 18)
	private String inputTm;
	
	/** 复核操作员代码 **/
	@Column(name = "REVIEW_OPER_ID", unique = false, nullable = true, length = 8)
	private String reviewOperId;
	
	/** 复核时间 **/
	@Column(name = "REVIEW_TM", unique = false, nullable = true, length = 18)
	private String reviewTm;
	
	/** 确认人操作员代码 **/
	@Column(name = "CONFIRM_OPER_ID", unique = false, nullable = true, length = 8)
	private String confirmOperId;
	
	/** 确认时间 **/
	@Column(name = "CONFIRM_TM", unique = false, nullable = true, length = 18)
	private String confirmTm;
	
	/** 交易来源 **/
	@Column(name = "SRC_FROM", unique = false, nullable = true, length = 1)
	private String srcFrom;
	
	/** 备注 **/
	@Column(name = "REMARK", unique = false, nullable = true, length = 256)
	private String remark;
	
	
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
	 * @param tradeDate
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	
    /**
     * @return tradeDate
     */
	public String getTradeDate() {
		return this.tradeDate;
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
	 * @param settleDate
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	
    /**
     * @return settleDate
     */
	public String getSettleDate() {
		return this.settleDate;
	}
	
	/**
	 * @param settleType
	 */
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
    /**
     * @return settleType
     */
	public String getSettleType() {
		return this.settleType;
	}
	
	/**
	 * @param settleAmt
	 */
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	
    /**
     * @return settleAmt
     */
	public String getSettleAmt() {
		return this.settleAmt;
	}
	
	/**
	 * @param buyerMemCode
	 */
	public void setBuyerMemCode(String buyerMemCode) {
		this.buyerMemCode = buyerMemCode;
	}
	
    /**
     * @return buyerMemCode
     */
	public String getBuyerMemCode() {
		return this.buyerMemCode;
	}
	
	/**
	 * @param buyerMemName
	 */
	public void setBuyerMemName(String buyerMemName) {
		this.buyerMemName = buyerMemName;
	}
	
    /**
     * @return buyerMemName
     */
	public String getBuyerMemName() {
		return this.buyerMemName;
	}
	
	/**
	 * @param buyerHolderAccount
	 */
	public void setBuyerHolderAccount(String buyerHolderAccount) {
		this.buyerHolderAccount = buyerHolderAccount;
	}
	
    /**
     * @return buyerHolderAccount
     */
	public String getBuyerHolderAccount() {
		return this.buyerHolderAccount;
	}
	
	/**
	 * @param buyerHolderAccountName
	 */
	public void setBuyerHolderAccountName(String buyerHolderAccountName) {
		this.buyerHolderAccountName = buyerHolderAccountName;
	}
	
    /**
     * @return buyerHolderAccountName
     */
	public String getBuyerHolderAccountName() {
		return this.buyerHolderAccountName;
	}
	
	/**
	 * @param sellerMemCode
	 */
	public void setSellerMemCode(String sellerMemCode) {
		this.sellerMemCode = sellerMemCode;
	}
	
    /**
     * @return sellerMemCode
     */
	public String getSellerMemCode() {
		return this.sellerMemCode;
	}
	
	/**
	 * @param sellerMemName
	 */
	public void setSellerMemName(String sellerMemName) {
		this.sellerMemName = sellerMemName;
	}
	
    /**
     * @return sellerMemName
     */
	public String getSellerMemName() {
		return this.sellerMemName;
	}
	
	/**
	 * @param sellerHolderAccount
	 */
	public void setSellerHolderAccount(String sellerHolderAccount) {
		this.sellerHolderAccount = sellerHolderAccount;
	}
	
    /**
     * @return sellerHolderAccount
     */
	public String getSellerHolderAccount() {
		return this.sellerHolderAccount;
	}
	
	/**
	 * @param sellerHolderAccountName
	 */
	public void setSellerHolderAccountName(String sellerHolderAccountName) {
		this.sellerHolderAccountName = sellerHolderAccountName;
	}
	
    /**
     * @return sellerHolderAccountName
     */
	public String getSellerHolderAccountName() {
		return this.sellerHolderAccountName;
	}
	
	/**
	 * @param settleOrderId
	 */
	public void setSettleOrderId(String settleOrderId) {
		this.settleOrderId = settleOrderId;
	}
	
    /**
     * @return settleOrderId
     */
	public String getSettleOrderId() {
		return this.settleOrderId;
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
	 * @param cashSettleId
	 */
	public void setCashSettleId(String cashSettleId) {
		this.cashSettleId = cashSettleId;
	}
	
    /**
     * @return cashSettleId
     */
	public String getCashSettleId() {
		return this.cashSettleId;
	}
	
	/**
	 * @param bondSettleStatus
	 */
	public void setBondSettleStatus(String bondSettleStatus) {
		this.bondSettleStatus = bondSettleStatus;
	}
	
    /**
     * @return bondSettleStatus
     */
	public String getBondSettleStatus() {
		return this.bondSettleStatus;
	}
	
	/**
	 * @param bondSettleStatusUpdateTm
	 */
	public void setBondSettleStatusUpdateTm(String bondSettleStatusUpdateTm) {
		this.bondSettleStatusUpdateTm = bondSettleStatusUpdateTm;
	}
	
    /**
     * @return bondSettleStatusUpdateTm
     */
	public String getBondSettleStatusUpdateTm() {
		return this.bondSettleStatusUpdateTm;
	}
	
	/**
	 * @param cashSettleStatus
	 */
	public void setCashSettleStatus(String cashSettleStatus) {
		this.cashSettleStatus = cashSettleStatus;
	}
	
    /**
     * @return cashSettleStatus
     */
	public String getCashSettleStatus() {
		return this.cashSettleStatus;
	}
	
	/**
	 * @param cashSettleStatusUpdateTm
	 */
	public void setCashSettleStatusUpdateTm(String cashSettleStatusUpdateTm) {
		this.cashSettleStatusUpdateTm = cashSettleStatusUpdateTm;
	}
	
    /**
     * @return cashSettleStatusUpdateTm
     */
	public String getCashSettleStatusUpdateTm() {
		return this.cashSettleStatusUpdateTm;
	}
	
	/**
	 * @param settleOrderStatus
	 */
	public void setSettleOrderStatus(String settleOrderStatus) {
		this.settleOrderStatus = settleOrderStatus;
	}
	
    /**
     * @return settleOrderStatus
     */
	public String getSettleOrderStatus() {
		return this.settleOrderStatus;
	}
	
	/**
	 * @param settleOrderStatusUpdateTm
	 */
	public void setSettleOrderStatusUpdateTm(String settleOrderStatusUpdateTm) {
		this.settleOrderStatusUpdateTm = settleOrderStatusUpdateTm;
	}
	
    /**
     * @return settleOrderStatusUpdateTm
     */
	public String getSettleOrderStatusUpdateTm() {
		return this.settleOrderStatusUpdateTm;
	}
	
	/**
	 * @param cashPeroid
	 */
	public void setCashPeroid(String cashPeroid) {
		this.cashPeroid = cashPeroid;
	}
	
    /**
     * @return cashPeroid
     */
	public String getCashPeroid() {
		return this.cashPeroid;
	}
	
	/**
	 * @param bondPeroid
	 */
	public void setBondPeroid(String bondPeroid) {
		this.bondPeroid = bondPeroid;
	}
	
    /**
     * @return bondPeroid
     */
	public String getBondPeroid() {
		return this.bondPeroid;
	}
	
	/**
	 * @param inputOperId
	 */
	public void setInputOperId(String inputOperId) {
		this.inputOperId = inputOperId;
	}
	
    /**
     * @return inputOperId
     */
	public String getInputOperId() {
		return this.inputOperId;
	}
	
	/**
	 * @param inputTm
	 */
	public void setInputTm(String inputTm) {
		this.inputTm = inputTm;
	}
	
    /**
     * @return inputTm
     */
	public String getInputTm() {
		return this.inputTm;
	}
	
	/**
	 * @param reviewOperId
	 */
	public void setReviewOperId(String reviewOperId) {
		this.reviewOperId = reviewOperId;
	}
	
    /**
     * @return reviewOperId
     */
	public String getReviewOperId() {
		return this.reviewOperId;
	}
	
	/**
	 * @param reviewTm
	 */
	public void setReviewTm(String reviewTm) {
		this.reviewTm = reviewTm;
	}
	
    /**
     * @return reviewTm
     */
	public String getReviewTm() {
		return this.reviewTm;
	}
	
	/**
	 * @param confirmOperId
	 */
	public void setConfirmOperId(String confirmOperId) {
		this.confirmOperId = confirmOperId;
	}
	
    /**
     * @return confirmOperId
     */
	public String getConfirmOperId() {
		return this.confirmOperId;
	}
	
	/**
	 * @param confirmTm
	 */
	public void setConfirmTm(String confirmTm) {
		this.confirmTm = confirmTm;
	}
	
    /**
     * @return confirmTm
     */
	public String getConfirmTm() {
		return this.confirmTm;
	}
	
	/**
	 * @param srcFrom
	 */
	public void setSrcFrom(String srcFrom) {
		this.srcFrom = srcFrom;
	}
	
    /**
     * @return srcFrom
     */
	public String getSrcFrom() {
		return this.srcFrom;
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


}