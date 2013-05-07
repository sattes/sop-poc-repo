package com.sp3.mvc.enums;

public enum DiscountTypeEnum {
	REGULAR("REGULAR"),
	PREFERRED("PREFERRED"),
	CORPORATE("CORPORATE");
	
	String discType;
	
	DiscountTypeEnum(String discType) {
		this.discType = discType;
	}
	
	public static DiscountTypeEnum getEnumByValue(String addrType) {
		for(DiscountTypeEnum val : DiscountTypeEnum.values()) {
			if(val.toString().equals(addrType)){
				return val;
			}
		}
        return null;
    }
}
