package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

public class AnimationActor extends Group implements AnimationActionInterface{

    private String TAG = "AnimationActor";
    protected Rectangle rectangle = null;
    
    private List<String> actionAdded = new ArrayList<String>();
    
    AnimationGroup animationGroup;
    TextureRegion frame;

    public AnimationActor(AnimationGroup animationGroup){
        this(null, animationGroup);
    }
    public AnimationActor(String name, AnimationGroup animationGroup){
        super(name);
        this.animationGroup = animationGroup;
        frame = animationGroup.getKeyFrame(0f);
        this.width = animationGroup.getKeyFrame(0).getRegionWidth();
        this.height = animationGroup.getKeyFrame(0).getRegionHeight();
        this.touchable = true;
    }

    /*
     * different from setAction
     */
    @Override
    public AnimationActor addAction(String actionName){
        if(animationGroup.hasAction(actionName)){
            actionAdded.add(actionName);
        }
        return this;
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        frame = animationGroup.getKeyFrame(delta);
    }
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(color.r, color.g, color.b, color.a);
        batch.draw(frame, x, y, Math.abs(this.width*scaleX), Math.abs(this.height*scaleY));
    }
    
    @Override
    public Actor hit (float x, float y) {
        return x > 0 && x < width && y > 0 && y < height ? this : null;
    }
    
    public void setAction(String actionName){
        this.animationGroup.setAction(actionName);
    }
    

    public void setAction(String actionName, boolean loop){
        this.animationGroup.setAction(actionName, loop);
    }

    public void setAnimationGroup(AnimationGroup animationGroup){
        this.animationGroup = animationGroup;
    }
    
    public AnimationGroup getAnimationGroup(){
        return animationGroup;
    }

    public void setCollision(boolean able){
        rectangle = new Rectangle(x,y,this.width,this.height);
    }
    
    public void setCollistionArea(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public Rectangle getCollistionArea(){
        return rectangle;
    }

}
