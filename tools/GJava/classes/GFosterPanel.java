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


public class GFosterPanel extends Table {

    private String TAG = "GFosterPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public GFosterPanelActions actions = null;
    
    
    public GFosterPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new GFosterPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {




        Label tab    =   new Label("tab", skin.get("default", LabelStyle.class));
        tab.setName("tab");
        tab.setX(0);
        tab.setY(0);
        meaningLess(tab);

        Label power    =   new Label("power", skin.get("default", LabelStyle.class));
        power.setName("power");
        power.setX(0);
        power.setY(0);
        meaningLess(power);

        Label pN    =   new Label("pN", skin.get("default", LabelStyle.class));
        pN.setName("pN");
        pN.setX(0);
        pN.setY(0);
        meaningLess(pN);

        Label mentality    =   new Label("mentality", skin.get("default", LabelStyle.class));
        mentality.setName("mentality");
        mentality.setX(0);
        mentality.setY(0);
        meaningLess(mentality);

        Label mN    =   new Label("mN", skin.get("default", LabelStyle.class));
        mN.setName("mN");
        mN.setX(0);
        mN.setY(0);
        meaningLess(mN);

        Label agile    =   new Label("agile", skin.get("default", LabelStyle.class));
        agile.setName("agile");
        agile.setX(0);
        agile.setY(0);
        meaningLess(agile);

        Label aN    =   new Label("aN", skin.get("default", LabelStyle.class));
        aN.setName("aN");
        aN.setX(0);
        aN.setY(0);
        meaningLess(aN);

        Label endur    =   new Label("endur", skin.get("default", LabelStyle.class));
        endur.setName("endur");
        endur.setX(0);
        endur.setY(0);
        meaningLess(endur);

        Label eN    =   new Label("eN", skin.get("default", LabelStyle.class));
        eN.setName("eN");
        eN.setX(0);
        eN.setY(0);
        meaningLess(eN);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(tab).colspan(2);
        this.row();
        this.add(power);
        this.add(pN);
        this.row();
        this.add(mentality);
        this.add(mN);
        this.row();
        this.add(agile);
        this.add(aN);
        this.row();
        this.add(endur);
        this.add(eN);


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