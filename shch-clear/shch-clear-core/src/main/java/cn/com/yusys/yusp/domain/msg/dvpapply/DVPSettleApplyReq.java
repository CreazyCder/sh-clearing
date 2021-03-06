package cn.com.yusys.yusp.domain.msg.dvpapply;

import cn.com.yusys.yusp.commons.util.BeanUtil;
import cn.com.yusys.yusp.constant.BondSettleStatusEnum;
import cn.com.yusys.yusp.constant.CashSettleStatusEnum;
import cn.com.yusys.yusp.constant.SettleStatusEnum;
import cn.com.yusys.yusp.domain.SettleOrder;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 生成交易.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public class DVPSettleApplyReq implements Serializable {
    private String tradeId;
    private String tradeDate;
    private String bondCode;
    private String bondName;
    private String bondType;
    private String bondFaceAmt;
    private String settleDate;
    private String settleType;
    private String settleAmt;
    private String buyerMemCode;
    private String buyerMemName;
    private String buyerHolderAccount;
    private String buyerHolderAccountName;
    private String sellerMemCode;
    private String sellerMemName;
    private String sellerHolderAccount;
    private String sellerHolderAccountName;

    @Pattern(regexp = "0|1", message = "只接受指定来源的数据,客户端，直联接口")
    private String srcFrom;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getBondFaceAmt() {
        return bondFaceAmt;
    }

    public void setBondFaceAmt(String bondFaceAmt) {
        this.bondFaceAmt = bondFaceAmt;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(String settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getBuyerMemCode() {
        return buyerMemCode;
    }

    public void setBuyerMemCode(String buyerMemCode) {
        this.buyerMemCode = buyerMemCode;
    }

    public String getBuyerMemName() {
        return buyerMemName;
    }

    public void setBuyerMemName(String buyerMemName) {
        this.buyerMemName = buyerMemName;
    }

    public String getBuyerHolderAccount() {
        return buyerHolderAccount;
    }

    public void setBuyerHolderAccount(String buyerHolderAccount) {
        this.buyerHolderAccount = buyerHolderAccount;
    }

    public String getBuyerHolderAccountName() {
        return buyerHolderAccountName;
    }

    public void setBuyerHolderAccountName(String buyerHolderAccountName) {
        this.buyerHolderAccountName = buyerHolderAccountName;
    }

    public String getSellerMemCode() {
        return sellerMemCode;
    }

    public void setSellerMemCode(String sellerMemCode) {
        this.sellerMemCode = sellerMemCode;
    }

    public String getSellerMemName() {
        return sellerMemName;
    }

    public void setSellerMemName(String sellerMemName) {
        this.sellerMemName = sellerMemName;
    }

    public String getSellerHolderAccount() {
        return sellerHolderAccount;
    }

    public void setSellerHolderAccount(String sellerHolderAccount) {
        this.sellerHolderAccount = sellerHolderAccount;
    }

    public String getSellerHolderAccountName() {
        return sellerHolderAccountName;
    }

    public void setSellerHolderAccountName(String sellerHolderAccountName) {
        this.sellerHolderAccountName = sellerHolderAccountName;
    }

    public String getSrcFrom() {
        return srcFrom;
    }

    public void setSrcFrom(String srcFrom) {
        this.srcFrom = srcFrom;
    }

    /**
     * 将请求参数转化为数据
     *
     * @return
     */
    public SettleOrder toDomain() {
        SettleOrder settleOrder = new SettleOrder();
        BeanUtil.beanCopy(this, settleOrder);
        // 默认值为待生效.直接变成应履行.
        settleOrder.setSettleOrderStatus(SettleStatusEnum.SHOULD_PERFORM.getCode());
        // 默认值为应履行.
        settleOrder.setBondSettleStatus(BondSettleStatusEnum.SHOULD_PERFORM.getCode());
        // 默认值为应履行.
        settleOrder.setCashSettleStatus(CashSettleStatusEnum.SHOULD_PERFORM.getCode());
        return settleOrder;
    }

    @Override
    public String toString() {
        return "DVPSettleApplyReq [tradeId=" + tradeId + ", tradeDate=" + tradeDate + ", bondCode=" + bondCode
                + ", bondName=" + bondName + ", bondType=" + bondType + ", bondFaceAmt=" + bondFaceAmt + ", settleDate="
                + settleDate + ", settleType=" + settleType + ", settleAmt=" + settleAmt + ", buyerMemCode="
                + buyerMemCode + ", buyerMemName=" + buyerMemName + ", buyerHolderAccount=" + buyerHolderAccount
                + ", buyerHolderAccountName=" + buyerHolderAccountName + ", sellerMemCode=" + sellerMemCode
                + ", sellerMemName=" + sellerMemName + ", sellerHolderAccount=" + sellerHolderAccount
                + ", sellerHolderAccountName=" + sellerHolderAccountName + ", srcFrom=" + srcFrom + "]";
    }

}

