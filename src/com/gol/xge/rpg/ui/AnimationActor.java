package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class AnimationActor extends Group implements AnimationActionInterface{

    private String TAG = "AnimationActor";
    protected Rectangle rectangle = null;
    
    private List<String> actionAdded = new ArrayList<String>();
    
    AnimationGroup animationGroup;
    TextureRegion frame;
    
    private boolean flipX = false;
    
    public AnimationActor(){
        
    }
    
    public AnimationActor(AnimationGroup animationGroup){
        this(null, animationGroup, false);
    }
    
    public AnimationActor(String name, AnimationGroup animationGroup, boolean flipX){
        this.setName(name);
        this.animationGroup = animationGroup;
        this.flipX = flipX;
        
        this.setAnimationGroup(animationGroup);
    }

    /*
     * different from setAction
     */
    @Override
    public AnimationActor addAction(String actionName){
        
        
        if( animationGroup!= null 
                && animationGroup.hasAction(actionName)){
            actionAdded.add(actionName);
        }
        return this;
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);

        if( animationGroup == null ){
            return;
        }
        
        frame = animationGroup.getKeyFrame(delta);
        
        if( flipX ){
            if( ! frame.isFlipX() ){
                frame.flip(true, false);
            }
        } else {
            if( frame.isFlipX() ){
                frame.flip(true, false);
            }
        }
        
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        if( animationGroup == null ){
            return;
        }
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a);
        batch.draw(frame, this.getX() - animationGroup.getOffsetX(), this.getY() -  animationGroup.getOffsetY(), 
                this.getOriginX(), this.getOriginY()
                , this.getWidth(), this.getHeight()
                , this.getScaleX(), this.getScaleY(), this.getRotation());
        super.draw(batch, parentAlpha);
    }
    
    @Override
    public Actor hit (float x, float y, boolean touchable) {
        return x > 0 && x < this.getWidth() && y > 0 && y < this.getHeight() ? this : null;
    }
    
    public boolean getFlipX(){
        return this.flipX;
    }
    
    public void setFlipX( boolean flipX ){
        this.flipX = flipX;
    }
    
    public void setAction(String actionName){
        this.setAction(actionName, false);
    }
    

    public void setAction(String actionName, boolean loop){
        if( animationGroup == null ){
            return;
        }
        this.animationGroup.setAction(actionName, loop);
    }

    
    public void setAction(String actionName, int  playMode){
        this.animationGroup.setAction(actionName, playMode);
    }
    

    public void setAction(String actionName, float speed, int  playMode){
        this.animationGroup.setAction(actionName, speed, playMode);
    }
    
    public void setAnimationGroup(AnimationGroup animationGroup){
        this.animationGroup = animationGroup;

        if( animationGroup == null ){
            return;
        }
        
        frame = animationGroup.getKeyFrame(0f);
        this.setWidth(Math.abs(animationGroup.getKeyFrame(0).getRegionWidth()));
        this.setHeight(Math.abs(animationGroup.getKeyFrame(0).getRegionHeight()));
        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.setTouchable(Touchable.enabled);
        
    }
    
    public AnimationGroup getAnimationGroup(){
        return animationGroup;
    }

    public void setCollistionArea(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public Rectangle getCollistionArea(){
        return rectangle;
    }

}
