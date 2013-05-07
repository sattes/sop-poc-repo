package com.sp3.mvc.enums;

public enum TransactionTypeEnum {
	DebitCard("DebitCard"),
	CreditCard("CreditCard"),
	NetBanking("NetBanking"),
	ECS("ECS"),
	CashOnDelivery("CashOnDelivery");
	
	String txnType;
	
	TransactionTypeEnum(String txnType) {
		this.txnType = txnType;
	}
	
	public static TransactionTypeEnum getEnumByValue(String txnType) {
		for(TransactionTypeEnum val : TransactionTypeEnum.values()) {
			if(val.toString().equals(txnType)){
				return val;
			}
		}
        return null;
    }
}
