package com.gol.xge.dragonBones.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.ConstValues;

public class TextureAtlasData {

    private Map<String, SubTextureData> subTextureDatas = new HashMap<String, SubTextureData>();
    public TextureAtlasData(Element textureElement){
        Array<Element> subTextureElements = textureElement.getChildrenByName(ConstValues.SUB_TEXTURE);
        for(Element element : subTextureElements){
            SubTextureData subTextureData = new SubTextureData(element);
            this.subTextureDatas.put(subTextureData.getName(), subTextureData);
        }
    }
    
    public SubTextureData getSubTextureData(String name){
        return this.subTextureDatas.get(name);
    }
}
