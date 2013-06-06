package com.gol.xge.rpg.scense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;

/*
 * 横版类游戏父类
 * 支持  角色设定，移动
 *      npc设定，响应
 */
public abstract class CoreScreen implements Screen, InputProcessor {
    private String TAG = "CoreScreen";
    
    protected static Game game;

    public static int width = 800;
    public static int height = 480;
    
    // background_xxx are used to deal with the scope of leading role can move to.
    private int background_width  = 0;
    private int background_height = 0;
    
    private   Stage rootStage;       // stage for things controlled by user, like player, menu, control button
                                     // which are relative static with screen
    private   Group bottom;

    private   Stage backgroundStage; // stage for background, like tiled map,  
    
    private boolean lockEvent = false;

    // all value below is usd to deal with view and leading role movement
    protected OrthographicCamera cam;
    
    private boolean isHided = false;
    
    public CoreScreen(Game game) {
        this(game, width, height);
    }
    
    public CoreScreen(Game game, int width, int height){
        CoreScreen.game = game;
        CoreScreen.width = width;
        CoreScreen.height = height;
        
        rootStage = new Stage(CoreScreen.width, CoreScreen.height, true);
        
        bottom = new Group();
        rootStage.addActor(bottom);
        
        backgroundStage = new Stage(CoreScreen.width, CoreScreen.height, true);
    }
    

    @Override
    public void show(){
        Gdx.input.setInputProcessor(this);
        rootStage.getRoot().setVisible(true);
        backgroundStage.getRoot().setVisible(true);
        isHided = false;
    }
    

    public void initCam(){

        cam = (OrthographicCamera) this.getCamera();
        cam.position.x = CoreScreen.width/2;
        cam.position.y = CoreScreen.height/2;
        
        rootStage.getCamera().position.x = CoreScreen.width/2;
        rootStage.getCamera().position.y = CoreScreen.height/2;
//        cam.viewportWidth = rootStage.getWidth() - rootStage.getGutterWidth()*2;
//        cam.viewportHeight = rootStage.getHeight() - rootStage.getGutterHeight() * 2;
    }
    
    public Vector2 toBackgroudStageCoordinates(int x, int y){
        Vector2 coords = new Vector2();
        coords.x = x;
        coords.y = y;
        return this.backgroundStage.screenToStageCoordinates(coords);
    }

    // for just one screen background picture, this is easy to use
    public void setBackGround(TextureRegion background){
        this.setBackGround(background, true);
    }
    
    public void setBackGround(TextureRegion background, boolean fitScreen){
        
        if(background == null){
            return ;
        }

        Image backgroundImg = new Image(background);
        if(fitScreen){
            backgroundImg.setWidth(rootStage.getWidth());
            backgroundImg.setHeight(rootStage.getHeight());
        }
        background_width = (int) backgroundImg.getWidth();
        background_height = (int) backgroundImg.getHeight();
        backgroundStage.addActor(backgroundImg);
    }
    
    public int getBackgroundWidth(){
        return background_width;
    }
    
    public int getBackgroundHeight(){
        return background_height;
    }
    
    public void setRootVisible(boolean visible) {
        this.rootStage.getRoot().setVisible(visible);
    }
    
    public Camera getCamera(){
        return backgroundStage.getCamera();
    }
    
    public void clickOnBackgroud(int x, int y){
        Gdx.app.log(TAG, "!!!!!x - " + x + " : y - " + y);
    }
    
    // ui like alert dialog added to this layer
    public void addActorTop(Actor actor){
        this.rootStage.addActor(actor);
        bottom.toBack();
    }
    
    // most of the main ui added to this layer
    public void addActorBottom(Actor actor){
        this.bottom.addActor(actor);
        bottom.toBack();
    }
    
    public void addActorBackground(Actor actor){
        this.backgroundStage.addActor(actor);
    }
    
    public void setBottomTouchable(Touchable touchable){
        bottom.setTouchable(touchable);
    }
    
    public void setBackgroundTouchable(Touchable touchable){
        backgroundStage.getRoot().setTouchable(touchable);
    }
    
    public void setScreenTouchable(Touchable touchable){
        rootStage.getRoot().setTouchable(touchable);
        backgroundStage.getRoot().setTouchable(touchable);
    }
    
    
    public Actor findActorBackground(int id){
        return this.findActorBackground(id + "");
    }
    
    public Actor findActorBackground(String name){
        return this.backgroundStage.getRoot().findActor(name);
    }
    
