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

public class CreateWindow extends XWindow implements XMsgUpdate{
    
    private String TAG = "CreateWindow";

    private final static String TITLE = "";

    private Skin skin = null;
    private AssetManager manager = null;
    
    public CreateWindow(AssetManager manager, Skin skin, float width, float height) {
        super(skin.get("default", XWindowStyle.class), width, height);
        this.skin = skin;
        this.manager = manager;
        init();
        selfInit();
    }

    private void init() {



        Label typeL    =   new Label("type", skin.get("default", LabelStyle.class));
        typeL.setName("typeL");
        typeL.setX(10);
        typeL.setY(290);
        meaningLess(typeL);

        Label nameL    =   new Label("name", skin.get("default", LabelStyle.class));
        nameL.setName("nameL");
        nameL.setX(10);
        nameL.setY(290);
        meaningLess(nameL);

        Label widthL    =   new Label("width:", skin.get("default", LabelStyle.class));
        widthL.setName("widthL");
        widthL.setX(10);
        widthL.setY(290);
        meaningLess(widthL);

        Label heightL    =   new Label("height", skin.get("default", LabelStyle.class));
        heightL.setName("heightL");
        heightL.setX(10);
        heightL.setY(290);
        meaningLess(heightL);


        TextButton createBtn = new TextButton("Create",skin.get(TextButtonStyle.class));
        createBtn.setName("createBtn");
        createBtn.setX(10);
        createBtn.setY(260);
        createBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "createBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                createBtnAction();
                return true;
            }
    
        });
        meaningLess(createBtn);

        TextButton cancelBtn = new TextButton("Cancel",skin.get(TextButtonStyle.class));
        cancelBtn.setName("cancelBtn");
        cancelBtn.setX(10);
        cancelBtn.setY(260);
        cancelBtn.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "cancelBtn clicked");
                // TODO: strongly recommend do your own implementation in the method below
                cancelBtnAction();
                return true;
            }
    
        });
        meaningLess(cancelBtn);



        TextField nameTF = new TextField("",skin);
        nameTF.setName("nameTF");
        nameTF.setX(100);
        nameTF.setY(300);
        nameTF.setMessageText("");
        meaningLess(nameTF);

        TextField widthTF = new TextField("",skin);
        widthTF.setName("widthTF");
        widthTF.setX(100);
        widthTF.setY(250);
        widthTF.setMessageText("");
        meaningLess(widthTF);

        TextField heightTF = new TextField("",skin);
        heightTF.setName("heightTF");
        heightTF.setX(100);
        heightTF.setY(250);
        heightTF.setMessageText("");
        meaningLess(heightTF);





        // manage your layout here, sorry wo do not support layout configuration now
        // but we will do the support in the future
        this.add(createBtn);
        this.row();
        this.add(cancelBtn);
        this.row();
        this.add(typeL);
        this.row();
        this.add(nameL);
        this.row();
        this.add(widthL);
        this.row();
        this.add(heightL);
        this.row();
        this.add(nameTF);
        this.row();
        this.add(widthTF);
        this.row();
        this.add(heightTF);
        this.row();

    }

    private void selfInit() {
        // TODO: do your own special init here
    }
    
    public void createBtnAction(){
        Gdx.app.log(TAG, "createBtnAction called");
        return ;
    }
    public void cancelBtnAction(){
        Gdx.app.log(TAG, "cancelBtnAction called");
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
