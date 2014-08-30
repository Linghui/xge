package com.gol.xge.rpg.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class XGEScissor extends Group {
    private final Rectangle scissorBounds = new Rectangle();
    private final Rectangle widgetAreaBounds = new Rectangle();
    
    public XGEScissor(){
        scissorBounds.set(0, 0, 200, 200);
        this.setWidth(200);
        this.setHeight(200);
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        if( ! this.isVisible() ){
            return;
        }
        applyTransform(batch, computeTransform());
        // Enable scissors for widget area and draw the widget.
        this.getStage().calculateScissors(widgetAreaBounds, scissorBounds);
//        ScissorStack.calculateScissors(getStage().getCamera(), parentAlpha, parentAlpha, parentAlpha, parentAlpha, batch.getTransformMatrix(), widgetAreaBounds, scissorBounds);
        batch.flush();
        if (ScissorStack.pushScissors(scissorBounds)) {
        	drawChildren(batch, parentAlpha);
            ScissorStack.popScissors();
        }
        
        resetTransform(batch);
    }
    
    @Override
    public void setWidth (float width) {
        super.setWidth(width);
        widgetAreaBounds.setWidth(width);
    }
    
    @Override
    public void setHeight (float height) {
        super.setHeight(height);
        widgetAreaBounds.setHeight(height);
    }
    
}
