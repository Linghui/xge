package com.gol.xge.rpg.ui;

import com.badlogic.gdx.utils.JsonValue;

public class AnimationResource {
	private JsonValue resourceMap;
	private float offsetX;
	private float offsetY;

	public AnimationResource(JsonValue map) {
		this.resourceMap = map;
	}

	public AnimationResource(JsonValue jsonData, Float offsetX,
            Float offsetY) {
	    this.resourceMap = jsonData;
	    this.offsetX = offsetX;
	    this.offsetY = offsetY;
    }

    public JsonValue getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(JsonValue resourceMap) {
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
