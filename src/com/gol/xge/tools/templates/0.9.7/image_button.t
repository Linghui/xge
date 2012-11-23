        TextureRegion [% button_name %]Down = $down_region;
        TextureRegion [% button_name %]Up   = $up_region;
        ImageButton [% button_name %] = new ImageButton(new TextureRegionDrawable([% button_name %]Down), new TextureRegionDrawable([% button_name %]Up));
        [% button_name %].setX($button_x);
        [% button_name %].setY($button_y);
        [% button_name %].addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("[% button_name %] clicked");
                return true;
            }
    
        });
        
        this.addActorBottom([% button_name %]);

