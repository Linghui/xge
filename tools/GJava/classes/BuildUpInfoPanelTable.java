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


public class BuildUpInfoPanelTable extends Table {

    private String TAG = "BuildUpInfoPanelTable";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public BuildUpInfoPanelTable(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
    }
    
    
    private void init() {

        TextButton upgradeBtn = new TextButton("升级",skin.get(TextButtonStyle.class));
        upgradeBtn.setName("upgradeBtn");
        upgradeBtn.setX(320);
        upgradeBtn.setY(260);
        upgradeBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "upgradeBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                upgradeBtnAction();
                return true;
            }
    
        });
        meaningLess(upgradeBtn);




        Label titleL    =   new Label("武神殿", skin.get("default", LabelStyle.class));
        titleL.setName("titleL");
        titleL.setX(10);
        titleL.setY(290);
        meaningLess(titleL);

        Label cLevelL    =   new Label("当前等级:", skin.get("default", LabelStyle.class));
        cLevelL.setName("cLevelL");
        cLevelL.setX(90);
        cLevelL.setY(290);
        meaningLess(cLevelL);

        Label cLevelNL    =   new Label("26 级", skin.get("default", LabelStyle.class));
        cLevelNL.setName("cLevelNL");
        cLevelNL.setX(10);
        cLevelNL.setY(260);
        meaningLess(cLevelNL);

        Label userLevelL    =   new Label("主角等级:", skin.get("default", LabelStyle.class));
        userLevelL.setName("userLevelL");
        userLevelL.setX(60);
        userLevelL.setY(260);
        meaningLess(userLevelL);

        Label userLevelNL    =   new Label("27", skin.get("default", LabelStyle.class));
        userLevelNL.setName("userLevelNL");
        userLevelNL.setX(10);
        userLevelNL.setY(230);
        meaningLess(userLevelNL);

        Label priceL    =   new Label("银币:", skin.get("default", LabelStyle.class));
        priceL.setName("priceL");
        priceL.setX(60);
        priceL.setY(230);
        meaningLess(priceL);

        Label priceNL    =   new Label("9527", skin.get("default", LabelStyle.class));
        priceNL.setName("priceNL");
        priceNL.setX(10);
        priceNL.setY(200);
        meaningLess(priceNL);

        Label honorL    =   new Label("军功:", skin.get("default", LabelStyle.class));
        honorL.setName("honorL");
        honorL.setX(60);
        honorL.setY(200);
        meaningLess(honorL);

        Label honorNL    =   new Label("123", skin.get("green", LabelStyle.class));
        honorNL.setName("honorNL");
        honorNL.setX(60);
        honorNL.setY(200);
        meaningLess(honorNL);

        Label descTL    =   new Label("升级效果:", skin.get("green", LabelStyle.class));
        descTL.setName("descTL");
        descTL.setX(60);
        descTL.setY(200);
        meaningLess(descTL);

        Label desc1L    =   new Label("1. 主城达到30级时，可开启八卦阵", skin.get("default", LabelStyle.class));
        desc1L.setName("desc1L");
        desc1L.setX(10);
        desc1L.setY(160);
        meaningLess(desc1L);

        Label desc2L    =   new Label("2. 主城升级后，其他建筑均可升级", skin.get("default", LabelStyle.class));
        desc2L.setName("desc2L");
        desc2L.setX(10);
        desc2L.setY(160);
        meaningLess(desc2L);





        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(upgradeBtn);
        this.row();
        this.add(titleL);
        this.row();
        this.add(cLevelL);
        this.row();
        this.add(cLevelNL);
        this.row();
        this.add(userLevelL);
        this.row();
        this.add(userLevelNL);
        this.row();
        this.add(priceL);
        this.row();
        this.add(priceNL);
        this.row();
        this.add(honorL);
        this.row();
        this.add(honorNL);
        this.row();
        this.add(descTL);
        this.row();
        this.add(desc1L);
        this.row();
        this.add(desc2L);
        this.row();


    }

    
    public void upgradeBtnAction(){
        Gdx.app.log(TAG, "upgradeBtnAction called");
        return ;
    }


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }


}