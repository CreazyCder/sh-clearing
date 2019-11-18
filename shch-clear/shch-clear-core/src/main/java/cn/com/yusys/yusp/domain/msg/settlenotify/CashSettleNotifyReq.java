package cn.com.yusys.yusp.domain.msg.settlenotify;

import lombok.Data;

@Data
public class CashSettleNotifyReq {
	private String settleOrderId;
	private String tradeId;
	private String cashSettleId;
	private String cashProcStatus;
	private String retMsg;

}
