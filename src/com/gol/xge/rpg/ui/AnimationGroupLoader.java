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

    @Override
    public AnimationGroup load(AssetManager assetManager, String fileName,
            AnimationGroupParameter parameter) {
        FileHandle handle = this.resolve(fileName);
        TextureAtlas altas = new TextureAtlas(handle);
        
        return null;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName,
            AnimationGroupParameter parameter) {
        // TODO Auto-generated method stub
        return null;
    }

    public class AnimationGroupParameter extends AssetLoaderParameters<AnimationGroup>{

    }
}
