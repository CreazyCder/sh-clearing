package cn.com.yusys.yusp.domain;

import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "CASH_SETTLE_ORDER")
public class CashSettleOrder extends BaseDomain implements Serializable {
    /**
     * 流水号（PK）
     */
    @Id
    @Column(name = "SERIAL_NUM")
    private String serialNum;

    /**
     * 交易编号
     */
    @Column(name = "TRADE_ID")
    private String tradeId;

    /**
     * 款指令编号（FK）
     */
    @Column(name = "CASH_SETTLE_ID")
    private String cashSettleId;

    /**
     * 资金账户代码
     */
    @Column(name = "CASH_ACCOUNT")
    private String cashAccount;

    /**
     * 资金账户简称
     */
    @Column(name = "CASH_ACCOUNT_NAME")
    private String cashAccountName;

    /**
     * 借方参与者代码
     */
    @Column(name = "DEBIT_MEM_ID")
    private String debitMemId;

    /**
     * 借方参与者简称
     */
    @Column(name = "DEBIT_MEM_NAME")
    private String debitMemName;

    /**
     * 借方持有人账号
     */
    @Column(name = "DEBIT_HOLDER_ACCOUNT")
    private String debitHolderAccount;

    /**
     * 借方持有人账号简称
     */
    @Column(name = "DEBIT_HOLDER_ACCOUNT_NAME")
    private String debitHolderAccountName;

    /**
     * 资金借方科目
     */
    @Column(name = "CASH_DEBIT_TITLE")
    private String cashDebitTitle;

    /**
     * 贷方参与者代码
     */
    @Column(name = "CREDIT_MEM_ID")
    private String creditMemId;

    /**
     * 贷方参与者简称
     */
    @Column(name = "CREDIT_MEM_NAME")
    private String creditMemName;

    /**
     * 贷方持有人账号
     */
    @Column(name = "CREDIT_HOLDER_ACCOUNT")
    private String creditHolderAccount;

    /**
     * 贷方持有人账号简称
     */
    @Column(name = "CREDIT_HOLDER_ACCOUNT_NAME")
    private String creditHolderAccountName;

    /**
     * 资金贷方科目
     */
    @Column(name = "CASH_CREDIT_TITLE")
    private String cashCreditTitle;

    /**
     * 结算款金额
     */
    @Column(name = "CASH_SETTLE_AMT")
    private BigDecimal cashSettleAmt;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TM")
    private String updateTm;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 资金处理状态
     */
    @Column(name = "CASH_PROC_STATUS")
    private String cashProcStatus;

    @Column(name = "SETTLE_ORDER_ID")
    private String settleOrderId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取流水号（PK）
     *
     * @return SERIAL_NUM - 流水号（PK）
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 设置流水号（PK）
     *
     * @param serialNum 流水号（PK）
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    /**
     * 获取交易编号
     *
     * @return TRADE_ID - 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置交易编号
     *
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 获取款指令编号（FK）
     *
     * @return CASH_SETTLE_ID - 款指令编号（FK）
     */
    public String getCashSettleId() {
        return cashSettleId;
    }

    /**
     * 设置款指令编号（FK）
     *
     * @param cashSettleId 款指令编号（FK）
     */
    public void setCashSettleId(String cashSettleId) {
        this.cashSettleId = cashSettleId == null ? null : cashSettleId.trim();
    }

    /**
     * 获取资金账户代码
     *
     * @return CASH_ACCOUNT - 资金账户代码
     */
    public String getCashAccount() {
        return cashAccount;
    }

    /**
     * 设置资金账户代码
     *
     * @param cashAccount 资金账户代码
     */
    public void setCashAccount(String cashAccount) {
        this.cashAccount = cashAccount == null ? null : cashAccount.trim();
    }

    /**
     * 获取资金账户简称
     *
     * @return CASH_ACCOUNT_NAME - 资金账户简称
     */
    public String getCashAccountName() {
        return cashAccountName;
    }

    /**
     * 设置资金账户简称
     *
     * @param cashAccountName 资金账户简称
     */
    public void setCashAccountName(String cashAccountName) {
        this.cashAccountName = cashAccountName == null ? null : cashAccountName.trim();
    }

    /**
     * 获取借方参与者代码
     *
     * @return DEBIT_MEM_ID - 借方参与者代码
     */
    public String getDebitMemId() {
        return debitMemId;
    }

    /**
     * 设置借方参与者代码
     *
     * @param debitMemId 借方参与者代码
     */
    public void setDebitMemId(String debitMemId) {
        this.debitMemId = debitMemId == null ? null : debitMemId.trim();
    }

    /**
     * 获取借方参与者简称
     *
     * @return DEBIT_MEM_NAME - 借方参与者简称
     */
    public String getDebitMemName() {
        return debitMemName;
    }

