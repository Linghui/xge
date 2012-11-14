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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.gol.xge.rpg.ui.AnimationActor;
import com.gol.xge.rpg.ui.AnimationGroup;

/*
 * 横版类游戏父类
 * 支持  角色设定，移动
 *      npc设定，响应
 */
public abstract class TacticsScreen implements Screen, InputProcessor {
    private String TAG = "RPGScreen";
    
    protected static Game game;

    public static int width = 800;
    public static int height = 480;
    
    // background_xxx are used to deal with the scope of leading role can move to.
    private int background_width  = 0;
    private int background_height = 0;
    
    private   Stage rootStage;       // stage for things controlled by user, like player, menu, control button
                                     // which are relative static with screen
    private   Group bottomGroup;
    private   Group topGroup;

    private   Stage backgroundStage; // stage for background, like tiled map,  
    
    private int limitY = 0;

    // call for target.action() when leading role movement done
    MoveTarget target = null;
    
    // all value below is usd to deal with view and leading role movement
    protected OrthographicCamera cam;
    private float camCurrentX = 0;
    private float camCurrentY = 0;
    private float camMoveToX = 0;
    private float camMoveToY = 0;
    private float movedOnX = 0f;
    private float movedOnY = 0f;
    private float distanceOnX = 0f;
    private float distanceOnY = 0f;
    private float DPS = 1000;

    public TacticsScreen(Game game) {
        this(game, width, height);
    }
    
    public TacticsScreen(Game game, int width, int height){
        this.game = game;
        TacticsScreen.width = width;
        TacticsScreen.height = height;
    }
    

    @Override
    public void show(){
        Gdx.input.setInputProcessor(this);

        // topGroup display priority is higher than bottomGroup.
        rootStage = new Stage(TacticsScreen.width, TacticsScreen.height, false);
        bottomGroup = new Group();
        rootStage.addActor(bottomGroup);
        
        topGroup = new Group();
        rootStage.addActor(topGroup);
        
        backgroundStage = new Stage(TacticsScreen.width, TacticsScreen.height, false);
    }
    
    public void initCam(){

        cam = (OrthographicCamera) this.getCamera();
        cam.position.x = this.background_width/2;
        cam.position.y = this.background_height/2;
        camCurrentX = cam.position.x;
        camCurrentY = cam.position.y;
        camMoveToX  = cam.position.x;
        camMoveToY  = cam.position.y;
    }
    
    public int getBackgroundWidth(){
        return background_width;
    }
    
    public int getBackgroundHeight(){
        return background_height;
    }
    
    /*
     * need to add some function to deal with more than one stage.
     * like 
     *  addStage(name, stage)
     *  stage topStage();
     *  removeStage(name)
     *  
     */
    public Vector2 toRootStageCoordinates(int x, int y){
        Vector2 out = new Vector2();
        Vector2 coords = new Vector2();
        rootStage.toStageCoordinates(x, y, coords);
        Group.toChildCoordinates(topGroup, coords.x, coords.y, out);
        return out;
    }
    
    public Vector2 toBackgroudStageCoordinates(int x, int y){
        Vector2 coords = new Vector2();
        this.backgroundStage.toStageCoordinates(x, y, coords);
        return coords;
    }
    
    public void setBackgroudStage(Stage stage){
        this.backgroundStage = stage;
    }
    
    // for just one screen background picture, this is easy to use
    public void setBackGround(TextureRegion background){
        this.setBackGround(background, true);
    }
    
    public void setBackGround(TextureRegion background, boolean fitScreen){

        Image backgroundImg = new Image(background);
        if(fitScreen){
            backgroundImg.width = rootStage.width();
            backgroundImg.height = rootStage.height();
        }
        background_width = (int) backgroundImg.width;
        background_height = (int) backgroundImg.height;
        backgroundStage.addActor(backgroundImg);
    }
    
    public Camera getCamera(){
        return backgroundStage.getCamera();
    }

    public void setLimitY(int y){
        this.limitY = y;
    }
    
    public void addActorTop(Actor actor){
        this.topGroup.addActor(actor);
    }
    
    public void addActorBottom(Actor actor){
        this.bottomGroup.addActor(actor);
    }
    
