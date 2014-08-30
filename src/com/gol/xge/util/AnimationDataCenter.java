package com.gol.xge.util;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gol.xge.rpg.ui.AnimationGroup;

public class AnimationDataCenter {

	private static AnimationDataCenter self = null;
	private HashMap<TextureAtlas, HashMap<String, Animation>> map = new HashMap<TextureAtlas, HashMap<String, Animation>>();

	private AnimationDataCenter() {

	}

	public static AnimationDataCenter getInstance() {
		if (self == null) {
			self = new AnimationDataCenter();
		}
		return self;
	}

	public void save(TextureAtlas key, HashMap<String, Animation> value) {
		map.put(key, value);
	}

	public HashMap<String, Animation> get(TextureAtlas key) {
		return map.get(key);
	}
}
