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


public class SkillItemPane extends Table {

    private String TAG = "SkillItemPane";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public SkillItemPaneActions actions = null;
    
    
    public SkillItemPane(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new SkillItemPaneActions(manager, skin, this);
        
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

        TextButton up = new TextButton("up",skin.get(TextButtonStyle.class));
        up.setName("up");
        up.setX(0);
        up.setY(0);
        up.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "up clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.upAction();
            }
    
        });
        meaningLess(up);




        Label typeName    =   new Label("name", skin.get("default", LabelStyle.class));
        typeName.setName("typeName");
        typeName.setX(0);
        typeName.setY(0);
        meaningLess(typeName);

        Label name    =   new Label("name", skin.get("default", LabelStyle.class));
        name.setName("name");
        name.setX(0);
        name.setY(0);
        meaningLess(name);

        Label level    =   new Label("level", skin.get("default", LabelStyle.class));
        level.setName("level");
        level.setX(0);
        level.setY(0);
        meaningLess(level);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(typeName);
        this.add(icon).padLeft(10);
        this.add(name).padLeft(10);
        this.add(level).padLeft(5);
        this.add(up).padLeft(10);


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