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

    private int pageSize = 1;           // pageSize 是一个窗口现实几个actor
    private LineActors lineActors = null;
    private boolean horizontal = false;// by default, add items to bottom, if set to true then add item to the right
    
    private int currentPage = 1;
    
    private float cellSizeWidth;

    private float cellSizeHeight;
    
    private InputListener listener = null;

    private boolean touched = false;
    
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
        this.setFlingTime(0.3f);
        this.setClamp(true);
        this.setSmoothScrolling(true);
        listener = new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                touched = true;
            }
        };
        this.addListener(listener);
    }


    private void homing(float distance) {
//        Gdx.app.log(TAG, "homing targetP " + distance);
        if( this.horizontal ){
            this.scrollTo(distance, 0, this.getWidth(), this.getHeight());
        } else {
            distance = distance - cellSizeHeight * (this.pageSize - 1);
//            Gdx.app.log(TAG, "distance " + distance);
            this.scrollTo(0, distance , this.getWidth(), this.getHeight());
        }
        
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        if( !Gdx.input.isTouched() 
                && touched
                && !this.isFlinging() ){
            touched = false;
//            Gdx.app.log(TAG, "listener touched " + touched);

          int index = 0;
          float nowP = 0f;
         
          if(horizontal){
              nowP = ManneredScrollPane.this.getScrollX();
              index = (int) Math.rint(nowP/cellSizeWidth);
          } else {
              nowP = ManneredScrollPane.this.getScrollY();
              index = (int) Math.rint(nowP/cellSizeHeight);
          }
//          Gdx.app.log(TAG, "homing index " + index);
          
          // avoid out of bounds of array
          if( index < 0 ){
              index = 0;
          } else if( index > lineActors.getChildren().size - 1 ) {
              index = lineActors.getChildren().size - 1;
          }
          
          currentPage = index + 1;
          float targetP  = getIndexPosition(index);
          homing(targetP);
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
                
                this.setWidth(cellSizeWidth * this.pageSize);
                this.setHeight(cellSizeHeight);
            } else {
                cellSizeWidth = actor.getWidth();
                cellSizeHeight = actor.getHeight();
                
                this.setWidth(cellSizeWidth);
                this.setHeight(cellSizeHeight * this.pageSize);
            }
            
        }
       
        lineActors.addActor(actor);
    }
   
}
