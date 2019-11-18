package cn.com.yusys.yusp.domain.msg.dvpmsgpush;

public class DVPMsgPushReq {
	private String bizDate;
	private String tradeId;
	private String settleOrderId;
	private String settleDate;
	private String tradeDate;
	private String inputOperId;
	private String bondCode;
	private String settleAmt;
	private String srcFrom;
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getSettleOrderId() {
		return settleOrderId;
	}
	public void setSettleOrderId(String settleOrderId) {
		this.settleOrderId = settleOrderId;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getInputOperId() {
		return inputOperId;
	}
	public void setInputOperId(String inputOperId) {
		this.inputOperId = inputOperId;
	}
	public String getBondCode() {
		return bondCode;
	}
	public void setBondCode(String bondCode) {
		this.bondCode = bondCode;
	}
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getSrcFrom() {
		return srcFrom;
	}
	public void setSrcFrom(String srcFrom) {
		this.srcFrom = srcFrom;
	}

}
