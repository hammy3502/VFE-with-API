package com.techjar.vivecraftforge.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class VRPlayerEvent extends Event {

    public final PlayerEntity player;

    public VRPlayerEvent(PlayerEntity player) {
        this.player = player;
    }
}
