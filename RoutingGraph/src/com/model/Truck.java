package com.model;

import java.util.ArrayList;

public class Truck {
    private Integer truckId;
    private Integer capacity;
    private Integer speed;
    private Location location;

    private Drone drone;

    private Node parentNode;
    ArrayList<Node> tracks;


    public Truck() {
    }

    public Truck(Integer capacity, Integer speed, Drone drone) {
        this.capacity = capacity;
        this.speed = speed;
        this.drone = drone;
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
        return tracks;
    }

    public void setTracks(ArrayList<Node> tracks) {
        this.tracks = tracks;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }
}
