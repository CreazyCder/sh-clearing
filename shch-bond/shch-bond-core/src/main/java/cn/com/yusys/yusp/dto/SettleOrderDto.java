package cn.com.yusys.yusp.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @项目名称: shch-bond-core模块
 * @类名称: SettleOrder
 * @类描述: SETTLE_ORDER数据实体类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:54
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
public class SettleOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 结算指令编号 **/
	private String settleOrderId;
	
	/** 营业日期 **/
	private String bizDate;
	
	/** 交易编号 **/
	private String tradeId;
	
	/** 交易日期 **/
	private String tradeDate;
	
	/** 债券代码 **/
	private String bondCode;
	
	/** 债券简称 **/
	private String bondName;
	
	/** 债券类型 **/
	private String bondType;
	
	/** 债券面额 **/
	private java.math.BigDecimal bondFaceAmt;
	
	/** 结算日期 **/
	private String settleDate;
	
	/** 结算方式 **/
	private String settleType;
	
	/** 结算金额 **/
	private String settleAmt;
	
	/** 买方参与者代码 **/
	private String buyerMemCode;
	
	/** 买方参与者简称 **/
	private String buyerMemName;
	
	/** 买方持有人账号 **/
	private String buyerHolderAccount;
	
	/** 买方持有人账号简称 **/
	private String buyerHolderAccountName;
	
	/** 卖方参与者代码 **/
	private String sellerMemCode;
	
	/** 卖方参与者简称 **/
	private String sellerMemName;
	
	/** 卖方持有人账号 **/
	private String sellerHolderAccount;
	
	/** 卖方持有人账号简称 **/
	private String sellerHolderAccountName;
	
	/** 债券结算编号 **/
	private String bondSettleId;
	
	/** 资金结算编号 **/
	private String cashSettleId;
	
	/** 债券结算状态 **/
	private String bondSettleStatus;
	
	/** 债券结算状态更新时间 **/
	private String bondSettleStatusUpdateTm;
	
	/** 资金结算状态 **/
	private String cashSettleStatus;
	
	/** 资金结算状态更新时间 **/
	private String cashSettleStatusUpdateTm;
	
	/** 结算指令状态 **/
	private String settleOrderStatus;
	
	/** 结算指令状态更新时间 **/
	private String settleOrderStatusUpdateTm;
	
	/** 资金宽限期 **/
	private String cashPeroid;
	
	/** 债券宽限期 **/
	private String bondPeroid;
	
	/** 录入操作员代码 **/
	private String inputOperId;
	
	/** 录入时间 **/
	private String inputTm;
	
	/** 复核操作员代码 **/
	private String reviewOperId;
	
	/** 复核时间 **/
	private String reviewTm;
	
	/** 确认人操作员代码 **/
	private String confirmOperId;
	
	/** 确认时间 **/
	private String confirmTm;
	
	/** 交易来源 **/
	private String srcFrom;
	
	/** 备注 **/
	private String remark;
	
	
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
	 * @param tradeDate
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate == null ? null : tradeDate.trim();
	}
	
    /**
     * @return TradeDate
     */	
	public String getTradeDate() {
		return this.tradeDate;
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
	 * @param settleDate
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate == null ? null : settleDate.trim();
	}
	
    /**
     * @return SettleDate
     */	
	public String getSettleDate() {
		return this.settleDate;
	}
	
	/**
	 * @param settleType
	 */
	public void setSettleType(String settleType) {
		this.settleType = settleType == null ? null : settleType.trim();
	}
	
    /**
     * @return SettleType
     */	
	public String getSettleType() {
		return this.settleType;
	}
	
	/**
	 * @param settleAmt
	 */
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt == null ? null : settleAmt.trim();
	}
	
    /**
     * @return SettleAmt
     */	
	public String getSettleAmt() {
		return this.settleAmt;
	}
	
	/**
	 * @param buyerMemCode
	 */
	public void setBuyerMemCode(String buyerMemCode) {
		this.buyerMemCode = buyerMemCode == null ? null : buyerMemCode.trim();
	}
	
    /**
     * @return BuyerMemCode
     */	
	public String getBuyerMemCode() {
		return this.buyerMemCode;
	}
	
	/**
	 * @param buyerMemName
	 */
	public void setBuyerMemName(String buyerMemName) {
		this.buyerMemName = buyerMemName == null ? null : buyerMemName.trim();
	}
	
    /**
     * @return BuyerMemName
     */	
	public String getBuyerMemName() {
		return this.buyerMemName;
	}
	
	/**
	 * @param buyerHolderAccount
	 */
	public void setBuyerHolderAccount(String buyerHolderAccount) {
		this.buyerHolderAccount = buyerHolderAccount == null ? null : buyerHolderAccount.trim();
	}
	
    /**
     * @return BuyerHolderAccount
     */	
	public String getBuyerHolderAccount() {
		return this.buyerHolderAccount;
	}
	
	/**
	 * @param buyerHolderAccountName
	 */
	public void setBuyerHolderAccountName(String buyerHolderAccountName) {
		this.buyerHolderAccountName = buyerHolderAccountName == null ? null : buyerHolderAccountName.trim();
	}
	
    /**
     * @return BuyerHolderAccountName
     */	
	public String getBuyerHolderAccountName() {
		return this.buyerHolderAccountName;
	}
	
	/**
	 * @param sellerMemCode
	 */
	public void setSellerMemCode(String sellerMemCode) {
		this.sellerMemCode = sellerMemCode == null ? null : sellerMemCode.trim();
	}
	
    /**
     * @return SellerMemCode
     */	
	public String getSellerMemCode() {
		return this.sellerMemCode;
	}
	
	/**
	 * @param sellerMemName
	 */
	public void setSellerMemName(String sellerMemName) {
		this.sellerMemName = sellerMemName == null ? null : sellerMemName.trim();
	}
	
    /**
     * @return SellerMemName
     */	
	public String getSellerMemName() {
		return this.sellerMemName;
	}
	
	/**
	 * @param sellerHolderAccount
	 */
	public void setSellerHolderAccount(String sellerHolderAccount) {
		this.sellerHolderAccount = sellerHolderAccount == null ? null : sellerHolderAccount.trim();
	}
	
    /**
     * @return SellerHolderAccount
     */	
	public String getSellerHolderAccount() {
		return this.sellerHolderAccount;
	}
	
	/**
	 * @param sellerHolderAccountName
	 */
	public void setSellerHolderAccountName(String sellerHolderAccountName) {
		this.sellerHolderAccountName = sellerHolderAccountName == null ? null : sellerHolderAccountName.trim();
	}
	
    /**
     * @return SellerHolderAccountName
     */	
	public String getSellerHolderAccountName() {
		return this.sellerHolderAccountName;
	}
	
	/**
	 * @param settleOrderId
	 */
	public void setSettleOrderId(String settleOrderId) {
		this.settleOrderId = settleOrderId == null ? null : settleOrderId.trim();
	}
	
    /**
     * @return SettleOrderId
     */	
	public String getSettleOrderId() {
		return this.settleOrderId;
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
	 * @param cashSettleId
	 */
	public void setCashSettleId(String cashSettleId) {
		this.cashSettleId = cashSettleId == null ? null : cashSettleId.trim();
	}
	
    /**
     * @return CashSettleId
     */	
	public String getCashSettleId() {
		return this.cashSettleId;
	}
	
	/**
	 * @param bondSettleStatus
	 */
	public void setBondSettleStatus(String bondSettleStatus) {
		this.bondSettleStatus = bondSettleStatus == null ? null : bondSettleStatus.trim();
	}
	
    /**
     * @return BondSettleStatus
     */	
	public String getBondSettleStatus() {
		return this.bondSettleStatus;
	}
	
	/**
	 * @param bondSettleStatusUpdateTm
	 */
	public void setBondSettleStatusUpdateTm(String bondSettleStatusUpdateTm) {
		this.bondSettleStatusUpdateTm = bondSettleStatusUpdateTm == null ? null : bondSettleStatusUpdateTm.trim();
	}
	
    /**
     * @return BondSettleStatusUpdateTm
     */	
	public String getBondSettleStatusUpdateTm() {
		return this.bondSettleStatusUpdateTm;
	}
	
	/**
	 * @param cashSettleStatus
	 */
	public void setCashSettleStatus(String cashSettleStatus) {
		this.cashSettleStatus = cashSettleStatus == null ? null : cashSettleStatus.trim();
	}
	
    /**
     * @return CashSettleStatus
     */	
	public String getCashSettleStatus() {
		return this.cashSettleStatus;
	}
	
	/**
	 * @param cashSettleStatusUpdateTm
	 */
	public void setCashSettleStatusUpdateTm(String cashSettleStatusUpdateTm) {
		this.cashSettleStatusUpdateTm = cashSettleStatusUpdateTm == null ? null : cashSettleStatusUpdateTm.trim();
	}
	
    /**
     * @return CashSettleStatusUpdateTm
     */	
	public String getCashSettleStatusUpdateTm() {
		return this.cashSettleStatusUpdateTm;
	}
	
	/**
	 * @param settleOrderStatus
	 */
	public void setSettleOrderStatus(String settleOrderStatus) {
		this.settleOrderStatus = settleOrderStatus == null ? null : settleOrderStatus.trim();
	}
	
    /**
     * @return SettleOrderStatus
     */	
	public String getSettleOrderStatus() {
		return this.settleOrderStatus;
	}
	
	/**
	 * @param settleOrderStatusUpdateTm
	 */
	public void setSettleOrderStatusUpdateTm(String settleOrderStatusUpdateTm) {
		this.settleOrderStatusUpdateTm = settleOrderStatusUpdateTm == null ? null : settleOrderStatusUpdateTm.trim();
	}
	
    /**
     * @return SettleOrderStatusUpdateTm
     */	
	public String getSettleOrderStatusUpdateTm() {
		return this.settleOrderStatusUpdateTm;
	}
	
	/**
	 * @param cashPeroid
	 */
	public void setCashPeroid(String cashPeroid) {
		this.cashPeroid = cashPeroid == null ? null : cashPeroid.trim();
	}
	
    /**
     * @return CashPeroid
     */	
	public String getCashPeroid() {
		return this.cashPeroid;
	}
	
	/**
	 * @param bondPeroid
	 */
	public void setBondPeroid(String bondPeroid) {
		this.bondPeroid = bondPeroid == null ? null : bondPeroid.trim();
	}
	
    /**
     * @return BondPeroid
     */	
	public String getBondPeroid() {
		return this.bondPeroid;
	}
	
	/**
	 * @param inputOperId
	 */
	public void setInputOperId(String inputOperId) {
		this.inputOperId = inputOperId == null ? null : inputOperId.trim();
	}
	
    /**
     * @return InputOperId
     */	
	public String getInputOperId() {
		return this.inputOperId;
	}
	
	/**
	 * @param inputTm
	 */
	public void setInputTm(String inputTm) {
		this.inputTm = inputTm == null ? null : inputTm.trim();
	}
	
    /**
     * @return InputTm
     */	
	public String getInputTm() {
		return this.inputTm;
	}
	
	/**
	 * @param reviewOperId
	 */
	public void setReviewOperId(String reviewOperId) {
		this.reviewOperId = reviewOperId == null ? null : reviewOperId.trim();
	}
	
    /**
     * @return ReviewOperId
     */	
	public String getReviewOperId() {
		return this.reviewOperId;
	}
	
	/**
	 * @param reviewTm
	 */
	public void setReviewTm(String reviewTm) {
		this.reviewTm = reviewTm == null ? null : reviewTm.trim();
	}
	
    /**
     * @return ReviewTm
     */	
	public String getReviewTm() {
		return this.reviewTm;
	}
	
	/**
	 * @param confirmOperId
	 */
	public void setConfirmOperId(String confirmOperId) {
		this.confirmOperId = confirmOperId == null ? null : confirmOperId.trim();
	}
	
    /**
     * @return ConfirmOperId
     */	
	public String getConfirmOperId() {
		return this.confirmOperId;
	}
	
	/**
	 * @param confirmTm
	 */
	public void setConfirmTm(String confirmTm) {
		this.confirmTm = confirmTm == null ? null : confirmTm.trim();
	}
	
    /**
     * @return ConfirmTm
     */	
	public String getConfirmTm() {
		return this.confirmTm;
	}
	
	/**
	 * @param srcFrom
	 */
	public void setSrcFrom(String srcFrom) {
		this.srcFrom = srcFrom == null ? null : srcFrom.trim();
	}
	
    /**
     * @return SrcFrom
     */	
	public String getSrcFrom() {
		return this.srcFrom;
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


}