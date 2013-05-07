package com.sp3.mvc.enums;

public enum GatewayTypeEnum {
	
	VISA("VISA"),
	MASTERCARD("MASTERCARD"),
	AMEX("AMEX");
	
	String gatewayType;
	
	GatewayTypeEnum(String gatewayType) {
		this.gatewayType = gatewayType;
	}
	
	public String getValue() {
		return gatewayType;
	}
	
	public static GatewayTypeEnum getEnumByValue(String gatewayType) {
		for(GatewayTypeEnum val : GatewayTypeEnum.values()) {
			if(val.toString().equals(gatewayType)){
				return val;
			}
		}
        return null;
    }

}
