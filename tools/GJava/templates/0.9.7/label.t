
        Label $name    =   new Label("$label_text", skin.get("$label_style", LabelStyle.class));
        [% name %].setName("$name");
        [% name %].setX($label_x);
        [% name %].setY($label_y);
        $add_to([% name %]);
