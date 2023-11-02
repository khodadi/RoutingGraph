package com.utility;

import com.basedata.Constraint;
import com.model.CostTransfer;
import com.model.Node;

import java.util.ArrayList;

public class Utility {
    public static  Integer random(Integer min, Integer max){
        return Long.valueOf(Math.round(Math.random() * (max - min + min) + min)).intValue();
    }
    public static CostTransfer[][] defaultCost(ArrayList<Node> nodes){
        CostTransfer[][] costTransfers = new CostTransfer[nodes.size()][nodes.size()];
        for(int i = 0; i <= Constraint.Max_Nodes-1; i++){
            for(int j = 0; j<= Constraint.Max_Nodes-1; j++){
                costTransfers[i][j] = new CostTransfer(nodes.get(i),nodes.get(j));
            }
        }
        return costTransfers;
    }
    public static Integer manhattan(Node destination,Node target){
        Integer retVal = Math.abs(destination.getLocation().getX()-target.getLocation().getX())+
                Math.abs(destination.getLocation().getY()-target.getLocation().getY());
        return retVal;
    }
    public static Integer euclidean(Node destination,Node target){
        Integer retVal = Double.valueOf(Math.round(Math.sqrt(Math.pow(destination.getLocation().getX()-target.getLocation().getX(),2)+
                Math.pow(destination.getLocation().getY()-target.getLocation().getY(),2)))).intValue();
        return retVal;
    }
    public static Double calCostTruck(Node source,Node target){
        Double retVal = 0D;
        if(Constraint.Cal_Cost_Truck.equals("M")){
            retVal = manhattan(source,target) * Constraint.Truck_Cost;
        }else{
            retVal = euclidean(source, target) * Constraint.Truck_Cost;
        }
        return retVal;
    }
    public static Double calCostDrone(Node source,Node target){
        Double retVal = 0D;
        if(Constraint.Cal_Cost_Drone.equals("M")){
            retVal = Utility.manhattan(source, target) * Constraint.Drone_Cost;
        }else{
            retVal = Utility.euclidean(source, target) * Constraint.Drone_Cost;
        }
        return retVal;
    }
    public static Integer calTimeTruck(Node source,Node target){
        Integer retVal = 0;
        if(Constraint.Cal_Distance_Truck.equals("M")){
            retVal = Utility.manhattan(source, target) / Constraint.Truck_Speed;
        }else{
            retVal = Utility.euclidean(source, target) / Constraint.Truck_Speed;
        }
        return retVal;
    }

    public static Integer calTimeDrone(Node source,Node target){
        Integer retVal = 0;
        if(Constraint.Cal_Distance_Drone.equals("M")){
            retVal = Utility.manhattan(source, target) / Constraint.Drone_Speed;
        }else{
            retVal = Utility.euclidean(source, target) / Constraint.Drone_Speed;
        }
        return retVal;
    }

    public static ArrayList<Node> copyArrayNode(ArrayList<Node> nodes){
        ArrayList<Node> retVal = new ArrayList<Node>(nodes.size());
        for(Node node:nodes){
            try{
                retVal.add(node.clone());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return retVal;
    }

}
