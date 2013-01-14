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

    private Skin skin = null;
    private final static int TOP_LEFT = 1;
    private int type = TOP_LEFT;
    
    private final String panelPreffix = "panel";
    private final String tabButtonPreffix = "tB";
    
    // add a actor into tab group in this name, 
    // tab will take it as background and strength it to tab size 
    public static final String backgroundName = "BN";
    
    private LineActors tabs = new LineActors();
    private int totalTabNumber = 0;
    
    private TabChangeListener listener = null;
    
    public XTab(Skin skin, NinePatch background, float width, float height, Array<String> tabNames){
        totalTabNumber = tabNames.size;
        this.skin = skin;
        
        this.setWidth(width);
        this.setHeight(height);
        
        tabs.setLinerDirection(LineActors.DIRECTION_RIGHT);
        this.addActor(tabs);
        
        for(int index = 0; index < this.totalTabNumber; index++){
            TextButton tabButton = new TextButton(tabNames.get(index)
                    , skin.get("toggle", TextButtonStyle.class));
            tabButton.setName(tabButtonPreffix + index);
            
            tabs.addActor(tabButton);
            
            
            final Group panelGroup = new Group();
            panelGroup.setName(panelPreffix + index);
            
            Image panelBackground = new Image(background);
            panelBackground.setName(backgroundName);
            panelGroup.addActor(panelBackground);
            
//            // for debug
//            TextButton debugB = new TextButton("t"+ index, skin);
//            debugB.setX(20*index);
//            panelGroup.addActor(debugB);
            
            this.addActor(panelGroup);
            
            final int page = index;
            
            tabButton.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    showTab(page);
                    return false;
                }

            });

        }

        
        tabs.setButtonCheck(0, true);
        
        pack();
    }

    /*
     * 调整标签和面板位置
     *  
     */
    private void pack() {
        switch(this.type){
        case TOP_LEFT:

            // 标签位置
            tabs.setY(this.getHeight() - tabs.getHeight());
            
            float backgroundWidth = this.getWidth();
            float backgroundHeight = this.getHeight() - tabs.getHeight();
            
            // 调整所有面板宽高 位置
            for(int index = 0; index < totalTabNumber; index++){
                Group tabGroup = ((Group)this.findActor(panelPreffix + index));
                tabGroup.setWidth(backgroundWidth);
                tabGroup.setHeight(backgroundHeight);
                
                Actor backgroudActor = tabGroup.findActor(backgroundName);
                if( backgroudActor != null ){

                    backgroudActor.setWidth(backgroundWidth);
                    backgroudActor.setHeight(backgroundHeight);   
                }
            }
            showTab(0);
            break;
        default:
            break;
        }
        
    }
    
    public void addActorToTab(int tabNumber, Actor actor) {
        if(tabNumber >= this.totalTabNumber){
            Gdx.app.log(TAG, "BIG Warning!!! no " + tabNumber + " to add actor!");
            return;
        }
        Group tabGroup = (Group) this.findActor(panelPreffix + tabNumber);
        tabGroup.addActor(actor);
    }
    
    public void addTab(String name){
        this.addTab(name, new Group());
    }
    
    public void addTab(String name, final Group tab){
        // TODO: not implemented yet

        TextButton tabButton = new TextButton(name
                , skin.get("toggle", TextButtonStyle.class));
        tabButton.setName(tabButtonPreffix + this.totalTabNumber);
        tabs.addActor(tabButton);
        
        final int page = this.totalTabNumber;
        
        tabButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                showTab(page);
                return false;
            }
        });
        
        tab.setName(panelPreffix + this.totalTabNumber);
        this.addActor(tab);
        
        this.totalTabNumber++;
        
        this.pack();
    }
    
    public void setTab(int index, Group tab){
        
    }
    
    public void showTab(int pageNumber){
        
        hideAllTabs();
        
        if( listener != null ){
            listener.tabTo(pageNumber);    
        }
        
        Actor actor = this.findActor(panelPreffix + pageNumber);
        actor.setVisible(true);
        actor.toFront();
    }   
    
    public void hideAllTabs(){

        for(int index = 0; index < this.totalTabNumber; index++){
            Actor actor = this.findActor(panelPreffix + index);
            actor.setVisible(false);
        }
    }

    public TabChangeListener getListener() {
        return listener;
    }

    public void setListener(TabChangeListener listener) {
        this.listener = listener;
    }
}
