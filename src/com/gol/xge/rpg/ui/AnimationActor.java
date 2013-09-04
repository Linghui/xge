package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
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
    
    private Group bottomLayer = new Group();
    
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
//        this.addActor(bottomLayer);
    }
    
    public void addBottomActor( Actor actor ){
        bottomLayer.addActor(actor);
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
        bottomLayer.act(delta);
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
        if( !this.isVisible() ){
            return;
        }
        
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a);
        
        bottomLayer.setX(this.getX());
        bottomLayer.setY(this.getY());
        
        bottomLayer.draw(batch, parentAlpha);
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a);
        
        float x = this.getX() - animationGroup.getOffsetX();
        if( frame.isFlipX() ){
            x = this.getX() - (this.getWidth() - animationGroup.getOffsetX());
        }
        batch.draw(frame, x, this.getY() -  animationGroup.getOffsetY(), 
                this.getOriginX(), this.getOriginY()
                , this.getWidth(), this.getHeight()
                , this.getScaleX(), this.getScaleY(), this.getRotation());
        super.draw(batch, parentAlpha);
    }
    
    @Override
    public Actor hit (float x, float y, boolean touchable) {
        return x >= -this.getWidth()/2 && x <= this.getWidth()/2 && y >= -this.getHeight()/2 && y <= this.getHeight()/2 ? this : null;
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
        int playMode = 0;
        if( loop ){
            playMode = Animation.LOOP;
        } else {
            playMode = Animation.NORMAL;
        }
        this.setAction(actionName, playMode);
    }

    
    public void setAction(String actionName, int  playMode){
        this.setAction(actionName, AnimationGroup.defaultSpeed , playMode);
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
