package com.model;

import java.util.ArrayList;

public class Truck implements Cloneable {
    private Integer truckId;
    private Integer nodeId;
    private Integer capacity;
    private Integer speed;
    private Location location;
    private Integer workTime = 0;
    private Drone drone;
    ArrayList<Node> tracks;
    public Truck() {
    }

    public Truck(Integer capacity, Integer speed, Drone drone,Integer truckId,Integer nodeId) {
        this.capacity = capacity;
        this.speed = speed;
        this.drone = drone;
        this.truckId = truckId;
        this.nodeId = nodeId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public ArrayList<Node> getTracks() {
        if(tracks == null){
            tracks = new ArrayList<>();
        }
        return tracks;
    }

    public void setTracks(ArrayList<Node> tracks) {
        this.tracks = tracks;
    }


    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    @Override
    public Truck clone() throws CloneNotSupportedException {
        return (Truck) super.clone();
    }
}
