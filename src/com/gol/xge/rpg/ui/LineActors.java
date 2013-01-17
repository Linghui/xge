package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class LineActors extends Group {

    private String TAG = "LineActors";
    
    private int space = 0;
    
    private int spaceAdjust = 0;
    
    private PickListener listener = null;
    
    public final static int DIRECTION_UP      = 1;
    public final static int DIRECTION_DOWN    = 2;
    public final static int DIRECTION_LEFT    = 3;
    public final static int DIRECTION_RIGHT   = 4;
    
    private int linerDirection;
    
    public LineActors(){
        this(0, DIRECTION_UP);
    }
    
    public LineActors(int space){
        this(space, DIRECTION_UP);
    }
    
    public LineActors(int space, int direction){
        this.space = space;
        linerDirection = direction;
        
        this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                setAllButtonsCheck(false);
                click(x, y);
                return false;
            }
        });
    }
    
    public void click(float x, float y) {
        Actor actor = hit(x, y, true);
        setAllButtonsCheck(false);
        if( actor instanceof Checkable){
            ((Checkable)actor).setChecked(true);
        } else if(actor instanceof Button){
            ((Button)actor).setChecked(true);
        }
        
        if( listener != null){
            listener.pick(actor);
        }
    }


   public void appendOnLeft(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int xNext = (int) (lastActor.getX() - actor.getWidth() - space - spaceAdjust);
            actor.setX(xNext);
            actor.setY(0);
            this.setWidth(this.getWidth() + actor.getWidth() + space) ;
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        super.addActor(actor);
        spaceAdjust = 0;
//        Gdx.app.log(TAG, "line width - " + this.width + " : height" + this.height);
    }
    
    public void appendOnRight(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int xNext = (int) (lastActor.getX() + lastActor.getWidth() + space + spaceAdjust);
            actor.setX(xNext);
            actor.setY(0);
            this.setWidth(this.getWidth() + actor.getWidth() + space);
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        
        super.addActor(actor);
        spaceAdjust = 0;
    }
    
    public void appendOnTop(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int yNext = (int) (lastActor.getY() + lastActor.getHeight() + space + spaceAdjust);
            actor.setY(yNext);
            actor.setX(0);
            this.setHeight(this.getHeight() + actor.getHeight() + space);
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        
        super.addActor(actor);
        spaceAdjust = 0;
    }
    
    public void appendOnBottom(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int yNext = (int) (lastActor.getY() - lastActor.getHeight() - space - spaceAdjust);
            actor.setY(yNext);
            actor.setX(0);
            this.setHeight(this.getHeight() + actor.getHeight() + space) ;
            if(this.getWidth() < actor.getWidth()){
                this.setWidth(actor.getWidth());
            }
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        super.addActor(actor);
        spaceAdjust = 0;
    }
    
    @Override
    public void addActor (Actor actor) {
        switch(this.linerDirection){
        case LineActors.DIRECTION_UP:
            this.appendOnTop(actor);
            break;
        case LineActors.DIRECTION_DOWN:
            this.appendOnBottom(actor);
            break;
        case LineActors.DIRECTION_LEFT:
            this.appendOnLeft(actor);
            break;
        case LineActors.DIRECTION_RIGHT:
            this.appendOnRight(actor);
            break;
        default:
            break;
        }
    }
    
    public int getSize(){
        return this.getChildren().size;
    }
    
    // just add a temply space, only work once for the next actor added.
    public void setAdjustSpace(int spaceAdjust){
        this.spaceAdjust = spaceAdjust;
    }
    
    public void setButtonsVisible(boolean visible){
        this.setVisible(visible);
        Array<Actor> actors = this.getChildren();
        for (int i = 0; i < actors.size; i++){
            Actor child = actors.get(i);
            child.setVisible(visible);
            child.setVisible(true);
        }
    }
    
    public void setButtonCheck(int index, boolean check){
        
        if(this.getChildren().size <= index){
            return;
        }
        
        Actor actor = this.getChildren().get(index);
        if(actor != null){
            if((actor instanceof Button)){
                ((Button) actor).setChecked(check);
            } else if(actor instanceof Checkable){
                ((Checkable) actor).setChecked(check);
            }
        }
    }
    
    public int getFirstCheckedButtonIndex(){
        Array<Actor> actors = this.getChildren();
        for (int i = 0; i < actors.size; i++){
            Actor child = actors.get(i);
            if((child instanceof Button)){
                if(((Button) child).isChecked()){
                    return i;
                };
            } else if(child instanceof Checkable){
                if(((Checkable) child).isChecked()){
                    return i;
                }
            }
        }
        
        return -1; // did not find
    }
    
    public void setAllButtonsCheck(boolean check){
        Array<Actor> actors = this.getChildren();
        for (int i = 0; i < actors.size; i++){
            Actor child = actors.get(i);
            if((child instanceof Button)){
                ((Button) child).setChecked(check);
            } else if(child instanceof Checkable){
                ((Checkable) child).setChecked(check);
            }
        }
        Gdx.app.log(TAG, "test + " + check);
    }

    public PickListener getListener() {
        return listener;
    }

    public void setListener(PickListener listener) {
        this.listener = listener;
    }
    

    public int getLinerDirection() {
        return linerDirection;
    }

    public void setLinerDirection(int linerDirection) {
        this.linerDirection = linerDirection;
        
        Actor[] allActors = this.getChildren().toArray();
        Gdx.app.log(TAG, "setLinerDirection size before" + allActors.length);
        this.clear();
        Gdx.app.log(TAG, "setLinerDirection size after" + allActors.length);
        for( Actor actor : allActors){
            Gdx.app.log(TAG, "setLinerDirection " + actor);
            this.addActor(actor);
        }
    }

}
