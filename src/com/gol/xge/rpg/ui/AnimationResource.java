package com.gol.xge.rpg.ui;

import com.badlogic.gdx.utils.ObjectMap;

public class AnimationResource {
	private ObjectMap<String, String[]> resourceMap;

	public AnimationResource(ObjectMap<String, String[]> map) {
		this.resourceMap = map;
	}

	public ObjectMap<String, String[]> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(ObjectMap<String, String[]> resourceMap) {
		this.resourceMap = resourceMap;
	}

}