    public void addActorBackground(Actor actor){
        this.backgroundStage.addActor(actor);
    }
    
    public Actor findActorBackground(int id){
        return this.findActorBackground(id + "");
    }
    
    public Actor findActorBackground(String name){
        return this.backgroundStage.findActor(name);
    }
    
    public void removeActorBackground(Actor actor){
        this.backgroundStage.removeActor(actor);
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
        this.removeActorBackground(this.backgroundStage.findActor(name));
    }
    
    public Game getGame(){
        return game;
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
        


        if( camCurrentX != this.camMoveToX){

            float disOnX = DPS_ONX*delta;
            cam.position.x -= disOnX;
            movedOnX += disOnX;

            if(Math.abs(movedOnX) > Math.abs(distanceOnX)){
                DPS_ONX = 0f;
                movedOnX = 0f;
                this.cam.position.x = this.camMoveToX;
            }
        }
        
        if( camCurrentY != this.camMoveToY){

            float disOnY = DPS_ONY*delta;
            cam.position.y -= disOnY;
            movedOnY += disOnY;
            
            if(Math.abs(movedOnY) > Math.abs(distanceOnY)){
                DPS_ONY = 0f;
                movedOnY = 0f;
                this.cam.position.y = this.camMoveToY;
            }
        }
        
    }

    public void setRootVisible(boolean visible) {
        this.rootStage.getRoot().visible = visible;
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void hide() {
        
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
        rootStage.keyDown(keycode);
        Gdx.app.log(TAG, "keyDown !!!! " + keycode);
        if(keycode == 4  // back button
                || keycode == 29){ // button a , for testing
//            this.closeGame();
            return false;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        rootStage.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        rootStage.keyTyped(character);
        return false;
    }

    
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
//        Gdx.app.log(TAG, "x - " + x + " : y - " + y);
        if(!rootStage.touchDown(x, y, pointer, button)){
            if(!this.backgroundStage.touchDown(x, y, pointer, button)){
                
            }
        }

        
        this.moveCamTo(x, y);
//        this.moveCamTo(x, y);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        rootStage.touchUp(x, y, pointer, button);
        backgroundStage.touchUp(x, y, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Gdx.app.log(TAG, "touchDragged x - " + x + " : y - " + y);
        return rootStage.touchDragged(x, y, pointer);
    }

    @Override
    public boolean touchMoved(int x, int y) {
        rootStage.touchMoved(x, y);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        
        rootStage.scrolled(amount);
        return false;
    }
    
    float DPS_ONX = 0f;
    float DPS_ONY = 0f;
    
    public void moveCamTo(int x, int y){

        Vector2 coords = this.toBackgroudStageCoordinates(x, y);
        
        movedOnX = 0f;
        movedOnY = 0f;
        
        this.camMoveToX = coords.x;
        this.camMoveToY = coords.y;
        
        distanceOnX = cam.position.x - this.camMoveToX;
        distanceOnY = cam.position.y - this.camMoveToY;
        
        float totalDistance = (float)Math.sqrt(distanceOnX*distanceOnX + distanceOnY*distanceOnY);
//      Gdx.app.log(TAG, "distanceOnX - " + distanceOnX);
//      Gdx.app.log(TAG, "distanceOnY - " + distanceOnY);
//      Gdx.app.log(TAG, "distance - " + distance);
      float timeCost = totalDistance/DPS;
      DPS_ONX = distanceOnX/timeCost;
      DPS_ONY = distanceOnY/timeCost;
    }

    public class NPC extends AnimationActor implements MoveTarget{
        
        private NPCAction npcAction = null;

        public NPC(AnimationGroup animationGroup) {
            super(animationGroup);
        }
        
        public void setAction(String actionStr){
            if( actionStr != null){
                npcAction   =   new NPCAction(actionStr);
            }
        }
        
        @Override
        public boolean touchDown (float x, float y, int pointer) {
            action();
            return false;
        }
        
        @Override
        public void action() {
            if(npcAction != null){
                playAction(npcAction);
            }
        }
    }
    
    public interface MoveTarget {
        public void action ();
    }

    public void playAction(NPCAction npcAction){
        
    }

}

