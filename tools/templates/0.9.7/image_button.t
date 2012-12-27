        TextureRegion [% name %]Down = $down_region;
        TextureRegion [% name %]Up   = $up_region;
        ImageButton [% name %] = new ImageButton(new TextureRegionDrawable([% name %]Down), new TextureRegionDrawable([% name %]Up));
        [% name %].setX($button_x);
        [% name %].setY($button_y);
        [% name %].addListener(new ClickListener(){
            
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "[% name %] clicked");
                // TODO: strongly recommend do your own implementation in the method below
                actions.[% name %]Action();
            }
    
        });
        
        $add_to([% name %]);

