package com.gol.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.bearcat.obj.proto.XMsgObj.XMsg;
import com.bearcat.obj.proto.interfaces.XMsgUpdate;
import com.gol.xge.rpg.ui.XWindow;

public class BingqiWindow extends XWindow implements XMsgUpdate{
    
    private String TAG = "BingqiWindow";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    
    public BingqiWindow(AssetManager manager, Skin skin, float width, float height) {
        super(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.manager = manager;
        init();
        selfInit();
    }

    private void init() {



        Label feeL    =   new Label("强化费用:", skin.get("default", LabelStyle.class));
        feeL.setName("feeL");
        feeL.setX(10);
        feeL.setY(290);
        meaningLess(feeL);

        Label feeNL    =   new Label("9527", skin.get("default", LabelStyle.class));
        feeNL.setName("feeNL");
        feeNL.setX(10);
        feeNL.setY(290);
        meaningLess(feeNL);

        Label luckyL    =   new Label("幸运值", skin.get("default", LabelStyle.class));
        luckyL.setName("luckyL");
        luckyL.setX(10);
        luckyL.setY(290);
        meaningLess(luckyL);

        Label descLuckyL    =   new Label("我相信你懂的，括弧笑", skin.get("default", LabelStyle.class));
        descLuckyL.setName("descLuckyL");
        descLuckyL.setX(10);
        descLuckyL.setY(290);
        meaningLess(descLuckyL);


        TextButton enhanceB = new TextButton("强化",skin.get(TextButtonStyle.class));
        enhanceB.setName("enhanceB");
        enhanceB.setX(10);
        enhanceB.setY(260);
        enhanceB.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "enhanceB clicked");
                // TODO: strongly recommend do your own implementation in the method below
                enhanceBAction();
                return true;
            }
    
        });
        meaningLess(enhanceB);





        QHEquipPanelTable qhEquipPanelTable = new QHEquipPanelTable(manager, skin);        EquipPanelTable equipPanelTable = new EquipPanelTable(manager, skin);

        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(enhanceB);
        this.row();
        this.add(feeL);
        this.row();
        this.add(feeNL);
        this.row();
        this.add(luckyL);
        this.row();
        this.add(descLuckyL);
        this.row();
        this.add(qhEquipPanelTable).width(280).height(320);
        this.row();
        this.add(equipPanelTable).width(350).height(320);
        this.row();

    }

    private void selfInit() {
        // TODO: do your own special init here
    }
    
    public void enhanceBAction(){
        Gdx.app.log(TAG, "enhanceBAction called");
        return ;
    }


    @Override
    public void update(XMsg msg) {
        // for update content of this table using message come from server
        
    }


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }
}
