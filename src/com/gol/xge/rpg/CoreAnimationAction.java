package com.gol.xge.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.AnimationAction;
import com.badlogic.gdx.scenes.scene2d.actions.ActionResetingPool;
import com.gol.xge.rpg.ui.AnimationActor;

public class CoreAnimationAction extends AnimationAction {
    private String TAG = "CoreAnimationAction";
    private static final ActionResetingPool<CoreAnimationAction> pool = new ActionResetingPool<CoreAnimationAction>(4, 100) {
        @Override
        protected CoreAnimationAction newObject () {
            return new CoreAnimationAction();
        }
    };
    
    private String actionName = null;

    // actually only support AnimationActor here
    // do not try this action on other actors.
    public static CoreAnimationAction $ (String actionName) {
        CoreAnimationAction action = pool.obtain();
        action.actionName = actionName;
        return action;
    }
    

    @Override
    public void setTarget(Actor actor) {
        this.target = actor;
//        Gdx.app.log(TAG, "actionName === " + actionName + " -- on " + actor.name);
        if( !(actor instanceof AnimationActor)){
            // throw out exception is better.
            return;
        }
        ((AnimationActor)actor).getAnimationGroup().setAction(actionName, false);
    }

    @Override
    public void act(float delta) {
//        Gdx.app.log(TAG, "((AnimationActor)target).animationGroup.isDone === " + ((AnimationActor)target).animationGroup.isDone);
        this.done = ((AnimationActor)target).getAnimationGroup().isDone;
    }

    @Override
    public Action copy() {
        return null;
    }
    
    @Override
    public void finish () {
        super.finish();
        pool.free(this);
    }

}
