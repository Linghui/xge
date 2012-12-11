package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class XTab extends Group {
    
    private String TAG = "XTab";

    private final static int TOP_LEFT = 1;
    private int type = TOP_LEFT;
    
    private final String panelPreffix = "panel";
    private final String tabButtonPreffix = "tB";
    private final String backgroundName = "BN";
    
    private LineActors tabs = new LineActors();
    private int tabNumber = 0;
    
    public XTab(Skin skin, NinePatch background, float width, float height, Array<String> tabNames){
        tabNumber = tabNames.size;
        
        this.setWidth(width);
        this.setHeight(height);
        
        for(int index = 0; index < this.tabNumber; index++){
            TextButton tabButton = new TextButton(tabNames.get(index)
                    , skin.get("toggle", TextButtonStyle.class));
            tabButton.setName(tabButtonPreffix + index);
            tabs.setLinerDirection(LineActors.DIRECTION_RIGHT);
            tabs.addActor(tabButton);
            this.addActor(tabs);
            
            
            final Group panelGroup = new Group();
            panelGroup.setName(panelPreffix + index);
            
            Image panelBackground = new Image(background);
            panelBackground.setName(backgroundName);
            panelGroup.addActor(panelBackground);
            panelGroup.addActor(new TextButton("t"+ index, skin));
            
            this.addActor(panelGroup);
            
            tabButton.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    panelGroup.toFront();
                    return false;
                }

            });

        }
        
        tabs.setButtonCheck(0, true);
        
        pack();
    }

    private void pack() {
        switch(this.type){
        case TOP_LEFT:

            tabs.setY(this.getHeight() - tabs.getHeight());
            float backgroundWidth = this.getWidth();
            float backgroundHeight = this.getHeight() - tabs.getHeight();
            for(int index = 0; index < tabNumber; index++){
                Group tabGroup = ((Group)this.findActor(panelPreffix + index));
                tabGroup.setWidth(backgroundWidth);
                tabGroup.setHeight(backgroundHeight);
                
                Actor backgroudActor = tabGroup.findActor(backgroundName);
                backgroudActor.setWidth(backgroundWidth);
                backgroudActor.setHeight(backgroundHeight);
                if(index == 0){
                    tabGroup.toFront();
                }
            }
            
            break;
        default:
            break;
        }
        
    }
    
    public void addActorToTab(int tabNumber, Actor actor){
        
    }
}
