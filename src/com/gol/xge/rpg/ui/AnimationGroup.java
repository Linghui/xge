package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gol.xge.dragonBones.objects.SkeletonAndTextureAtlasData;

public class AnimationGroup{

//    private String TAG = "AnimationGroup";

	private float currentFrameTime = 0f;
	private float timeOut = 0f;
	private float defaultSpeed = 0.1f;
	private float speedPower = 1;
	// for better performance on Android made it public instead of getter and setter, 
	// but *do not* try to modify this outside of this class
	public boolean isDone = false; 
	
	
	// for hash made
	private HashMap<String, Animation> animationHash = new HashMap<String, Animation>();
	public HashMap<String, Animation> getAnimationHash() {
        return animationHash;
    }

    private List<String> actionNames = new ArrayList<String>();

	private Animation currentAnimation = null;
	private TextureRegion keyFrame = null;;
	public  int playMode;
	private boolean animationStop = false;
	
	private float offsetX = 0f;
	private float offsetY = 0f;

	public AnimationGroup(HashMap<String, Animation> animationHash){
	    this(animationHash, 0, 0);
	}

    public AnimationGroup(HashMap<String, Animation> animationHash,
            float offsetX, float offsetY) {
        init(animationHash);
        this.offsetX = offsetX;    
        this.offsetY = offsetY;    
        
    }
	
	public AnimationGroup(SkeletonAndTextureAtlasData data) {
	    
    }

    private void init(HashMap<String, Animation> animationHash){
	    

        this.animationHash = animationHash;
        Set<String> actions = animationHash.keySet();
        Iterator<String> iter = actions.iterator();
        if(iter.hasNext()){
            String actionName = iter.next();
            this.setAction(actionName);
        }
	}
    
    // if there is same action already, over write it.
    public void mergeOverWrite( AnimationGroup group ){
        Iterator<Entry<String, Animation>> iter = group.getAnimationHash().entrySet().iterator();
        while ( iter.hasNext() ){
            Entry<String, Animation> entry = iter.next();
            this.animationHash.put(entry.getKey(), entry.getValue());
        }
    }

    // if there is same action already, do not add it with the new one.
    public void mergeCombine( AnimationGroup group ){
        Iterator<Entry<String, Animation>> iter = group.getAnimationHash().entrySet().iterator();
        while ( iter.hasNext() ){
            Entry<String, Animation> entry = iter.next();
            if( ! this.animationHash.containsKey(entry.getKey())){
                this.animationHash.put(entry.getKey(), entry.getValue());    
            }
        }
    }
	
	public boolean setAction(String actionName){
		return setAction(actionName, defaultSpeed , Animation.LOOP);
	}
	
	public boolean setAction(String actionName, boolean loop){
	    int playMode = 0;
	    if( loop ){
	        playMode = Animation.LOOP;
	    } else {
	        playMode = Animation.NORMAL;
	    }
		return setAction(actionName, defaultSpeed , playMode);
	}
	
	public boolean setAction(String actionName, int  playMode){
	    return setAction(actionName, defaultSpeed, playMode);
	}

	// set action to change the showing animation , for hash made
	public boolean setAction(String actionName, float speed,  int playMode){
	    isDone = false;
	    currentFrameTime = 0f; // always start animation for the first frame 
	    animationStop = false;
	    
		if(animationHash.containsKey(actionName)){
//			Gdx.app.log("group", " setAction - actionName : " + actionName + " loop " + loop);
			Animation animation = animationHash.get(actionName); 
			timeOut = animation.animationDuration;
			currentAnimation = animation;
			currentAnimation.setPlayMode(playMode);
			this.playMode = playMode;
			
			if( speed <= 0 ){
			    speed = this.defaultSpeed;
			}
			
			speedPower = speed/animation.frameDuration;
			return true;
		}
		// there is no animation for this action name, there is something wrong
		// so return false , that caller need to deal with this
		return false;
	}
	
	public boolean hasAction(String actionName){
	    if(animationHash.containsKey(actionName)){
	        return true;
	    } else {
	        return false;
	    }
	}
	
	// after loaded json, this tells caller what are these actions.
	public List<String> getActionNames(){
		return actionNames;
	}
	
	public TextureRegion getKeyFrame(float stateTime){
	    currentFrameTime += (stateTime / speedPower);
	    
	    if( currentAnimation != null && animationStop == false){
	        keyFrame = currentAnimation.getKeyFrame(currentFrameTime);
	    }
//	    Gdx.app.log("group", "loop = " + loop + " currentFrameTime = " + currentFrameTime + " timeOut = " + timeOut);
	    if(playMode == Animation.NORMAL && currentFrameTime >= timeOut && timeOut != 0f){
	        timeOut = 0f;
            isDone = true;
	    }
		return keyFrame;
	}
	
	public void stop(){
	    animationStop = true;
	}
	
	public int getPlayMode(){
		return playMode;
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
