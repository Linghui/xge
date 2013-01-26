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


public class PKTargetLine extends Table {

    private String TAG = "PKTargetLine";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public PKTargetLineActions actions = null;
    
    
    public PKTargetLine(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new PKTargetLineActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {




        Label type    =   new Label("type", skin.get("default", LabelStyle.class));
        type.setName("type");
        type.setX(0);
        type.setY(0);
        meaningLess(type);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(type);


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