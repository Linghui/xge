package com.gol.xge.rpg.ui;

import com.badlogic.gdx.utils.ObjectMap;

public class AnimationResource {
	private String url;
	private ObjectMap<String, String[]> resourceMap;

	public AnimationResource(String url, ObjectMap<String, String[]> map) {
		this.url = url;
		this.resourceMap = map;
	}

	public ObjectMap<String, String[]> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(ObjectMap<String, String[]> resourceMap) {
		this.resourceMap = resourceMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
