package com.gol.xge.dragonBones.objects;

import java.io.IOException;

import org.w3c.dom.Document;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader.Element;

public class SkeletonAndTextureAtlasData implements Disposable{

    public SkeletonData skeletonData = null;
    public TextureAtlas atlas = null;
    public TextureAtlasData textureAtlasData = null;
    
    public SkeletonAndTextureAtlasData(Element skeletonElement,
            Element textureElement, byte[] textureBytes) {
        System.out.println(skeletonElement.getName());
        System.out.println(textureElement.getName());
        
        skeletonData = new SkeletonData(skeletonElement);
        
        atlas = XMLDataParser.paresTextureData(textureElement, textureBytes);
        
        textureAtlasData = new TextureAtlasData(textureElement);
    }

    @Override
    public void dispose() {
        skeletonData = null;
        atlas.dispose();
        
    }
    
    

}
