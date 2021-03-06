package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class NumericBar extends Group {

	private String TAG = "NumericBar";
	private Image backgroundBar;
	private Image topBar;
	private Label label;

	private long baseNum = 0;
	private long statusNum = 0;
	private float topBarMaxWidth = 0;
	private float padXPix = 0f;
	private float padYPix = 0f;

	private TextureRegion topRegion;
	private boolean isPersent = false;;

	private final static float DEFAULT_X_PIX = 2;
	private final static float DEFAULT_Y_PIX = 2;

	private boolean left = true;

	private Array<Image> topCovers = new Array<Image>();
	private Skin skin;
	private float labelYPadding = 0f;
	private Label tipLabel;

	public NumericBar(NinePatch background, NinePatch top, int width, int height) {
		this(background, top, width, height, 0, 0, null);
	}

	public NumericBar(NinePatch background, NinePatch top, int width,
			int height, long statusNum, long baseNum) {
		this(background, top, width, height, statusNum, baseNum, null);
	}

	public NumericBar(NinePatch background, NinePatch top, int width,
			int height, long statusNum, long baseNum, LabelStyle style) {

		backgroundBar = new Image(background);
		topBar = new Image(top);

		setBarWidHei(width, height);

		this.addActor(backgroundBar);
		this.addActor(topBar);

		this.setBaseNum(baseNum);
		this.setStatusNum(statusNum);

		if (style != null) {
			label = new Label(statusNum + "/" + baseNum, style);
			label.setWidth(width);
			label.setHeight(height);
			label.setAlignment(Align.center, Align.center);
			this.addActor(label);

		}
	}

	public NumericBar(TextureRegion backgroupRegion, TextureRegion topRegion,
			long statusNum, long baseNum) {
		this(backgroupRegion, topRegion, statusNum, baseNum, null);
	}

	public NumericBar(TextureRegion backgroupRegion, TextureRegion topRegion,
			long statusNum, long baseNum, LabelStyle style) {
		this.topRegion = topRegion;

		backgroundBar = new Image(backgroupRegion);
		topBar = new Image(topRegion);

		this.setWidth(backgroundBar.getWidth());
		this.setHeight(backgroundBar.getHeight());

		this.addActor(backgroundBar);
		this.addActor(topBar);

		topBarMaxWidth = (int) topBar.getWidth();
		// Gdx.app.log(TAG, "topBar.width - " + topBar.width);
		this.setBaseNum(baseNum);
		this.setStatusNum(statusNum);

		if (style != null) {
			label = new Label(statusNum + "/" + baseNum, style);
			label.setWidth(backgroundBar.getWidth());
			label.setHeight(backgroundBar.getPrefHeight());
			// label.setAlignment(Align.CENTER, Align.CENTER);
			label.setAlignment(Align.center);
			this.addActor(label);
		}
	}

	public NumericBar(Drawable background, Drawable top) {
		this(background, top, 0, 0);
	}

	public NumericBar(Drawable background, Drawable top, long statusNum,
			long baseNum) {
		this(background, top, statusNum, baseNum, DEFAULT_X_PIX, DEFAULT_Y_PIX);
	}

	public NumericBar(Drawable background, Drawable top, long statusNum,
			long baseNum, float padXPix, float padYPix) {
		this(background, top, 0, 0, statusNum, baseNum, padXPix, padYPix);
	}

	public NumericBar(Drawable background, Drawable top, int width, int height,
			long statusNum, long baseNum, float padXPix, float padYPix) {

		backgroundBar = new Image(background);
		topBar = new Image(top);

		if (width != 0 && height != 0) {
			this.setBarWidHei(width, height, padXPix, padYPix);
		} else {

			if (width == 0 && height == 0) {

				this.setBarWidHei(backgroundBar.getWidth(),
						backgroundBar.getHeight(), padXPix, padYPix);
			} else if (width == 0) {
				this.setBarWidHei(backgroundBar.getWidth(), height, padXPix,
						padYPix);
			} else {

				this.setBarWidHei(
						width,
						backgroundBar.getHeight() > topBar.getHeight() ? backgroundBar
								.getHeight() : topBar.getHeight(), padXPix,
						padYPix);
			}

		}

		this.addActor(backgroundBar);
		this.addActor(topBar);

		this.setBaseNum(baseNum);
		this.setStatusNum(statusNum);

		this.setSize(backgroundBar.getWidth(), backgroundBar.getHeight());
	}

	public void setAlignment(boolean isLeft) {
		this.left = isLeft;
		setTopBarWid(topBar.getWidth());
	}

	public void backToTop() {
		backgroundBar.toFront();
	}

	// public void set

	public void setReduceNum(int reduct) {
		this.setStatusNum(statusNum - reduct);
	}

	public void setLabelStyle(String labelStyleName) {

		if (labelStyleName == null || labelStyleName.equals("")) {
			return;
		}

		setLabelStyle(skin.get(labelStyleName, LabelStyle.class));
	}

	public void setLabelStyle(LabelStyle style) {
		setLabel(style, labelYPadding);
	}

	public void setLabel(LabelStyle style, float yPadding) {
		setLabel(style, yPadding, false);
	}

	public void setLabelYPadding(String yPadding) {
		if (yPadding == null || yPadding.equals("")) {
			return;
		}
		setLabelYPadding(Float.valueOf(yPadding));
	}

	public void setLabelYPadding(float yPadding) {
		if (yPadding == 0) {
			return;
		}

		this.labelYPadding = yPadding;
		label.setY(labelYPadding);
	}

	public void setLabel(LabelStyle style, float yPadding, boolean isPersent) {
		this.isPersent = isPersent;

		if (label == null) {
			label = new Label("default", style);
		}

		setLabelContent();

		this.labelYPadding = yPadding;

		label.setWidth(backgroundBar.getWidth());
		label.setHeight(backgroundBar.getPrefHeight());
		label.setAlignment(Align.center);
		label.setY(labelYPadding);
		this.addActor(label);
	}

	private void setLabelContent() {
		if (label == null) {
			return;
		}

		if (!isPersent) {
			label.setText(statusNum + "/" + baseNum);
		} else {
			label.setText((int) Math.rint(statusNum * 100 / baseNum) + "%");
		}
	}

	public void addTips(String tips) {
		this.addTips(tips, 0);
	}

	public void addTips(String tips, float yPadding) {
		if (label == null) {
			return;
		}

		if (tipLabel == null) {

			tipLabel = new Label(tips, label.getStyle());

			tipLabel.setWidth(backgroundBar.getWidth());
			tipLabel.setHeight(backgroundBar.getPrefHeight());
			tipLabel.setAlignment(Align.center);
			tipLabel.setY(yPadding);
			this.addActor(tipLabel);
		}
		tipLabel.setText(tips);
	}

	public void setStatusNum(String statusNum) {
		setStatusNum(Long.valueOf(statusNum));
	}

	public void setStatusNum(long statusNum) {
		if (statusNum <= 0) {
			// TODO: error
			// Gdx.app.log(TAG, "setStatusNum error ");
			this.topBar.setVisible(false);
			statusNum = 0;
		} else {
			this.topBar.setVisible(true);
		}

		if (statusNum > baseNum) {
			this.statusNum = baseNum;
		} else {
			this.statusNum = statusNum;
		}

		setLabelContent();

		float temp = (float) this.statusNum / baseNum;
		float topBarWidth = temp * topBarMaxWidth;
		this.setTopBarWid(topBarWidth);

	}

	public long getStatusNum() {
		return this.statusNum;
	}

	public void setBaseNum(String baseNum) {
		this.setBaseNum(Long.valueOf(baseNum));
	}

	public void setBaseNum(long baseNum) {
		if (baseNum < 0) {
			// TODO: error
//			Gdx.app.log(TAG, "setBaseNum error ");
			baseNum = 0;
			return;
		}
		this.baseNum = baseNum;
	}

	public long getBaseNum() {
		return this.baseNum;
	}

	public void setStatusAndBase(long status, long base) {
		this.setBaseNum(base);
		this.setStatusNum(status);
	}

	public void addTopCover(Drawable topCover) {
		Image cover = new Image(topCover);
		cover.setX(topBar.getX());
		cover.setY(topBar.getY());
		cover.setWidth(topBar.getWidth());
		cover.setHeight(topBar.getHeight());
		this.addActor(cover);
		this.topCovers.add(cover);
	}

	private void setTopBarWid(float width) {
//		 Gdx.app.log(TAG, "setTopBarWid " + width);
		if (width > this.topBarMaxWidth || width < 0) {
			// TODO: error
//			Gdx.app.log(TAG, "setTopBarWid error ");
			return;
		}
		if (topRegion != null) {
			topRegion.setRegion(topRegion, 0, 0, (int) width,
					topRegion.getRegionHeight());
		}
		if (width < topBar.getDrawable().getMinWidth()) {
			width = topBar.getDrawable().getMinWidth();
		}
		topBar.setWidth(width);
		topBar.invalidate();

		if (this.left) {
			topBar.setX(padXPix);
		} else {
			topBar.setX(this.getWidth() - topBar.getWidth() - padXPix);
		}

		for (Image cover : this.topCovers) {
			cover.setWidth(topBar.getWidth());
			cover.layout();
			cover.setX(topBar.getX());
		}
	}

	public void setWidth(float width) {
		setBarWidHei(width, this.getHeight());
	}

	public void setHeight(float height) {
		setBarWidHei(this.getWidth(), height);
	}

	public void setPadXPix(String padXPix) {
		this.setPadXPix(Float.valueOf(padXPix));
	}

	public void setPadXPix(float padXPix) {
		setBarWidHei(this.getWidth(), this.getHeight(), padXPix, this.padYPix);
	}

	public void setPadYPix(String padYPix) {
		this.setPadYPix(Float.valueOf(padYPix));
	}

	public void setPadYPix(float padYPix) {
		setBarWidHei(this.getWidth(), this.getHeight(), this.padXPix, padYPix);
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public void setBackPatch(String drawableName) {
		backgroundBar.setDrawable(skin.getDrawable(drawableName));
	}

	public void setTopPatch(String drawableName) {
		this.topBar.setDrawable(skin.getDrawable(drawableName));
	}

	/*
     * 
     */
	public void setBarWidHei(float width, float height) {
		setBarWidHei(width, height, padXPix, padYPix);
	}

	public void setBarWidHei(float width, float height, float padXPix,
			float padYPix) {
		super.setWidth(width);
		super.setHeight(height);
		backgroundBar.setWidth(width);
		backgroundBar.setHeight(height);
		backgroundBar.invalidate();

		this.padXPix = padXPix;
		this.padYPix = padYPix;
		topBar.setX(padXPix);
		topBar.setY(padYPix);
		topBarMaxWidth = width - padXPix * 2;
		topBar.setWidth(topBarMaxWidth);
		topBar.setHeight(height - padYPix * 2);
		topBar.invalidate();
	}
}
