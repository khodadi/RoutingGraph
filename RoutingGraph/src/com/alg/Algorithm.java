package com.alg;

import com.basedata.CustomerType;
import com.basedata.NodeType;
import com.model.*;
import com.utility.Utility;
import java.util.ArrayList;

public class Algorithm {

    public Solution AlgorithmOptimizer(Input input,Integer countRepeat){
        Solution retVal = new Solution();
        Integer counter = 0;
        retVal.setStateNodes(Utility.copyArrayNode(input.getNodes()));
        solution(retVal,input.getNodes().size());
        Solution currentSolution = new Solution();
        currentSolution.setStateNodes(Utility.copyArrayNode(retVal.getStateNodes()));
        currentSolution.setCost(retVal.getCost());
        while(counter < countRepeat){
            System.out.println("The cost of solution in loop "+ counter +" is : "+retVal.getCost());
            counter++;
            RandomFactory.getInstance().selectNode(currentSolution);
            solution(currentSolution,input.getNodes().size());
            if(currentSolution.getCost() < retVal.getCost()){
                retVal.setStateNodes(Utility.copyArrayNode(currentSolution.getStateNodes()));
                retVal.setCost(currentSolution.getCost());
            }else{
                currentSolution = new Solution();
                currentSolution.setStateNodes(Utility.copyArrayNode(retVal.getStateNodes()));
                currentSolution.setCost(retVal.getCost());
            }
        }
        return retVal;

    }


