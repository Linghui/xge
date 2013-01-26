package com.gol.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gol.xge.rpg.Common;
import com.gol.xge.rpg.InAndExternalFileHandleResolver;
import com.gol.xge.rpg.scense.RPGScreen;
import com.gol.xge.rpg.scense.TacticsScreen;
import com.gol.xge.rpg.ui.AnimationActor;
import com.gol.xge.rpg.ui.AnimationGroup;
import com.gol.xge.rpg.ui.NumericBar;
import com.gol.xge.socket.listener.GMessageProcessInterface;
import com.gol.xge.socket.listener.InOutPutInterface;

public class RegisterScreen extends TacticsScreen implements PBMessageProcessInterface{

    private String TAG = "RegisterScreen";
    
    
    
    private AssetManager manager = null;
    Skin skin = null;
    
    private boolean srcLoadDone = false;
    private Texture loadingBackgroundTexture;
    private NumericBar loadingBar = null;

    public RegisterScreen(Game game, AssetManager manager){
        super(game, 800, 480);
        this.manager = manager;
    }
    
    @Override
    public void show(){
        super.show();
        TextureRegion loadingRegion = getLoadingTextureRegion("");
        this.setBackGround(loadingRegion);
        manager.load("data/uiskin.json", Skin.class);

    }
    
    private TextureRegion getLoadingTextureRegion(String path){

        if(path != null || "".equals(path)){
            return null;
        }

        loadingBackgroundTexture = new Texture(Gdx.files.internal(path));
        TextureRegion region = new TextureRegion(loadingBackgroundTexture);
        return region;
    }

	
    
    @Override
    public void render(float delta){
        super.render(delta);
        if(!manager.update()){
            if(manager.isLoaded("data/uiskin.json") && loadingBar == null){
                skin = manager.get("data/uiskin.json", Skin.class);
                
                loadingBar = new NumericBar(skin.getPatch("default-round"), skin.getPatch("default-round"), 300, 50, 0, 1, skin.get(LabelStyle.class));

                loadingBar.setX(50);
                loadingBar.setY(50);
                this.addActorBackground(loadingBar);
                
            }
            
            if(loadingBar != null){
                int status = (int) (manager.getProgress()*10);
                Gdx.app.log(TAG, "status " + status);
                loadingBar.setStatusNum(status);
                
            }
        } 
        if(manager.update() && this.srcLoadDone == false){
            if(loadingBackgroundTexture != null){
                loadingBackgroundTexture.dispose();
            }
            this.srcLoadDone = true;
            init();
            initLabels();
            initButtons();
            initTextFields();
            initNpcs();
            selfInit();
            return;
        }
        
        selfRender(delta);

    }
    
    private void selfRender(float delta) {
        // TODO: do your own special render here
    }

    private void init(){
    
        if(skin == null){
            skin = manager.get("data/uiskin.json", Skin.class);
        }
        
        TextureRegion backRegion = null;
        this.setBackGround(backRegion, false);
        this.setLimitY(0);
        
        Actor actor = createLeadingRole("", "");

        initCam();
        initLeadingRole(actor);


    }
    
    private Actor createLeadingRole(String json, String pack){
    
        if(!json.endsWith("json")){
            return null;
        }

        AnimationActor playerAnimation = new AnimationActor( new AnimationGroup(Common.readAnimationResource(json, false, false, manager.get(pack, TextureAtlas.class))));
        playerAnimation.setY(480/8);
        Group leadingRole = new Group();
        leadingRole.addActor(playerAnimation);
        leadingRole.setWidth(playerAnimation.getWidth());
        leadingRole.setHeight(playerAnimation.getHeight());
        
        return leadingRole;
    }

    private void initLabels(){



    }
    
    private void initButtons(){

        TextButton btn1 = new TextButton("注册",skin.get(TextButtonStyle.class));
        btn1.setName("btn1");
        btn1.setX(300);
        btn1.setY(200);
        btn1.addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "btn1 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.btn1Action();
            }
    
        });
        this.addActorBottom(btn1);



    }
    
    private void initTextFields(){

        TextField username = new TextField("",skin);
        username.setName("username");
        username.setX(300);
        username.setY(350);
        username.setMessageText("用户名");
        this.addActorBottom(username);

        TextField password = new TextField("",skin);
        password.setName("password");
        password.setX(300);
        password.setY(300);
        password.setMessageText("密码");
        this.addActorBottom(password);

        TextField passwordConfirm = new TextField("",skin);
        passwordConfirm.setName("passwordConfirm");
        passwordConfirm.setX(300);
        passwordConfirm.setY(250);
        passwordConfirm.setMessageText("密码确认");
        this.addActorBottom(passwordConfirm);



    }
    
    private void initNpcs(){



    }
    
    private void initLiners(){



    }
    
    private void selfInit() {
        // TODO: do your own special init here
    }

    public void btn1Action(){
        Gdx.app.log(TAG, "btn1Action called");
        return ;
    }

    
    @Override
    public boolean processMessage(XMsg message) {
        Gdx.app.log(TAG, "processMessage " + message);
        return false;
    }

    
    @Override
    public void dispose() {
        manager.unload("data/uiskin.json");

    }
}

