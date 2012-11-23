package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class NumericBar extends Group{

    private String TAG = "NumericBar";
    private Image backgroundBar;
    private Image topBar;
    private Label label;
    
    private int padPix = 2;
    
    private int baseNum   = 0;
    private int statusNum = 0;
    private int topBarMaxWidth = 0;
    
    private TextureRegion topRegion;
    
    
    public NumericBar(NinePatch background, NinePatch top, int width, int height){
        this(background, top, width, height, 0, 0, null);
    }

    public NumericBar(NinePatch background, NinePatch top, int width, int height,
            int statusNum, int baseNum){
        this(background, top, width, height, statusNum, baseNum, null);
    }

    public NumericBar(NinePatch background, NinePatch top, int width, int height, 
            int statusNum, int baseNum, LabelStyle style){

        backgroundBar = new Image(background);
        topBar = new Image(top);

        setBarWidHei(width, height);
        
        this.addActor(backgroundBar);
        this.addActor(topBar);
        
        this.setBaseNum(baseNum);
        this.setStatusNum(statusNum);

        if(style != null){
            label = new Label(statusNum+"/"+baseNum, style);
            label.setWidth(width);
            label.setHeight(height);
            label.setAlignment(Align.center, Align.center);
            this.addActor(label);
            
        }
    }
    public NumericBar(TextureRegion backgroupRegion, TextureRegion topRegion,int statusNum, int baseNum){
        this(backgroupRegion,topRegion,statusNum,baseNum,null);
    }
    public NumericBar(TextureRegion backgroupRegion, TextureRegion topRegion,int statusNum, int baseNum, LabelStyle style){
        this.topRegion = topRegion;
        
        backgroundBar = new Image(backgroupRegion);
        topBar = new Image(topRegion);
        
        this.setWidth(backgroundBar.getWidth());
        this.setHeight(backgroundBar.getHeight());
        
        this.addActor(backgroundBar);
        this.addActor(topBar);

        topBarMaxWidth = (int)topBar.getWidth();
//        Gdx.app.log(TAG, "topBar.width - " + topBar.width);
        this.setBaseNum(baseNum);
        this.setStatusNum(statusNum);
        
        if(style != null){
            label = new Label(statusNum+"/"+baseNum, style);
            label.setWidth(backgroundBar.getWidth());
            label.setHeight(backgroundBar.getPrefHeight());
//            label.setAlignment(Align.CENTER, Align.CENTER);
            label.setAlignment(Align.center);
            this.addActor(label);
            
        }
    }
    
//    public void set
    
    public void setReduceNum(int reduct){
        this.setStatusNum(statusNum - reduct); 
    }

    public void setStatusNum(int statusNum){
        //Gdx.app.log(TAG, "statusNum - " + statusNum);
        if(baseNum <= 0 ){
            // TODO: error 
            Gdx.app.log(TAG, "setStatusNum error ");
            return;
        }
        if( statusNum < 0){
            statusNum = 0;
        }
        
        if( statusNum > baseNum){
            this.statusNum = baseNum;
        } else {
            this.statusNum = statusNum;
        }
        if(label != null){
            label.setText(this.statusNum +"/" + this.baseNum);    
        }
        float temp = (float)this.statusNum/baseNum;
        float topBarWidth = temp * topBarMaxWidth;
        this.setTopBarWid(topBarWidth);
        
    }

    public int getStatusNum(){
        return this.statusNum;
    }
    
    public void setBaseNum(int baseNum){
        if(baseNum < 0){
            // TODO: error 
            Gdx.app.log(TAG, "setBaseNum error ");
            baseNum = 0;
            return;
        }
        this.baseNum = baseNum;
    }
    
    public int getBaseNum(){
        return this.baseNum;
    }
    
    private void setTopBarWid(float width){
        if(width > this.topBarMaxWidth || width < 0){
            // TODO: error 
            Gdx.app.log(TAG, "setTopBarWid error ");
            return;
        }
        if(topRegion != null){
            topRegion.setRegion(topRegion, 0, 0, (int)width, topRegion.getRegionHeight());
        } 
        topBar.setWidth(width);
        topBar.layout();
    }
    
    /*
     * 
     */
    public void setBarWidHei(int width, int height){

        this.setWidth(width);
        this.setHeight(height);
        backgroundBar.setWidth(width);
        backgroundBar.setHeight(height);
        backgroundBar.layout();
        
        topBar.setX(padPix); 
        topBar.setY(padPix); 
        topBarMaxWidth  =  width - padPix * 2;
        topBar.setWidth(topBarMaxWidth);
        topBar.setHeight(height - padPix * 2);
        topBar.layout();
        
    }
}
