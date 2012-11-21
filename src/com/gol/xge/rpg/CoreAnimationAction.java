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
        action.actionName = actionName;
        return action;
    }
    

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
//        Gdx.app.log(TAG, "actionName === " + actionName + " -- on " + actor.name);
        if( !(actor instanceof AnimationActor)){
            // throw out exception is better.
            return;
        }
        ((AnimationActor)actor).getAnimationGroup().setAction(actionName, false);
    }

    @Override
    public boolean act(float delta) {
        return ((AnimationActor)actor).getAnimationGroup().isDone;
    }


    @Override
    protected void update(float percent) {
        
    }

}
