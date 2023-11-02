package com.model;

import java.util.ArrayList;

public class Drone implements Cloneable {
    private Location location;
    private Integer lunchTime;
    private Integer landTime;
    private Integer capacity;
    private Integer speed;

    private Integer workTime = 0;

    private Integer droneId;

    private Integer truckId;


    private ArrayList<Node> tracks;

    public Drone() {
    }

    public Drone(Integer lunchTime, Integer landTime, Integer capacity, Integer speed,Integer droneId,Integer truckId) {
        this.lunchTime = lunchTime;
        this.landTime = landTime;
        this.capacity = capacity;
        this.speed = speed;
        this.droneId = droneId;
        this.truckId = truckId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getLunchTime() {
        return lunchTime;
    }

    public void setLunchTime(Integer lunchTime) {
        this.lunchTime = lunchTime;
    }

    public Integer getLandTime() {
        return landTime;
    }

    public void setLandTime(Integer landTime) {
        this.landTime = landTime;
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

    public Integer getDroneId() {
        return droneId;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public void setDroneId(Integer droneId) {
        this.droneId = droneId;
    }

    @Override
    public Drone clone() throws CloneNotSupportedException {
        return (Drone)super.clone();
    }
}
