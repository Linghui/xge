$package

$import

public class $class_name extends RPGScreen $implements{
    
    $values
    
    AssetManager manager = new AssetManager(new InAndExternalFileHandleResolver());
    Skin skin = null;
    
    private boolean srcLoadDone = false;
    private Texture loadingBackgroundTexture;
    private NumericBar loadingBar = null;

    public $class_name(Game game){
        super(game);
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
            initButtons();
            initNpcs();
        }

    }
    
    private void init(){
    
        this.setBackGround(new TextureRegion(manager.get("$background", Texture.class)));

$init
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
//        backgroundTexture.dispose();

    }
}

