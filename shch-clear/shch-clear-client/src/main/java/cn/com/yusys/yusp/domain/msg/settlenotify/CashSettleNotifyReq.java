package cn.com.yusys.yusp.domain.msg.settlenotify;


public class CashSettleNotifyReq {
	private String settleOrderId;
	private String tradeId;
	private String cashSettleId;
	private String cashProcStatus;
	private String retMsg;
	public String getSettleOrderId() {
		return settleOrderId;
	}
	public void setSettleOrderId(String settleOrderId) {
		this.settleOrderId = settleOrderId;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getCashSettleId() {
		return cashSettleId;
	}
	public void setCashSettleId(String cashSettleId) {
		this.cashSettleId = cashSettleId;
	}
	public String getCashProcStatus() {
		return cashProcStatus;
	}
	public void setCashProcStatus(String cashProcStatus) {
		this.cashProcStatus = cashProcStatus;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

}
