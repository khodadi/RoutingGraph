package com.basedata;

public enum NodeType {
    DEPO(0),CUSTOMER(1);

    private Integer nodeTypeCode;
    NodeType(Integer nodeTypeCode ){
        setNodeTypeCode(nodeTypeCode);
    }

    public void setNodeTypeCode(Integer nodeTypeCode) {
        this.nodeTypeCode = nodeTypeCode;
    }
}
