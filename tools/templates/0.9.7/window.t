package com.gol.game;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bearcat.obj.proto.XMsgObj.XMsg;
import com.bearcat.obj.proto.interfaces.XMsgUpdate;
import com.gol.xge.rpg.ui.XWindow;

public class $window_class extends XWindow implements XMsgUpdate{

    private final static String TITLE = "$title";

    public $window_class(Skin skin, float width, float height) {
        this(skin.get("default", XWindowStyle.class), width, height);
    }

    public $window_class(XWindowStyle windowStyle, float width, float height) {
        super(windowStyle, width, height);
        init();
        initLabels();
        initButtons();
        initTextFields();
        selfInit();
    }

    private void init() {
$init
    }
    
    private void initLabels(){

$init_labels

    }
    
    private void initButtons(){

$init_buttons

    }
    
    private void initTextFields(){

$init_textfields

    }

    private void selfInit() {
        // TODO: do your own special init here
    }
    

    @Override
    public void update(XMsg msg) {
        // TODO Auto-generated method stub
        
    }
    
}
