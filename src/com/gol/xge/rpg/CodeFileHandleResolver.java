package com.gol.xge.rpg;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.gol.xge.XGECommon;

public class CodeFileHandleResolver implements FileHandleResolver {

    private String code = null;
    public CodeFileHandleResolver(){
        code = null;
    }
    
    public CodeFileHandleResolver( String code ){
        this.code = code;
    }
    
    @Override
    public FileHandle resolve(String fileName) {
        return XGECommon.openFile(fileName, code);
    }
}
