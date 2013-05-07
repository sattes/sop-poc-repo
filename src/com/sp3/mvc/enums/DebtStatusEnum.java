package com.sp3.mvc.enums;

public enum DebtStatusEnum {
	
	DEBITED("DEBITED"),
	PENDING("PENDING"),
	FAILURE("FAILURE");
	
	String debtStatus;
	
	DebtStatusEnum(String debtStatus) {
		this.debtStatus = debtStatus;
	}
	
	public String getValue() {
		return debtStatus;
	}
	
	public static DebtStatusEnum getEnumByValue(String debtStatus) {
		for(DebtStatusEnum val : DebtStatusEnum.values()) {
			if(val.toString().equals(debtStatus)){
				return val;
			}
		}
        return null;
    }

}
