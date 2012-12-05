
        NPC $name = new NPC( new AnimationGroup(Common.readAnimationResource("$npc_json", false, false, manager.get("$npc_pack", TextureAtlas.class)))){

            @Override
            public void action() {
                // TODO Auto-generated method stub
                super.action();
                Gdx.app.log(TAG, "$name action");
            }
        };
        [% name %].setX($npc_x);
        [% name %].setY($npc_y);
        $add_to($name);
