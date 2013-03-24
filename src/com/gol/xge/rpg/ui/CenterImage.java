package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CenterImage extends Group {

    public CenterImage( Texture texture){
        Image image = new Image(texture);
        image.setX(-image.getWidth()/2);
        image.setY(-image.getHeight()/2);
        this.addActor(image);
    }
    
}
