package com.techjar.vivecraftforge.api.events;

import com.techjar.vivecraftforge.api.dataclass.VRPlayerData;
import com.techjar.vivecraftforge.util.RawVRPlayerData;
import net.minecraft.entity.player.PlayerEntity;

public class VRPlayerTickEvent extends VRPlayerEvent {

    public final VRPlayerData data;

    public VRPlayerTickEvent(PlayerEntity player, RawVRPlayerData data) {
        super(player);
        this.data = new VRPlayerData(data, player);
    }
}
