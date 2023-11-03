package com.alg;

import com.model.Node;
import com.model.Solution;
import com.model.Truck;
import com.utility.Utility;

import java.util.ArrayList;

public class MinCostNode extends ADestroyNode{

    @Override
    public void selectNode(Solution solution) {
        Integer nodeCount =  Utility.random(0,solution.getStateNodes().size());
        ArrayList<Node> nodes = findMaxNodeTruck(solution,nodeCount/3);
        nodes.addAll(findMaxNodeDrone(solution,nodeCount / 3));
        for(Node node:nodes){
            destroyNode(node,solution);
        }
    }
    public ArrayList<Node> findMaxNodeTruck(Solution solution, int n){
        ArrayList<Node> retVal = new ArrayList<>();
        Double[] minCosts = new Double[n];
        Integer[] nodesId = new Integer[n];
        for(int i= 0 ;i < n ; i++){
            minCosts[i] = 100000D;
        }
        Double currentCost = 0D;
        Integer nodeId = 0 ;
        Integer tNodeId = 0 ;
        for(Node node: solution.getStateNodes()){
            for(Truck truck:node.getTrucks()){
                if(truck.getTracks() != null){
                    for(int index = 0 ; index< truck.getTracks().size();index++){
                        Node perNode = index-1 < 0 ? node :  truck.getTracks().get(index-1);
                        currentCost = Utility.calCostTruck(perNode,truck.getTracks().get(index));
                        nodeId = truck.getTracks().get(index).getNodeId();
                        for(int i= 0 ;i < n ; i++){
                            if(currentCost < minCosts[i]){
                                currentCost = minCosts[i];
                                tNodeId = nodesId[i];
                                nodesId[i]  = nodeId;
                                nodeId = tNodeId;
                            }
                        }
                    }
                }
            }
        }
        for(Node node:solution.getStateNodes()){
            for(int i= 0 ;i < n ; i++){
                try{
                    if(node.getNodeId().equals(nodesId[i])){
                        retVal.add(node);
                        break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return retVal;
    }


    public ArrayList<Node> findMaxNodeDrone(Solution solution, int n){
        ArrayList<Node> retVal = new ArrayList<>();
        Double[] minCosts = new Double[n];
        Integer[] nodesId = new Integer[n];
        for(int i= 0 ;i < n ; i++){
            minCosts[i] = 100000D;
        }
        Double currentCost = 0D;
        Integer nodeId = 0 ;
        Integer tNodeId = 0 ;
        for(Node node: solution.getStateNodes()){
            for(Truck truck:node.getTrucks()){
                if(truck.getDrone() != null && truck.getDrone().getTracks() != null){
                    for(int index = 0 ; index< truck.getDrone().getTracks().size();index++){
                        Node perNode = index-1 < 0 ? node :  truck.getDrone().getTracks().get(index-1);
                        currentCost = Utility.calCostDrone(perNode,truck.getDrone().getTracks().get(index));
                        nodeId = truck.getDrone().getTracks().get(index).getNodeId();
                        for(int i= 0 ;i < n ; i++){
                            if(currentCost < minCosts[i]){
                                currentCost = minCosts[i];
                                tNodeId = nodesId[i];
                                nodesId[i]  = nodeId;
                                nodeId = tNodeId;
                            }
                        }
                    }
                }
            }
        }
        for(Node node:solution.getStateNodes()){
            for(int i= 0 ;i < n ; i++){
                try{
                    if(node.getNodeId().equals(nodesId[i])){
                        retVal.add(node);
                        break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return retVal;
    }
}
