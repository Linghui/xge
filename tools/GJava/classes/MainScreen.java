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

public class MainScreen extends TacticsScreen implements GMessageProcessInterface{

    private String TAG = "MainScreen";
    
    
    
    private AssetManager manager = null;
    Skin skin = null;
    
    private boolean srcLoadDone = false;
    private Texture loadingBackgroundTexture;
    private NumericBar loadingBar = null;

    public MainScreen(Game game, AssetManager manager){
        super(game, 800, 480);
        this.manager = manager;
    }
    
    @Override
    public void show(){
        super.show();
        TextureRegion loadingRegion = getLoadingTextureRegion("data/40009002_pack1.png");
        this.setBackGround(loadingRegion);
        manager.load("data/uiskin.json", Skin.class);
        manager.load("data/40009002_pack1.png", Texture.class);
        manager.load("data/30100001/pack", TextureAtlas.class);

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
            Gdx.app.log(TAG, "progress " + manager.getProgress());
            if(manager.isLoaded("data/uiskin.json") && loadingBar == null){
                skin = manager.get("data/uiskin.json", Skin.class);
                
                loadingBar = new NumericBar(skin.getPatch("default-round"), skin.getPatch("default-round"), 300, 50, 0, 3, skin.get(LabelStyle.class));

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
        
        TextureRegion backRegion =  new TextureRegion(manager.get("data/40009002_pack1.png", Texture.class));
;
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


        Label l1    =   new Label("Lv.99", skin.get("yellow", LabelStyle.class));
        l1.setName("l1");
        l1.setX(60);
        l1.setY(410);
        this.addActorBottom(l1);

        Label playerName    =   new Label("上帝", skin.get("yellow", LabelStyle.class));
        playerName.setName("playerName");
        playerName.setX(120);
        playerName.setY(410);
        this.addActorBottom(playerName);

        Label jingliL    =   new Label("15/20", skin.get("default", LabelStyle.class));
        jingliL.setName("jingliL");
        jingliL.setX(90);
        jingliL.setY(440);
        this.addActorBottom(jingliL);

        Label goldL    =   new Label("9900", skin.get("default", LabelStyle.class));
        goldL.setName("goldL");
        goldL.setX(190);
        goldL.setY(440);
        this.addActorBottom(goldL);

        Label sliverL    =   new Label("9900", skin.get("default", LabelStyle.class));
        sliverL.setName("sliverL");
        sliverL.setX(290);
        sliverL.setY(440);
        this.addActorBottom(sliverL);

        Label rongyuL    =   new Label("9900", skin.get("default", LabelStyle.class));
        rongyuL.setName("rongyuL");
        rongyuL.setX(390);
        rongyuL.setY(440);
        this.addActorBottom(rongyuL);


    }
    
    private void initButtons(){

        TextureRegion playerHeadDown = skin.getRegion("yaoguai");
        TextureRegion playerHeadUp   = skin.getRegion("yaoguai");
        ImageButton playerHead = new ImageButton(new TextureRegionDrawable(playerHeadDown), new TextureRegionDrawable(playerHeadUp));
        playerHead.setX(0);
        playerHead.setY(410);
        playerHead.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "playerHead clicked");
                // TODO: strongly recommend do your own implementation in the method below
                playerHeadAction();
                return true;
            }
    
        });
        
        this.addActorBottom(playerHead);

        TextureRegion qishuDown = skin.getRegion("qishu");
        TextureRegion qishuUp   = skin.getRegion("qishu");
        ImageButton qishu = new ImageButton(new TextureRegionDrawable(qishuDown), new TextureRegionDrawable(qishuUp));
        qishu.setX(730);
        qishu.setY(10);
        qishu.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "qishu clicked");
                // TODO: strongly recommend do your own implementation in the method below
                qishuAction();
                return true;
            }
    
        });
        
        this.addActorBottom(qishu);

        TextureRegion bagDown = skin.getRegion("bag");
        TextureRegion bagUp   = skin.getRegion("bag");
        ImageButton bag = new ImageButton(new TextureRegionDrawable(bagDown), new TextureRegionDrawable(bagUp));
        bag.setX(660);
        bag.setY(10);
        bag.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "bag clicked");
                // TODO: strongly recommend do your own implementation in the method below
                bagAction();
                return true;
            }
    
        });
        
        this.addActorBottom(bag);

        TextureRegion zhenfaDown = skin.getRegion("zhenfa");
        TextureRegion zhenfaUp   = skin.getRegion("zhenfa");
        ImageButton zhenfa = new ImageButton(new TextureRegionDrawable(zhenfaDown), new TextureRegionDrawable(zhenfaUp));
        zhenfa.setX(590);
        zhenfa.setY(10);
        zhenfa.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "zhenfa clicked");
                // TODO: strongly recommend do your own implementation in the method below
                zhenfaAction();
                return true;
            }
    
        });
        
        this.addActorBottom(zhenfa);

        TextureRegion tavernDown = skin.getRegion("tavern");
        TextureRegion tavernUp   = skin.getRegion("tavern");
        ImageButton tavern = new ImageButton(new TextureRegionDrawable(tavernDown), new TextureRegionDrawable(tavernUp));
        tavern.setX(520);
        tavern.setY(10);
        tavern.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "tavern clicked");
                // TODO: strongly recommend do your own implementation in the method below
                tavernAction();
                return true;
            }
    
        });
        
        this.addActorBottom(tavern);

        TextureRegion caishenDown = skin.getRegion("caishen");
        TextureRegion caishenUp   = skin.getRegion("caishen");
        ImageButton caishen = new ImageButton(new TextureRegionDrawable(caishenDown), new TextureRegionDrawable(caishenUp));
        caishen.setX(730);
        caishen.setY(410);
        caishen.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "caishen clicked");
                // TODO: strongly recommend do your own implementation in the method below
                caishenAction();
                return true;
            }
    
        });
        
        this.addActorBottom(caishen);

        TextureRegion jingliDown = skin.getRegion("sliver");
        TextureRegion jingliUp   = skin.getRegion("sliver");
        ImageButton jingli = new ImageButton(new TextureRegionDrawable(jingliDown), new TextureRegionDrawable(jingliUp));
        jingli.setX(50);
        jingli.setY(440);
        jingli.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "jingli clicked");
                // TODO: strongly recommend do your own implementation in the method below
                jingliAction();
                return true;
            }
    
        });
        
        this.addActorBottom(jingli);

        TextureRegion goldDown = skin.getRegion("yuanbao");
        TextureRegion goldUp   = skin.getRegion("yuanbao");
        ImageButton gold = new ImageButton(new TextureRegionDrawable(goldDown), new TextureRegionDrawable(goldUp));
        gold.setX(150);
        gold.setY(440);
        gold.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "gold clicked");
                // TODO: strongly recommend do your own implementation in the method below
                goldAction();
                return true;
            }
    
        });
        
        this.addActorBottom(gold);

        TextureRegion sliverDown = skin.getRegion("sliver");
        TextureRegion sliverUp   = skin.getRegion("sliver");
        ImageButton sliver = new ImageButton(new TextureRegionDrawable(sliverDown), new TextureRegionDrawable(sliverUp));
        sliver.setX(250);
        sliver.setY(440);
        sliver.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "sliver clicked");
                // TODO: strongly recommend do your own implementation in the method below
                sliverAction();
                return true;
            }
    
        });
        
        this.addActorBottom(sliver);

        TextureRegion rongyuDown = skin.getRegion("yuanbao");
        TextureRegion rongyuUp   = skin.getRegion("yuanbao");
        ImageButton rongyu = new ImageButton(new TextureRegionDrawable(rongyuDown), new TextureRegionDrawable(rongyuUp));
        rongyu.setX(350);
        rongyu.setY(440);
        rongyu.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "rongyu clicked");
                // TODO: strongly recommend do your own implementation in the method below
                rongyuAction();
                return true;
            }
    
        });
        
        this.addActorBottom(rongyu);



    }
    
    private void initTextFields(){



    }
    
    private void initNpcs(){


        NPC cuihuaNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100001/30100001.json", false, false, manager.get("data/30100001/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "cuihua action");
                // TODO: strongly recommend do your own implementation in the method below
                cuihuaAction();
            }
        };
        cuihuaNpcActor.setX(350);
        cuihuaNpcActor.setY(240);
        this.addActorBackground(cuihuaNpcActor);


    }
    
    private void initLiners(){



    }
    
    private void selfInit() {
        // TODO: do your own special init here
    }

    public void playerHeadAction(){
        Gdx.app.log(TAG, "playerHeadAction called");
        return ;
    }
    public void qishuAction(){
        Gdx.app.log(TAG, "qishuAction called");
        return ;
    }
    public void bagAction(){
        Gdx.app.log(TAG, "bagAction called");
        return ;
    }
    public void zhenfaAction(){
        Gdx.app.log(TAG, "zhenfaAction called");
        return ;
    }
    public void tavernAction(){
        Gdx.app.log(TAG, "tavernAction called");
        return ;
    }
    public void caishenAction(){
        Gdx.app.log(TAG, "caishenAction called");
        return ;
    }
    public void jingliAction(){
        Gdx.app.log(TAG, "jingliAction called");
        return ;
    }
    public void goldAction(){
        Gdx.app.log(TAG, "goldAction called");
        return ;
    }
    public void sliverAction(){
        Gdx.app.log(TAG, "sliverAction called");
        return ;
    }
    public void rongyuAction(){
        Gdx.app.log(TAG, "rongyuAction called");
        return ;
    }
    public void cuihuaAction(){
        Gdx.app.log(TAG, "cuihuaAction called");
        return ;
    }

    
    @Override
    public boolean processMessage(byte[] message) {
        Gdx.app.log(TAG, "processMessage "+new String(message));
        return false;
    }

    @Override
    public void sendMessage(byte[] message) {
        if(this.game instanceof InOutPutInterface){
            ((InOutPutInterface)this.game).onWrite(message);
        }
    }
    
    @Override
    public void dispose() {
        manager.unload("data/uiskin.json");
        manager.unload("data/40009002_pack1.png");
        manager.unload("data/30100001/pack");

    }
}

