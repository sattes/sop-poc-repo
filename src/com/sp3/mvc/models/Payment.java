package com.sp3.mvc.models;

import java.util.Date;

import com.sp3.mvc.enums.CardTypeEnum;
import com.sp3.mvc.enums.GatewayTypeEnum;
import com.sp3.mvc.enums.TransactionTypeEnum;

public class Payment {
	
	private String paymentId;
	private String orderId;
	private Double paymentAmount;
	private String paymentStatus;
	private Date paymentDate;
	private TransactionTypeEnum txnType; //PaymentMode
	private CardTypeEnum cardType;
	private GatewayTypeEnum gatewayType;
	
	private CardInfo cardInfo;
	private DirectDebit directDebit;
	private String selectedNetBankName;
	
	public String getSelectedNetBankName() {
		return selectedNetBankName;
	}
	public void setSelectedNetBankName(String selectedNetBankName) {
		this.selectedNetBankName = selectedNetBankName;
	}
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	public DirectDebit getDirectDebit() {
		return directDebit;
	}
	public void setDirectDebit(DirectDebit directDebit) {
		this.directDebit = directDebit;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public TransactionTypeEnum getTxnType() {
		return txnType;
	}
	public void setTxnType(TransactionTypeEnum txnType) {
		this.txnType = txnType;
	}
	public CardTypeEnum getCardType() {
		return cardType;
	}
	public void setCardType(CardTypeEnum cardType) {
		this.cardType = cardType;
	}
	
	public GatewayTypeEnum getGatewayType() {
		return gatewayType;
	}
	public void setGatewayType(GatewayTypeEnum gatewayType) {
		this.gatewayType = gatewayType;
	}

}
