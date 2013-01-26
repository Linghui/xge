
        NPC [% name %]NpcActor = new NPC( new AnimationGroup(Common.readAnimationResource("$npc_json", false, false, manager.get("$npc_pack", TextureAtlas.class)))){

            @Override
            public void action() {
                super.action();
                Gdx.app.log(TAG, "$name action");
                // TODO: strongly recommend do your own implementation in the method below
                [% name %]Action();
            }
        };
        [% name %]NpcActor.setX($npc_x);
        [% name %]NpcActor.setY($npc_y);
        $add_to([% name %]NpcActor);
