package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class XCell extends Group implements Checkable{

    private String TAG = "XCell";
    
    private TextureAtlas alats = null;
    private Image imgB = null;
    private Image frame = null;
    private Image icon = null;
    
    public XCell(NinePatch background){
        this(background, background.getTotalWidth(), background.getTotalHeight(),  null);
    }
    
    public XCell(NinePatch background, float width, float height, TextureAtlas alats){
        Image imgB = new Image(background);
        imgB.setWidth(width);
        imgB.setHeight(height);
        this.setWidth(width);
        this.setHeight(height);
        this.addActor(imgB);
        this.setAlats(alats);
    }
    
    public XCell(TextureRegion background){
        this(background, null);
    }
    
    public XCell(TextureRegion background, TextureAtlas alats){
        imgB = new Image(background);
        this.addActor(imgB);
        this.setAlats(alats);
    }
    
    public void setAlats(TextureAtlas alats) {
        this.alats = alats;
    }
    
    public void setIcon(String name){
        AtlasRegion region = alats.findRegion(name);
        if(icon == null){
            icon = new Image(region);
            icon.setX( (this.getWidth() - icon.getWidth())/2 );
            icon.setY( (this.getHeight() - icon.getHeight())/2 );
            this.addActor(icon);
            icon.toFront();
        } else {
            icon.setDrawable(new TextureRegionDrawable(region));    
        }
    }
    
    public void setChooseFrame(NinePatch patch){
        frame = new Image(patch);
        frame.setWidth(this.getWidth() + 2);
        frame.setHeight(this.getHeight() + 2);
        frame.setX(-1);
        frame.setY(-1);
        this.addActor(frame);
        frame.toFront();
        frame.setVisible(false);
    }

    @Override
    public void setChecked(boolean check) {
        if( frame == null){
            return;
        }
        frame.setVisible(check);
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public Actor hit (float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight() ? this : null;
    }
}