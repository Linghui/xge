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


public class PKInfoPanel extends Table {

    private String TAG = "PKInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    public PKInfoPanelActions actions = null;
    
    
    public PKInfoPanel(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;

        actions = new PKInfoPanelActions(manager, skin, this);
        
        init();
        
        this.setTouchable(Touchable.enabled);

    }
    
    
    private void init() {

        TextButton fresh = new TextButton("fresh",skin.get(TextButtonStyle.class));
        fresh.setName("fresh");
        fresh.setX(0);
        fresh.setY(0);
        fresh.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "fresh clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.freshAction();
            }
    
        });
        meaningLess(fresh);

        TextButton buy = new TextButton("buy",skin.get(TextButtonStyle.class));
        buy.setName("buy");
        buy.setX(0);
        buy.setY(0);
        buy.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "buy clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.buyAction();
            }
    
        });
        meaningLess(buy);




        Label honor    =   new Label("honor", skin.get("default", LabelStyle.class));
        honor.setName("honor");
        honor.setX(0);
        honor.setY(0);
        meaningLess(honor);

        Label honorN    =   new Label("honorN", skin.get("default", LabelStyle.class));
        honorN.setName("honorN");
        honorN.setX(0);
        honorN.setY(0);
        meaningLess(honorN);

        Label cha    =   new Label("cha", skin.get("default", LabelStyle.class));
        cha.setName("cha");
        cha.setX(0);
        cha.setY(0);
        meaningLess(cha);

        Label chaN    =   new Label("chaN", skin.get("default", LabelStyle.class));
        chaN.setName("chaN");
        chaN.setX(0);
        chaN.setY(0);
        meaningLess(chaN);

        Label descTitle    =   new Label("descTitle", skin.get("default", LabelStyle.class));
        descTitle.setName("descTitle");
        descTitle.setX(0);
        descTitle.setY(0);
        meaningLess(descTitle);

        Label desc    =   new Label("desc", skin.get("default", LabelStyle.class));
        desc.setName("desc");
        desc.setX(0);
        desc.setY(0);
        meaningLess(desc);







        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(honor);
        this.row();
        this.add(honorN);
        this.add(fresh);
        this.row();
        this.add(cha);
        this.row();
        this.add(chaN);
        this.add(buy);
        this.row();
        this.add(descTitle);
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