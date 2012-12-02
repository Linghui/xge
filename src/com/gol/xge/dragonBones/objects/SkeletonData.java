package com.gol.xge.dragonBones.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class SkeletonData {
    
    private Array<String> armatureList = new Array<String>();
    private Map<String, ArmatureData> armatureDataHash = new HashMap<String, ArmatureData>();
    
    private Array<String> animationList = new Array<String>();
    private Map<String, AnimationData> animationDataHash = new HashMap<String, AnimationData>();
    

    public SkeletonData(Element skeletonElement) {
        
        Array<Element> allArmElements = skeletonElement.getChildByName(ConstValues.ARMATURES).getChildrenByName(ConstValues.ARMATURE);
        for(Element armElement : allArmElements){
            
            ArmatureData armatureData = new ArmatureData(armElement);
            armatureDataHash.put(armElement.getAttribute(ConstValues.A_NAME), armatureData);
            armatureList.add(armatureData.getName());
        }
        
        Array<Element> allAniElements = skeletonElement.getChildByName(ConstValues.ANIMATIONS).getChildrenByName(ConstValues.ANIMATION);
        for(Element aniElement : allAniElements){
            AnimationData animationData = new AnimationData(aniElement);
            animationDataHash.put(aniElement.getAttribute(ConstValues.A_NAME), animationData);
            animationList.add(animationData.getName());
        }
    }
    

    public Array<String> getArmatureList() {
        return armatureList;
    }

    public ArmatureData getArmatureData(String name){
        return armatureDataHash.get(name);
    } 
    
    public AnimationData getAnimationData(String name){
        return animationDataHash.get(name);
    }

    public Array<String> getAnimationList() {
        return animationList;
    }
}