    public void solution(Solution retVal,Integer countRepeat){
        int counter = 0;
        MovingDevice movingDroneDevice;
        MovingDevice movingTruckDevice;
        while(continueLoop(retVal.getStateNodes())  &&   counter < countRepeat){
            counter++;
            for(Node node:retVal.getStateNodes()){
                if(truckCustomer(node)){
                    movingTruckDevice = selectTruckSourceNode(node,retVal.getStateNodes());
                    moveTruck(movingTruckDevice.getDeviceId(),movingTruckDevice.getSource(),movingTruckDevice.getTarget(),movingTruckDevice.getTarget().getCustomer().getRequestCapacity(), movingTruckDevice.getWorkTime());
                    retVal.setCost(retVal.getCost() + movingTruckDevice.getCost());
                }else if(droneCustomer(node)){
                    movingDroneDevice = selectDroneSourceNode(node,retVal.getStateNodes());
                    moveDrone(movingDroneDevice.getDeviceId(),movingDroneDevice.getSource(),movingDroneDevice.getTarget(),movingDroneDevice.getTarget().getCustomer().getRequestCapacity(), movingDroneDevice.getWorkTime());
                    retVal.setCost(retVal.getCost() + movingDroneDevice.getCost());
                }else if(jointCustomer(node)){
                    movingTruckDevice = selectTruckSourceNode(node,retVal.getStateNodes());
                    movingDroneDevice = selectDroneSourceNode(node,retVal.getStateNodes());
                    if(selectionTruck(movingDroneDevice,movingTruckDevice)){
                        moveTruck(movingTruckDevice.getDeviceId(),movingTruckDevice.getSource(),movingTruckDevice.getTarget(),movingTruckDevice.getTarget().getCustomer().getRequestCapacity(), movingTruckDevice.getWorkTime());
                        retVal.setCost(retVal.getCost() + movingTruckDevice.getCost());
                    }else if(selectionDrone(movingDroneDevice,movingTruckDevice)){
                        moveDrone(movingDroneDevice.getDeviceId(),movingDroneDevice.getSource(),movingDroneDevice.getTarget(),movingDroneDevice.getTarget().getCustomer().getRequestCapacity(), movingDroneDevice.getWorkTime());
                        retVal.setCost(retVal.getCost() + movingDroneDevice.getCost());
                    }
                }
            }
        }
        calculateRejected(retVal);
        returnDevice(retVal);
    }
    public boolean continueLoop(ArrayList<Node> nodes){
        boolean retVal = false;
        for(Node node:nodes){
            if(node.getNodeType().equals(NodeType.CUSTOMER)  && !node.getCustomer().isAccepted()){
                retVal = true;
                break;
            }
        }
        return retVal;
    }
    public boolean truckCustomer(Node node){
        boolean retVal = false;
        try{
            if(node.getNodeType().equals(NodeType.CUSTOMER)  &&
                    node.getCustomer().getCustomerType().equals(CustomerType.Truck) &&
                    !node.getCustomer().isAccepted()){
                retVal = true;
            }
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }
        return retVal;
    }
    public boolean droneCustomer(Node node){
        boolean retVal = false;
        try{
            if(node.getNodeType().equals(NodeType.CUSTOMER) &&
                    node.getCustomer().getCustomerType().equals(CustomerType.Drone) &&
                    !node.getCustomer().isAccepted()){
                retVal = true;
            }
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }
        return retVal;
    }
    public boolean jointCustomer(Node node){
        boolean retVal = false;
        try{
            if(node.getNodeType().equals(NodeType.CUSTOMER) &&
                    node.getCustomer().getCustomerType().equals(CustomerType.Joint) &&
                    !node.getCustomer().isAccepted()){
                retVal = true;
            }
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }
        return retVal;
    }
    public MovingDevice selectTruckSourceNode(Node target, ArrayList<Node> nodes){
        Double currentCost;
        Integer currentTime = 0;
        Double minCost = 1000D;
        Node minNode= null;
        Integer truckId = -1;
        for(Node node:nodes){
            if(node.getTrucks().size() > 0 && !target.getNodeId().equals(node.getNodeId())){
                currentCost = Utility.calCostTruck(node,target);
                currentTime = Utility.calTimeTruck(node,target);
                if(currentCost < minCost){
                    for(Truck truck:node.getTrucks()){
                        if(target.getCustomer().getRequestCapacity() <= truck.getCapacity() && (truck.getWorkTime() + currentTime) <= (8 * 60)){
                            minNode = node;
                            truckId = truck.getTruckId();
                            minCost = currentCost;
                            break;
                        }
                    }
                }
            }
        }
        return new MovingDevice(truckId,minNode,target,minCost.equals(1000D)? 0 : minCost.intValue(),currentTime);
    }
    public MovingDevice selectDroneSourceNode(Node target, ArrayList<Node> nodes){
        Double currentCost;
        Double minCost = 1000D;
        Integer workTime = 0;
        Node minNode= null;
        Integer droneId = -1;
        for(Node node:nodes){
            if(target.getNodeId().equals(node.getNodeId())){
                continue;
            }
            currentCost = Utility.calCostDrone(node,target);
            workTime = Utility.calTimeDrone(node, target);
            if(currentCost < minCost) {
                if (node.getDrone() != null &&
                        target.getCustomer().getRequestCapacity() <= node.getDrone().getCapacity() && (node.getDrone().getWorkTime()+workTime) <= (8*60) ) {
                    minNode = node;
                    droneId = node.getDrone().getDroneId();
                    minCost = currentCost;
                }else{
                    for(Truck truck:node.getTrucks()){
                        if(truck.getDrone()!= null &&
                                target.getCustomer().getRequestCapacity() <= truck.getDrone().getCapacity() &&
                                (truck.getDrone().getWorkTime()+workTime) <= (8*60)){
                            minNode = node;
                            droneId = truck.getDrone().getDroneId();
                            minCost = currentCost;
                            break;
                        }
                    }
                }
            }
        }
        return new MovingDevice(droneId,minNode,target,minCost.equals(1000D)? 0 : minCost.intValue(),workTime);
    }
    public void moveTruck(Integer truckId,Node source, Node target,Integer capacity,Integer workTime){
        try{
            Truck movingTruck = moveTruckFormSource(truckId,source);
            if(movingTruck != null){
                movingTruck.getTracks().add(target);
                movingTruck.setCapacity(movingTruck.getCapacity() - capacity);
                movingTruck.setWorkTime(movingTruck.getWorkTime() + workTime);
                target.getCustomer().setAccepted(true);
                target.getTrucks().add(movingTruck);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void moveDrone(Integer droneId,Node source, Node target,Integer capacity,Integer workTime ){
        try{
            Drone movingDrone = moveDroneFormSource(droneId,source);
            if(movingDrone != null){
                movingDrone.getTracks().add(target);
                movingDrone.setCapacity(movingDrone.getCapacity()-capacity);
                movingDrone.setWorkTime(movingDrone.getWorkTime()+workTime);
                target.getCustomer().setAccepted(true);
                target.setDrone(movingDrone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Truck moveTruckFormSource(Integer truckId,Node source) throws CloneNotSupportedException {
        Truck retVal = null;
        if(source != null){
            for(Truck truck:source.getTrucks()){
                if(truck.getTruckId().equals(truckId)){
                    retVal = truck.clone();
                    source.getTrucks().remove(truck);
                    break;
                }
            }
        }
        return retVal;
    }
    public Drone moveDroneFormSource(Integer droneId, Node source) throws CloneNotSupportedException {
        Drone retVal = null;
        if(source != null && source.getDrone() != null && source.getDrone().getDroneId().equals(droneId)){
            retVal = source.getDrone().clone();
            source.setDrone(null);
        }else if(source != null){
            for(Truck truck:source.getTrucks()){
                if(truck.getDrone() != null && truck.getDrone().getDroneId().equals(droneId)){
                    retVal = truck.getDrone().clone();
                    truck.setDrone(null);
                    break;
                }
            }
        }
        return retVal;
    }
    public boolean selectionDrone(MovingDevice movingDroneDevice, MovingDevice movingTruckDevice){
        boolean retVal = false;
        if(movingTruckDevice.getCost() == 0 && movingDroneDevice.getCost() != 0 ){
            retVal = true;
        }
        if(movingTruckDevice.getCost() != 0 && movingDroneDevice.getCost() != 0 && movingTruckDevice.getCost() > movingDroneDevice.getCost()){
            retVal = true;
        }
        return retVal;
    }
    public boolean selectionTruck(MovingDevice movingDroneDevice, MovingDevice movingTruckDevice){
        boolean retVal = false;
        if(movingTruckDevice.getCost() != 0 && movingDroneDevice.getCost() == 0 ){
            retVal = true;
        }
        if(movingTruckDevice.getCost() != 0 && movingDroneDevice.getCost() != 0 && movingTruckDevice.getCost() <= movingDroneDevice.getCost()){
            retVal = true;
        }
        return retVal;
    }

    public void returnDevice(Solution solution){
        for(Node node:solution.getStateNodes()){
            returnDrone(node,solution);
            returnTruck(node,solution);
        }
    }
    public void returnTruck(Truck truck,Node source, Node target){
        try{
            if(source != null && target != null){
                Truck movingTruck = truck.clone();
                movingTruck.getTracks().add(target);
                target.getTrucks().add(movingTruck);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void returnTruck(Node node, Solution solution){
        if(node.getNodeType().equals(NodeType.CUSTOMER) && node.getTrucks().size() > 0){
            for(Truck truck:node.getTrucks()){
                for(Node n: solution.getStateNodes()){
                    if(n.getNodeId().equals(truck.getNodeId())){
                        returnTruck(truck,node,n);
                        solution.setCost(solution.getCost() + Utility.calCostTruck(node,n).intValue());
                        break;
                    }
                }
            }
            node.setTrucks(null);
        }
    }
    public void returnDrone(Node node, Solution solution){
        boolean find = false;
        if(node.getNodeType().equals(NodeType.CUSTOMER) && node.getDrone() != null){
            for(Node n: solution.getStateNodes()){
                for(Truck truck:n.getTrucks()){
                    if(truck.getTruckId().equals(node.getDrone().getTruckId())){
                        try{
                            Drone returnDrone = node.getDrone().clone();
                            returnDrone.getTracks().add(n);
                            solution.setCost(solution.getCost() + Utility.calCostDrone(node,n).intValue());
                            truck.setDrone(returnDrone);
                            node.setDrone(null);
                            find = true;
                            break;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                if(find){
                    break;
                }
            }
        }
    }

    public void calculateRejected(Solution solution){
        for(Node node:solution.getStateNodes()){
            if(node.getNodeType().equals(NodeType.CUSTOMER)  && !node.getCustomer().isAccepted()){
                solution.setCost(node.getCustomer().getRejectCost() + solution.getCost());
            }
        }
    }
}
