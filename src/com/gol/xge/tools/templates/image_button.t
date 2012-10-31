        TextureRegion [% button_name %]Down = $down_region;
        TextureRegion [% button_name %]Up   = $up_region;
        ImageButton [% button_name %] = new ImageButton([% button_name %]Down, [% button_name %]Up);
        [% button_name %].x = $button_x;
        [% button_name %].y = $button_y;
        [% button_name %].setClickListener(new ClickListener(){

            @Override
            public void click(Actor actor, float x, float y) {
                // TODO: add implementation here
                System.out.println("[% button_name %] clicked");
        
            }
    
    
    
        });
        this.addActorBottom([% button_name %]);

