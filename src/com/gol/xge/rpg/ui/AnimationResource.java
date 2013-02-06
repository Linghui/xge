package com.gol.xge.rpg.ui;

import com.badlogic.gdx.utils.ObjectMap;

public class AnimationResource {
	private ObjectMap<String, String[]> resourceMap;
	private float offsetX;
	private float offsetY;

	public AnimationResource(ObjectMap<String, String[]> map) {
		this.resourceMap = map;
	}

	public AnimationResource(ObjectMap<String, String[]> map, Float offsetX,
            Float offsetY) {
	    this.resourceMap = map;
	    this.offsetX = offsetX;
	    this.offsetY = offsetY;
    }

    public ObjectMap<String, String[]> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(ObjectMap<String, String[]> resourceMap) {
		this.resourceMap = resourceMap;
	}

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

}
