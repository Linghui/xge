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

public class SceneInfoWindow extends XWindow implements XMsgUpdate{
    
    private String TAG = "SceneInfoWindow";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    public SceneInfoWindowActions actions = null;
    
    public SceneInfoWindow(AssetManager manager, Skin skin, float width, float height) {
        super(skin, width, height);
        this.skin = skin;
        this.manager = manager;

        actions = new SceneInfoWindowActions(manager, skin, this);
        
        init();
        selfInit();
        

    }

    private void init() {








        SceneInfoPanel t1 = new SceneInfoPanel(manager, skin);
        t1.setName("t1");
        SceneSelectPanel t2 = new SceneSelectPanel(manager, skin);
        t2.setName("t2");


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(t1);
        this.add(t2);

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
