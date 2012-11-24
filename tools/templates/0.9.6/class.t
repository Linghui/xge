$package

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.gol.xge.rpg.Common;
import com.gol.xge.rpg.InAndExternalFileHandleResolver;
import com.gol.xge.rpg.scense.NPCAction;
import com.gol.xge.rpg.scense.RPGScreen;
import com.gol.xge.rpg.ui.AnimationActor;
import com.gol.xge.rpg.ui.AnimationGroup;
import com.gol.xge.rpg.ui.NumericBar;

public class $class_name extends $parent $implements{
    
    $values
    
    AssetManager manager = new AssetManager(new InAndExternalFileHandleResolver());
    Skin skin = null;
    
    private boolean srcLoadDone = false;
    private Texture loadingBackgroundTexture;
    private NumericBar loadingBar = null;

    public $class_name(Game game){
        super(game, $screen_width, $screen_height);
    }
    
    @Override
    public void show(){
        super.show();
        loadingBackgroundTexture = new Texture(Gdx.files.internal("$loading_backgroud"));
        this.setBackGround(new TextureRegion(loadingBackgroundTexture));
$source_loading
    }

	$class_body
    
    @Override
    public void render(float delta){
        super.render(delta);
        if(!manager.update()){
            
            System.out.println("progress " + manager.getProgress());
            if(manager.isLoaded("data/uiskin.json") && loadingBar == null){
                skin = manager.get("$skin", Skin.class);
                
                loadingBar = new NumericBar(skin.getPatch("$bar_top"), skin.getPatch("$bar_bottom"), $bar_width, $bar_height, 0, $bar_source_count, skin.getStyle(LabelStyle.class));
                
                loadingBar.x = $bar_x;
                loadingBar.y = $bar_y;
                this.addActorBackground(loadingBar);
                
            }
            
            if(loadingBar != null){
                int status = (int) (manager.getProgress()*10);
                System.out.println("status " + status); 
                loadingBar.setStatusNum(status);
                
            }
        } 
        if(manager.update() && this.srcLoadDone == false){
            loadingBackgroundTexture.dispose();
            this.srcLoadDone = true;
            init();
            initLabels();
            initButtons();
            initNpcs();
        }

    }
    
    private void init(){
    
        TextureRegion backRegion = $background;
        this.setBackGround(backRegion, false);
        this.setLimitY($limit_y);
        
        AnimationActor playerAnimation = new AnimationActor( new AnimationGroup(Common.readAnimationResource("$leading_role_json", false, false, manager.get("$leading_role_pack", TextureAtlas.class))));
        playerAnimation.y = 480/8;
        Group leadingRole = new Group();
        leadingRole.addActor(playerAnimation);
        leadingRole.width = playerAnimation.width;
        leadingRole.height = playerAnimation.height;


        initCam();
        initLeadingRole(leadingRole);

$init
    }

    private void initLabels(){

$init_labels

    }
    private void initButtons(){

$init_buttons

    }
    
    
    private void initNpcs(){

$init_npcs

    }

    @Override
    public void dispose() {
$source_unloading
        manager.dispose();
    }
}

