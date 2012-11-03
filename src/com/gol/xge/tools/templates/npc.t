
        NPC $name = new NPC( new AnimationGroup(Common.readAnimationResource("$npc_json", false, false, manager.get("$npc_pack", TextureAtlas.class)))){

            @Override
            public void action() {
                // TODO Auto-generated method stub
                super.action();
                System.out.println("$name action");
            }
        };
        [% name %].x = $npc_x;
        [% name %].y = $npc_y;
        this.addActorBackground($name);