    /**
     * 设置借方参与者简称
     *
     * @param debitMemName 借方参与者简称
     */
    public void setDebitMemName(String debitMemName) {
        this.debitMemName = debitMemName == null ? null : debitMemName.trim();
    }

    /**
     * 获取借方持有人账号
     *
     * @return DEBIT_HOLDER_ACCOUNT - 借方持有人账号
     */
    public String getDebitHolderAccount() {
        return debitHolderAccount;
    }

    /**
     * 设置借方持有人账号
     *
     * @param debitHolderAccount 借方持有人账号
     */
    public void setDebitHolderAccount(String debitHolderAccount) {
        this.debitHolderAccount = debitHolderAccount == null ? null : debitHolderAccount.trim();
    }

    /**
     * 获取借方持有人账号简称
     *
     * @return DEBIT_HOLDER_ACCOUNT_NAME - 借方持有人账号简称
     */
    public String getDebitHolderAccountName() {
        return debitHolderAccountName;
    }

    /**
     * 设置借方持有人账号简称
     *
     * @param debitHolderAccountName 借方持有人账号简称
     */
    public void setDebitHolderAccountName(String debitHolderAccountName) {
        this.debitHolderAccountName = debitHolderAccountName == null ? null : debitHolderAccountName.trim();
    }

    /**
     * 获取资金借方科目
     *
     * @return CASH_DEBIT_TITLE - 资金借方科目
     */
    public String getCashDebitTitle() {
        return cashDebitTitle;
    }

    /**
     * 设置资金借方科目
     *
     * @param cashDebitTitle 资金借方科目
     */
    public void setCashDebitTitle(String cashDebitTitle) {
        this.cashDebitTitle = cashDebitTitle == null ? null : cashDebitTitle.trim();
    }

    /**
     * 获取贷方参与者代码
     *
     * @return CREDIT_MEM_ID - 贷方参与者代码
     */
    public String getCreditMemId() {
        return creditMemId;
    }

    /**
     * 设置贷方参与者代码
     *
     * @param creditMemId 贷方参与者代码
     */
    public void setCreditMemId(String creditMemId) {
        this.creditMemId = creditMemId == null ? null : creditMemId.trim();
    }

    /**
     * 获取贷方参与者简称
     *
     * @return CREDIT_MEM_NAME - 贷方参与者简称
     */
    public String getCreditMemName() {
        return creditMemName;
    }

    /**
     * 设置贷方参与者简称
     *
     * @param creditMemName 贷方参与者简称
     */
    public void setCreditMemName(String creditMemName) {
        this.creditMemName = creditMemName == null ? null : creditMemName.trim();
    }

    /**
     * 获取贷方持有人账号
     *
     * @return CREDIT_HOLDER_ACCOUNT - 贷方持有人账号
     */
    public String getCreditHolderAccount() {
        return creditHolderAccount;
    }

    /**
     * 设置贷方持有人账号
     *
     * @param creditHolderAccount 贷方持有人账号
     */
    public void setCreditHolderAccount(String creditHolderAccount) {
        this.creditHolderAccount = creditHolderAccount == null ? null : creditHolderAccount.trim();
    }

    /**
     * 获取贷方持有人账号简称
     *
     * @return CREDIT_HOLDER_ACCOUNT_NAME - 贷方持有人账号简称
     */
    public String getCreditHolderAccountName() {
        return creditHolderAccountName;
    }

    /**
     * 设置贷方持有人账号简称
     *
     * @param creditHolderAccountName 贷方持有人账号简称
     */
    public void setCreditHolderAccountName(String creditHolderAccountName) {
        this.creditHolderAccountName = creditHolderAccountName == null ? null : creditHolderAccountName.trim();
    }

    /**
     * 获取资金贷方科目
     *
     * @return CASH_CREDIT_TITLE - 资金贷方科目
     */
    public String getCashCreditTitle() {
        return cashCreditTitle;
    }

    /**
     * 设置资金贷方科目
     *
     * @param cashCreditTitle 资金贷方科目
     */
    public void setCashCreditTitle(String cashCreditTitle) {
        this.cashCreditTitle = cashCreditTitle == null ? null : cashCreditTitle.trim();
    }

    /**
     * 获取结算款金额
     *
     * @return CASH_SETTLE_AMT - 结算款金额
     */
    public BigDecimal getCashSettleAmt() {
        return cashSettleAmt;
    }

