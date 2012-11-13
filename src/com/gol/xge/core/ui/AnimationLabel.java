package com.gol.xge.core.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class AnimationLabel extends Group {

    public static final int DIR_VER = 1;
    
    private int direction = DIR_VER;
    
    List<Label> labels = new ArrayList<Label>();
    
    public AnimationLabel(String text, LabelStyle style){
        this(text, style, null);
    }
    
    public AnimationLabel(String text, LabelStyle style, String name){
        super(name);
        init(text, style);
    }

    private void init(String text, LabelStyle style) {
        for(int index = 0; index < text.length(); index++){
            String word = text.substring(index, index + 1);
            Label l = new Label(word, style);
            l.y = index * - 20;
            this.addActor(l);
            labels.add(l);
        }
    }
    
    @Override
    public void action (Action action) {
//        super.action(action);
        int index = 0;
        for(Label l: labels){
//            if(action instanceof MoveTo){
                l.action(Sequence.$(Delay.$(0.1f*index), action.copy()));
                index++;
//            }
            
        }
    }
    
}
