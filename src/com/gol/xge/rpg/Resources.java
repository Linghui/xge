package com.gol.xge.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Resources {
    
    private static String TAG = "Resources";
    
    private static Resources resources = null;
    
    final public static String slash            = "/";

    private Resources(){
        
    }
    
    public static Resources getInstance(){
        if( resources == null){
            resources = new Resources();
        }
        return resources;
    }
    
    public HashMap<String, Animation> readAnimationResource(String jsonFile, boolean flipX, boolean flipY, TextureAtlas atlas){
        HashMap<String, Animation> animationHash = new HashMap<String, Animation>();
//        Gdx.app.log(TAG, "jsonFile -- " + jsonFile );
        FileHandle jsonFileHandle = getFileHandle(jsonFile);
        String content = jsonFileHandle.readString();
//        String path = jsonFile.substring(0, jsonFile.lastIndexOf("/"));
        JsonParser jsonParser = new JsonParser();
        
        JsonObject actionsObj = jsonParser.parse(content).getAsJsonObject();
//        Gdx.app.log(TAG, "content -- " + content );
        String packFile = jsonFileHandle.parent()+"/pack";
//        Gdx.app.log(TAG, "packFile -====== " + packFile);
        
        actionsObj.remove("url");
        Iterator<Entry<String, JsonElement>> iter = actionsObj.entrySet().iterator();
        String defaultActionName = "";
        
//        TextureAtlas atlas = getTextureAtlasByFile(packFile);
        while(iter.hasNext()){
            Entry<String, JsonElement> entry = iter.next();
            String actionName = entry.getKey();
//            Gdx.app.log(TAG, "actionName -====== " + actionName);
            if( "".equals(defaultActionName) ){
                defaultActionName = actionName;
            }
            JsonArray texIds = entry.getValue().getAsJsonArray();
            List<TextureRegion> actionAnimation = new ArrayList<TextureRegion>();
            for( JsonElement element : texIds){
                String id = element.getAsString();
//                Gdx.app.log(TAG, "getting ||||||||||| 'id'" + id);
                TextureRegion one = atlas.createSprite(id);
                one.flip(flipX, flipY);
                actionAnimation.add(one);
            }
            Animation animation = new Animation(0.1f, actionAnimation);
            animationHash.put(actionName, animation);
//            Gdx.app.log(TAG, "done actionName ||||||||||| ''" + actionName);
        }
        return animationHash;
    }
    
    private FileHandle getFileHandle(String uri){
//    	HeapMonitor.printHeap();
    	FileHandle fh = Gdx.files.external(uri);//优先使用外部存储，以便于今后可以使用新下载的资源更新界面相关资源
    	if(fh.exists())
    		return fh;
    	else{
    		fh = Gdx.files.internal(uri);
    		return fh;
    	}
    	
    	
    }

    
}
