package com.techjar.vivecraftforge.api.utils;

import com.techjar.vivecraftforge.api.dataclass.VRPlayerData;
import com.techjar.vivecraftforge.util.PlayerTracker;
import com.techjar.vivecraftforge.util.RawVRPlayerData;
import com.techjar.vivecraftforge.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Note: All functions inside of this API are only usable on the logical side.
 * ie. Check that {@link net.minecraft.world.World#isRemote} is false before running any API functions!
 * Otherwise, crashes may occur in multiplayer worlds or to players who are NOT hosting a LAN world!
 */
@SuppressWarnings("unused")
public class VRAPI {

    /**
     * Checks if the supplied PlayerEntity is in VR.<br>
     * <br>
     * @param player Player to check the VR status of.
     * @return Whether or not the player is in VR.
     */
    public static boolean isVRPlayer(PlayerEntity player) {
        return PlayerTracker.hasPlayerData(player);
    }

    /**
     * Get a list of all players in VR.<br>
     * <br>
     * @return A list of players in VR.
     */
    public static List<PlayerEntity> getVRPlayers() {
        List<PlayerEntity> vrPlayers = new ArrayList<>();
        PlayerList playerList = ServerLifecycleHooks.getCurrentServer().getPlayerList();
        for (Map.Entry<UUID, RawVRPlayerData> entry: PlayerTracker.players.entrySet()) {
            vrPlayers.add(playerList.getPlayerByUUID(entry.getKey()));
        }
        return vrPlayers;
    }

    /**
     * Get VR Player data for the supplied player. Returns null if the player is not in VR.<br>
     *<br>
     * NOTE: If you're using this in a TickEvent during the END phase (such as in PlayerTickEvent), it's recommended you
     * subscribe to the {@link com.techjar.vivecraftforge.api.events.VRPlayerTickEvent} event instead.<br>
     * <br>
     * @param player THe player to get data for.
     * @return The VR data for the player, or null if the player is not in VR.
     */
    public static VRPlayerData getVRPlayerData(PlayerEntity player) {
        RawVRPlayerData data = PlayerTracker.getPlayerData(player);
        if (data == null) {
            return null;
        }
        return new VRPlayerData(data, player);
    }

    /**
     * Get a very high quality ASCII dollar bill.
     *
     * @return An ASCII dollar bill.
     */
    public static String getMoney() {
        return Util.getMoney();
    }
}
