package com.sp3.mvc.enums;

public enum OrderItemStatusEnum {
	ORDERED("ORDERED"),
	PAID("PAID"),
	DELIVERED("DELIVERED");
	
	String orderStatus;
	
	OrderItemStatusEnum(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public static OrderItemStatusEnum getEnumByValue(String addrType) {
		for(OrderItemStatusEnum val : OrderItemStatusEnum.values()) {
			if(val.toString().equals(addrType)){
				return val;
			}
		}
        return null;
    }

}
