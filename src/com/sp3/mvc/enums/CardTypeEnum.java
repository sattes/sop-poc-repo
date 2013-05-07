package com.sp3.mvc.enums;

public enum CardTypeEnum {
	
	DEBITCARD("DEBITCARD"),
	CREDITCARD("CREDITCARD");
	
	String cardType;
	
	CardTypeEnum(String cardType) {
		this.cardType = cardType;
	}
	
	public String getValue() {
		return cardType;
	}
	
	public static CardTypeEnum getEnumByValue(String cardType) {
		for(CardTypeEnum val : CardTypeEnum.values()) {
			if(val.toString().equals(cardType)){
				return val;
			}
		}
        return null;
    }

}
