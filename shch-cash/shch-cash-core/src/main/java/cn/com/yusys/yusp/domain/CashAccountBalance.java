package cn.com.yusys.yusp.domain;

import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "CASH_ACCOUNT_BALANCE")
public class CashAccountBalance extends BaseDomain implements Serializable {
    /**
     * 参与者代码
     */
    @Id
    @Column(name = "MEM_CODE")
    private String memCode;

    private String serialNum;
    
    /**
     * 持有人账号
     */
    @Id
    @Column(name = "HOLDER_ACCOUNT")
    private String holderAccount;

    /**
     * 科目代码
     */
    @Id
    @Column(name = "TITLE_CODE")
    private String titleCode;

    /**
     * 资金账户代码
     */
    @Id
    @Column(name = "CASH_ACCOUNT")
    private String cashAccount;

    /**
     * 营业日期
     */
    @Column(name = "BIZ_DATE")
    private String bizDate;

    /**
     * 参与者简称
     */
    @Column(name = "MEM_NAME")
    private String memName;

    /**
     * 持有人账号简称
     */
    @Column(name = "HOLDER_ACCOUNT_NAME")
    private String holderAccountName;

    /**
     * 科目名称
     */
    @Column(name = "TITLE_NAME")
    private String titleName;

    /**
     * 当前余额
     */
    @Column(name = "CURRENCY_AMT")
    private BigDecimal currencyAmt;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TM")
    private String updateTm;

    /**
     * 资金账户简称
     */
    @Column(name = "CASH_ACCOUNT_NAME")
    private String cashAccountName;

    /**
     * 交易代码
     */
    @Column(name = "MSG_TYPE")
    private String msgType;

    
    public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	private static final long serialVersionUID = 1L;

    /**
     * 获取参与者代码
     *
     * @return MEM_CODE - 参与者代码
     */
    public String getMemCode() {
        return memCode;
    }

    /**
     * 设置参与者代码
     *
     * @param memCode 参与者代码
     */
    public void setMemCode(String memCode) {
        this.memCode = memCode == null ? null : memCode.trim();
    }

    /**
     * 获取持有人账号
     *
     * @return HOLDER_ACCOUNT - 持有人账号
     */
    public String getHolderAccount() {
        return holderAccount;
    }

    /**
     * 设置持有人账号
     *
     * @param holderAccount 持有人账号
     */
    public void setHolderAccount(String holderAccount) {
        this.holderAccount = holderAccount == null ? null : holderAccount.trim();
    }

    /**
     * 获取科目代码
     *
     * @return TITLE_CODE - 科目代码
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * 设置科目代码
     *
     * @param titleCode 科目代码
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode == null ? null : titleCode.trim();
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
     * 获取营业日期
     *
     * @return BIZ_DATE - 营业日期
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * 设置营业日期
     *
     * @param bizDate 营业日期
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate == null ? null : bizDate.trim();
    }

    /**
     * 获取参与者简称
     *
     * @return MEM_NAME - 参与者简称
     */
    public String getMemName() {
        return memName;
    }

    /**
     * 设置参与者简称
     *
     * @param memName 参与者简称
     */
    public void setMemName(String memName) {
        this.memName = memName == null ? null : memName.trim();
    }

    /**
     * 获取持有人账号简称
     *
     * @return HOLDER_ACCOUNT_NAME - 持有人账号简称
     */
    public String getHolderAccountName() {
        return holderAccountName;
    }

    /**
     * 设置持有人账号简称
     *
     * @param holderAccountName 持有人账号简称
     */
    public void setHolderAccountName(String holderAccountName) {
        this.holderAccountName = holderAccountName == null ? null : holderAccountName.trim();
    }

    /**
     * 获取科目名称
     *
     * @return TITLE_NAME - 科目名称
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * 设置科目名称
     *
     * @param titleName 科目名称
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName == null ? null : titleName.trim();
    }

    /**
     * 获取当前余额
     *
     * @return CURRENCY_AMT - 当前余额
     */
    public BigDecimal getCurrencyAmt() {
        return currencyAmt;
    }

    /**
     * 设置当前余额
     *
     * @param currencyAmt 当前余额
     */
    public void setCurrencyAmt(BigDecimal currencyAmt) {
        this.currencyAmt = currencyAmt;
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
        CashAccountBalance other = (CashAccountBalance) that;
        return (this.getMemCode() == null ? other.getMemCode() == null : this.getMemCode().equals(other.getMemCode()))
            && (this.getHolderAccount() == null ? other.getHolderAccount() == null : this.getHolderAccount().equals(other.getHolderAccount()))
            && (this.getTitleCode() == null ? other.getTitleCode() == null : this.getTitleCode().equals(other.getTitleCode()))
            && (this.getCashAccount() == null ? other.getCashAccount() == null : this.getCashAccount().equals(other.getCashAccount()))
            && (this.getBizDate() == null ? other.getBizDate() == null : this.getBizDate().equals(other.getBizDate()))
            && (this.getMemName() == null ? other.getMemName() == null : this.getMemName().equals(other.getMemName()))
            && (this.getHolderAccountName() == null ? other.getHolderAccountName() == null : this.getHolderAccountName().equals(other.getHolderAccountName()))
            && (this.getTitleName() == null ? other.getTitleName() == null : this.getTitleName().equals(other.getTitleName()))
            && (this.getCurrencyAmt() == null ? other.getCurrencyAmt() == null : this.getCurrencyAmt().equals(other.getCurrencyAmt()))
            && (this.getUpdateTm() == null ? other.getUpdateTm() == null : this.getUpdateTm().equals(other.getUpdateTm()))
            && (this.getCashAccountName() == null ? other.getCashAccountName() == null : this.getCashAccountName().equals(other.getCashAccountName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMemCode() == null) ? 0 : getMemCode().hashCode());
        result = prime * result + ((getHolderAccount() == null) ? 0 : getHolderAccount().hashCode());
        result = prime * result + ((getTitleCode() == null) ? 0 : getTitleCode().hashCode());
        result = prime * result + ((getCashAccount() == null) ? 0 : getCashAccount().hashCode());
        result = prime * result + ((getBizDate() == null) ? 0 : getBizDate().hashCode());
        result = prime * result + ((getMemName() == null) ? 0 : getMemName().hashCode());
        result = prime * result + ((getHolderAccountName() == null) ? 0 : getHolderAccountName().hashCode());
        result = prime * result + ((getTitleName() == null) ? 0 : getTitleName().hashCode());
        result = prime * result + ((getCurrencyAmt() == null) ? 0 : getCurrencyAmt().hashCode());
        result = prime * result + ((getUpdateTm() == null) ? 0 : getUpdateTm().hashCode());
        result = prime * result + ((getCashAccountName() == null) ? 0 : getCashAccountName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", memCode=").append(memCode);
        sb.append(", holderAccount=").append(holderAccount);
        sb.append(", titleCode=").append(titleCode);
        sb.append(", cashAccount=").append(cashAccount);
        sb.append(", bizDate=").append(bizDate);
        sb.append(", memName=").append(memName);
        sb.append(", holderAccountName=").append(holderAccountName);
        sb.append(", titleName=").append(titleName);
        sb.append(", currencyAmt=").append(currencyAmt);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", cashAccountName=").append(cashAccountName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
    
}