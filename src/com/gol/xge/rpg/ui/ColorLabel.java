package com.gol.xge.rpg.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/*
 * this is for showing coloring label
 * rule:
 *  使用'|'引用需要改变颜色的内容，在'|'引用内侧指定颜色,比如 r g b 
 * 例:
 *  使用'|'引用需要改变｜r颜色r|的|b内容b|
 *      这里 颜色两个字会变成红色, 内容两个字会变成蓝色
 *      
 * 注意:
 *  对英文支持 会有很小的几率误差
 *  比如:
 *     b aaaa b|rcccr|  
 *     第一部分会变成蓝色， 
 *  解决方法，在可能会产生误差的地方 添加空格，或者改变字母，让本段首尾字母不统一为一个颜色标示就可以了
 *  不会太影响使用
 */
public class ColorLabel extends Group {


    // S for short
    // L for long, its LabelStyle name
    public final static String defaultS  = "default";
    public final static String defaultL  = "white";

    public final static String whiteS = "w";
    public final static String whiteL = "white";
    
    public final static String redS = "r";
    public final static String redL = "red";

    public final static String blueS = "b";
    public final static String blueL = "blue";

    public final static String greenS = "g";
    public final static String greenL = "green";

    public final static String purpleS = "p";
    public final static String purpleL = "purple";
    
    public final static String splitS = "|";
    
    private HashMap<String, String> colorMap = new HashMap<String, String>();
    private Skin skin;
    private String str;
    
    private int labelAlign = Align.left;
    private boolean wrap;
    private float totalLength;
    
    public ColorLabel(String str, Skin skin){
        this.skin = skin;
        

        colorMap.put(defaultS, defaultL);
        colorMap.put(redS, redL);
        colorMap.put(blueS, blueL);
        colorMap.put(greenS, greenL);
        colorMap.put(purpleS, purpleL);
        
        setText(str);
    }

    private void setText(String str) {
        this.str = str;
        totalLength = 0;
        Gdx.app.log("ColorLabel", "one " + str);
        String[] pieces = str.split("\\" + splitS);
        for(String one: pieces){
            Gdx.app.log("ColorLabel", "one " + one);
            String labelStyleName;
            String realWords;
            
            String colorS = one.substring(0, 1); // probably
            Gdx.app.log("ColorLabel", "one.substring(0, 1) " + one.substring(0, 1));
            Gdx.app.log("ColorLabel", "one.substring(0, 1) " + one.substring(one.length() - 1));
            
            // if it's given a color, at least it has 3 letters, like |cxc| without |
            if( one.length() >= 3
                    &&
                    colorS
                    .equals( one.substring(one.length() - 1) )
                    ){
                if( colorMap.containsKey( colorS ) ){
                    labelStyleName = colorMap.get(colorS);
                    realWords = one.substring(1,  one.length() - 1 );
                } else {
                    labelStyleName = colorMap.get(defaultS);
                    realWords = one;
                }
            } else {
                labelStyleName = colorMap.get(defaultS);
                realWords = one;
            }
            Gdx.app.log("ColorLabel", "labelStyleName " + labelStyleName);
            Gdx.app.log("ColorLabel", "realWords " + realWords);
            Label chip = new Label(realWords, skin.get(labelStyleName, LabelStyle.class));
            chip.setX(totalLength);
            this.addActor(chip);
            totalLength += chip.getWidth();
        }
        this.setWidth(totalLength);
    }

    public void setAlignment(int labelAlign) {
        this.labelAlign = labelAlign;   
        computeSize();
    }
    
    private void computeSize(){
        
        // warp 没实现呢,需要了再说
        if( wrap && this.totalLength > this.getWidth() ){
            
        } else {
            float xC = 0;
            if( this.labelAlign == Align.center 
                    && this.totalLength < this.getWidth() ){
                xC = ( this.getWidth() - this.totalLength ) /2;
            }
            
            for( Actor actor : this.getChildren() ){
                actor.setX( actor.getX() + xC);
            }
        }
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;        
    }
    
    @Override
    public void setWidth(float width){
        super.setWidth(width);
        computeSize();
    }
    
    // for putting you own coloring rule into this
    // notice: 
    //  String S must be only one letter, you can replace the default ones, like r g b
    //  String L must be a LabelStyle name in skin given
    public void addColorS(String S, String L){
        this.colorMap.put(S, L);
    }
}