    public void removeActorBackground(Actor actor){
        this.backgroundStage.getRoot().removeActor(actor);
        if(actor instanceof Disposable ){
            ((Disposable) actor).dispose();
        }
    }
    
    public void clearActorBackground(){
        this.backgroundStage.clear();
    }

    public void removeActorBackground(int id){
        this.removeActorBackground(id + "");
    }
    
    public void removeActorBackground(String name){
//        Gdx.app.log(TAG, "removeActorBackground(String name) = " + name);
        this.removeActorBackground(this.backgroundStage.getRoot().findActor(name));
    }
    
    public Game getGame(){
        return game;
    }
    
    public float getRootWidth(){
        return rootStage.getWidth();
    }
    
    public float getRootHeight(){
        return rootStage.getHeight();
    }
    
    public void setCover(Drawable patch){
        float gutterWidth = rootStage.getGutterWidth();
        if( gutterWidth != 0){
            Image leftGutter = new Image(patch);
            leftGutter.setWidth(gutterWidth);
            leftGutter.setHeight(rootStage.getHeight());
            leftGutter.setX( - gutterWidth );
            rootStage.addActor(leftGutter);

            Image rightGutter = new Image(patch);
            rightGutter.setWidth(gutterWidth);
            rightGutter.setHeight(rootStage.getHeight());
            rightGutter.setX( rootStage.getWidth() - gutterWidth * 2  );
            rootStage.addActor(rightGutter);
        }
        float gutterHeight = rootStage.getGutterHeight();
        
        if( gutterHeight != 0 ){
            Image bottomGutter = new Image(patch);
            bottomGutter.setWidth(rootStage.getWidth());
            bottomGutter.setHeight(gutterHeight);
            bottomGutter.setY(-gutterHeight);
            rootStage.addActor(bottomGutter);

            Image topGutter = new Image(patch);
            topGutter.setWidth(rootStage.getWidth());
            topGutter.setHeight(gutterHeight);
            topGutter.setY( rootStage.getHeight() - gutterHeight * 2 );
            rootStage.addActor(topGutter);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL10.GL_BLEND);
        Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        backgroundStage.act(delta);
        rootStage.act(delta);
        backgroundStage.draw();
        rootStage.draw();
    }

    public void renderTableDebug(){
        Table.drawDebug(rootStage);
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void hide() {
        rootStage.getRoot().setVisible(false);
        backgroundStage.getRoot().setVisible(false);
        isHided = true;
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
//        Gdx.app.log(TAG, "dispose");
        rootStage.clear();
        rootStage.dispose();
        backgroundStage.clear();
        backgroundStage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        
        if(keycode == 4  // back button
                || keycode == 29){ // button a , for testing
            this.callBack();
            return false;
        } else if ( keycode == 3 ){
            this.callHome();
            return false;
        }

        if(!rootStage.keyDown(keycode)){
            return this.backgroundStage.keyDown(keycode);
        }
        
        return true;
    }

    protected void callHome() {
        
    }

    protected void callBack() {
        
    }

    @Override
    public boolean keyUp(int keycode) {

        if(!rootStage.keyUp(keycode)){
            return this.backgroundStage.keyUp(keycode);
        }
        
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        
        if(!rootStage.keyTyped(character)){
            return this.backgroundStage.keyTyped(character);
        }
        return true;
    }
    
    public void disableBackground(){
        this.backgroundStage.getRoot().setTouchable(Touchable.disabled);
    }
    
    public void enableBackground(){
        this.backgroundStage.getRoot().setTouchable(Touchable.enabled);
    }

    public void disableEvent(){
        this.lockEvent = true;
    }
    
    public void enableEvent(){
        this.lockEvent = false;
    }
    
    public boolean isEventLock(){
        return this.lockEvent;
    }
    
    public boolean isHided(){
        return this.isHided;
    }
    
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if( lockEvent){
            Gdx.app.log(TAG, "touchDown  lockEvent");
            return true;
        }
        
//        Gdx.app.log(TAG, "x - " + x + " : y - " + y);
        if(!rootStage.touchDown(x, y, pointer, button)){
            if(!this.backgroundStage.touchDown(x, y, pointer, button)){
                
                this.clickOnBackgroud(x,y);
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if( lockEvent){
            return true;
        }
        
        if(!rootStage.touchUp(x, y, pointer, button)){
            return backgroundStage.touchUp(x, y, pointer, button);
        }
        
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if( lockEvent){
            return true;
        }
        
        if( ! rootStage.touchDragged(x, y, pointer) ){
            backgroundStage.touchDragged(x, y, pointer);
            return false;
        }
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

}

