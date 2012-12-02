package com.gol.xge.dragonBones.objects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class BoneData extends Node{

    private String name = null;
    private String parent = null;
    
    private Array<DisplayData> displayList = new Array<DisplayData>();

    public BoneData(Element boneElement) {
        name    = boneElement.getAttribute(ConstValues.A_NAME);
        x       = boneElement.getFloatAttribute(ConstValues.A_X);
        y       = boneElement.getFloatAttribute(ConstValues.A_Y);
        skewX   = boneElement.getFloatAttribute(ConstValues.A_SKEW_X);
        skewY   = boneElement.getFloatAttribute(ConstValues.A_SKEW_Y);
        z       = boneElement.getIntAttribute(ConstValues.A_Z);
        
        Array<Element> displays = boneElement.getChildrenByName(ConstValues.DISPLAY);
        for(Element element : displays){
            DisplayData display = new DisplayData(element.getAttribute(ConstValues.A_NAME)
                    , element.getBoolean(ConstValues.A_IS_ARMATURE, true));
            displayList.add(display);
        }
    }
    public String getName() {
        return name;
    }
    
    public String getParent() {
        return parent;
    }

    public DisplayData getDisplayDataAt(int index){
        return this.displayList.get(index);
    }
}
