package com.basedata;

public enum CustomerType {

    Truck(0),Drone(1),Joint(2);

    private Integer customerTypeCode;
    CustomerType(Integer customerTypeCode ){
        setCustomerTypeCode(customerTypeCode);
    }

    public void setCustomerTypeCode(Integer customerTypeCode) {
        this.customerTypeCode = customerTypeCode;
    }
}
