package com.alg;

import com.basedata.Constraint;
import com.basedata.CustomerType;
import com.basedata.NodeType;
import com.model.Input;
import com.model.Node;
import com.model.Solution;
import com.model.Truck;
import com.utility.Utility;

public class DistanceCalculate {
    public Solution initSolution(Input input){
        Solution retVal = new Solution();
        while(true){
            for(Node node:input.getNodes()){
                if(node.getNodeType().equals(NodeType.CUSTOMER)){
                    //calNode
                }
            }
            break;
        }
        return retVal;
    }

    public void assignNode(Node target, Solution solution,Input input){
        Double minCost = 1000D;
        Double currentCost;
        int minIndex = -1;
        Integer truckId = -1;
        if(target.getCustomer().getCustomerType().equals(CustomerType.Truck)){
            for(Node destination:solution.getStateNodes()){
                if(Constraint.Cal_Cost_Truck.equals("M")){
                    currentCost = Utility.manhattan(destination,target) * Constraint.Truck_Cost;
                }else{
                    currentCost = Utility.euclidean(destination, target) * Constraint.Truck_Cost;
                }
                if(currentCost >= minCost){
                    for(Truck truck:destination.getTrucks()){
                        if(target.getCustomer().getRequestCapacity() >= truck.getCapacity()){
                            minIndex = destination.getNodeId();
                            truckId = truck.getTruckId();
                        }
                    }
                }
            }
        }

    }
}
