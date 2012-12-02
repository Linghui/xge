package com.gol.xge.dragonBones.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class MovementData {
    
    private String name = null;
    private int duration = 0;
    private int durationTo = 0;
    private int durationTween = 0;
    private boolean loop = false;
    private float tweenEasing = 0f;

    private Map<String, MovementBoneData> movementBoneDatas = new HashMap<String, MovementBoneData>();
    private Array<MovementFrameData> movementFrameDatas = new Array<MovementFrameData>();
    
    public MovementData(Element element) {
        name        = element.getAttribute(ConstValues.A_NAME);
        duration    = element.getIntAttribute(ConstValues.A_DURATION);
        durationTo    = element.getIntAttribute(ConstValues.A_DURATION_TO);
        durationTween = element.getIntAttribute(ConstValues.A_DURATION_TWEEN);
        loop        = element.getBooleanAttribute(ConstValues.A_LOOP, true);
        tweenEasing = element.getFloatAttribute(ConstValues.A_TWEEN_EASING);
        
    }

    public String getName() {
        return name;
    }

}
