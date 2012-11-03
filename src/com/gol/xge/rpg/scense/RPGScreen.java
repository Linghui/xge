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
public abstract class RPGScreen implements Screen, InputProcessor {
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
    private OrthographicCamera cam;
    private float camCurrentX = 0;
    private float camCurrentY = 0;
    private float camMoveToX = 0;
    private float camMoveToY = 0;

    private float movedOnX = 0f;
    private float movedOnY = 0f;
    
    private boolean cam_x_moving_stop = false;
    private boolean cam_y_moving_stop = false;
    
    private int on_x_edge = 0; // left : -1, middle : 0, right : 1
    private int on_y_edge = 0; // down : -1, middle : 0, up    : 1
    private boolean isTaskMovement = false;

    private float DPS = 200f;  // distance per second, relate with moving speed of leading role
    private float distance = 0f;
    private float distanceOnX = 0f;
    private float distanceOnY = 0f;
    private float timeCost = 0f;
    private float DPS_ONX = 0f;
    private float DPS_ONY = 0f;
    
    
    private Actor player = null;
    
    public RPGScreen(Game game) {
        this(game, width, height);
    }
    
    public RPGScreen(Game game, int width, int height){
        this.game = game;
        RPGScreen.width = width;
        RPGScreen.height = height;
    }
    

    @Override
    public void show(){
        Gdx.input.setInputProcessor(this);

        // topGroup display priority is higher than bottomGroup.
        rootStage = new Stage(RPGScreen.width, RPGScreen.height, false);
        bottomGroup = new Group();
        rootStage.addActor(bottomGroup);
        
        topGroup = new Group();
        rootStage.addActor(topGroup);
        
        backgroundStage = new Stage(RPGScreen.width, RPGScreen.height, false);
    }
    
