package com.gol.xge.dragonBones.objects;

public class DisplayData {

    private String name = null;
    public String getName() {
        return name;
    }

    private boolean isArmature = true;
    
    public DisplayData(String name, boolean isArmature){
        this.name = name;
        this.isArmature = isArmature;
    } 
    
    public boolean isArmature(){
        return isArmature;
    }
    
    
}
