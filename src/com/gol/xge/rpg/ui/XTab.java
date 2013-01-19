package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;

public class XTab extends Group {
    
    private String TAG = "XTab";

    private Skin skin = null;
    
    private final String panelPreffix = "panel";
    private final String tabButtonPreffix = "tB";
    
    // add a actor into tab group in this name, 
    // tab will take it as background and strength it to tab size 
    public static final String backgroundName = "BN";
    
    private LineActors tabs = new LineActors();
    private int totalTabNumber = 0;
    
    private TabChangeListener listener = null;
    
    private int position = Align.top;

    private int tabDirection = LineActors.DIRECTION_RIGHT;

    private int align = Align.left;
    
    public XTab(Skin skin, float width, float height, Array<String> tabNames){
        this(skin, null, width, height, tabNames);
    }
    
    public XTab(Skin skin, NinePatch background, float width, float height, Array<String> tabNames){
        totalTabNumber = tabNames.size;
        this.skin = skin;
        
        this.setWidth(width);
        this.setHeight(height);
        
        tabs.setLinerDirection(tabDirection);
        this.addActor(tabs);
        
        for(int index = 0; index < this.totalTabNumber; index++){
            TextButton tabButton = new TextButton(tabNames.get(index)
                    , skin.get("toggle", TextButtonStyle.class));
            tabButton.setName(tabButtonPreffix + index);
            
            tabs.addActor(tabButton);
            
            
            final Group panelGroup = new Group();
            panelGroup.setName(panelPreffix + index);
            
            if( background != null){
                Image panelBackground = new Image(background);
                panelBackground.setName(backgroundName);
                panelGroup.addActor(panelBackground);    
            }
            
            
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
        
        tabs.setLinerDirection(this.tabDirection);

        try {
            resetBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        showTab(0);
    }
    
    private void resetBackground() throws Exception {
        float backgroundWidth = 0; 
        float backgroundHeight = 0;
        float backgroundX = 0;
        float backgroundY = 0;
        float tabsX = 0;
        float tabsY = 0;
        
        switch(this.tabDirection){
        case LineActors.DIRECTION_LEFT:
        case LineActors.DIRECTION_RIGHT:
            
            backgroundWidth = this.getWidth();
            backgroundHeight = this.getHeight() - tabs.getHeight();
            
            switch(this.position){
            case Align.top:
                backgroundX = 0;
                backgroundY = 0;
                tabsX = 0;
                tabsY = backgroundHeight;
                break;
            case Align.bottom:
                backgroundX = 0;
                backgroundY = tabs.getHeight();
                tabsX = 0;
                tabsY = 0;
                break;
            default:
                throw new Exception("when tab set to left or right, position only can be set to top or bottom");
            }
            
            break;
        case LineActors.DIRECTION_DOWN:
        case LineActors.DIRECTION_UP:

            backgroundWidth = this.getWidth() - tabs.getWidth();
            backgroundHeight = this.getHeight();
            
            switch(this.position){
            case Align.left:
                backgroundX = tabs.getWidth();
                backgroundY = 0;
                tabsX = 0;
                tabsY = backgroundHeight - tabs.getChildren().get(0).getHeight();
                break;
            case Align.right:
                backgroundX = 0;
                backgroundY = 0;
                tabsX = backgroundWidth;
                tabsY = backgroundHeight - tabs.getChildren().get(0).getHeight();
                break;
            default:
                throw new Exception("when tab set to left or right, position only can be set to top or bottom");
            }
            
            break;
        default:
            throw new Exception("value of this.tabDirection is wrong " + this.tabDirection);
        }

        // reset size and position
        for(int index = 0; index < totalTabNumber; index++){
            Group tabGroup = ((Group)this.findActor(panelPreffix + index));
            tabGroup.setWidth(backgroundWidth);
            tabGroup.setHeight(backgroundHeight);
              
            Actor backgroudActor = tabGroup.findActor(backgroundName);
            if( backgroudActor != null ){

                backgroudActor.setWidth(backgroundWidth);
                backgroudActor.setHeight(backgroundHeight);
                
                backgroudActor.setX(backgroundX);
                backgroudActor.setY(backgroundY);
                
                
            }
        }
        tabs.setX(tabsX);
        tabs.setY(tabsY);
        tabs.toFront();
    }

    /*
     * position : this position of tap by content
     *          Align.top, Align.bottom, Align.left, Align.right
     * tabDirection : 
     *              LineActors.DIRECTION_UP LineActors.DIRECTION_DOWN
     *              , LineActors.DIRECTION_LEFT, LineActors.DIRECTION_RIGHT
     * TODO:K do not implement yet for now
     * align    : after position set, 
     *          Align.top, Align.bottom, Align.left, Align.right, Align.center
     */
    
    // TODO:K there is still little bug, but not big deal for now. 
    public void setTabPosition(int position, int tabDirection, int align){
        this.position = position;
        this.tabDirection = tabDirection;
        this.align = align;
        this.pack();
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
