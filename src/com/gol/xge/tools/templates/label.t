
        Label $label_name    =   new Label("$label_text", skin.getStyle("$label_style", LabelStyle.class));
        [% label_name %].x = $label_x;
        [% label_name %].y = $label_y;
        this.addActorBottom([% label_name %]);