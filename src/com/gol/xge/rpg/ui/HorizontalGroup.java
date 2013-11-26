package com.gol.xge.rpg.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.SnapshotArray;


public class HorizontalGroup extends WidgetGroup {
    private float prefWidth, prefHeight;
    private boolean sizeInvalid = true;
    private int alignment;
    private boolean reverse;
    private float spacing;

    public HorizontalGroup () {
            setTouchable(Touchable.childrenOnly);
    }

    /** Sets the vertical alignment of the children. Default is center.
     * @see Align */
    public void setAlignment (int alignment) {
            this.alignment = alignment;
    }

    /** If true, the children will be ordered from right to left rather than the default left to right. */
    public void setReverse (boolean reverse) {
            this.reverse = reverse;
    }

    public void invalidate () {
            super.invalidate();
            sizeInvalid = true;
    }

    private void computeSize () {
            sizeInvalid = false;
            SnapshotArray<Actor> children = getChildren();
            int n = children.size;
            prefWidth = spacing * (n - 1);
            prefHeight = 0;
            for (int i = 0; i < n; i++) {
                    Actor child = children.get(i);
                    if (child instanceof Layout) {
                            Layout layout = (Layout)child;
                            prefWidth += layout.getPrefWidth();
                            prefHeight = Math.max(prefHeight, layout.getPrefHeight());
                    } else {
                            prefWidth += child.getWidth();
                            prefHeight = Math.max(prefHeight, child.getHeight());
                    }
            }
    }

    public void layout () {
            float spacing = this.spacing;
            float groupHeight = getHeight();
            float x = reverse ? getWidth() : 0;
            float dir = reverse ? -1 : 1;
            SnapshotArray<Actor> children = getChildren();
            for (int i = 0, n = children.size; i < n; i++) {
                    Actor child = children.get(i);
                    float width, height;
                    if (child instanceof Layout) {
                            Layout layout = (Layout)child;
                            width = layout.getPrefWidth();
                            height = layout.getPrefHeight();
                    } else {
                            width = child.getWidth();
                            height = child.getHeight();
                    }
                    float y;
                    if ((alignment & Align.bottom) != 0)
                            y = 0;
                    else if ((alignment & Align.top) != 0)
                            y = groupHeight - height;
                    else
                            y = (groupHeight - height) / 2;
                    if (reverse) x += (width + spacing) * dir;
                    child.setBounds(x, y, width, height);
                    if (!reverse) x += (width + spacing) * dir;
            }
    }

    public float getPrefWidth () {
            if (sizeInvalid) computeSize();
            return prefWidth;
    }

    public float getPrefHeight () {
            if (sizeInvalid) computeSize();
            return prefHeight;
    }

    /** Sets the space between children. */
    public void setSpacing (float spacing) {
            this.spacing = spacing;
    }
}