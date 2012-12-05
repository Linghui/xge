package com.gol.xge.rpg.scense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.gol.xge.rpg.scense.RPGScreen.MoveTarget;
import com.gol.xge.rpg.scense.RPGScreen.NPC;
import com.gol.xge.rpg.ui.AnimationActor;
import com.gol.xge.rpg.ui.AnimationGroup;

/*
 * 横版类游戏父类
 * 支持  角色设定，移动
 *      npc设定，响应
 */
public abstract class TacticsScreen extends CoreScreen implements InputProcessor {
    private String TAG = "TacticsScreen";
    
    // call for target.action() when leading role movement done
    MoveTarget target = null;
    
    // all value below is usd to deal with view and leading role movement
    private boolean fixedCam = false;
    private float camCurrentX = 0;
    private float camCurrentY = 0;
    private float camMoveToX = 0;
    private float camMoveToY = 0;
    private float movedOnX = 0f;
    private float movedOnY = 0f;
    private float distanceOnX = 0f;
    private float distanceOnY = 0f;
    private float DPS = 1000;
    private float DPS_ONX = 0f;
    private float DPS_ONY = 0f;
    

    public TacticsScreen(Game game) {
        super(game);
    }
    
    public TacticsScreen(Game game, int width, int height){
        super(game, width, height);
    }
    
    public void initCam(){
        super.initCam();
        cam = (OrthographicCamera) this.getCamera();
        camCurrentX = cam.position.x;
        camCurrentY = cam.position.y;
        camMoveToX  = cam.position.x;
        camMoveToY  = cam.position.y;
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        
        // shaking camera
        if(this.cmaShakingLeftSeconds != 0f){
            shakingCount += delta;
            camShakingPerCount += delta;
            if(shakingCount > cmaShakingLeftSeconds){
                cmaShakingLeftSeconds = 0f;
                camShakingPerCount = 0f;
                shakingCount = 0f;
                cam.position.x = camXbeforeShaking;
                cam.position.y = camYbeforeShaking;
                return;
            }
            if(camShakingPerCount < this.camShakingInterval){
                return;
            }
            camShakingPerCount = 0f;
            this.camShakingRange*=-1;
//            Gdx.app.log(TAG, "this.camShakingRange " + this.camShakingRange);
            
            float camTx = this.camShakingRange + this.camXbeforeShaking;
            float camTy = this.camShakingRange + this.camYbeforeShaking;
//            Gdx.app.log(TAG, "camTx " + camTx + "camTy " + camTy);
            
            cam.position.x = camTx;
            cam.position.y = camTy;
            return;
        }
        
//
//
//        if( camCurrentX != this.camMoveToX){
//
//            float disOnX = DPS_ONX*delta;
//            cam.position.x -= disOnX;
//            movedOnX += disOnX;
//
//            if(Math.abs(movedOnX) > Math.abs(distanceOnX)){
//                DPS_ONX = 0f;
//                movedOnX = 0f;
//                this.cam.position.x = this.camMoveToX;
//            }
//        }
//        
//        if( camCurrentY != this.camMoveToY){
//
//            float disOnY = DPS_ONY*delta;
//            cam.position.y -= disOnY;
//            movedOnY += disOnY;
//            
//            if(Math.abs(movedOnY) > Math.abs(distanceOnY)){
//                DPS_ONY = 0f;
//                movedOnY = 0f;
//                this.cam.position.y = this.camMoveToY;
//            }
//        }
        
    }
    
    public void setCamFixed(boolean fixed){
        this.fixedCam = fixed;
    }

    @Override
    public void clickOnBackgroud(int x, int y){
        if(this.fixedCam){
            return;
        }
        this.moveCamTo(x, y);
    }
    
    
    public void moveCamTo(int x, int y){

        if(cam == null){
            return;
        }
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
            this.addListener(new EventListener(){

                @Override
                public boolean handle(Event event) {
                    action();
                    return true;
                }
                
            });
        }
        
        public void setAction(String actionStr){
            if( actionStr != null){
                npcAction   =   new NPCAction(actionStr);
            }
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
    
    private float cmaShakingLeftSeconds = 0f;
    private float shakingCount = 0f;
    private float camXbeforeShaking = 0f;
    private float camYbeforeShaking = 0f;
    private float camShakingRange = 2f;
    private float camShakingPerCount = 0f;
    private float camShakingInterval = 0.06f;
    
    public void shakeCam(float seconds){
        cmaShakingLeftSeconds = seconds;
        camXbeforeShaking = cam.position.x;
        camYbeforeShaking = cam.position.y;
    }

    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();
    
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        cam.unproject(curr.set(x, y, 0));
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            cam.unproject(delta.set(last.x, last.y, 0));
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, 0);
        }
        last.set(x, y, 0);
        return false;
    }


    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return super.touchUp(x, y, pointer, button);
    }
    
}

