package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

// todo : 
//	1. add an optional close button
//	2. add an optional image title
public class XWindow extends Table {
    protected String TAG = this.getClass().getSimpleName();
    
    private XWindowStyle style;
    
    protected ImageButton closeImage = null;
    private Image titleImage = null;
    private Label titleLabel = null;
    
    String title = null;
    
    private Skin skin = null;

    private int padding = 5;
    private float xp = padding;
    
    private float yp = padding;


    public XWindow(){
        
    }

    public XWindow(Skin skin, float width, float height) {
        this(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.setSkin(skin);
    }
    
    public XWindow(XWindowStyle style, float width, float height){
        this.style = style;
        this.setWidth(width);
        this.setHeight(height);
        if( style != null ){
            this.setBackground(style.background);    
        }
        
        
        if( style != null
                && style.hasClose){

            // implement touchDown and touchUp for change the down/up image
            closeImage = new ImageButton(style.closeImg, style.closeImgDown){
                private int overRegion = 20;

                public Actor hit (float x, float y, boolean touchable) {
                    if (touchable && this.getTouchable() != Touchable.enabled) return null;
                    return x >= (0 - overRegion) && x < (this.getWidth() + overRegion) && y >= (0 - overRegion) && y < (this.getHeight() + overRegion) ? this : null;
                }
            };

            // for for close click
            closeImage.addListener(new ClickListener(){
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    close();
                }
            });
            
            resetClosePosition();
            this.addActor(closeImage);
        }
        
        this.addListener(new InputListener(){
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                Gdx.app.log(TAG, "touchDragged");
                event.handle();
            }
        });
        
        this.addListener(new ClickListener(){
            public void drag (InputEvent event, float x, float y, int pointer) {
                Gdx.app.log(TAG, "drag");
                
            } 
        });
        this.setTouchable(Touchable.enabled);
        this.setVisible(false);
    }
    
    public void setTitle(TextureRegion title){
        titleImage = new Image(title);
        titleImage.setX((this.getWidth() - titleImage.getWidth())/2);
        titleImage.setY(this.getHeight() - titleImage.getHeight());
        Gdx.app.log(TAG, "titleImage x:"+titleImage.getX() + "-y:"+titleImage.getY());
        this.addActor(titleImage);
    }
    
    public void setTitle(String title){
        if( titleLabel != null ){
            titleLabel.setText(title);
        }
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
    
    public void setClosePadding(int padding){
        setClosePadding(padding, padding);
    }
    
    public void setClosePadding(int xp, int yp){
        this.xp = xp;
        this.yp = yp;
        this.resetClosePosition();
    }
    
    private void resetClosePosition(){
        if( closeImage != null){
            closeImage.setX(this.getWidth() - closeImage.getPrefWidth() - xp);
            closeImage.setY(this.getHeight() - closeImage.getPrefHeight() - yp);
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
        if( style != null && style.hasClose && this.getChildren().size > 1){
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
