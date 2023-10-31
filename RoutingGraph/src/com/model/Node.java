package com.model;

import com.basedata.Constraint;
import com.basedata.CustomerType;
import com.basedata.NodeType;
import com.utility.Utility;

import java.util.ArrayList;

public class Node {

    private NodeType nodeType;
    private ArrayList<Truck> trucks;
    private Customer customer;
    private Location location;

    private Integer nodeId;

    public Node() {
    }

    public Node(NodeType nodeType,int nodeId,CustomerType customerType ) {
        this.nodeType = nodeType;
        this.setLocation(new Location(Utility.random(Constraint.Min_Location,Constraint.Max_Location),Utility.random(Constraint.Min_Location,Constraint.Max_Location)));
        this.setNodeId(nodeId);
        if(nodeType.equals(NodeType.DEPO)){
            this.setTrucks(createDefaultTruck());
        }else{
            this.setCustomer(createDefaultCustomer(customerType));
        }
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public ArrayList<Truck> getTrucks() {
        if(trucks == null){
            trucks = new ArrayList<>();
        }
        return trucks;
    }

    public void setTrucks(ArrayList<Truck> trucks) {
        this.trucks = trucks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Truck> createDefaultTruck(){
        Drone drone;
        Truck truck;
        ArrayList<Truck> trucks = new ArrayList<>();
        for(int j = 1; j <= Utility.random(Constraint.Min_Truck_Number,Constraint.Max_Truck_Number); j++){
            drone = new Drone(Constraint.Drone_Lunch_Time,Constraint.Drone_Land_Time,Constraint.Drone_Capacity,Constraint.Drone_Speed);
            truck = new Truck(Constraint.Truck_Capacity,Constraint.Truck_Speed,drone);
            trucks.add(truck);
        }
        return trucks;
    }
    public Customer createDefaultCustomer(CustomerType customerType){
        Integer requestCapacity = customerType.equals(CustomerType.Truck) ? Constraint.Truck_Capacity:Constraint.Drone_Capacity;
        return  new Customer(customerType,Utility.random(Constraint.Min_Reject_Request,Constraint.Max_Reject_Request),Utility.random(Constraint.MIn_Capacity_Request,requestCapacity));
    }
}
