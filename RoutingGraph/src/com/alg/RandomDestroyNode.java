package com.alg;

import com.basedata.NodeType;
import com.model.Solution;
import com.utility.Utility;

public class RandomDestroyNode extends ADestroyNode{

    public void selectNode(Solution solution){
        Integer nodesCount = Utility.random(0,solution.getStateNodes().size()-1);
        Integer randomIndex;
        for(int i=0;i<(nodesCount/2);i++){
            randomIndex = Utility.random(0,solution.getStateNodes().size()-1);
            if(solution.getStateNodes().get(randomIndex).getNodeType().equals(NodeType.CUSTOMER)){
                destroyNode(solution.getStateNodes().get(randomIndex),solution);
            }
        }
    }
}
