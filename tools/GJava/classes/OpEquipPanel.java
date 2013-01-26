package com.gol.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class OpEquipPanel extends Table {

    private String TAG = "OpEquipPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public OpEquipPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
    }
    
    
    private void init() {




        Label nameL    =   new Label("苗人凤", skin.get("default", LabelStyle.class));
        nameL.setName("nameL");
        nameL.setX(0);
        nameL.setY(0);
        meaningLess(nameL);

        Label strenthL    =   new Label("力量", skin.get("default", LabelStyle.class));
        strenthL.setName("strenthL");
        strenthL.setX(0);
        strenthL.setY(0);
        meaningLess(strenthL);

        Label strenthNL    =   new Label("20", skin.get("default", LabelStyle.class));
        strenthNL.setName("strenthNL");
        strenthNL.setX(0);
        strenthNL.setY(0);
        meaningLess(strenthNL);

        Label intL    =   new Label("智力", skin.get("default", LabelStyle.class));
        intL.setName("intL");
        intL.setX(0);
        intL.setY(0);
        meaningLess(intL);

        Label intNL    =   new Label("31", skin.get("default", LabelStyle.class));
        intNL.setName("intNL");
        intNL.setX(0);
        intNL.setY(0);
        meaningLess(intNL);

        Label agiL    =   new Label("敏捷", skin.get("default", LabelStyle.class));
        agiL.setName("agiL");
        agiL.setX(0);
        agiL.setY(0);
        meaningLess(agiL);

        Label agiNL    =   new Label("31", skin.get("default", LabelStyle.class));
        agiNL.setName("agiNL");
        agiNL.setX(0);
        agiNL.setY(0);
        meaningLess(agiNL);

        Label staL    =   new Label("体力", skin.get("default", LabelStyle.class));
        staL.setName("staL");
        staL.setX(0);
        staL.setY(0);
        meaningLess(staL);

        Label staNL    =   new Label("100", skin.get("default", LabelStyle.class));
        staNL.setName("staNL");
        staNL.setX(0);
        staNL.setY(0);
        meaningLess(staNL);


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(nameL);
        this.row();
        this.add(strenthL);
        this.row();
        this.add(strenthNL);
        this.row();
        this.add(intL);
        this.row();
        this.add(intNL);
        this.row();
        this.add(agiL);
        this.row();
        this.add(agiNL);
        this.row();
        this.add(staL);
        this.row();
        this.add(staNL);
        this.row();


    }

    


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}