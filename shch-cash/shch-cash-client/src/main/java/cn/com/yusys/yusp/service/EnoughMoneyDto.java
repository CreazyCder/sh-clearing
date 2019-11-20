package cn.com.yusys.yusp.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class EnoughMoneyDto implements Serializable {

	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
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
	public String getCurrencyAmt() {
		return currencyAmt;
	}
	public void setCurrencyAmt(String currencyAmt) {
		this.currencyAmt = currencyAmt;
	}
	private String msgType;
    private String serialNum;
    private String memCode;
    private String holderAccount;
    private String titleCode;
    private String cashAccount;
    private String currencyAmt;
}
