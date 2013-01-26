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


public class QHEquipPanelTable extends Table {

    private String TAG = "QHEquipPanelTable";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public QHEquipPanelTable(AssetManager manager, Skin skin) {
        this.manager = manager;
        this.skin = skin;
        init();
    }
    
    
    private void init() {

        TextButton unequipBtn = new TextButton("卸任",skin.get(TextButtonStyle.class));
        unequipBtn.setName("unequipBtn");
        unequipBtn.setX(320);
        unequipBtn.setY(260);
        unequipBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "unequipBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                unequipBtnAction();
                return true;
            }
    
        });
        meaningLess(unequipBtn);




        Label nameL    =   new Label("勇者铠甲", skin.get("green", LabelStyle.class));
        nameL.setName("nameL");
        nameL.setX(10);
        nameL.setY(290);
        this.addActor(nameL);

        Label levelL    =   new Label("+31", skin.get("green", LabelStyle.class));
        levelL.setName("levelL");
        levelL.setX(90);
        levelL.setY(290);
        this.addActor(levelL);

        Label typeL    =   new Label("铠甲", skin.get("default", LabelStyle.class));
        typeL.setName("typeL");
        typeL.setX(10);
        typeL.setY(260);
        this.addActor(typeL);

        Label levelL    =   new Label("等级:", skin.get("default", LabelStyle.class));
        levelL.setName("levelL");
        levelL.setX(60);
        levelL.setY(260);
        this.addActor(levelL);

        Label levelNL    =   new Label("12", skin.get("default", LabelStyle.class));
        levelNL.setName("levelNL");
        levelNL.setX(10);
        levelNL.setY(230);
        this.addActor(levelNL);

        Label priceL    =   new Label("售价:", skin.get("default", LabelStyle.class));
        priceL.setName("priceL");
        priceL.setX(60);
        priceL.setY(230);
        this.addActor(priceL);

        Label priceNL    =   new Label("200", skin.get("default", LabelStyle.class));
        priceNL.setName("priceNL");
        priceNL.setX(10);
        priceNL.setY(200);
        this.addActor(priceNL);

        Label attributeL    =   new Label("物防+", skin.get("green", LabelStyle.class));
        attributeL.setName("attributeL");
        attributeL.setX(60);
        attributeL.setY(200);
        this.addActor(attributeL);

        Label attributeNL    =   new Label("123", skin.get("green", LabelStyle.class));
        attributeNL.setName("attributeNL");
        attributeNL.setX(60);
        attributeNL.setY(200);
        this.addActor(attributeNL);

        Label placeL    =   new Label("出处:", skin.get("green", LabelStyle.class));
        placeL.setName("placeL");
        placeL.setX(60);
        placeL.setY(200);
        this.addActor(placeL);

        Label desc    =   new Label("智擒鳌拜", skin.get("default", LabelStyle.class));
        desc.setName("desc");
        desc.setX(10);
        desc.setY(160);
        this.addActor(desc);


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(unequipBtn);
        this.row();
        this.add(nameL);
        this.row();
        this.add(levelL);
        this.row();
        this.add(typeL);
        this.row();
        this.add(levelL);
        this.row();
        this.add(levelNL);
        this.row();
        this.add(priceL);
        this.row();
        this.add(priceNL);
        this.row();
        this.add(attributeL);
        this.row();
        this.add(attributeNL);
        this.row();
        this.add(placeL);
        this.row();
        this.add(desc);
        this.row();


    }

    
    public void unequipBtnAction(){
        Gdx.app.log(TAG, "unequipBtnAction called");
        return ;
    }



    private void meaningLess(TextButton unequipBtn) {
        // ingnore this, just for avoiding some history implement problem
        
    }

}