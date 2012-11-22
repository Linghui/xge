package com.gol.xge.core.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.Action;

public class AnimationLabel extends Group {
    private String TAG  = "AnimationLabel";

    public static final int DIR_VER = 1;
    
    private int direction = DIR_VER;
    private float delay = 0.3f;
    
    List<Label> labels = new ArrayList<Label>();
    
    public AnimationLabel(String text, LabelStyle style){
        this(text, style, null);
    }
    
    public AnimationLabel(String text, LabelStyle style, String name){
        this.setName(name);
        init(text, style);
    }

    private void init(String text, LabelStyle style) {
        for(int index = 0; index < text.length(); index++){
            String word = text.substring(index, index + 1);
            Label l = new Label(word, style);
            l.setY(index * - 20);
            this.addActor(l);
            labels.add(l);
        }
    }
    
    /*
     * 注意：这里的MoveTo指定的坐标是文字当前位置的相对坐标，不是全局坐标， 要注意！ 
     */
    
    public void MoveTo(float x, float y, float duration){
        MoveTo(x, y, duration, null);
    }
    
    public void MoveTo(float x, float y, float duration, Action doneAction){
        for(int index = 0; index < labels.size(); index++){
            Label l = labels.get(index);
            float moveToX = x + l.getX();
            float moveToY = y + l.getY();
            SequenceAction sqaction = sequence(delay(delay * index), moveTo(moveToX, moveToY, duration));
            if(index == labels.size() - 1 
                    && doneAction != null){
                Gdx.app.log(TAG, "set up success");
                sqaction.addAction(doneAction);
            }
            l.addAction(sqaction);
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.badlogic.gdx.scenes.scene2d.Actor#action(com.badlogic.gdx.scenes.scene2d.Action)
     * 不能完全包装出想要的结果，各个action中操作的属性都不同，并且action的最终状态值不可读，对于透明度调整的action还可以
     * 但是坐标移动就比较困难 比如 MoveTo action, 从参数中不可得知到底要移动到哪里，所以各个小label的偏移后的最终值也不可计算,
     * 只能重写一个不同的方法 实现坐标类的移动
     * 注意：坐标移动相关：比如MoveTo指定的坐标是文字当前位置的相对坐标，不是全局坐标， 要注意！
     */
    @Override
    public void addAction (Action action) {
//        super.action(action);
        int index = 0;
        super.addAction(sequence(delay(delay*index), action));
        for(Label l: labels){
////            if(action instanceof MoveToAction.class){
//                l.addAction(sequence(delay(delay*index), action));
//                l.action(Sequence.$(Delay.$(delay * index), action.copy()));
//                index++;
////            }
//            
        }
    }
    
    public void setDelay(float delay){
        this.delay = delay;
    }

}
