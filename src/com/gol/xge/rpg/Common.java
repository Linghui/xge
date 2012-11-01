package com.gol.xge.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Common {
    
    private static String TAG = "Common";
    
    final public static String URIHead      = "gol";
    final public static String headSplit    = "://";
    final public static String xgeHead      = URIHead + headSplit;
    final public static String midSplit     = "/";
    final public static String paraStart    = "\\?";
    final public static String paraSplit    = "&";
    final public static String valueSplit   = "=";
    
    // get path pieces of link
    // example : 
    //  gol://map/20001?x=100 returns [map, 20001]
    static public String[] decodeURI(String uri){
        String[] result = null;
        if( uri == null || "".equals(uri.trim()) ){
            // do something, may just return null
            Gdx.app.log(TAG, "decodeURI uri null");
        } else {
            String[] pathPara = uri.split(paraStart);
            String[] pieces = pathPara[0].split(headSplit);
            if( pieces[0].equals(URIHead) ){
                // now we have no key=value needs , so just return the result.
                // need more process in the future
                result = pieces[1].split(midSplit);
            } else {
                // do something, may just return null
            }
        }
        
        return result;
    }
    
    // get path part of link with gol head
    // example : 
    //  gol://path?x=1 returns gol://path
    static public String getPath(String uri){
        String path = null;
        String[] pieces = uri.split(paraStart);
        path = pieces[0];
        return path;
    }
    
    // get parameter hash of link
    // example : 
    //  gol://path?x=1 returns {x => 1}
    static public HashMap<String, String> decodeURIParamters(String uri){
        HashMap<String, String> keyValues = null;
        if( uri == null || "".equals(uri.trim()) ){
            // do something, may just return null
//            Gdx.app.log(TAG, "decodeURIParamters uri null " + uri);
        } else {
            keyValues = new HashMap<String, String>();
            String[] pathPara = uri.split(paraStart);
            if(pathPara.length == 2){
                String[] pieces = pathPara[1].split(paraSplit);
                for(int index = 0; index < pieces.length;index++){
                    String[] KV = pieces[index].split(valueSplit);
                    if(KV.length == 2){
                        keyValues.put(KV[0], KV[1]);
                    }
                }
            } else {
//                Gdx.app.log(TAG, "decodeURIParamters uri has no parameters '" + uri +"'");
            }
        }
        
        return keyValues;
    }
    

    public static HashMap<String, Animation> readAnimationResource(String jsonFile, boolean flipX, boolean flipY, TextureAtlas atlas){
        HashMap<String, Animation> animationHash = new HashMap<String, Animation>();
//        Gdx.app.log(TAG, "jsonFile -- " + jsonFile );
        FileHandle jsonFileHandle = getFileHandle(jsonFile);
        String content = jsonFileHandle.readString();
//        String path = jsonFile.substring(0, jsonFile.lastIndexOf("/"));
        JsonParser jsonParser = new JsonParser();
        
        JsonObject actionsObj = jsonParser.parse(content).getAsJsonObject();
//        Gdx.app.log(TAG, "content -- " + content );
        
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
    
    public static FileHandle getFileHandle(String uri){
//      HeapMonitor.printHeap();
        FileHandle fh = Gdx.files.external(uri);//优先使用外部存储，以便于今后可以使用新下载的资源更新界面相关资源
        if(fh.exists())
            return fh;
        else{
            fh = Gdx.files.internal(uri);
            return fh;
        }
        
        
    }
}
