package cn.com.yusys.yusp.domain.msg.settlenotify;


public class BondSettleNotifyReq {
	private String settleOrderId;
	private String tradeId;
	private String bondSettleId;
	private String bondProcStatus;
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
	public String getBondSettleId() {
		return bondSettleId;
	}
	public void setBondSettleId(String bondSettleId) {
		this.bondSettleId = bondSettleId;
	}
	public String getBondProcStatus() {
		return bondProcStatus;
	}
	public void setBondProcStatus(String bondProcStatus) {
		this.bondProcStatus = bondProcStatus;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
