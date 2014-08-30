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

public class StringLoader extends AsynchronousAssetLoader<String, StringLoader.parameter> {

    private String content = null;
    public StringLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class parameter extends AssetLoaderParameters<String>{

    }

	@Override
	public void loadAsync(AssetManager arg0, String fileName, FileHandle arg2,
			parameter arg3) {
		// TODO Auto-generated method stub

        FileHandle handle = resolve(fileName);

        content = handle.readString("UTF-8");
	}

	@Override
	public String loadSync(AssetManager arg0, String arg1, FileHandle arg2,
			parameter arg3) {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String arg0, FileHandle arg1,
			parameter arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
