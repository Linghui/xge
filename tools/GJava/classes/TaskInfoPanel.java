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


public class TaskInfoPanel extends Table {

    private String TAG = "TaskInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public TaskInfoPanelActions actions = null;
    
    
    public TaskInfoPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new TaskInfoPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {

        TextButton icon = new TextButton("icon",skin.get(TextButtonStyle.class));
        icon.setName("icon");
        icon.setX(0);
        icon.setY(0);
        icon.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "icon clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.iconAction();
            }
    
        });
        meaningLess(icon);

        TextButton get = new TextButton("get",skin.get(TextButtonStyle.class));
        get.setName("get");
        get.setX(0);
        get.setY(0);
        get.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "get clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.getAction();
            }
    
        });
        meaningLess(get);




        Label name    =   new Label("name", skin.get("default", LabelStyle.class));
        name.setName("name");
        name.setX(0);
        name.setY(0);
        meaningLess(name);

        Label desc    =   new Label("desc", skin.get("default", LabelStyle.class));
        desc.setName("desc");
        desc.setX(0);
        desc.setY(0);
        meaningLess(desc);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(name);
        this.add(icon);
        this.add(get);
        this.row();
        this.add(desc);


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