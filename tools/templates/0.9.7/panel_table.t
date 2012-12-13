package com.gol.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class $panel_name extends Table {

    private String TAG = "$panel_name";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public $panel_name(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
    }
    
    
    private void init() {

$init_buttons

$init_labels

        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
$init_layout

    }

    
$actions

    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}