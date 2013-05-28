package com.gol.xge.rpg.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class PageSpot extends Group implements PageUpdateListener {

    private int pad = 14;
    private List<ImageButton> spotList;
    public PageSpot(Drawable up, Drawable down, int number){
    
        spotList = new ArrayList<ImageButton>();
        float width = 0;
        float height = 0;
        for( int index = 0; index < number; index++){
            ImageButton b = new ImageButton( up, down, down);
            if( width == 0 ){
                width = b.getWidth();
                height = b.getHeight();
            }
            
            b.setX(index * width + index * pad);
            b.setY(0);
            if( index == 0 ){
                b.setChecked(true);
            } else {
                b.setChecked(false);
            }
            this.addActor(b);
            spotList.add(b);
        }
        
        this.setWidth( width * number + pad * ( number - 1) );
        this.setHeight(height);
    }
    
    @Override
    public void setX(float x){
        super.setX(x - this.getWidth()/2);
    }

    @Override
    public void update(int currentPage, int pageSize, int totalPage) {
        turnOnPage(currentPage);
    }
    
    // index starts from 0
    public void turnOnPage( int pageNumber){
        if( pageNumber >= spotList.size() ){
            return;
        }
        
        for( ImageButton b : spotList){
            b.setChecked(false);
        }
        spotList.get(pageNumber).setChecked(true);
    }
}
