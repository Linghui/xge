package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PageNext extends Group implements PageUpdateListener{

    
    private ImageButton button = null;
    private PageOperateListener listener = null;

    public PageNext(TextureRegion up, TextureRegion disable){
        
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = new TextureRegionDrawable(up);
        style.disabled = new TextureRegionDrawable(disable);
        
        button = new ImageButton(style);
        button.addListener( new ClickListener(){

            public void clicked (InputEvent event, float x, float y) {
                if( ! button.isDisabled() 
                        && listener != null){
                    listener.goNextPage();
                }
            }
        });
        this.addActor(button);
        
        this.setWidth(button.getWidth());
        this.setHeight(button.getHeight());
    }

    @Override
    public void update(int currentPage, int pageSize, int totalPage) {
        if( currentPage >= ( totalPage - pageSize ) ){
            button.setDisabled(true);
        } else {
            button.setDisabled(false);
        }
    }

    public void setPageOperateListener( PageOperateListener listener){
        this.listener = listener;
    }
    
}
