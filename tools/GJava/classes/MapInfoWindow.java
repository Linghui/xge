package com.gol.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.bearcat.obj.proto.XMsgObj.XMsg;
import com.bearcat.obj.proto.interfaces.XMsgUpdate;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gol.xge.rpg.ui.XWindow;

public class MapInfoWindow extends XWindow implements XMsgUpdate{
    
    private String TAG = "MapInfoWindow";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    public MapInfoWindowActions actions = null;
    
    public MapInfoWindow(AssetManager manager, Skin skin, float width, float height) {
        super(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.manager = manager;
        init();
        selfInit();
        
        actions = new MapInfoWindowActions(manager, skin, this);
    }

    private void init() {










        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future

    }

    private void selfInit() {
        // TODO: do your own special init here
    }


    @Override
    public void update(XMsg msg) {
        // for update content of this table using message come from server
        
    }


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }
}
