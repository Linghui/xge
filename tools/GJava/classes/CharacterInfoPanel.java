package com.gol.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.gol.game.xmsg.ui.XPanel;

public class CharacterInfoPanel extends Table {

    private String TAG = "CharacterInfoPanel";
    
    private AssetManager manager = null;
    private Skin skin = null;
    
    
    public CharacterInfoPanel(AssetManager manager, Skin skin) {
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




        Label nameL    =   new Label("东方不败", skin.get("green", LabelStyle.class));
        nameL.setName("nameL");
        nameL.setX(10);
        nameL.setY(290);
        this.addActor(nameL);

        Label levelL    =   new Label("Lv.99", skin.get("yellow", LabelStyle.class));
        levelL.setName("levelL");
        levelL.setX(90);
        levelL.setY(290);
        this.addActor(levelL);

        Label stepL    =   new Label("进阶", skin.get("default", LabelStyle.class));
        stepL.setName("stepL");
        stepL.setX(10);
        stepL.setY(260);
        this.addActor(stepL);

        Label stepNL    =   new Label("15/45", skin.get("default", LabelStyle.class));
        stepNL.setName("stepNL");
        stepNL.setX(60);
        stepNL.setY(260);
        this.addActor(stepNL);

        Label strengthL    =   new Label("力量", skin.get("default", LabelStyle.class));
        strengthL.setName("strengthL");
        strengthL.setX(10);
        strengthL.setY(230);
        this.addActor(strengthL);

        Label strengthNL    =   new Label("343", skin.get("default", LabelStyle.class));
        strengthNL.setName("strengthNL");
        strengthNL.setX(60);
        strengthNL.setY(230);
        this.addActor(strengthNL);

        Label speedL    =   new Label("敏捷", skin.get("default", LabelStyle.class));
        speedL.setName("speedL");
        speedL.setX(10);
        speedL.setY(200);
        this.addActor(speedL);

        Label speedNL    =   new Label("532", skin.get("default", LabelStyle.class));
        speedNL.setName("speedNL");
        speedNL.setX(60);
        speedNL.setY(200);
        this.addActor(speedNL);

        Label desc    =   new Label("目前就调个大概吧，坐标没精力弄的太细.凑合看吧，括弧笑", skin.get("default", LabelStyle.class));
        desc.setName("desc");
        desc.setX(10);
        desc.setY(160);
        this.addActor(desc);


this.add(unequipBtn);
this.add(nameL);
this.add(levelL);
this.add(stepL);
this.add(stepNL);
this.add(strengthL);
this.add(strengthNL);
this.add(speedL);
this.add(speedNL);
this.add(desc);


    }

    
    public void unequipBtnAction(){
        Gdx.app.log(TAG, "unequipBtnAction called");
        return ;
    }


}