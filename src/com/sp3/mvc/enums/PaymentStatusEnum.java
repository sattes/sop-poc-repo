package com.sp3.mvc.enums;

public enum PaymentStatusEnum {
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE"),
	PENDING("PENDING");
	
	String paymentStatus;
	
	PaymentStatusEnum(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public static PaymentStatusEnum getEnumByValue(String paymentStatus) {
		for(PaymentStatusEnum val : PaymentStatusEnum.values()) {
			if(val.toString().equals(paymentStatus)){
				return val;
			}
		}
        return null;
    }

}
