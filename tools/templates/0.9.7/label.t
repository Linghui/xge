
        Label $label_name    =   new Label("$label_text", skin.get("$label_style", LabelStyle.class));
        [% label_name %].setName("$label_name");
        [% label_name %].setX($label_x);
        [% label_name %].setY($label_y);
        $add_to([% label_name %]);
