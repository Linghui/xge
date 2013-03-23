package com.gol.xge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class XGECommon {

    public static FileHandle openFile( String fileName ){
        FileHandle fh = Gdx.files.external(fileName);
        if(!fh.exists()){
            fh = Gdx.files.internal(fileName);
        }
        return fh;
    }
    
    public static String readFile(String fileName){
        FileHandle fh = openFile(fileName);
        if( fh == null ){
            return null;
        }
        return fh.readString("UTF-8");
    }
}
