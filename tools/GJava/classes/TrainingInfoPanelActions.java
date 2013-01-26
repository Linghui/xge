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


public class TrainingInfoPanelActions {

    private String TAG = "TrainingInfoPanelActions";

    private AssetManager manager = null;
    private Skin skin = null;
    
    private TrainingInfoPanel table;
    
    
    public TrainingInfoPanelActions(AssetManager manager, Skin skin, TrainingInfoPanel table) {
        this.table = table;
        this.manager = manager;
        this.skin = skin;
    }

    
    public void open(){
        table.setVisible(true);
    }
    
    
    public void close(){
        table.setVisible(false);
    }
    
    public void expAction(){
        Gdx.app.log(TAG, "expAction called");
        return ;
    }
    public void barAction(){
        Gdx.app.log(TAG, "barAction called");
        return ;
    }
    public void overAction(){
        Gdx.app.log(TAG, "overAction called");
        return ;
    }
    public void eatAction(){
        Gdx.app.log(TAG, "eatAction called");
        return ;
    }



}