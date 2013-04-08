package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class XGEScissor extends Group {
    private final Rectangle scissorBounds = new Rectangle();
    
    public XGEScissor(){
        scissorBounds.set(0, 0, 200, 200);
        this.setWidth(200);
        this.setHeight(200);
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        // Enable scissors for widget area and draw the widget.
        if (ScissorStack.pushScissors(scissorBounds)) {
            super.draw(batch, parentAlpha);
            ScissorStack.popScissors();
        }
    }
    
    @Override
    public void setWidth (float width) {
        super.setWidth(width);
        scissorBounds.setWidth(width);
    }
    
    @Override
    public void setHeight (float height) {
        super.setHeight(height);
        scissorBounds.setHeight(height);
    }
    
    @Override
    public void setX (float x) {
        super.setX(x);
        scissorBounds.setX(x);
    }
    
    @Override
    public void setY (float y) {
        super.setY(y);
        scissorBounds.setY(y);
    }
    
}
