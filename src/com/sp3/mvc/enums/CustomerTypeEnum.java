package com.sp3.mvc.enums;

public enum CustomerTypeEnum {
	REGULAR("REGULAR"),
	PREFERRED("PREFERRED"),
	CORPORATE("CORPORATE");
	
	String custType;
	
	CustomerTypeEnum(String custType) {
		this.custType = custType;
	}
	
	public static CustomerTypeEnum getEnumByValue(String custType) {
		for(CustomerTypeEnum val : CustomerTypeEnum.values()) {
			if(val.toString().equals(custType)){
				return val;
			}
		}
        return null;
    }
}
