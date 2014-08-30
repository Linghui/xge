package com.gol.xge.assetsLoader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class JsonLoader extends AsynchronousAssetLoader<JsonValue, JsonLoader.parameter> {

    private JsonValue json = null;
    public JsonLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class parameter extends AssetLoaderParameters<JsonValue>{

    }


	@Override
	public void loadAsync(AssetManager arg0, String arg1, FileHandle arg2,
			parameter arg3) {

        FileHandle handle = resolve(arg1);

        String jsonStr = handle.readString("UTF-8");

        json = new JsonReader().parse(jsonStr);		
	}

	@Override
	public JsonValue loadSync(AssetManager arg0, String arg1, FileHandle arg2,
			parameter arg3) {
		// TODO Auto-generated method stub
		return json;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String arg0, FileHandle arg1,
			parameter arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
