package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class CountingLabel extends Group {

    private int totalNum = 0;
    private int showNum = 0;    // the number showing jumping
    private int numAddOn = 0;   // number added on totalNum
    
    private float totalMicSec = 0;
    private float actingTime = 0f;
    private float numJumpSec = 0f;
    private float numJumpCounter = 0f;
    
    private final float default_jump_sec = 0.03f;
    
    private Label num;

    public CountingLabel(LabelStyle style){
        num = new Label("" + totalNum, style);
        this.setWidth(100);
        this.setHeight(num.getHeight());
        this.addActor(num);
    }
    
    public void act (float delta) {
        super.act(delta);
        if( numAddOn == 0 ){
            return;
        }
        
        actingTime += delta;
        
        // time passed, set number to it should be
        if( actingTime >= totalMicSec){
            numAddOn = 0;
            actingTime = 0f;
            numJumpCounter = 0f;
            num.setText("" + (totalNum));
            return;
        }
        
        numJumpCounter += delta;
        if( numJumpCounter < numJumpSec ){
            return;
        }
        
        // animation number showing
        float per = (float)numJumpCounter/ totalMicSec;
        int temp = (int) (per * numAddOn);
        num.setText("" + (showNum+=temp));
        numJumpCounter = 0f;
    }
    
    public void addOnNum( int num ){
        this.addOnNum(num, 0.5f);
    }
    
    public void addOnNum( int num, float seconds ){
        if( seconds < 0.5){
            seconds = 0.5f;
        }
        actingTime = 0f;
        // record this to colulate how much time passed, so how many number add on on every act(delta)
        totalMicSec = seconds;
        float temp = seconds / 30;
        numJumpSec =  temp > default_jump_sec?default_jump_sec:temp;
        
        numAddOn = num;
        showNum = totalNum;
        totalNum += num;
    }
    
    public int getTotalNum(){
        return totalNum;
    }
}
