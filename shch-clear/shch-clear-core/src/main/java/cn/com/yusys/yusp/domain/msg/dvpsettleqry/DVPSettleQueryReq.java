package cn.com.yusys.yusp.domain.msg.dvpsettleqry;

/**
 * 清算查询.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public class DVPSettleQueryReq {
    private String tradeDate;
    private String tradeId;
    private String settleOrderId;
    private String settleDate;
    private String srcFrom;
    private String memCode;
    private String holderAccount;
    private String bondCode;

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
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

    public String getSrcFrom() {
        return srcFrom;
    }

    public void setSrcFrom(String srcFrom) {
        this.srcFrom = srcFrom;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getHolderAccount() {
        return holderAccount;
    }

    public void setHolderAccount(String holderAccount) {
        this.holderAccount = holderAccount;
    }

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

}
