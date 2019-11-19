package cn.com.yusys.yusp.service;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class MoneyDto {
	@NotEmpty
	private BigDecimal currencyAmt;
	@NotEmpty
	private String memCode;
	@NotEmpty
	private String holderAccount;
	@NotEmpty
	private String titleCode;
	@NotEmpty
	private String cashAccount;
	public BigDecimal getCurrencyAmt() {
		return currencyAmt;
	}
	public void setCurrencyAmt(BigDecimal currencyAmt) {
		this.currencyAmt = currencyAmt;
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
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getCashAccount() {
		return cashAccount;
	}
	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}
	
	private static final long serialVersionUID = 2L;
}
