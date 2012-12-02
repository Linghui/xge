package com.gol.xge.dragonBones.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class AnimationData {

    private String name = null;
    
    private Map<String, MovementData> movementDatas = new HashMap<String, MovementData>();
    
    public AnimationData(Element aniElement) {
        name = aniElement.getAttribute(ConstValues.A_NAME);
        
        Array<Element> movements = aniElement.getChildrenByName(ConstValues.MOVEMENT);
        for(Element element : movements){
            MovementData MovementData = new MovementData(element);
            movementDatas.put(MovementData.getName(), MovementData);
        }
    }

    public String getName() {
        return name;
    }

}
