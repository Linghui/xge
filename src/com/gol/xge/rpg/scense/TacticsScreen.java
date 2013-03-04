package com.gol.xge.rpg.scense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
    // all value below is usd to deal with view and leading role movement
    private boolean fixedCam = false;
    

    public TacticsScreen(Game game) {
        super(game);
    }
    
    public TacticsScreen(Game game, int width, int height){
        super(game, width, height);
    }
    
    public void initCam(){
        super.initCam();
        cam = (OrthographicCamera) this.getCamera();
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
    }
    

public class NPC extends AnimationActor {
        
        private NPCAction npcAction = null;

        public NPC(AnimationGroup animationGroup) {
            super(animationGroup);
            this.addListener(new InputListener(){
                
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
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
        
        public void action() {
            playAction(npcAction);
        }
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
        if(super.touchDragged(x, y, pointer)){
            return true;
        }
        if(fixedCam || cam == null){
            return false;
        }
        cam.unproject(curr.set(x, y, 0));
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            cam.unproject(delta.set(last.x, last.y, 0));
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, 0);
            
            if( this.getBackgroundWidth() > CoreScreen.width){
                if(cam.position.x < CoreScreen.width/2){
                    cam.position.x = CoreScreen.width/2;
                } else if ( cam.position.x > this.getBackgroundWidth() - CoreScreen.width/2){
                    cam.position.x = this.getBackgroundWidth() - CoreScreen.width/2;
                }
            }
            
            if( this.getBackgroundHeight() > CoreScreen.height ){
                if( cam.position.y < CoreScreen.height/2 ){
                    cam.position.y = CoreScreen.height/2;
                } else if( cam.position.y > this.getBackgroundHeight() - CoreScreen.height/2 ) {
                    cam.position.y = this.getBackgroundHeight() - CoreScreen.height/2;
                }
            }
        }
        last.set(x, y, 0);
        return true;
    }


    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return super.touchUp(x, y, pointer, button);
    }
    
}

