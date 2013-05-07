package com.sp3.mvc.enums;

public enum AddressTypeEnum {
	
	PERMANENT("PERMANENT"),
	SHIPPING("SHIPPING"),
	BILLING("BILLING");
	
	String addrType;
	
	AddressTypeEnum(String addrType) {
		this.addrType = addrType;
	}
	
	public String getValue() {
		return addrType;
	}
	
	public static AddressTypeEnum getEnumByValue(String addrType) {
		for(AddressTypeEnum val : AddressTypeEnum.values()) {
			if(val.toString().equals(addrType)){
				return val;
			}
		}
        return null;
    }

}
