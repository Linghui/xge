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


public class EquipComparePane extends Table {

    private String TAG = "EquipComparePane";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public EquipComparePaneActions actions = null;
    
    
    public EquipComparePane(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new EquipComparePaneActions(manager, skin, this);
        
        init();

    }
    
    
    private void init() {








        EquipInfoPane t1 = new EquipInfoPane(manager, skin);
        t1.setName("t1");
        EquipInfoPane t2 = new EquipInfoPane(manager, skin);
        t2.setName("t2");


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(t1);
        this.add(t2);


    }

    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}