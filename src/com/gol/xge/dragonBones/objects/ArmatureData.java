package com.gol.xge.dragonBones.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class ArmatureData {
    
    private String name = null;
    
    private Array<String> boneList = new Array<String>();
    private Map<String, BoneData> boneDatas = new HashMap<String, BoneData>();

    
    public ArmatureData(Element armElement) {
        
        name = armElement.getAttribute(ConstValues.A_NAME);
        
        Array<Element> boneElements = armElement.getChildrenByName(ConstValues.BONE);
        for(Element element : boneElements){
            BoneData bone = new BoneData(element);
            boneDatas.put(bone.getName(), bone);
            boneList.add(bone.getName());
        }
    }


    public String getName() {
        return name;
    }
    
    public BoneData getBoneData(String name){
        return this.boneDatas.get(name);
    }

    public Array<String> getBoneList(){
        return this.boneList;
    }
}
