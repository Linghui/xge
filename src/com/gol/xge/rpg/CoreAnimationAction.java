package com.gol.xge.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.gol.xge.rpg.ui.AnimationActor;

public class CoreAnimationAction extends TemporalAction {
    private String TAG = "CoreAnimationAction";

    private String actionName = null;
    private float speed = 0.1f;

    private int playMode = Animation.NORMAL;

    // actually only support AnimationActor here
    // do not try this action on other actors.
    public static CoreAnimationAction $ (String actionName) {
        CoreAnimationAction action = Actions.action(CoreAnimationAction.class);
        action.setActionName(actionName);
        return action;
    }
    
    public static CoreAnimationAction $ (String actionName, float speed) {
        CoreAnimationAction action = Actions.action(CoreAnimationAction.class);
        action.setActionName(actionName);
        action.setSpeed(speed);
        return action;
    }

    
    public static CoreAnimationAction $ (String actionName, float speed, int playMode) {

        CoreAnimationAction action = Actions.action(CoreAnimationAction.class);
        action.setActionName(actionName);
        action.setSpeed(speed);
        action.setPlayMode(playMode);
        return action;
    }
    
    private void setPlayMode(int playMode) {
        this.playMode  = playMode;
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        
//        Gdx.app.log(TAG, "actionName === " + getActionName() + " -- on " + this.actor.getName());
        if( !(actor instanceof AnimationActor)){
            // throw out exception is better.
            return;
        }
        ((AnimationActor)actor).setAction(getActionName(), speed, playMode);
    }

    @Override
    public boolean act(float delta) {
        boolean complete = ((AnimationActor)actor).getAnimationGroup().isDone;
//        Gdx.app.log(TAG, "act " + complete);
        return complete;
    }


    @Override
    protected void update(float percent) {
        
    }


    public String getActionName() {
        return actionName;
    }


    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
