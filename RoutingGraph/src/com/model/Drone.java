package com.model;

import java.util.ArrayList;

public class Drone {
    private Location location;
    private Integer lunchTime;
    private Integer landTime;
    private Integer capacity;
    private Integer speed;

    private Truck parentNode;

    private ArrayList<Node> tracks;

    public Drone() {
    }

    public Drone(Integer lunchTime, Integer landTime, Integer capacity, Integer speed) {
        this.lunchTime = lunchTime;
        this.landTime = landTime;
        this.capacity = capacity;
        this.speed = speed;
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
        return tracks;
    }

    public void setTracks(ArrayList<Node> tracks) {
        this.tracks = tracks;
    }

    public Truck getParentNode() {
        return parentNode;
    }

    public void setParentNode(Truck parentNode) {
        this.parentNode = parentNode;
    }
}
