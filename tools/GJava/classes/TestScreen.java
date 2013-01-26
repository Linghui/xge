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

public class TestScreen extends RPGScreen implements GMessageProcessInterface{

    private String TAG = "TestScreen";
    
    
    
    private AssetManager manager = null;
    Skin skin = null;
    
    private boolean srcLoadDone = false;
    private Texture loadingBackgroundTexture;
    private NumericBar loadingBar = null;

    public TestScreen(Game game, AssetManager manager){
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
        manager.load("data/30100001/pack", TextureAtlas.class);
        manager.load("data/30100002/pack", TextureAtlas.class);
        manager.load("data/30100003/pack", TextureAtlas.class);
        manager.load("data/30100004/pack", TextureAtlas.class);
        manager.load("data/30100005/pack", TextureAtlas.class);
        manager.load("data/30100006/pack", TextureAtlas.class);
        manager.load("data/30100008/pack", TextureAtlas.class);
        manager.load("data/30100009/pack", TextureAtlas.class);

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
                
                loadingBar = new NumericBar(skin.getPatch("default-round"), skin.getPatch("default-round"), 300, 50, 0, 11, skin.get(LabelStyle.class));

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
        this.setLimitY(200);
        
        Actor actor = createLeadingRole("data/30100001/30100001.json", "data/30100001/pack");

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


        Label l1    =   new Label("Lv.990", skin.get("yellow", LabelStyle.class));
        l1.setName("l1");
        l1.setX(20);
        l1.setY(450);
        this.addActorBottom(l1);


    }
    
    private void initButtons(){

        TextButton btn1 = new TextButton("充值",skin.get(TextButtonStyle.class));
        btn1.setName("btn1");
        btn1.setX(80);
        btn1.setY(420);
        btn1.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "btn1 clicked");
                // TODO: strongly recommend do your own implementation in the method below
                btn1Action();
                return true;
            }
    
        });
        this.addActorBottom(btn1);

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

        TextureRegion yaoguaiDown = skin.getRegion("yaoguai");
        TextureRegion yaoguaiUp   = skin.getRegion("yaoguai");
        ImageButton yaoguai = new ImageButton(new TextureRegionDrawable(yaoguaiDown), new TextureRegionDrawable(yaoguaiUp));
        yaoguai.setX(520);
        yaoguai.setY(10);
        yaoguai.addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "yaoguai clicked");
                // TODO: strongly recommend do your own implementation in the method below
                yaoguaiAction();
                return true;
            }
    
        });
        
        this.addActorBottom(yaoguai);

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

        NPC taishanNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100002/30100002.json", false, false, manager.get("data/30100002/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "taishan action");
                // TODO: strongly recommend do your own implementation in the method below
                taishanAction();
            }
        };
        taishanNpcActor.setX(450);
        taishanNpcActor.setY(240);
        this.addActorBackground(taishanNpcActor);

        NPC kezhanNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100003/30100003.json", false, false, manager.get("data/30100003/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "kezhan action");
                // TODO: strongly recommend do your own implementation in the method below
                kezhanAction();
            }
        };
        kezhanNpcActor.setX(750);
        kezhanNpcActor.setY(240);
        this.addActorBackground(kezhanNpcActor);

        NPC shanpaoNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100004/30100004.json", false, false, manager.get("data/30100004/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "shanpao action");
                // TODO: strongly recommend do your own implementation in the method below
                shanpaoAction();
            }
        };
        shanpaoNpcActor.setX(950);
        shanpaoNpcActor.setY(240);
        this.addActorBackground(shanpaoNpcActor);

        NPC biesanNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100005/30100005.json", false, false, manager.get("data/30100005/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "biesan action");
                // TODO: strongly recommend do your own implementation in the method below
                biesanAction();
            }
        };
        biesanNpcActor.setX(1100);
        biesanNpcActor.setY(240);
        this.addActorBackground(biesanNpcActor);

        NPC danaNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100006/30100006.json", false, false, manager.get("data/30100006/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "dana action");
                // TODO: strongly recommend do your own implementation in the method below
                danaAction();
            }
        };
        danaNpcActor.setX(1300);
        danaNpcActor.setY(240);
        this.addActorBackground(danaNpcActor);

        NPC dashengNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100008/30100008.json", false, false, manager.get("data/30100008/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "dasheng action");
                // TODO: strongly recommend do your own implementation in the method below
                dashengAction();
            }
        };
        dashengNpcActor.setX(1450);
        dashengNpcActor.setY(240);
        this.addActorBackground(dashengNpcActor);

        NPC zhuNpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("data/30100009/30100009.json", false, false, manager.get("data/30100009/pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "zhu action");
                // TODO: strongly recommend do your own implementation in the method below
                zhuAction();
            }
        };
        zhuNpcActor.setX(1800);
        zhuNpcActor.setY(180);
        this.addActorBackground(zhuNpcActor);


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
    public void yaoguaiAction(){
        Gdx.app.log(TAG, "yaoguaiAction called");
        return ;
    }
    public void caishenAction(){
        Gdx.app.log(TAG, "caishenAction called");
        return ;
    }
    public void cuihuaAction(){
        Gdx.app.log(TAG, "cuihuaAction called");
        return ;
    }
    public void taishanAction(){
        Gdx.app.log(TAG, "taishanAction called");
        return ;
    }
    public void kezhanAction(){
        Gdx.app.log(TAG, "kezhanAction called");
        return ;
    }
    public void shanpaoAction(){
        Gdx.app.log(TAG, "shanpaoAction called");
        return ;
    }
    public void biesanAction(){
        Gdx.app.log(TAG, "biesanAction called");
        return ;
    }
    public void danaAction(){
        Gdx.app.log(TAG, "danaAction called");
        return ;
    }
    public void dashengAction(){
        Gdx.app.log(TAG, "dashengAction called");
        return ;
    }
    public void zhuAction(){
        Gdx.app.log(TAG, "zhuAction called");
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
        manager.unload("data/30100001/pack");
        manager.unload("data/30100002/pack");
        manager.unload("data/30100003/pack");
        manager.unload("data/30100004/pack");
        manager.unload("data/30100005/pack");
        manager.unload("data/30100006/pack");
        manager.unload("data/30100008/pack");
        manager.unload("data/30100009/pack");

    }
}

