        TextButton $button_name = new TextButton("$button_text",skin.getStyle(TextButtonStyle.class), "$button_name");
        [% button_name %].x = $button_x;
        [% button_name %].y = $button_y;
        [% button_name %].setClickListener(new ClickListener(){

            @Override
            public void click(Actor actor, float x, float y) {
                // TODO: add implementation here
                System.out.println("[% button_name %] clicked");
        
            }
    
        });
        this.addActorBottom($button_name);

