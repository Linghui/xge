package com.gol.xge.rpg;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class JsonLoader extends AsynchronousAssetLoader<Json, JsonLoader.parameter> {

    public JsonLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class parameter extends AssetLoaderParameters<Json>{

    }

    @Override
    public void loadAsync(AssetManager manager, String fileName,
            parameter parameter) {
        
    }

    @Override
    public Json loadSync(AssetManager manager, String fileName,
            parameter parameter) {
        return null;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName,
            parameter parameter) {
        return null;
    }

}
