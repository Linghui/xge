package com.gol.xge.dragonBones.objects;

public class Node {

    public float x;
    public float y;
    public float scaleX;
    public float scaleY;
    public float skewX;
    public float skewY;
    public int z;
    
    public float getRotation(){
        return skewY;
    }
    
    public void setRotation(int rotation){
        this.skewX = this.skewY = rotation;
    }
}
