package cn.com.yusys.yusp.domain.msg.revoke;
/**
 * 撤消申请
 * @author boip
 *
 */
public class DVPRevokeReq {

	// 交易编号
	private String tradeId;
	// 原交易编号
	private String origTradeId;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getOrigTradeId() {
		return origTradeId;
	}
	public void setOrigTradeId(String origTradeId) {
		this.origTradeId = origTradeId;
	}
	@Override
	public String toString() {
		return "DVPRevokeReq [tradeId=" + tradeId + ", origTradeId=" + origTradeId + "]";
	}
}
