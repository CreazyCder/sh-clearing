package cn.com.yusys.yusp.domain.msg.settlenotify;

import lombok.Data;

@Data
public class BondSettleNotifyReq {
	private String settleOrderId;
	private String tradeId;
	private String bondSettleId;
	private String bondProcStatus;
	private String retMsg;
}
