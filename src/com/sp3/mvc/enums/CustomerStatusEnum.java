package com.sp3.mvc.enums;

public enum CustomerStatusEnum {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE");
	
	String custStatus;
	
	CustomerStatusEnum(String custStatus) {
		this.custStatus = custStatus;
	}
	
	public static CustomerStatusEnum getEnumByValue(String custStatus) {
		for(CustomerStatusEnum val : CustomerStatusEnum.values()) {
			if(val.toString().equals(custStatus)){
				return val;
			}
		}
        return null;
    }
}
