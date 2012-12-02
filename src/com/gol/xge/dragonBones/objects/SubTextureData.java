package com.gol.xge.dragonBones.objects;

import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class SubTextureData {

    public float pivotX = 0;
    public float pivotY = 0;
    public String name = null;
    
    public String getName() {
        return name;
    }

    public SubTextureData(Element element) {
        pivotX = element.getFloatAttribute(ConstValues.A_PIVOT_X);
        pivotY = element.getFloatAttribute(ConstValues.A_PIVOT_Y);
        name = element.getAttribute(ConstValues.A_NAME);
    }
}
