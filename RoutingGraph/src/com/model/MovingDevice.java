package com.model;

public class MovingDevice {

    private Integer deviceId;
    private Node source;
    private Node target;
    private Integer cost;

    private Integer workTime;

    public MovingDevice(Integer deviceId, Node source, Node target, Integer cost,Integer workTime) {
        this.deviceId = deviceId;
        this.source = source;
        this.target = target;
        this.cost = cost;
        this.workTime = workTime;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }
}
