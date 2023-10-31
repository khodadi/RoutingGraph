package com.model;

import java.util.ArrayList;

public class Input {
    ArrayList<Node> nodes;
    CostTransfer[][] costTransfers;

    public Input(ArrayList<Node> nodes, CostTransfer[][] costTransfers) {
        this.nodes = nodes;
        this.costTransfers = costTransfers;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public CostTransfer[][] getCostTransfers() {
        return costTransfers;
    }

    public void setCostTransfers(CostTransfer[][] costTransfers) {
        this.costTransfers = costTransfers;
    }
}
