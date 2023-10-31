package com.model;

import com.basedata.Constraint;
import com.utility.Utility;


public class CostTransfer implements Comparable<CostTransfer> {
    private Node destination;
    private Node target;
    private Integer timeTruck;
    private Integer timeDrone;
    private Double costTruck;
    private Double costDrone;

    public CostTransfer() {
    }

    public CostTransfer(Node destination, Node target) {
        this.destination = destination;
        this.target = target;
        if(Constraint.Cal_Distance_Truck.equals("M")){
            this.timeTruck = Utility.manhattan(this.destination, this.target) / Constraint.Truck_Speed;
        }else{
            this.timeTruck = Utility.euclidean(this.destination, this.target) / Constraint.Truck_Speed;
        }
        if(Constraint.Cal_Distance_Drone.equals("M")){
            this.timeDrone = Utility.manhattan(this.destination, this.target) / Constraint.Drone_Speed;
        }else{
            this.timeDrone = Utility.euclidean(this.destination, this.target) / Constraint.Drone_Speed;
        }
        if(Constraint.Cal_Cost_Truck.equals("M")){
            this.costTruck = Utility.manhattan(this.destination, this.target) * Constraint.Truck_Cost;
        }else{
            this.costTruck = Utility.euclidean(this.destination, this.target) * Constraint.Truck_Cost;
        }
        if(Constraint.Cal_Cost_Drone.equals("M")){
            this.costDrone = Utility.manhattan(this.destination, this.target) * Constraint.Drone_Cost;
        }else{
            this.costDrone = Utility.euclidean(this.destination, this.target) * Constraint.Drone_Cost;
        }
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Integer getTimeTruck() {
        return timeTruck;
    }

    public void setTimeTruck(Integer timeTruck) {
        this.timeTruck = timeTruck;
    }

    public Integer getTimeDrone() {
        return timeDrone;
    }

    public void setTimeDrone(Integer timeDrone) {
        this.timeDrone = timeDrone;
    }

    public Double getCostTruck() {
        return costTruck;
    }

    public void setCostTruck(Double costTruck) {
        this.costTruck = costTruck;
    }

    public Double getCostDrone() {
        return costDrone;
    }

    public void setCostDrone(Double costDrone) {
        this.costDrone = costDrone;
    }

    @Override
    public int compareTo(CostTransfer o) {
        int retVal = 0;
        if(o.getCostTruck() >= this.getCostTruck()){
            retVal = 1;
        }else{
            retVal = -1;
        }
        return retVal;
    }
}
