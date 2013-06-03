package com.gol.xge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class XGECommon {

    public static FileHandle openFile( String fileName ){
        return openFile(fileName, null);
    }
    
    public static FileHandle openFile( String fileName, String code ){
        FileHandle fh = Gdx.files.external(fileName);
        if(!fh.exists()){
            fh = Gdx.files.internal(fileName);
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

    public static void writeFile(String userconfigjson, String json) {
        FileHandle fh = Gdx.files.external(userconfigjson);
        try{
            fh.writeString(json, false);    
        }
        catch( GdxRuntimeException e){
            e.printStackTrace();
        }
        
    }
}
