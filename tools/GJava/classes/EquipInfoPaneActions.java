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


public class EquipInfoPaneActions {

    private String TAG = "EquipInfoPaneActions";

    private AssetManager manager = null;
    private Skin skin = null;
    
    private EquipInfoPane table;
    
    
    public EquipInfoPaneActions(AssetManager manager, Skin skin, EquipInfoPane table) {
        this.table = table;
        this.manager = manager;
        this.skin = skin;
    }

    
    public void b1Action(){
        Gdx.app.log(TAG, "b1Action called");
        return ;
    }



}