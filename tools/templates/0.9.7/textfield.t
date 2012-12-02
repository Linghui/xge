        TextField $name = new TextField("",skin);
        [% name %].setName("$name");
        [% name %].setX($x);
        [% name %].setY($y);
        [% name %].setMessageText("$hint");
        $add_to($name);

