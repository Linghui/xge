package com.gol.xge.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.gol.xge.rpg.ui.AnimationActor;

public class CoreAnimationAction extends TemporalAction {
    private String TAG = "CoreAnimationAction";

    private String actionName = null;

    // actually only support AnimationActor here
    // do not try this action on other actors.
    public static CoreAnimationAction $ (String actionName) {
        CoreAnimationAction action = Actions.action(CoreAnimationAction.class);
        action.setActionName(actionName);
        return action;
    }
    

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
//        Gdx.app.log(TAG, "actionName === " + getActionName() + " -- on " + this.actor.getName());
        if( !(actor instanceof AnimationActor)){
            // throw out exception is better.
            return;
        }
        ((AnimationActor)actor).getAnimationGroup().setAction(getActionName(), false);
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

}
