package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class XGETextInputListener extends InputListener {

    private TextField textField;
    private String title;
    
    public XGETextInputListener(TextField textField, String title){
        this.textField = textField;
        this.title = title;
    }
    
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.input.getTextInput(new TextInputListener(){

            @Override
            public void input(String text) {
                textField.setText(text);
            }

            @Override
            public void canceled() {
                // TODO Auto-generated method stub
                
            }
            
        }, title + "(点击输入)", "");
        return true;
    }
}
