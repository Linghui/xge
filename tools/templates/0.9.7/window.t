package com.gol.game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class $window_class extends Window {

    private final static String TITLE = "$title";

    public $window_class(Skin skin) {
        this(skin.get("default", WindowStyle.class));
    }

    public $window_class(WindowStyle windowStyle) {
        super(TITLE,windowStyle);
        init();
    }

    private void init() {
$init
    }

}
