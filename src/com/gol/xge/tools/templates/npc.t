
        AnimationActor $npc_name = new AnimationActor( new AnimationGroup(Resources.getInstance().readAnimationResource("$npc_json", false, false, manager.get("$npc_pack", TextureAtlas.class))));
        [% npc_name %].x = $npc_x;
        [% npc_name %].y = $npc_y;
        this.addActorBackground($npc_name);