    /**
     * 设置结算款金额
     *
     * @param cashSettleAmt 结算款金额
     */
    public void setCashSettleAmt(BigDecimal cashSettleAmt) {
        this.cashSettleAmt = cashSettleAmt;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TM - 更新时间
     */
    public String getUpdateTm() {
        return updateTm;
    }

    /**
     * 设置更新时间
     *
     * @param updateTm 更新时间
     */
    public void setUpdateTm(String updateTm) {
        this.updateTm = updateTm == null ? null : updateTm.trim();
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取资金处理状态
     *
     * @return CASH_PROC_STATUS - 资金处理状态
     */
    public String getCashProcStatus() {
        return cashProcStatus;
    }

    /**
     * 设置资金处理状态
     *
     * @param cashProcStatus 资金处理状态
     */
    public void setCashProcStatus(String cashProcStatus) {
        this.cashProcStatus = cashProcStatus == null ? null : cashProcStatus.trim();
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CashSettleOrder other = (CashSettleOrder) that;
        return (this.getSerialNum() == null ? other.getSerialNum() == null : this.getSerialNum().equals(other.getSerialNum()))
            && (this.getTradeId() == null ? other.getTradeId() == null : this.getTradeId().equals(other.getTradeId()))
            && (this.getCashSettleId() == null ? other.getCashSettleId() == null : this.getCashSettleId().equals(other.getCashSettleId()))
            && (this.getCashAccount() == null ? other.getCashAccount() == null : this.getCashAccount().equals(other.getCashAccount()))
            && (this.getCashAccountName() == null ? other.getCashAccountName() == null : this.getCashAccountName().equals(other.getCashAccountName()))
            && (this.getDebitMemId() == null ? other.getDebitMemId() == null : this.getDebitMemId().equals(other.getDebitMemId()))
            && (this.getDebitMemName() == null ? other.getDebitMemName() == null : this.getDebitMemName().equals(other.getDebitMemName()))
            && (this.getDebitHolderAccount() == null ? other.getDebitHolderAccount() == null : this.getDebitHolderAccount().equals(other.getDebitHolderAccount()))
            && (this.getDebitHolderAccountName() == null ? other.getDebitHolderAccountName() == null : this.getDebitHolderAccountName().equals(other.getDebitHolderAccountName()))
            && (this.getCashDebitTitle() == null ? other.getCashDebitTitle() == null : this.getCashDebitTitle().equals(other.getCashDebitTitle()))
            && (this.getCreditMemId() == null ? other.getCreditMemId() == null : this.getCreditMemId().equals(other.getCreditMemId()))
            && (this.getCreditMemName() == null ? other.getCreditMemName() == null : this.getCreditMemName().equals(other.getCreditMemName()))
            && (this.getCreditHolderAccount() == null ? other.getCreditHolderAccount() == null : this.getCreditHolderAccount().equals(other.getCreditHolderAccount()))
            && (this.getCreditHolderAccountName() == null ? other.getCreditHolderAccountName() == null : this.getCreditHolderAccountName().equals(other.getCreditHolderAccountName()))
            && (this.getCashCreditTitle() == null ? other.getCashCreditTitle() == null : this.getCashCreditTitle().equals(other.getCashCreditTitle()))
            && (this.getCashSettleAmt() == null ? other.getCashSettleAmt() == null : this.getCashSettleAmt().equals(other.getCashSettleAmt()))
            && (this.getUpdateTm() == null ? other.getUpdateTm() == null : this.getUpdateTm().equals(other.getUpdateTm()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCashProcStatus() == null ? other.getCashProcStatus() == null : this.getCashProcStatus().equals(other.getCashProcStatus()))
            && (this.getSettleOrderId() == null ? other.getSettleOrderId() == null : this.getSettleOrderId().equals(other.getSettleOrderId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSerialNum() == null) ? 0 : getSerialNum().hashCode());
        result = prime * result + ((getTradeId() == null) ? 0 : getTradeId().hashCode());
        result = prime * result + ((getCashSettleId() == null) ? 0 : getCashSettleId().hashCode());
        result = prime * result + ((getCashAccount() == null) ? 0 : getCashAccount().hashCode());
        result = prime * result + ((getCashAccountName() == null) ? 0 : getCashAccountName().hashCode());
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
        result = prime * result + ((getUpdateTm() == null) ? 0 : getUpdateTm().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCashProcStatus() == null) ? 0 : getCashProcStatus().hashCode());
        result = prime * result + ((getSettleOrderId() == null) ? 0 : getSettleOrderId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialNum=").append(serialNum);
        sb.append(", tradeId=").append(tradeId);
        sb.append(", cashSettleId=").append(cashSettleId);
        sb.append(", cashAccount=").append(cashAccount);
        sb.append(", cashAccountName=").append(cashAccountName);
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
        sb.append(", updateTm=").append(updateTm);
        sb.append(", remark=").append(remark);
        sb.append(", cashProcStatus=").append(cashProcStatus);
        sb.append(", settleOrderId=").append(settleOrderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}