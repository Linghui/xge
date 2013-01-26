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


public class PKTargetPanel extends Table {

    private String TAG = "PKTargetPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public PKTargetPanelActions actions = null;
    
    
    public PKTargetPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new PKTargetPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {








        PKTargetLine t1 = new PKTargetLine(manager, skin);
        t1.setName("t1");
        PKTargetLine t2 = new PKTargetLine(manager, skin);
        t2.setName("t2");
        PKTargetLine t3 = new PKTargetLine(manager, skin);
        t3.setName("t3");


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(t1);
        this.row();
        this.add(t2);
        this.row();
        this.add(t3);


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