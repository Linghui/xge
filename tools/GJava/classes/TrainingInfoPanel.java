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


public class TrainingInfoPanel extends Table {

    private String TAG = "TrainingInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public TrainingInfoPanelActions actions = null;
    
    
    public TrainingInfoPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new TrainingInfoPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {

        TextButton exp = new TextButton("exp",skin.get(TextButtonStyle.class));
        exp.setName("exp");
        exp.setX(0);
        exp.setY(0);
        exp.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "exp clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.expAction();
            }
    
        });
        meaningLess(exp);

        TextButton bar = new TextButton("bar",skin.get(TextButtonStyle.class));
        bar.setName("bar");
        bar.setX(0);
        bar.setY(0);
        bar.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "bar clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.barAction();
            }
    
        });
        meaningLess(bar);

        TextButton over = new TextButton("over",skin.get(TextButtonStyle.class));
        over.setName("over");
        over.setX(0);
        over.setY(0);
        over.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "over clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.overAction();
            }
    
        });
        meaningLess(over);

        TextButton eat = new TextButton("eat",skin.get(TextButtonStyle.class));
        eat.setName("eat");
        eat.setX(0);
        eat.setY(0);
        eat.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "eat clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.eatAction();
            }
    
        });
        meaningLess(eat);




        Label name    =   new Label("name", skin.get("default", LabelStyle.class));
        name.setName("name");
        name.setX(0);
        name.setY(0);
        meaningLess(name);

        Label level    =   new Label("lv.10", skin.get("default", LabelStyle.class));
        level.setName("level");
        level.setX(0);
        level.setY(0);
        meaningLess(level);

        Label status    =   new Label("status", skin.get("default", LabelStyle.class));
        status.setName("status");
        status.setX(0);
        status.setY(0);
        meaningLess(status);

        Label time    =   new Label("time", skin.get("default", LabelStyle.class));
        time.setName("time");
        time.setX(0);
        time.setY(0);
        meaningLess(time);

        Label l1    =   new Label("get", skin.get("default", LabelStyle.class));
        l1.setName("l1");
        l1.setX(0);
        l1.setY(0);
        meaningLess(l1);

        Label available    =   new Label("available", skin.get("default", LabelStyle.class));
        available.setName("available");
        available.setX(0);
        available.setY(0);
        meaningLess(available);

        Label intro    =   new Label("intro", skin.get("default", LabelStyle.class));
        intro.setName("intro");
        intro.setX(0);
        intro.setY(0);
        meaningLess(intro);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(name);
        this.add(level);
        this.add(exp);
        this.row();
        this.add(status);
        this.add(time);
        this.row();
        this.add(l1);
        this.add(available);
        this.row();
        this.add(bar);
        this.row();
        this.add(intro);
        this.row();
        this.add(over);
        this.add(eat);


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