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


public class TaskInfoPane extends Table {

    private String TAG = "TaskInfoPane";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public TaskInfoPaneActions actions = null;
    
    
    public TaskInfoPane(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new TaskInfoPaneActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {

        TextButton b2 = new TextButton("",skin.get(TextButtonStyle.class));
        b2.setName("b2");
        b2.setX(0);
        b2.setY(0);
        b2.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b2 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b2Action();
            }
    
        });
        meaningLess(b2);

        TextButton b3 = new TextButton("",skin.get(TextButtonStyle.class));
        b3.setName("b3");
        b3.setX(0);
        b3.setY(0);
        b3.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b3 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b3Action();
            }
    
        });
        meaningLess(b3);




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
        this.add(b2);
        this.add(b3);
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