package com.gol.xge.rpg.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

/*
 * the same with Table, just add PickListener
 */

public class XTable extends Table{

    private PickListener listener = null;

    public XTable(){
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
        setAllCellCheck(false);
        if( actor instanceof Checkable){
            ((Checkable)actor).setChecked(true);
        } else if(actor instanceof Button){
            ((Button)actor).setChecked(true);
        }
        
        if( listener != null){
            listener.pick(actor);
        }
    }

    public void setAllCellCheck(boolean check){
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

    public PickListener getListener() {
        return listener;
    }

    public void setListener(PickListener listener) {
        this.listener = listener;
    }
    
    public void cleanAllXCell(){
        SnapshotArray<Actor> actors = this.getChildren();
        for( Actor one : actors ){
            if( one instanceof XCell ){
                XCell cell = (XCell)one;
                cell.removeContent();
            }
        }
    }
}
