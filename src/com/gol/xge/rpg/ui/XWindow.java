package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

// todo : 
//	1. add an optional close button
//	2. add an optional image title
public class XWindow extends Table {
    private String TAG = "CoreWindow";
    
    private XWindowStyle style;
    private float padding = 5;
    private Image closeImage = null;
    private Image titleImage = null;
    private Label titleLabel = null;
    
    String title = null;

    public XWindow(Skin skin, float width, float height) {
        this(skin.get("default", XWindowStyle.class), width, height);
    }
    
    public XWindow(XWindowStyle style, float width, float height){
        this.style = style;
        this.setWidth(width);
        this.setHeight(height);
        this.setBackground(style.background);
        
        if(style.hasClose){

            // implement touchDown and touchUp for change the down/up image
            closeImage = new Image(style.closeImg);

            // for for close click
            closeImage.addListener(new InputListener(){
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    closeImage.setDrawable(XWindow.this.style.closeImgDown);
                    return true;
                }
                
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    closeImage.setDrawable(XWindow.this.style.closeImg);
                    close();
                }
            });
            
            resetClosePosition();
            this.addActor(closeImage);
        }

    }
    
    public void setTitle(TextureRegion title){
        titleImage = new Image(title);
        titleImage.setX((this.getWidth() - titleImage.getWidth())/2);
        titleImage.setY(this.getHeight() - titleImage.getHeight());
        Gdx.app.log(TAG, "titleImage x:"+titleImage.getX() + "-y:"+titleImage.getY());
        this.addActor(titleImage);
    }
    
    public void setTitle(String title, LabelStyle style, int titlePadding){
        this.title = title;
        if(style != null){
            titleLabel = new Label(title, style);
            titleLabel.setX((this.getWidth() - titleLabel.getPrefWidth())/2);
            titleLabel.setY(this.getHeight() - titleLabel.getPrefHeight() - titlePadding); 
            this.addActor(titleLabel);
        }
    }
    
    public void setClosePad(float padding){
        this.padding = padding;
        resetClosePosition();
    }
    
    private void resetClosePosition(){
        if( closeImage != null){
            closeImage.setX(this.getWidth() - closeImage.getPrefWidth() - padding);
            closeImage.setY(this.getHeight() - closeImage.getPrefHeight() - padding);
        }
    }

    public void toggleDisplay(){
        if(this.isVisible()){
            close();
        } else {
            open();
        }
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }
    
    @Override
    public void pack(){
        super.pack();
//        this.height = this.height + 20 ;
        if(titleLabel != null){
            titleLabel.setX((this.getWidth() - titleLabel.getPrefWidth())/2);
            titleLabel.setY(this.getHeight() - titleLabel.getPrefHeight());
        }
        resetClosePosition();
    }
    
    @Override
    public void addActor (Actor actor) {
        super.addActor(actor);
        if(style.hasClose&& this.getChildren().size > 1){
            this.removeActor(closeImage);
            super.addActor(closeImage);
        } 

    }

    static public class XWindowStyle{
        public Drawable background;
        public Drawable closeImg, closeImgDown;
        private boolean hasClose = true;
        public XWindowStyle(){
            
        }
        
        // style for window has close button
        public XWindowStyle(Drawable background , Drawable closeImg, Drawable closeImgDown ){
            this.background = background;
            this.closeImg = closeImg;
            this.closeImgDown = closeImgDown;
        }
        
        // style for window has no close button
        public XWindowStyle(Drawable background){
            this.background = background;
            this.hasClose = false;
        }
        
        public boolean hasClose(){
            return hasClose;
        }
    } 
}
