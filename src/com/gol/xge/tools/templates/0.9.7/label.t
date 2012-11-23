
        Label $label_name    =   new Label("$label_text", skin.get("$label_style", LabelStyle.class));
        [% label_name %].setX($label_x);
        [% label_name %].setY($label_y);
        this.addActorBottom([% label_name %]);