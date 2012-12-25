        TextButton $name = new TextButton("$button_text",skin.get(TextButtonStyle.class));
        [% name %].setName("$name");
        [% name %].setX($button_x);
        [% name %].setY($button_y);
        [% name %].addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "[% name %] clicked");
                // TODO: strongly recommend do your own implementation in the method below
                [% name %]Action();
            }
    
        });
        $add_to($name);

