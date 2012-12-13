        TextButton $name = new TextButton("$button_text",skin.get(TextButtonStyle.class));
        [% name %].setName("$name");
        [% name %].setX($button_x);
        [% name %].setY($button_y);
        [% name %].addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "[% name %] clicked");
                // TODO: strongly recommend do your own implementation in the method below
                [% name %]Action();
                return true;
            }
    
        });
        $add_to($name);

