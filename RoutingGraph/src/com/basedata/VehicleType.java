package com.basedata;

public enum VehicleType {
    Drone(0),Trunc(1);
    private Integer vehicleTypeCode;
    VehicleType(Integer vehicleTypeCode){
        setVehicleTypeCode(vehicleTypeCode);
    }

    public void setVehicleTypeCode(Integer vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }
}
