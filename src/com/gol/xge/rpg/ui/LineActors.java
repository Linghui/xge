package com.gol.xge.rpg.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;

public class LineActors extends Group {

    private String TAG = "LineActors";
    
    private int space = 0;
    
    private int spaceAdjust = 0;
    
    public LineActors(){
        this(0);
    }
    
    public LineActors(int space){
        this.space = space;
    }
    
    public void addButtonOnLeft(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int xNext = (int) (lastActor.getX() - actor.getWidth() - space - spaceAdjust);
            actor.setX(xNext);
            this.setWidth(this.getWidth() + actor.getWidth() + space) ;
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        this.addActor(actor);
        spaceAdjust = 0;
//        Gdx.app.log(TAG, "line width - " + this.width + " : height" + this.height);
    }
    
    public void addButtonOnRight(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int xNext = (int) (lastActor.getX() + lastActor.getWidth() + space + spaceAdjust);
            actor.setX(xNext);
            this.setWidth(this.getWidth() + actor.getWidth() + space);
        } else {
            this.setWidth(actor.getWidth());
            this.setWidth(actor.getHeight());
        }
        
        this.addActor(actor);
        spaceAdjust = 0;
    }
    
    public void addButtonOnTop(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int yNext = (int) (lastActor.getY() + lastActor.getHeight() + space + spaceAdjust);
            actor.setY(yNext);
            this.setHeight(this.getHeight() + actor.getHeight() + space);
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        
        this.addActor(actor);
        spaceAdjust = 0;
    }
    
    public void addButtonOnBottom(Actor actor){
        Array<Actor> actors = this.getChildren();
        // do not need to set the first actor.x 
        if(actors.size >= 1){
            Actor lastActor = actors.get(actors.size - 1);
            int yNext = (int) (lastActor.getY() - lastActor.getHeight() - space - spaceAdjust);
            actor.setY(yNext);
            this.setHeight(this.getHeight() + actor.getHeight() + space) ;
            if(this.getWidth() < actor.getWidth()){
                this.setWidth(actor.getWidth());
            }
        } else {
            this.setWidth(actor.getWidth());
            this.setHeight(actor.getHeight());
        }
        this.addActor(actor);
        spaceAdjust = 0;
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
    }
    
}
