package com.gol.xge.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonValue;
import com.gol.xge.resolver.DecodeFileHandle;
import com.gol.xge.rpg.ui.AnimationGroup;
import com.gol.xge.rpg.ui.AnimationResource;

public class XGECommon {

    public static FileHandle openFile( String fileName ){
        return openFile(fileName, null);
    }
    
    public static FileHandle openFile( String fileName, String code ){
        FileHandle fh = Gdx.files.external(fileName);
        if(!fh.exists()){
        	//uncommnet below for robovm
//        	if(fileName.indexOf("data/") > 0)
//        		fileName = fileName.substring(fileName.indexOf("data/"));

            fh = Gdx.files.internal(fileName);
        }
        if( code == null ){
            return fh;
        }
        DecodeFileHandle dfh = new DecodeFileHandle(fh, code);
        return dfh;
    }
    
    public static String readFile(String fileName){
        FileHandle fh = openFile(fileName);
        if( !fh.exists() ){
            return null;
        }
        return fh.readString("UTF-8");
    }

    public static void writeFile(String filePath, String json) {
        FileHandle fh = Gdx.files.external(filePath);
        try{
            fh.writeString(json, false);    
        }
        catch( GdxRuntimeException e){
            e.printStackTrace();
        }
        
    }
    public static void writeFileToAbs(String filePath, String json) {
        FileHandle fh = Gdx.files.absolute(filePath);
        try{
            fh.writeString(json, false);    
        }
        catch( GdxRuntimeException e){
            e.printStackTrace();
        }
        
    }
    
    public static AnimationGroup readAnimationGroup(
            JsonValue json,
            TextureAtlas atlas){
        AnimationResource ar = new AnimationResource(json);
        return readAnimationGroup(ar, atlas, 0.1f);
    }
    
    public static AnimationGroup readAnimationGroup(
            AnimationResource ar,
            TextureAtlas atlas,
            float speed){

        HashMap<String, Animation> animationHash = new HashMap<String, Animation>();
        
        JsonValue actionsObj = ar.getResourceMap();
//        Gdx.app.log(TAG, "content -- " + content );
        
        String defaultActionName = "";
        
//        TextureAtlas atlas = getTextureAtlasByFile(packFile);
        for(JsonValue action = actionsObj.child(); action != null; action = action.next()){
            String actionName = action.name();
            
            if ( actionName.equals("offsetX")
                    || actionName.equals("offsetY")) {
                continue;
            }
            
            
//          Gdx.app.log(TAG, "actionName -====== " + actionName);
            if( "".equals(defaultActionName) ){
                defaultActionName = actionName;
            }
            
            List<TextureRegion> actionAnimation = new ArrayList<TextureRegion>();
            
            for(JsonValue texIds = action.child(); texIds != null; texIds = texIds.next()){

                String id = texIds.asString();
//                Gdx.app.log(TAG, "getting ||||||||||| 'id'" + id);
                TextureRegion one = atlas.createSprite(id);
                actionAnimation.add(one);
            }
            TextureRegion[] regions = new TextureRegion[actionAnimation.size()];
            for(int index = 0 ; index < actionAnimation.size(); index++){
                regions[index] = actionAnimation.get(index);
            }
            Animation animation = new Animation(speed, regions);
            animationHash.put(actionName, animation);
//            Gdx.app.log(TAG, "done actionName ||||||||||| ''" + actionName);
        }
        
        AnimationGroup group = new AnimationGroup(
                animationHash
                , ar.getOffsetX()
                , ar.getOffsetY());
        return group;
    }
    
    // tag reminds you something
    public static void timeStamp(String tag){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        System.out.println("time stamp - "
        + calendar.get(Calendar.MINUTE) + ":"
        + calendar.get(Calendar.SECOND) + ":"
        + calendar.get(Calendar.MILLISECOND ) 
        + " -- " + tag );
    }
}
