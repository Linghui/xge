package com.gol.xge.core.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.gol.xge.rpg.Resources;


/*
 * there are two made for initialize Animation Group
 * 	1. Array Made
 * 		just wrap up Animations to a group , use index to change showing animation 
 *  2. Hash Made : suggest to use hash made, easy :)
 *  	for this made, a json file with animation configuration is needed.:
 *  	example:
 *  	{
 *  	image : "$image" (only accept internal file, so need to begin with data/, like data/xxx.png)
 *  	actions : 
 *  	{
 *  		$action_name : {
 *  			speed : $speed (float)
 *  			TextureRegions : [
 *  				{
 *  					x : $x, (int)
 *  					y : $y, (int)
 *  					width : $width, (int)
 *  					height : $height, (int)
 *  					
 *  				}
 *  			],
 *  			......
 *  		},
 *  		......
 *  	}
 *  	
 *  	}
 *  	
 */
public class AnimationGroup{

    private String TAG = "AnimationGroup";

	// for array made
//	private Animation[] animationsArray;
//	private int animationSize = 0;
//	private int currentIndex = 0;
    
    
	private float currentFrameTime = 0f;
	private float timeOut = 0f;
	private float defaultSpeed = 0.15f;
	// for better performance on Android made it public instead of getter and setter, 
	// but *do not* try to modify this outside of this class
	public boolean isDone = false; 
	
	
	// for hash made
	private HashMap<String, Animation> animationHash = new HashMap<String, Animation>();
	private List<String> actionNames = new ArrayList<String>();

	private Animation currentAnimation = null;
	private TextureRegion keyFrame = null;;
	public  boolean loop = true;
	private boolean animationStop = false;
	private String packPath = null;

	public AnimationGroup(HashMap<String, Animation> animationHash){
	    init(animationHash);
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
	
	public boolean setAction(String actionName){
		return setAction(actionName, defaultSpeed , true);
	}
	
	public boolean setAction(String actionName, boolean loop){
		return setAction(actionName, defaultSpeed , loop);
	}

	// set action to change the showing animation , for hash made
	public boolean setAction(String actionName, float speed,  boolean loop){
	    isDone = false;
	    currentFrameTime = 0f; // always start animation for the first frame 
	    animationStop = false;
		if(animationHash.containsKey(actionName)){
//			Gdx.app.log(TAG, " setAction - actionName : " + actionName);
			Animation animation = animationHash.get(actionName); 
			timeOut = animation.animationDuration;
			currentAnimation = animation;
			this.loop = loop;
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
	    currentFrameTime += stateTime;
	    
	    if( currentAnimation != null && animationStop == false){
	        keyFrame = currentAnimation.getKeyFrame(currentFrameTime, loop);
	    }
//	    Gdx.app.log(TAG, "loop = " + loop + "currentFrameTime = " + currentFrameTime + "timeOut = " + timeOut);
	    if(loop == false && currentFrameTime > timeOut && timeOut != 0f){
	        timeOut = 0f;
            isDone = true;
	    }
		return keyFrame;
	}
	
	public void stop(){
	    animationStop = true;
	}
	
	public boolean getLoop(){
		return loop;
	}

}
