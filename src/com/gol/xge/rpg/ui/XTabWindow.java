package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class XTabWindow extends XWindow {

    private int padding = 10;
    
    private XTab tab = null;
    
    public XTabWindow(Skin skin, float width, float height
            , NinePatch background, Array<String> tabNames) {
        super(skin, width, height);
        
        tab = new XTab(skin, background , width - padding * 2, height - padding * 2, tabNames);
        tab.setX(padding);
        tab.setY(padding);
        
        this.addActor(tab);
        
    }
    
    public XTab getTab(){
        return tab;
    }
    
}
