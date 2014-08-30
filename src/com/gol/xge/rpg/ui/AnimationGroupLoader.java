package com.gol.xge.rpg.ui;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationGroupLoader extends SynchronousAssetLoader<AnimationGroup, AnimationGroupLoader.AnimationGroupParameter>{

    public AnimationGroupLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public class AnimationGroupParameter extends AssetLoaderParameters<AnimationGroup>{

    }

	@Override
	public AnimationGroup load(AssetManager arg0, String fileName, FileHandle arg2,
			AnimationGroupParameter arg3) {
		// TODO Auto-generated method stub
		FileHandle handle = this.resolve(fileName);
        TextureAtlas altas = new TextureAtlas(handle);
		return null;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String arg0, FileHandle arg1,
			AnimationGroupParameter arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