    public void initLeadingRole(Actor actor){
        cam = (OrthographicCamera) this.getCamera();

        camCurrentX = cam.position.x;
        camCurrentY = cam.position.y;
        camMoveToX  = cam.position.x;
        camMoveToY  = cam.position.y;

        cam_x_moving_stop = false;
        cam_y_moving_stop = false;
        
        this.player = actor;
        
        // display player in the middle of screen, 
        // but requirement is not always like this,
        // so need to figure out how to put it at other place, but not current priority
        player.x = (RPGScreen.width - player.width)/2;
        player.y = (RPGScreen.height - player.height)/2;
        
        addActorBottom(player);
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
    
    
    public void clickOnBackgroud(int x, int y){
        Gdx.app.log(TAG, "!!!!!x - " + x + " : y - " + y);
        if(!isTaskMovement){

            int mx = x;
            int my = y;
            
            // 处理当屏幕上半部分有不可行走部分的情况
            if(my < limitY){
            
                    float bh = (RPGScreen.height - my) - player.y;
                    float sh = (RPGScreen.height - limitY) - player.y;
                    float bw = mx - player.x;
                    
                    float sw = bw * (sh/bh);
                    
                    mx = (int) (player.x + sw);
                    my = limitY;
                    Gdx.app.log(TAG, "!!!!!mx - " + mx + " : my - " + my);
                
            }
            
            Vector2 coords = this.toBackgroudStageCoordinates(mx, my);
            moveTo(coords.x, coords.y);
        }
        
    }
    
    public void moveTo(float x, float y){

//      Gdx.app.log(TAG, "x - " + x + " : y - " + y);
      
      movedOnX = 0f;
      movedOnY = 0f;
      
      this.camMoveToX = x;
      this.camMoveToY = y;
      distanceOnX = this.camMoveToX - this.camCurrentX;
      distanceOnY = this.camMoveToY - this.camCurrentY;
//      if(distanceOnX == 0f){
//          distanceOnX = 0.001f;
//      }
//      if(distanceOnY == 0f){
//          distanceOnY = 0.001f;
//      }
      distance = (float)Math.sqrt(distanceOnX*distanceOnX + distanceOnY*distanceOnY);
//      Gdx.app.log(TAG, "distanceOnX - " + distanceOnX);
//      Gdx.app.log(TAG, "distanceOnY - " + distanceOnY);
//      Gdx.app.log(TAG, "distance - " + distance);
      timeCost = distance/DPS;
      DPS_ONX = distanceOnX/timeCost;
      DPS_ONY = distanceOnY/timeCost;
//      Gdx.app.log(TAG, "timeCost - " + timeCost);
      
      // set up player action
      float tan = Math.abs(distanceOnX/distanceOnY);
//      Gdx.app.log(TAG, "tan - " + tan);
      
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
        

        if(this.player != null){
         // 下面这部分是针对 角色在地图中移动的处理
            // 分两部分，如果角色在边缘区域 则移动角色本身
            //         如果角色在中央 则移动camera
            // x,y 分别处理
            if(this.camCurrentX != this.camMoveToX && this.camCurrentY != this.camMoveToY){
                
                float disOnX = DPS_ONX*delta;   // 
                this.camCurrentX += disOnX;     // 
                movedOnX += disOnX;             // total movement on x
                
                // not in x edge, so move camera on X
                if(!cam_x_moving_stop){
                    
                    // moved into edge, stop moving camera
                    if( this.camCurrentX < RPGScreen.width/2 
                            || this.camCurrentX > (background_width - RPGScreen.width/2)){
                        cam_x_moving_stop = true;
                        
                        // to see run into left edge or right edge
                        if(this.camCurrentX < RPGScreen.width/2 ){
                            this.camCurrentX = RPGScreen.width/2 ;
                            on_x_edge = -1; // left edge
                        } else {
                            this.camCurrentX = background_width - RPGScreen.width/2;
                            on_x_edge = 1;  // right edge
                        }
                    } else {
                        // moving camera
                        cam.position.x = this.camCurrentX;
                    }
                    
                } else {
                    // in edge , so move player
                    player.x += disOnX;
                    if( on_x_edge < 0){
                        // in edge left and move to right
                        if( disOnX > 0 && player.x > RPGScreen.width/2 - player.width/2){
                            player.x = RPGScreen.width/2 - player.width/2;
                            on_x_edge = 0;
                            cam_x_moving_stop = false;
                        }
                    } else if ( on_x_edge > 0){
                        // in edge right and move to left
                        if( disOnX < 0 && player.x < RPGScreen.width/2 - player.width/2){
                            player.x = RPGScreen.width/2 - player.width/2;
                            on_x_edge = 0;
                            cam_x_moving_stop = false;
                        }
                    }
                }
                
                if(Math.abs(movedOnX) > Math.abs(distanceOnX)){
                    DPS_ONX = 0f;
                    movedOnX = 0f;
                    this.camCurrentX = this.camMoveToX;
                }
                
                // start deal with Y
                float disOnY = DPS_ONY*delta;
                this.camCurrentY += disOnY;
                movedOnY += disOnY;
                
                // not in y edge, so move camera on Y
                // the same as we deal with X
                if(!cam_y_moving_stop){
                    
                    if(this.camCurrentY < RPGScreen.height/2 
                            || this.camCurrentY > (background_height - RPGScreen.height/2)){
                        cam_y_moving_stop = true;
                        if(this.camCurrentY < RPGScreen.height/2 ){
                            this.camCurrentY = RPGScreen.height/2 ;
                            on_y_edge = -1;
                        } else {
                            this.camCurrentY = (background_height - RPGScreen.height/2);
                            on_y_edge = 1;
                        }
                    } else {
                        cam.position.y = this.camCurrentY;    
                    }
                    
                } else {
                    player.y += disOnY;
                    if(on_y_edge < 0){
                        if( disOnY > 0 && player.y > RPGScreen.height/2 - player.height/2){
                            player.y = RPGScreen.height/2 - player.height/2;
                            on_y_edge = 0;
                            cam_y_moving_stop = false;
                        }
                    } else if(on_y_edge > 0){
                        if( disOnY < 0 && player.y < RPGScreen.height/2 - player.height/2){
                            player.y = RPGScreen.height/2 - player.height/2;
                            on_y_edge = 0;
                            cam_y_moving_stop = false;
                        }
                    }
                }

                if(Math.abs(movedOnY) > Math.abs(distanceOnY)){
                    DPS_ONY = 0f;
                    movedOnY = 0f;
                    this.camCurrentY = this.camMoveToY;
                    moveDone();
                }
                
                cam.update();
//                Gdx.app.log(TAG, "moving");
                
            } else {
                moveDone();
            }
            
        }
    }

    
    private void moveDone() {
        if(target != null){
            target.action();
            target = null;
        }
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
                
                this.clickOnBackgroud(x,y);
            }
        }
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

    
    public void setMoveTarget(MoveTarget target){
        this.target = target;
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
            setMoveTarget(this);
            return false;
        }
        
        @Override
        public void action() {
//            if(npcAction != null){
//                playAction(npcAction);
//            }
        }
    }
    
    public interface MoveTarget {
        public void action ();
    }

    public void playAction(NPCAction npcAction){
        
    }

}

