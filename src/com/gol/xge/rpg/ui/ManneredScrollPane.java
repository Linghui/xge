package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.SnapshotArray;

public class ManneredScrollPane extends ScrollPane implements PageOperateListener{

//    private String TAG = "ManneredScrollPane";

    private int pageSize = 1;           // pageSize 是一个窗口现实几个actor
    private LineActors lineActors = null;
    private boolean horizontal = false;// by default, add items to bottom, if set to true then add item to the right
    
    private int pad = 0;
    private int currentPage = 0;
    
    private float cellSizeWidth;

    private float cellSizeHeight;
    
    private InputListener listener = null;
    
    private List<PageUpdateListener> pageListener = new ArrayList<PageUpdateListener>();

    private boolean touched = false;
    
    private float realY = 0f;
    private float adjustY = 0f;
    
    public ManneredScrollPane(int pageSize, boolean isHorizontal) {
        this(pageSize, isHorizontal, 0);
    }
    
    public ManneredScrollPane(int pageSize, boolean isHorizontal, int pad) {
        super(null);
        
        this.pad = pad;
        
        lineActors = new LineActors(this.pad);
        
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
          
          currentPage = index;
          gotoPage(currentPage);
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
            cellSizeWidth = actor.getWidth();
            cellSizeHeight = actor.getHeight();                      
            if(horizontal){
                cellSizeWidth += pad;
                this.setWidth(cellSizeWidth * this.pageSize);
                this.setHeight(cellSizeHeight);
            } else {
                cellSizeHeight += pad;
                this.setWidth(cellSizeWidth);
                this.setHeight(cellSizeHeight * this.pageSize);
            }
            
        }

        lineActors.addActor(actor);
        if(horizontal){
            lineActors.setWidth( lineActors.getWidth() + pad);
        } else {
            lineActors.setHeight( lineActors.getHeight() + pad);
            if( lineActors.getChildren().size < this.pageSize ){
                int dis = this.pageSize - lineActors.getChildren().size;
//                Gdx.app.log(TAG, "dis " + dis + " cellSizeHeight " + cellSizeHeight);
                adjustY = ( cellSizeHeight * dis ) + pad * ( dis - 1);
            } else {
                adjustY = 0f;
            }
//            Gdx.app.log(TAG, "adjustY " + adjustY);
        }
        triggerPageListener();
    }
    
    public void setRealY( float y ){
        this.realY = y;
        this.setY(realY);
    }
    
    /*
     * (non-Javadoc)
     * @see com.badlogic.gdx.scenes.scene2d.Actor#setY(float)
     * 这里使用方法特殊，当滚动设置为上下滚动，并且元素数量少于每页显示数量的时候，
     * 调整完页面内元素个数后，一定要显示给定一个》0的y值
     * 否则坐标计算会出现误差, 或者当坐标出现误差后，使用setRealY来进行调整
     */
    @Override
    public void setY( float y ){
        if( horizontal ){
            super.setY(y);
        } else {
            if( lineActors.getChildren().size < this.pageSize ){
                if( realY == 0 ){
                    realY = y;
                }
                super.setY( realY + adjustY );
            } else {
                super.setY(y);
            }
        }
    }
    
    @Override
    public void setWidth (float width) {
        super.setWidth(width);
        if( lineActors != null)
            lineActors.setWidth(width);
    }
    
    @Override
    public void setHeight (float height) {
        super.setHeight(height);
        if( lineActors != null)
            lineActors.setHeight(height);
    }
    
    
    @Override
    public void clear(){
        lineActors.clear();
    }
    
//    @Override
//    public void validate () {
//        super.validate();
//        Gdx.app.log(TAG, "validate");
//        if( this.getChildren().size < this.pageSize ){
//            lineActors.setY( 200 );
//        } else {
//            lineActors.setY( 0 );
//        }
//    }
//    
    public void addPageListener(PageUpdateListener listener){
        pageListener.add(listener);
        triggerPageListener();
    }
    
    private void triggerPageListener(){
        if( pageListener.size() > 0){
            for(PageUpdateListener one : pageListener){
                one.update(currentPage, pageSize, getSize());    
            }
            
        }
    }
    
    public int getSize(){
        return lineActors.getSize();
    }
    
    public Actor getChild(int index){
        return lineActors.getChildren().get(index);
    }
    
    public SnapshotArray<Actor> getChildren(){
        return lineActors.getChildren();
    }

    @Override
    public void goFrontPage() {
        gotoPage( this.currentPage - 1);
    }

    @Override
    public void goNextPage() {
        gotoPage( this.currentPage + 1);        
    }

    @Override
    public void gotoPage(int page) {
        if( page < 0
                || page > getSize() + 1){
            return;
        }
        this.currentPage = page;
        float targetP  = this.getIndexPosition(page);
        this.homing(targetP);
        triggerPageListener();
    }

    public LineActors getLineActors() {
        return lineActors;
    }
}
