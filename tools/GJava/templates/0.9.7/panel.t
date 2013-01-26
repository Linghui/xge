package com.gol.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.gol.game.xmsg.ui.XPanel;

public class $panel_name extends XPanel {

    private String TAG = "$panel_name";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    private final static float width  = $width;
    private final static float height = $height;
    
    
    public $panel_name(AssetManager manager, Skin skin) {
        super(width, height);
        this.manager = manager;
        this.skin = skin;
        initLabels();
        initButtons();
    }
    
    
    private void initButtons() {

$init_buttons

    }

    private void initLabels() {

$init_labels

    }
    
$actions

}