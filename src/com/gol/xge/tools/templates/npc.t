
        NPC $npc_name = new NPC( new AnimationGroup(Common.readAnimationResource("$npc_json", false, false, manager.get("$npc_pack", TextureAtlas.class)))){

            @Override
            public void action() {
                // TODO Auto-generated method stub
                System.out.println("$npc_name action");
            }
        };
        [% npc_name %].x = $npc_x;
        [% npc_name %].y = $npc_y;
        this.addActorBackground($npc_name);
