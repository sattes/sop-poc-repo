package com.sp3.mvc.models;

public class CardInfo {
	
	private Long cardNumber;
	private String nameOnCard;
	private String expiryDate;
	private Integer cvvNumber;
	private Integer expiryMonth;
	private Integer expiryYear;
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(Integer cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	public Integer getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(Integer expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public Integer getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}
	

}
