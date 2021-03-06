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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class XCell<T> extends Group implements Checkable{

    private String TAG = "XCell";
    
    protected TextureAtlas alats = null;
    protected Image imgB = null;
    protected Image frame = null;
    protected Image icon = null;
    protected T object = null;
    public static final String backgroundName = "imgB";
    public final String iconName = "icon";

    private final int defaultPad = 1;
    
    public XCell(NinePatch background){
        this(background, background.getTotalWidth(), background.getTotalHeight(),  null);
    }
    

    public XCell(NinePatch background, TextureAtlas alats){
        this(background, background.getTotalWidth(), background.getTotalHeight(),  alats);
    }
    
    public XCell(NinePatch background, float width, float height, TextureAtlas alats){
        imgB = new Image(background);
        imgB.setName("imgB");
        imgB.setWidth(width);
        imgB.setHeight(height);
        this.setWidth(width);
        this.setHeight(height);
        this.addActor(imgB);
        this.setAtlas(alats);
    }
    
    public void setAtlas(TextureAtlas atlas) {
        this.alats = atlas;
    }
    
    public TextureAtlas getAtlas(){
        return this.alats;
    }
    
    public void removeIcon(){
        if( icon != null ){
            icon.remove();
            icon = null;
        }
    }
    
    public void removeObject(){
        this.object = null;
    }
    
    public void removeContent(){
        removeIcon();
        removeObject();
    }
    
    public void setIcon(String name){
//        Gdx.app.log(TAG, "setIcon " + name);
        AtlasRegion region = alats.findRegion(name);
        if(icon == null){
            icon = new Image(region);
            icon.setName(iconName);
            resetIconPosition();
            this.addActor(icon);
            icon.toFront();
        } else {
            icon.setDrawable(new TextureRegionDrawable(region));    
        }
    }
    
    private void resetIconPosition() {

        if( icon == null ){
            return;
        }
        if( this.getWidth() > icon.getWidth()){

            icon.setX( (this.getWidth() - icon.getWidth())/2 );
            icon.setY( (this.getHeight() - icon.getHeight())/2 );   
        } else {
            this.setWidth(icon.getWidth());
            this.setHeight(icon.getHeight());
        }        
    }


    public void setChooseFrame(NinePatch patch){
        setChooseFrame( new NinePatchDrawable(patch) );
    }
    
    public void setChooseFrame(NinePatch patch, int pad){
        setChooseFrame( new NinePatchDrawable(patch), pad);
    }
    
    public void setChooseFrame( Drawable drawable ){
        setChooseFrame(drawable, defaultPad);
    }
    
    public void setChooseFrame( Drawable drawable, int pad ){

        if( frame == null ) {
            frame = new Image(drawable);    
        }
        
        if( drawable == null){
            if( frame != null){
                frame.remove();
            }
            frame = null;
            return;
        }
        
        resetChooseFrame(pad);
    }
    
    public void resetChooseFrame(int pad){

        this.setWidth( imgB.getWidth() + pad * 2);
        this.setHeight( imgB.getHeight() + pad * 2);

        frame.setWidth(imgB.getWidth() + pad * 2);
        frame.setHeight(imgB.getHeight() + pad * 2);
        frame.setX(0);
        frame.setY(0);
        
        imgB.setX( pad );
        imgB.setY( pad );

        resetIconPosition();
        
        this.addActor(frame);
        frame.toFront();
        frame.setVisible(false);
    }
    
    public void setObject(T object){
        this.object = object;
    }
    
    public T getObject(){
        return this.object;
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
        return frame.isVisible();
    }
    
    @Override
    public Actor hit (float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight() ? this : null;
    }
}
