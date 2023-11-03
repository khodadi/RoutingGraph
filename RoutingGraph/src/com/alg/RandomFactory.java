package com.alg;

import com.utility.Utility;

public final class RandomFactory {

    private static IDestroyNode randomDestroyNode;
    private static IDestroyNode maxDestroyNode;
    private static IDestroyNode minDestroyNode;

    private static IDestroyNode depoDestroyNode;

    private RandomFactory(){

    }
    public static IDestroyNode getInstance(){
        IDestroyNode retVal = null;
        if(randomDestroyNode == null){
            randomDestroyNode = new RandomDestroyNode();
        }
        if(maxDestroyNode == null){
            maxDestroyNode = new MaxCostNode();
        }
        if(minDestroyNode == null){
            minDestroyNode  = new MinCostNode();
        }
        if(depoDestroyNode == null){
            depoDestroyNode = new DepoDestroyNode();
        }
        Integer ranNumber =  Utility.random(1,4);
        if(ranNumber.equals(1)){
            retVal = randomDestroyNode;
        }else if(ranNumber.equals(2)){
            retVal = maxDestroyNode;
        }else if(ranNumber.equals(3)){
            retVal = minDestroyNode;
        }else{
            retVal = depoDestroyNode;
        }
        return  retVal;
    }
}
