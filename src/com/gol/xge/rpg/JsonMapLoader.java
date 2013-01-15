package com.gol.xge.rpg;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.OrderedMap;

public class JsonMapLoader extends AsynchronousAssetLoader<OrderedMap, JsonMapLoader.parameter> {

    private OrderedMap json = null;
    public JsonMapLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class parameter extends AssetLoaderParameters<OrderedMap>{

    }

    @Override
    public void loadAsync(AssetManager manager, String fileName,
            parameter parameter) {
        FileHandle handle = resolve(fileName);

        String jsonStr = handle.readString("UTF-8");

        json = (OrderedMap) new JsonReader().parse(jsonStr);
    }

    @Override
    public OrderedMap loadSync(AssetManager manager, String fileName,
            parameter parameter) {
        return json;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName,
            parameter parameter) {
        return null;
    }

}
