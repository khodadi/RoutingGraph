package com.alg;

import com.basedata.NodeType;
import com.model.Node;
import com.model.Solution;
import com.model.Truck;
import com.utility.Utility;

import java.util.ArrayList;

public class DepoDestroyNode extends ADestroyNode{
    @Override
    public void selectNode(Solution solution) {
        Integer nodesCount = Utility.random(0,solution.getStateNodes().size()/2);
        Integer randomIndex;
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0;i<(nodesCount);i++){
            randomIndex = Utility.random(0,solution.getStateNodes().size()-1);
            if(solution.getStateNodes().get(randomIndex).getNodeType().equals(NodeType.DEPO)){
                for(Truck truck: solution.getStateNodes().get(randomIndex).getTrucks()){
                    if(truck.getTracks() != null){
                        nodes.addAll(truck.getTracks());
                    }
                    if(truck.getDrone() != null && truck.getDrone().getTracks() != null){
                        nodes.addAll(truck.getDrone().getTracks());
                    }
                }
            }
        }
        for(Node node:nodes){
            destroyNode(node,solution);
        }
    }
}
