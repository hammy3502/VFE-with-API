package com.techjar.vivecraftforge.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ThreadQuickExitException;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.math.vector.Vector3d;

public class AimFixHandler extends ChannelInboundHandlerAdapter {
	private final NetworkManager netManager;

	public AimFixHandler(NetworkManager netManager) {
		this.netManager = netManager;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ServerPlayerEntity player = ((ServerPlayNetHandler)netManager.getNetHandler()).player;
		boolean isCapturedPacket = msg instanceof CPlayerTryUseItemPacket || msg instanceof CPlayerTryUseItemOnBlockPacket || msg instanceof CPlayerDiggingPacket;

		if (!PlayerTracker.hasPlayerData(player) || !isCapturedPacket || player.getServer() == null) {
			// we don't need to handle this packet, just defer to the next handler in the pipeline
			ctx.fireChannelRead(msg);
			return;
		}

		LogHelper.debug("Captured message {}", msg.getClass().getSimpleName());
		player.getServer().runAsync(() -> {
			// Save all the current orientation data
			Vector3d oldPos = player.getPositionVec();
			Vector3d oldPrevPos = new Vector3d(player.prevPosX, player.prevPosY, player.prevPosZ);
			float oldPitch = player.rotationPitch;
			float oldYaw = player.rotationYaw;
			float oldYawHead = player.rotationYawHead;
			float oldPrevPitch = player.prevRotationPitch;
			float oldPrevYaw = player.prevRotationYaw;
			float oldPrevYawHead = player.prevRotationYawHead;
			float oldEyeHeight = player.eyeHeight;

			RawVRPlayerData data = null;
			if (PlayerTracker.hasPlayerData(player)) { // Check again in case of race condition
				data = PlayerTracker.getPlayerDataAbsolute(player);
				Vector3d pos = data.getController(0).getPos();
				Vector3d aim = data.getController(0).getRot().multiply(new Vector3d(0, 0, -1));

				// Inject our custom orientation data
				player.setRawPosition(pos.x, pos.y, pos.z);
				player.prevPosX = pos.x;
				player.prevPosY = pos.y;
				player.prevPosZ = pos.z;
				player.rotationPitch = (float)Math.toDegrees(Math.asin(-aim.y));
				player.rotationYaw = (float)Math.toDegrees(Math.atan2(-aim.x, aim.z));
				player.prevRotationPitch = player.rotationPitch;
				player.prevRotationYaw = player.prevRotationYawHead = player.rotationYawHead = player.rotationYaw;
				player.eyeHeight = 0;

				// Set up offset to fix relative positions
				data = PlayerTracker.getPlayerData(player);
				data.offset = oldPos.subtract(pos);
			}

			// Call the packet handler directly
			// This is several implementation details that we have to replicate
			try {
				if (netManager.isChannelOpen()) {
					try {
						((IPacket<INetHandler>)msg).processPacket(netManager.getNetHandler());
					} catch (ThreadQuickExitException e) { // Apparently might get thrown and can be ignored
					}
				}
			} finally {
				// Vanilla uses SimpleInboundChannelHandler, which automatically releases
				// by default, so we're expected to release the packet once we're done.
				ReferenceCountUtil.release(msg);
			}

			// Restore the original orientation data
			player.setRawPosition(oldPos.x, oldPos.y, oldPos.z);
			player.prevPosX = oldPrevPos.x;
			player.prevPosY = oldPrevPos.y;
			player.prevPosZ = oldPrevPos.z;
			player.rotationPitch = oldPitch;
			player.rotationYaw = oldYaw;
			player.rotationYawHead = oldYawHead;
			player.prevRotationPitch = oldPrevPitch;
			player.prevRotationYaw = oldPrevYaw;
			player.prevRotationYawHead = oldPrevYawHead;
			player.eyeHeight = oldEyeHeight;

			// Reset offset
			if (data != null)
				data.offset = new Vector3d(0, 0, 0);
		});
	}
}
