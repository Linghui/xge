package com.gol.xge.rpg.scense;

import com.badlogic.gdx.graphics.Texture;

public interface ResLoadedListener<T> {

    public void update(String resName, T res);
}
