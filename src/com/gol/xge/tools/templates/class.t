$package

$import

public class $class_name extends RPGScreen $implements{
    
    $values
    
    AssetManager manager = new AssetManager(new InAndExternalFileHandleResolver());
    Skin skin = null;
    
    private boolean srcLoadDone = false;
//    private Texture backgroundTexture;

    public $class_name(Game game){
        super(game);
$source_loading
    }

	$class_body
    
    @Override
    public void render(float delta){
        super.render(delta);
        if(!manager.update()){
            
            System.out.println("progress " + manager.getProgress());
            
        } 
        if(manager.update() && this.srcLoadDone == false){
            this.srcLoadDone = true;
            init();
            initButtons();
        }

    }
    
    private void init(){
    
        this.setBackGround(new TextureRegion(manager.get("$background", Texture.class)));
        skin = manager.get("$skin", Skin.class);

$init
    }
    
    private void initButtons(){

$init_buttons

    }
    
    @Override
    public void dispose() {
$source_unloading
//        backgroundTexture.dispose();

    }
}
