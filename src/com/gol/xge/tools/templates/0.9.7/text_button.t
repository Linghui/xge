        TextButton $button_name = new TextButton("$button_text",skin.get(TextButtonStyle.class));
        [% button_name %].setName($button_name);
        [% button_name %].setX($button_x);
        [% button_name %].setY($button_y);
        [% button_name %].addListener(new InputListener(){
            
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("[% button_name %] clicked");
                return true;
            }
    
        });
        this.addActorBottom($button_name);

