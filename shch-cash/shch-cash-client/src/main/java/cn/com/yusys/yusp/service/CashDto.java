package cn.com.yusys.yusp.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CashDto  implements Serializable {
    @NotEmpty
    private String tradeId;

    @NotEmpty
    @Column(name = "SETTLE_ORDER_ID")
    private String settleOrderId;

    @Column(name = "SETTLE_DATE")
    @NotEmpty
    private String settleDate;

    @Column(name = "DEBIT_MEM_ID")
    @NotEmpty
    private String debitMemId;

    @Column(name = "DEBIT_MEM_NAME")
    @NotEmpty
    private String debitMemName;

    @Column(name = "DEBIT_HOLDER_ACCOUNT")
    @NotEmpty
    private String debitHolderAccount;

    @Column(name = "DEBIT_HOLDER_ACCOUNT_NAME")
    @NotEmpty
    private String debitHolderAccountName;

    @Column(name = "CASH_DEBIT_TITLE")
    @NotEmpty
    @Length(min=1 ,max=4)
    private String cashDebitTitle;

    @Column(name = "CREDIT_MEM_ID")
    @NotEmpty
    private String creditMemId;

    @Column(name = "CREDIT_MEM_NAME")
    @NotEmpty
    private String creditMemName;

    @Column(name = "CREDIT_HOLDER_ACCOUNT")
    @NotEmpty
    private String creditHolderAccount;

    @Column(name = "CREDIT_HOLDER_ACCOUNT_NAME")
    @NotEmpty
    private String creditHolderAccountName;

    @Column(name = "CASH_CREDIT_TITLE")
    @NotEmpty
    @Length(min=1 ,max=4)
    private String cashCreditTitle;

    @Column(name = "CASH_SETTLE_AMT")
    private BigDecimal cashSettleAmt;

    @Column(name = "REMARK")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return TRADE_ID
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * @param tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * @return SETTLE_ORDER_ID
     */
    public String getSettleOrderId() {
        return settleOrderId;
    }

    /**
     * @param settleOrderId
     */
    public void setSettleOrderId(String settleOrderId) {
        this.settleOrderId = settleOrderId == null ? null : settleOrderId.trim();
    }

    /**
     * @return SETTLE_DATE
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * @param settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    /**
     * @return DEBIT_MEM_ID
     */
    public String getDebitMemId() {
        return debitMemId;
    }

    /**
     * @param debitMemId
     */
    public void setDebitMemId(String debitMemId) {
        this.debitMemId = debitMemId == null ? null : debitMemId.trim();
    }

    /**
     * @return DEBIT_MEM_NAME
     */
    public String getDebitMemName() {
        return debitMemName;
    }

    /**
     * @param debitMemName
     */
    public void setDebitMemName(String debitMemName) {
        this.debitMemName = debitMemName == null ? null : debitMemName.trim();
    }

    /**
     * @return DEBIT_HOLDER_ACCOUNT
     */
    public String getDebitHolderAccount() {
        return debitHolderAccount;
    }

    /**
     * @param debitHolderAccount
     */
    public void setDebitHolderAccount(String debitHolderAccount) {
        this.debitHolderAccount = debitHolderAccount == null ? null : debitHolderAccount.trim();
    }

    /**
     * @return DEBIT_HOLDER_ACCOUNT_NAME
     */
    public String getDebitHolderAccountName() {
        return debitHolderAccountName;
    }

    /**
     * @param debitHolderAccountName
     */
    public void setDebitHolderAccountName(String debitHolderAccountName) {
        this.debitHolderAccountName = debitHolderAccountName == null ? null : debitHolderAccountName.trim();
    }

    /**
     * @return CASH_DEBIT_TITLE
     */
    public String getCashDebitTitle() {
        return cashDebitTitle;
    }

    /**
     * @param cashDebitTitle
     */
    public void setCashDebitTitle(String cashDebitTitle) {
        this.cashDebitTitle = cashDebitTitle == null ? null : cashDebitTitle.trim();
    }

    /**
     * @return CREDIT_MEM_ID
     */
    public String getCreditMemId() {
        return creditMemId;
    }

    /**
     * @param creditMemId
     */
    public void setCreditMemId(String creditMemId) {
        this.creditMemId = creditMemId == null ? null : creditMemId.trim();
    }

    /**
     * @return CREDIT_MEM_NAME
     */
    public String getCreditMemName() {
        return creditMemName;
    }

    /**
     * @param creditMemName
     */
    public void setCreditMemName(String creditMemName) {
        this.creditMemName = creditMemName == null ? null : creditMemName.trim();
    }

    /**
     * @return CREDIT_HOLDER_ACCOUNT
     */
    public String getCreditHolderAccount() {
        return creditHolderAccount;
    }

    /**
     * @param creditHolderAccount
     */
    public void setCreditHolderAccount(String creditHolderAccount) {
        this.creditHolderAccount = creditHolderAccount == null ? null : creditHolderAccount.trim();
    }

    /**
     * @return CREDIT_HOLDER_ACCOUNT_NAME
     */
    public String getCreditHolderAccountName() {
        return creditHolderAccountName;
    }

    /**
     * @param creditHolderAccountName
     */
    public void setCreditHolderAccountName(String creditHolderAccountName) {
        this.creditHolderAccountName = creditHolderAccountName == null ? null : creditHolderAccountName.trim();
    }

    /**
     * @return CASH_CREDIT_TITLE
     */
    public String getCashCreditTitle() {
        return cashCreditTitle;
    }

    /**
     * @param cashCreditTitle
     */
    public void setCashCreditTitle(String cashCreditTitle) {
        this.cashCreditTitle = cashCreditTitle == null ? null : cashCreditTitle.trim();
    }


    public BigDecimal getCashSettleAmt() {
		return cashSettleAmt;
	}

	public void setCashSettleAmt(BigDecimal cashSettleAmt) {
		this.cashSettleAmt = cashSettleAmt;
	}

	/**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTradeId() == null) ? 0 : getTradeId().hashCode());
        result = prime * result + ((getSettleOrderId() == null) ? 0 : getSettleOrderId().hashCode());
        result = prime * result + ((getSettleDate() == null) ? 0 : getSettleDate().hashCode());
        result = prime * result + ((getDebitMemId() == null) ? 0 : getDebitMemId().hashCode());
        result = prime * result + ((getDebitMemName() == null) ? 0 : getDebitMemName().hashCode());
        result = prime * result + ((getDebitHolderAccount() == null) ? 0 : getDebitHolderAccount().hashCode());
        result = prime * result + ((getDebitHolderAccountName() == null) ? 0 : getDebitHolderAccountName().hashCode());
        result = prime * result + ((getCashDebitTitle() == null) ? 0 : getCashDebitTitle().hashCode());
        result = prime * result + ((getCreditMemId() == null) ? 0 : getCreditMemId().hashCode());
        result = prime * result + ((getCreditMemName() == null) ? 0 : getCreditMemName().hashCode());
        result = prime * result + ((getCreditHolderAccount() == null) ? 0 : getCreditHolderAccount().hashCode());
        result = prime * result + ((getCreditHolderAccountName() == null) ? 0 : getCreditHolderAccountName().hashCode());
        result = prime * result + ((getCashCreditTitle() == null) ? 0 : getCashCreditTitle().hashCode());
        result = prime * result + ((getCashSettleAmt() == null) ? 0 : getCashSettleAmt().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tradeId=").append(tradeId);
        sb.append(", settleOrderId=").append(settleOrderId);
        sb.append(", settleDate=").append(settleDate);
        sb.append(", debitMemId=").append(debitMemId);
        sb.append(", debitMemName=").append(debitMemName);
        sb.append(", debitHolderAccount=").append(debitHolderAccount);
        sb.append(", debitHolderAccountName=").append(debitHolderAccountName);
        sb.append(", cashDebitTitle=").append(cashDebitTitle);
        sb.append(", creditMemId=").append(creditMemId);
        sb.append(", creditMemName=").append(creditMemName);
        sb.append(", creditHolderAccount=").append(creditHolderAccount);
        sb.append(", creditHolderAccountName=").append(creditHolderAccountName);
        sb.append(", cashCreditTitle=").append(cashCreditTitle);
        sb.append(", cashSettleAmt=").append(cashSettleAmt);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}