package com.model;

import com.basedata.CustomerType;

public class Customer {
    private CustomerType customerType;
    private Integer rejectCost;
    private Integer requestCapacity;

    public Customer() {
    }

    public Customer(CustomerType customerType, Integer rejectCost, Integer requestCapacity) {
        this.customerType = customerType;
        this.rejectCost = rejectCost;
        this.requestCapacity = requestCapacity;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Integer getRejectCost() {
        return rejectCost;
    }

    public void setRejectCost(Integer rejectCost) {
        this.rejectCost = rejectCost;
    }

    public Integer getRequestCapacity() {
        return requestCapacity;
    }

    public void setRequestCapacity(Integer requestCapacity) {
        this.requestCapacity = requestCapacity;
    }
}
