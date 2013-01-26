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

public class MengJiangGeWindow extends XWindow implements XMsgUpdate{
    
    private String TAG = "MengJiangGeWindow";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    
    public MengJiangGeWindow(AssetManager manager, Skin skin, float width, float height) {
        super(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.manager = manager;
        init();
        selfInit();
    }

    private void init() {



        Label goblinNumberL    =   new Label("可拥有武将数:", skin.get("default", LabelStyle.class));
        goblinNumberL.setName("goblinNumberL");
        goblinNumberL.setX(0);
        goblinNumberL.setY(0);
        meaningLess(goblinNumberL);

        Label goblinNumberNL    =   new Label("8/10", skin.get("default", LabelStyle.class));
        goblinNumberNL.setName("goblinNumberNL");
        goblinNumberNL.setX(0);
        goblinNumberNL.setY(0);
        meaningLess(goblinNumberNL);

        Label bestFlushL    =   new Label("至尊刷新", skin.get("default", LabelStyle.class));
        bestFlushL.setName("bestFlushL");
        bestFlushL.setX(0);
        bestFlushL.setY(0);
        meaningLess(bestFlushL);

        Label betterFlushL    =   new Label("白金刷新", skin.get("default", LabelStyle.class));
        betterFlushL.setName("betterFlushL");
        betterFlushL.setX(0);
        betterFlushL.setY(0);
        meaningLess(betterFlushL);

        Label normalFlushL    =   new Label("普通刷新", skin.get("default", LabelStyle.class));
        normalFlushL.setName("normalFlushL");
        normalFlushL.setX(0);
        normalFlushL.setY(0);
        meaningLess(normalFlushL);


        TextButton bestFlush = new TextButton("至尊刷新",skin.get(TextButtonStyle.class));
        bestFlush.setName("bestFlush");
        bestFlush.setX(0);
        bestFlush.setY(0);
        bestFlush.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "bestFlush clicked");
                // TODO: strongly recommend do your own implementation in the method below
                bestFlushAction();
                return true;
            }
    
        });
        meaningLess(bestFlush);

        TextButton betterFlush = new TextButton("白金刷新",skin.get(TextButtonStyle.class));
        betterFlush.setName("betterFlush");
        betterFlush.setX(0);
        betterFlush.setY(0);
        betterFlush.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "betterFlush clicked");
                // TODO: strongly recommend do your own implementation in the method below
                betterFlushAction();
                return true;
            }
    
        });
        meaningLess(betterFlush);

        TextButton normalFlush = new TextButton("普通刷新",skin.get(TextButtonStyle.class));
        normalFlush.setName("normalFlush");
        normalFlush.setX(0);
        normalFlush.setY(0);
        normalFlush.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "normalFlush clicked");
                // TODO: strongly recommend do your own implementation in the method below
                normalFlushAction();
                return true;
            }
    
        });
        meaningLess(normalFlush);





        GoblinCardPanel goblinCardPanel1 = new GoblinCardPanel(manager, skin);
        GoblinCardPanel goblinCardPanel2 = new GoblinCardPanel(manager, skin);
        GoblinCardPanel goblinCardPanel3 = new GoblinCardPanel(manager, skin);


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(bestFlush);
        this.row();
        this.add(betterFlush);
        this.row();
        this.add(normalFlush);
        this.row();
        this.add(goblinNumberL);
        this.row();
        this.add(goblinNumberNL);
        this.row();
        this.add(bestFlushL);
        this.row();
        this.add(betterFlushL);
        this.row();
        this.add(normalFlushL);
        this.row();
        this.add(goblinCardPanel1).width(200).height(300);
        this.row();
        this.add(goblinCardPanel2).width(200).height(300);
        this.row();
        this.add(goblinCardPanel3).width(200).height(300);
        this.row();

    }

    private void selfInit() {
        // TODO: do your own special init here
    }
    
    public void bestFlushAction(){
        Gdx.app.log(TAG, "bestFlushAction called");
        return ;
    }
    public void betterFlushAction(){
        Gdx.app.log(TAG, "betterFlushAction called");
        return ;
    }
    public void normalFlushAction(){
        Gdx.app.log(TAG, "normalFlushAction called");
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
