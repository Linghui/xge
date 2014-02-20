package com.gol.xge.resolver;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.gol.xge.util.XGECommon;

public class InAndExternalFileHandleResolver implements FileHandleResolver {

    @Override
    public FileHandle resolve(String fileName) {
        return XGECommon.openFile(fileName);
    }

}
