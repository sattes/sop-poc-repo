package com.sp3.mvc.enums;

public enum OrderStatusEnum {
	ORDERED("ORDERED"),
	PAID("PAID"),
	DELIVERED("DELIVERED");
	
	String orderStatus;
	
	OrderStatusEnum(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public static OrderStatusEnum getEnumByValue(String addrType) {
		for(OrderStatusEnum val : OrderStatusEnum.values()) {
			if(val.toString().equals(addrType)){
				return val;
			}
		}
        return null;
    }
}
