package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class CenterImage extends Group {

    public CenterImage( Texture texture){
        Image image = new Image(texture);
        image.setX(-image.getWidth()/2);
        image.setY(-image.getHeight()/2);
        this.addActor(image);
    }

    
    public CenterImage( Drawable drawable){
        Image image = new Image(drawable);
        image.setX(-image.getWidth()/2);
        image.setY(-image.getHeight()/2);
        this.addActor(image);
    }
    
    public void setFlipX(boolean flipX) {
        
    }
    
}
