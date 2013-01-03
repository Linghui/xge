package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ManneredScrollPane extends ScrollPane {

    private String TAG = "ManneredScrollPane";

    private float distance = 0f;
    private float flag = 1; // 1 or -1
    private float moved = 1f;
    
    private int pageSize = 1;           // pageSize 是一个窗口现实几个actor
    private LineActors lineActors = null;
    private boolean horizontal = false;// by default, add items to bottom, if set to true then add item to the right
    
    private int currentPage = 1;
    
    private float cellSizeWidth;

    private float cellSizeHeight;
    
    private float speed = 1;
    
    public ManneredScrollPane(int pageSize, boolean isHorizontal) {
        super(null);
        
        lineActors = new LineActors();
        
        if( isHorizontal ){
            lineActors.setLinerDirection(LineActors.DIRECTION_RIGHT);
        } else {
            lineActors.setLinerDirection(LineActors.DIRECTION_DOWN);
        }
        setWidget(lineActors);

        this.setWidth(200);
        this.setHeight(200);
        this.pageSize = pageSize;
        this.horizontal = isHorizontal;
        

        InputListener movingListener = new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "touchDown");
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "touchUp");
                Gdx.app.log(TAG, "homing x cellSizeWidth " + ManneredScrollPane.this.getScrollX() +" "+ cellSizeWidth);
                Gdx.app.log(TAG, "homing y " + ManneredScrollPane.this.getScrollY());
                int index = 0;
                float nowP = 0f;
               
                if(horizontal){
                    nowP = ManneredScrollPane.this.getScrollX();
                    index = (int) Math.rint(nowP/cellSizeWidth);
                } else {
                    nowP = ManneredScrollPane.this.getScrollY();
                    index = (int) Math.rint(nowP/cellSizeHeight);
                }
//                Gdx.app.log(TAG, "homing index " + index);
                currentPage = index + 1;
                float targetP  = getIndexPosition(index);
//                Gdx.app.log(TAG, "homing targetP " + targetP);
                homing(targetP - nowP);
                event.handle();
            }
            
            
        };
        
        Gdx.app.log(TAG, " xxxx " + this.addListener(movingListener));
        
    }


    private void homing(float distance) {
        this.distance  = distance;
        flag = distance/Math.abs(distance);
//        Gdx.app.log(TAG, "homing targetP " + distance + " flag " + flag);
    }
   
    @Override
    public void act (float delta) {
        super.act(delta);
        if(distance != 0  ){
            if(horizontal){
                this.setScrollX(this.getScrollX() + speed * flag);   
            } else {
                this.setScrollY(this.getScrollY() + speed * flag);
            }
            
           
            moved += speed;
            if(moved >= Math.abs(distance)){
                moved = 0f;
                distance = 0f;
            }
        }
    }
    private float getIndexPosition(int index){
        if(this.horizontal){
            return lineActors.getChildren().get(index).getX();
        } else {
            return lineActors.getChildren().get(index).getY();
        }
    }
    
    @Override
    public void addActor(Actor actor){
        if(actor == null){
            // TODO: not allow
            return;
        }
       
        if(lineActors.getSize() == 0){                              
            if(horizontal){
                cellSizeWidth = actor.getWidth();
                cellSizeHeight = actor.getHeight();
                
                speed = cellSizeWidth/32;
                this.setWidth(cellSizeWidth * this.pageSize);
                this.setHeight(cellSizeHeight);
            } else {
                cellSizeWidth = actor.getWidth();
                cellSizeHeight = actor.getHeight();
                
                speed = cellSizeHeight/32;
                this.setWidth(cellSizeWidth);
                this.setHeight(cellSizeHeight * this.pageSize);
            }
            
        }
       
        lineActors.addActor(actor);
    }
    
}
