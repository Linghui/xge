package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class PagedActors extends Group{

    private String TAG = "PagedActors";
    
    private int currentPage = 1;
    private int pageSize = 1;
    private int maxPage = 10;
    private LineActors lineActors = null;
    private Group allActors = null;
    private boolean horizontal = false;// by default, add items to bottom, if set to true then add item to the right
    
    public PagedActors(){
        this(1, false);
    }

    public PagedActors(int i) {
        this(i, false);
    }
    
    public PagedActors(int pageSize, boolean isHorizontal){
        if(pageSize <= 0){
            // TODO: not allow
        }
        this.pageSize = pageSize;
        this.horizontal = isHorizontal;
        allActors = new Group();
        
        lineActors = new LineActors();
        if( horizontal ){
            lineActors.setLinerDirection(lineActors.DIRECTION_RIGHT);
        } else {
            lineActors.setLinerDirection(lineActors.DIRECTION_DOWN);
        }
        
        
        
//        Gdx.app.log(TAG, "x:"+lineActors.x + "-y:"+lineActors.y);
        this.addActor(lineActors);
        currentPage = 1;
        
    }
    

	public void addPageActor(Actor actor){
        if(actor == null){
            // TODO: not allow
            return;
        }
        allActors.addActor(actor);
        if(lineActors.getSize() < pageSize){
        	if(horizontal)
        		lineActors.addActor(actor);
        	else
        		lineActors.addActor(actor);
        }
        double convert = (double)allActors.getChildren().size/pageSize;
        maxPage = (int) Math.ceil(convert);
//        Gdx.app.log(TAG, "maxPage " + maxPage);
    }
    public void nextPage(){
//        Gdx.app.log(TAG, "currentPage " + currentPage);
        gotoPage(currentPage + 1);
    }
    
    public void prePage(){
        gotoPage(currentPage - 1);
    }
    
    public void gotoPage(int pageNum){
//        Gdx.app.log(TAG, "pageNum " + pageNum);
        if(pageNum <= 0 || pageNum > maxPage){
            // TODO: not allow
            return;
        }
        int startIndex = (pageNum - 1) * pageSize;
//        Gdx.app.log(TAG, "startIndex " + startIndex);
//        Gdx.app.log(TAG, "allActors size " + allActors.getActors().size());
        
        if(startIndex >= allActors.getChildren().size){
            return;
        }
        
        lineActors.clear();
        for(int index = startIndex, count=0  ;
                index < allActors.getChildren().size;
                index++, count++){
            if(count >= pageSize){
                break;
            }
//            Gdx.app.log(TAG, "index " + index);
            Actor actor = allActors.getChildren().get(index);
            lineActors.addActor(actor);
        }
        currentPage = pageNum;
    }
    
    public int getMaxPage(){
        return maxPage;
    }
    
    public int getCurrentPage(){
        return currentPage;
    }
    
    public void clear(){
    	this.lineActors.clear();
    	this.allActors.clear();
    	currentPage = 1;
    }
}
