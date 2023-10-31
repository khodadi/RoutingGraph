package com;

import com.alg.DistanceCalculate;
import com.basedata.Constraint;
import com.basedata.CustomerType;
import com.basedata.NodeType;
import com.model.Input;
import com.model.Node;
import com.utility.Utility;

import java.util.ArrayList;

public class MainClass {


    public static void main(String[] arg){

        DistanceCalculate distanceCalculate = new DistanceCalculate();
        ArrayList<Node> nodes = new ArrayList<>();
        Node node;
        for(int i = 1; i <= Constraint.Max_Nodes; i++){
            if(i <= Math.floor(Constraint.Percentage_depo * Constraint.Max_Nodes)){
                node = new Node(NodeType.DEPO,i,null);
            }else if (i <= Math.floor(Constraint.Percentage_customer_joint * Constraint.Max_Nodes) && i > Math.floor(Constraint.Percentage_depo * Constraint.Max_Nodes)){
                node = new Node(NodeType.CUSTOMER,i,CustomerType.Joint);
            }else if(i <= Math.floor(Constraint.Percentage_customer_truck * Constraint.Max_Nodes) && i > Math.floor(Constraint.Percentage_customer_joint * Constraint.Max_Nodes)){
                node = new Node(NodeType.CUSTOMER,i,CustomerType.Truck);
            }else{
                node = new Node(NodeType.CUSTOMER,i,CustomerType.Drone);
            }
            nodes.add(node);
        }
        // this is test
        Input input = new Input(nodes,Utility.defaultCost(nodes));
        distanceCalculate.initSolution(input);
        System.out.println("Hello word");
    }
}
