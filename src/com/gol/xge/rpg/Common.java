package com.gol.xge.rpg;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;


public class Common {
    
    private static String TAG = "Common";
    
    final public static String URIHead      = "gol";
    final public static String headSplit    = "://";
    final public static String xgeHead      = URIHead + headSplit;
    final public static String midSplit     = Resources.slash;
    final public static String paraStart    = "\\?";
    final public static String paraSplit    = "&";
    final public static String valueSplit   = "=";
    
    // get path pieces of link
    // gol://map/20001?x=100 returns {map, 20001}
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
    // gol://path?x=1 returns gol://path
    static public String getPath(String uri){
        String path = null;
        String[] pieces = uri.split(paraStart);
        path = pieces[0];
        return path;
    }
    
    // get parameter hash of link
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
}
