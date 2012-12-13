        TextureRegion [% name %]Down = $down_region;
        TextureRegion [% name %]Up   = $up_region;
        ImageButton [% name %] = new ImageButton(new TextureRegionDrawable([% name %]Down), new TextureRegionDrawable([% name %]Up));
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
        
        $add_to([% name %]);

