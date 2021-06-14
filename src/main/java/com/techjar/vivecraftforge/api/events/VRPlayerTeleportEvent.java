package com.techjar.vivecraftforge.api.events;

import net.minecraft.entity.player.PlayerEntity;

public class VRPlayerTeleportEvent extends VRPlayerEvent {

    public final double x;
    public final double y;
    public final double z;

    public VRPlayerTeleportEvent(PlayerEntity player, double x, double y, double z) {
        super(player);
        this.x = x;
        this.y = y;
        this.z = z;

    }
}
