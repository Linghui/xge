package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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

    private int disToHead = 0;

    private int disToPane = 0;
    
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
                    , skin.get("toggle", TextButtonStyle.class)){
                
                public Actor hit (float x, float y, boolean touchable) {
                    return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight() ? this : null;
                }
            };
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
            Gdx.app.log(TAG, " tabButton " + tabButton.getListeners().size );
            
            // we do not need its origin listener anymore
            // it can involve some defect which make two tab can be choosed in the same time.
            tabButton.removeListener(tabButton.getListeners().get(0)); 
            
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
        case LineActors.DIRECTION_RIGHT:
            
            backgroundWidth = this.getWidth();
            backgroundHeight = this.getHeight();
            
            switch(this.position){
            case Align.top:
                backgroundX = 0;
                backgroundY = 0;
                tabsX = this.disToHead;
                tabsY = backgroundHeight + disToPane;
                break;
            case Align.bottom:
                backgroundX = 0;
                backgroundY = tabs.getHeight();
                tabsX = this.disToHead;
                tabsY = 0 + disToPane;
                break;
            default:
                throw new Exception("when tab set to left , position only can be set to top or bottom");
            }
            
            break;
        case LineActors.DIRECTION_DOWN:

            backgroundWidth = this.getWidth();
            backgroundHeight = this.getHeight();
            
            switch(this.position){
            case Align.left:
                backgroundX = tabs.getWidth();
                backgroundY = 0;
                tabsX = + disToPane;
                tabsY = backgroundHeight - tabs.getChildren().get(0).getHeight() - this.disToHead;
                break;
            case Align.right:
                backgroundX = 0;
                backgroundY = 0;
                tabsX = backgroundWidth + disToPane;
                tabsY = backgroundHeight - tabs.getChildren().get(0).getHeight() - this.disToHead;
                break;
            default:
                throw new Exception("when tab set to down, position only can be set to left or right");
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
     *               LineActors.DIRECTION_DOWN
     *              , LineActors.DIRECTION_RIGHT
     * TODO:K do not implement yet for now
     * align    : distance to head, 
     *      head means when tabDirection set to left or right, it's to left
     *      when tabDirection set to up or down, it's to top
     */
    
    // TODO:K there is still little bug, but not big deal for now. 
    
    public void setTabPosition(int position, int tabDirection){
        this.setTabPosition(position, tabDirection, 0, 0);
    }
    
    public void setTabPosition(int position, int tabDirection, int disToHead, int disToPane){
        this.position = position;
        this.tabDirection = tabDirection;
        this.disToHead = disToHead;
        this.disToPane  = disToPane;
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
