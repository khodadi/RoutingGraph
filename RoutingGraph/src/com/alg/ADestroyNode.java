package com.alg;

import com.basedata.NodeType;
import com.model.Drone;
import com.model.Node;
import com.model.Solution;
import com.model.Truck;
import com.utility.Utility;

public abstract class ADestroyNode implements IDestroyNode {

    public void destroyNode(Node node, Solution solution){
        boolean retVal;
        if(node.getNodeType().equals(NodeType.CUSTOMER)){
            for(Node n:solution.getStateNodes()){
                retVal = findInTruck(n,node,solution);
                if(retVal){
                    break;
                }
            }
        }
    }

    public boolean findInTruck(Node truckNode,Node node,Solution  solution){
        boolean retVal = false;
        if(truckNode.getTrucks()!= null && node != null){
            for(Truck truck:truckNode.getTrucks()){
                retVal = findInRoutingTruck(truck,truckNode,node,solution);
                if(!retVal){
                    retVal = findInRoutingDrone(truck.getDrone(),truckNode,node,solution);
                }
                if(retVal){
                    break;
                }
            }

        }
        return retVal;
    }
    public boolean findInRoutingTruck(Truck truck,Node truckNode,Node target,Solution  solution){
        boolean retVal = false;
        int index = 0;
        if(truck!= null && truck.getTracks() != null){
            for(index = 0 ;index < truck.getTracks().size();index++){
                if(truck.getTracks().get(index).getNodeId().equals(target.getNodeId())){
                    retVal = true;
                    break;
                }
            }
        }
        if(retVal){
            deleteNodeTruck(index,truck,truckNode,target,solution);
        }
        return retVal;
    }

    public boolean findInRoutingDrone(Drone drone,Node droneNode,Node target,Solution  solution){
        boolean retVal = false;
        int index = 0;
        if(drone!= null && drone.getTracks()!= null ){
            for(index = 0 ;index < drone.getTracks().size();index++){
                if(drone.getTracks().get(index).getNodeId().equals(target.getNodeId())){
                    retVal = true;
                    break;
                }
            }
        }
        if(retVal){
            deleteNodeDrone(index,drone,droneNode,target,solution);
        }
        return retVal;
    }

    public void deleteNodeTruck(int index,Truck truck,Node truckNode,Node target,Solution solution){
        if(truck.getTracks().size() == 2){
            Double goesCost = Utility.calCostTruck(truckNode,target);
            Double returnCost = Utility.calCostTruck(target,truckNode);
            solution.setCost(solution.getCost()- (goesCost.intValue()+returnCost.intValue()));
            target.getCustomer().setAccepted(false);
            truck.setTracks(null);
        }else{
            Node perNode = index-1 < 0 ? truckNode :  truck.getTracks().get(index-1);
            Double goesToNext = 0D ;
            Double goesPerToNext = 0D;
            Node nextNode = index+1 >= truck.getTracks().size() ? null :  truck.getTracks().get(index+1);
            Double goesFromPer = Utility.calCostTruck(perNode, truck.getTracks().get(index));
            if(nextNode != null){
                goesToNext = Utility.calCostTruck(truck.getTracks().get(index),nextNode);
                goesPerToNext = Utility.calCostTruck(perNode,nextNode);
            }
            truck.getTracks().remove(index);
            solution.setCost(solution.getCost()- (goesFromPer.intValue()+goesToNext.intValue()) + goesPerToNext.intValue());
        }
    }

    public void deleteNodeDrone(int index, Drone drone, Node droneNode, Node target, Solution solution){
        if(drone.getTracks().size() == 2){
            Double goesCost = Utility.calCostDrone(droneNode,target);
            Double returnCost = Utility.calCostDrone(target,droneNode);
            solution.setCost(solution.getCost()- (goesCost.intValue()+returnCost.intValue()));
            target.getCustomer().setAccepted(false);
            drone.setTracks(null);
        }else{
            Node perNode = index-1 < 0 ? droneNode :  drone.getTracks().get(index-1);
            Node nextNode = index+1 >= drone.getTracks().size() ? null :  drone.getTracks().get(index+1);
            Double goesToNext = 0D;
            Double goesPerToNext = 0D;
            Double goesFromPer = Utility.calCostDrone(perNode, drone.getTracks().get(index));
            if(nextNode != null){
                goesToNext = Utility.calCostTruck(drone.getTracks().get(index),nextNode);
                goesPerToNext = Utility.calCostTruck(perNode,nextNode);
            }
            drone.getTracks().remove(index);
            solution.setCost(solution.getCost()- (goesFromPer.intValue()+goesToNext.intValue()) + goesPerToNext.intValue());
        }
    }
}
