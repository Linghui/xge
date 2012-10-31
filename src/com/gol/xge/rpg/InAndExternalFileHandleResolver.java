package com.gol.xge.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class InAndExternalFileHandleResolver implements FileHandleResolver {

    @Override
    public FileHandle resolve(String fileName) {
        FileHandle fh = Gdx.files.external(fileName);
        if(!fh.exists()){
            fh = Gdx.files.internal(fileName);
        }
        return fh;
    }

}
