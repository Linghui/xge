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


public class SceneInfoPanel extends Table {

    private String TAG = "SceneInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public SceneInfoPanelActions actions = null;
    
    
    public SceneInfoPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
        
        actions = new SceneInfoPanelActions(manager, skin, this);
    }
    
    
    private void init() {

        TextButton b10 = new TextButton("test",skin.get(TextButtonStyle.class));
        b10.setName("b10");
        b10.setX(0);
        b10.setY(0);
        b10.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b10 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b10Action();
            }
    
        });
        meaningLess(b10);

        TextButton b11 = new TextButton("test",skin.get(TextButtonStyle.class));
        b11.setName("b11");
        b11.setX(0);
        b11.setY(0);
        b11.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b11 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b11Action();
            }
    
        });
        meaningLess(b11);

        TextButton b13 = new TextButton("test",skin.get(TextButtonStyle.class));
        b13.setName("b13");
        b13.setX(0);
        b13.setY(0);
        b13.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b13 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b13Action();
            }
    
        });
        meaningLess(b13);

        TextButton b14 = new TextButton("test",skin.get(TextButtonStyle.class));
        b14.setName("b14");
        b14.setX(0);
        b14.setY(0);
        b14.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b14 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b14Action();
            }
    
        });
        meaningLess(b14);




        Label l1    =   new Label("level:", skin.get("default", LabelStyle.class));
        l1.setName("l1");
        l1.setX(0);
        l1.setY(0);
        meaningLess(l1);

        Label l2    =   new Label("20", skin.get("default", LabelStyle.class));
        l2.setName("l2");
        l2.setX(0);
        l2.setY(0);
        meaningLess(l2);

        Label l3    =   new Label("energy:", skin.get("default", LabelStyle.class));
        l3.setName("l3");
        l3.setX(0);
        l3.setY(0);
        meaningLess(l3);

        Label l4    =   new Label("1", skin.get("default", LabelStyle.class));
        l4.setName("l4");
        l4.setX(0);
        l4.setY(0);
        meaningLess(l4);

        Label l5    =   new Label("limit:", skin.get("default", LabelStyle.class));
        l5.setName("l5");
        l5.setX(0);
        l5.setY(0);
        meaningLess(l5);

        Label l6    =   new Label("1/3", skin.get("default", LabelStyle.class));
        l6.setName("l6");
        l6.setX(0);
        l6.setY(0);
        meaningLess(l6);

        Label l7    =   new Label("reward:", skin.get("default", LabelStyle.class));
        l7.setName("l7");
        l7.setX(0);
        l7.setY(0);
        meaningLess(l7);

        Label l8    =   new Label("123", skin.get("default", LabelStyle.class));
        l8.setName("l8");
        l8.setX(0);
        l8.setY(0);
        meaningLess(l8);

        Label l9    =   new Label("diaoluo:", skin.get("default", LabelStyle.class));
        l9.setName("l9");
        l9.setX(0);
        l9.setY(0);
        meaningLess(l9);

        Label l12    =   new Label("ling X 10", skin.get("default", LabelStyle.class));
        l12.setName("l12");
        l12.setX(0);
        l12.setY(0);
        meaningLess(l12);





        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(l1).width(80).padLeft(10).height(30).padTop(10);
        this.add(l2).width(40).padTop(10);
        this.row();
        this.add(l3);
        this.add(l4);
        this.row();
        this.add(l5);
        this.add(l6);
        this.row();
        this.add(l7);
        this.add(l8);
        this.row();
        this.add(l9).padTop(10);
        this.row();
        this.add(b10);
        this.add(b11);
        this.row();
        this.add(l12);
        this.add(b13);
        this.add(b14);


    }

    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}