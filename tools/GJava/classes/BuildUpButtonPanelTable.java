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
import com.gol.xge.rpg.ui.XCell;


public class BuildUpButtonPanelTable extends Table {

    private String TAG = "BuildUpButtonPanelTable";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public BuildUpButtonPanelTable(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
    }
    
    
    private void init() {

        TextButton wushenUpBtn = new TextButton("Lv26 武神殿(可升级)",skin.get(TextButtonStyle.class));
        wushenUpBtn.setName("wushenUpBtn");
        wushenUpBtn.setX(320);
        wushenUpBtn.setY(260);
        wushenUpBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "wushenUpBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                wushenUpBtnAction();
                return true;
            }
    
        });
        meaningLess(wushenUpBtn);

        TextButton shenbingUpBtn = new TextButton("Lv22 神兵坊(可升级)",skin.get(TextButtonStyle.class));
        shenbingUpBtn.setName("shenbingUpBtn");
        shenbingUpBtn.setX(320);
        shenbingUpBtn.setY(260);
        shenbingUpBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "shenbingUpBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                shenbingUpBtnAction();
                return true;
            }
    
        });
        meaningLess(shenbingUpBtn);

        TextButton juxianUpBtn = new TextButton("Lv23 聚贤庄(可升级)",skin.get(TextButtonStyle.class));
        juxianUpBtn.setName("juxianUpBtn");
        juxianUpBtn.setX(320);
        juxianUpBtn.setY(260);
        juxianUpBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "juxianUpBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                juxianUpBtnAction();
                return true;
            }
    
        });
        meaningLess(juxianUpBtn);

        TextButton xianjingUpBtn = new TextButton("Lv24 仙境(可升级)",skin.get(TextButtonStyle.class));
        xianjingUpBtn.setName("xianjingUpBtn");
        xianjingUpBtn.setX(320);
        xianjingUpBtn.setY(260);
        xianjingUpBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "xianjingUpBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                xianjingUpBtnAction();
                return true;
            }
    
        });
        meaningLess(xianjingUpBtn);








        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(wushenUpBtn);
        this.row();
        this.add(shenbingUpBtn);
        this.row();
        this.add(juxianUpBtn);
        this.row();
        this.add(xianjingUpBtn);
        this.row();


    }

    
    public void wushenUpBtnAction(){
        Gdx.app.log(TAG, "wushenUpBtnAction called");
        return ;
    }
    public void shenbingUpBtnAction(){
        Gdx.app.log(TAG, "shenbingUpBtnAction called");
        return ;
    }
    public void juxianUpBtnAction(){
        Gdx.app.log(TAG, "juxianUpBtnAction called");
        return ;
    }
    public void xianjingUpBtnAction(){
        Gdx.app.log(TAG, "xianjingUpBtnAction called");
        return ;
    }


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}