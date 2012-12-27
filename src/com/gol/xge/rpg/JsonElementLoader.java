package com.gol.xge.rpg;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonElementLoader extends AsynchronousAssetLoader<JsonElement, JsonElementLoader.parameter> {

    private JsonElement json = null;
    public JsonElementLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class parameter extends AssetLoaderParameters<JsonElement>{

    }

    @Override
    public void loadAsync(AssetManager manager, String fileName,
            parameter parameter) {
        FileHandle handle = resolve(fileName);

        String jsonStr = handle.readString("UTF-8");

        JsonParser parser = new JsonParser();
        json = parser.parse(jsonStr);
    }

    @Override
    public JsonElement loadSync(AssetManager manager, String fileName,
            parameter parameter) {
        return json;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName,
            parameter parameter) {
        return null;
    }

}
