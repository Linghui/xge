package com.gol.xge.rpg.ui;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.tablelayout.Cell;

/** A button with a child {@link Image} to display an image. This is useful when the button must be larger than the image and the
 * image centered on the button. If the image is the size of the button, a {@link Button} without any children can be used, where
 * the {@link Button.ButtonStyle#up}, {@link Button.ButtonStyle#down}, and {@link Button.ButtonStyle#checked} nine patches define
 * the image.
 * @author Nathan Sweet */
public class FrameImageButton extends Button {
    private final Image frame;
    private final Image image;
    private ImageButtonStyle style;

    public FrameImageButton (ImageButtonStyle style) {
        super(style);
//        
        image = new Image();
        image.setScaling(Scaling.fit);
        add(image);

        frame = new Image(style.imageDown);
        frame.setVisible(false);
        this.addActor(frame);
        
        setStyle(style);
        setWidth(getPrefWidth());
        setHeight(getPrefHeight());

    }

    public FrameImageButton (Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        this(new ImageButtonStyle(null, null, null, imageUp, imageDown, imageChecked));
    }

    public void setStyle (ButtonStyle style) {
        if (!(style instanceof ImageButtonStyle)) throw new IllegalArgumentException("style must be an ImageButtonStyle.");
        super.setStyle(style);
        this.style = (ImageButtonStyle)style;
        if (image != null) updateImage();
    }

    public ImageButtonStyle getStyle () {
        return style;
    }

    private void updateImage () {
        boolean isPressed = isPressed();
        image.setDrawable(style.imageUp);
        if (isPressed && style.imageDown != null)
            frame.setVisible(true);
        else if (this.isChecked() && style.imageChecked != null)
            frame.setVisible(true);
        else if (isOver() && style.imageOver != null)
            frame.setVisible(true);
        else if (style.imageUp != null) //
            frame.setVisible(false);
    }

    public void draw (SpriteBatch batch, float parentAlpha) {
        updateImage();
        super.draw(batch, parentAlpha);
    }

    public Image getImage () {
        return image;
    }

    public Cell getImageCell () {
        return getCell(image);
    }

}
