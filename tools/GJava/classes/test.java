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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gol.xge.rpg.ui.XWindow;

public class Test extends XWindow implements XMsgUpdate{
    
    private String TAG = "Test";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    public TestActions actions = null;
    
    public Test(AssetManager manager, Skin skin, float width, float height) {
        super(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.manager = manager;
        init();
        selfInit();
        
        actions = new TestActions(manager, skin, this);
    }

    private void init() {




        TextButton b1 = new TextButton("",skin.get(TextButtonStyle.class));
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

        TextButton b2 = new TextButton("",skin.get(TextButtonStyle.class));
        b2.setName("b2");
        b2.setX(0);
        b2.setY(0);
        b2.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b2 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b2Action();
            }
    
        });
        meaningLess(b2);

        TextButton b3 = new TextButton("",skin.get(TextButtonStyle.class));
        b3.setName("b3");
        b3.setX(0);
        b3.setY(0);
        b3.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b3 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b3Action();
            }
    
        });
        meaningLess(b3);

        TextButton b4 = new TextButton("tet",skin.get(TextButtonStyle.class));
        b4.setName("b4");
        b4.setX(0);
        b4.setY(0);
        b4.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "b4 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.b4Action();
            }
    
        });
        meaningLess(b4);





        SceneInfoPanel t5 = new SceneInfoPanel(manager, skin);
        t5.setName("t5");


        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(HASH(0x7ff962028998));
        this.add(HASH(0x7ff9620fc0b0));
        this.add(HASH(0x7ff9620fc1e8));
        this.add(HASH(0x7ff9620fc320));
        this.add(HASH(0x7ff9620fc4d0));

    }

    private void selfInit() {
        // TODO: do your own special init here
    }


    @Override
    public void update(XMsg msg) {
        // for update content of this table using message come from server
        
    }


    private void meaningLess(Actor actor) {
        // ingnore this, just for avoiding some history implement problem
        
    }
}
