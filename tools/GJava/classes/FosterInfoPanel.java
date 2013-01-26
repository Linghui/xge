package com.gol.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gol.xge.rpg.ui.XCell;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class FosterInfoPanel extends Table {

    private String TAG = "FosterInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public FosterInfoPanelActions actions = null;
    
    
    public FosterInfoPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new FosterInfoPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {




        Label name    =   new Label("name", skin.get("default", LabelStyle.class));
        name.setName("name");
        name.setX(0);
        name.setY(0);
        meaningLess(name);

        Label level    =   new Label("level", skin.get("default", LabelStyle.class));
        level.setName("level");
        level.setX(0);
        level.setY(0);
        meaningLess(level);





        GFosterPanel t3 = new GFosterPanel(manager, skin);
        t3.setName("t3");
        GFosterPanel t4 = new GFosterPanel(manager, skin);
        t4.setName("t4");


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(name);
        this.add(level);
        this.row();
        this.add(t3);
        this.add(t4);


    }

    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }

    public void open(){
        actions.open();
    }
    
    
    public void close(){
        actions.close();
    }

}