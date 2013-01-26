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


public class EquipInfoPane extends Table {

    private String TAG = "EquipInfoPane";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public EquipInfoPaneActions actions = null;
    
    
    public EquipInfoPane(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new EquipInfoPaneActions(manager, skin, this);
        
        init();

    }
    
    
    private void init() {

        TextButton b1 = new TextButton("tu",skin.get(TextButtonStyle.class));
        b1.setName("b1");
        b1.setX(0);
        b1.setY(0);
        b1.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b1 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b1Action();
            }
    
        });
        meaningLess(b1);




        Label name    =   new Label("name", skin.get("default", LabelStyle.class));
        name.setName("name");
        name.setX(0);
        name.setY(0);
        meaningLess(name);

        Label type    =   new Label("type", skin.get("default", LabelStyle.class));
        type.setName("type");
        type.setX(0);
        type.setY(0);
        meaningLess(type);

        Label level    =   new Label("level", skin.get("default", LabelStyle.class));
        level.setName("level");
        level.setX(0);
        level.setY(0);
        meaningLess(level);

        Label attri    =   new Label("attri", skin.get("default", LabelStyle.class));
        attri.setName("attri");
        attri.setX(0);
        attri.setY(0);
        meaningLess(attri);





        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(b1).pad(10);
        this.row();
        this.add(name).pad(5);
        this.row();
        this.add(type).pad(5);
        this.row();
        this.add(level).pad(5);
        this.row();
        this.add(attri).pad(5);


    }

    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}