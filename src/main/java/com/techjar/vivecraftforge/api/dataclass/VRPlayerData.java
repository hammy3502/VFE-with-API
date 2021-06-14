package com.techjar.vivecraftforge.api.dataclass;

import com.techjar.vivecraftforge.util.RawVRObjectInfo;
import com.techjar.vivecraftforge.util.RawVRPlayerData;
import net.minecraft.entity.player.PlayerEntity;

public class VRPlayerData {

    protected final PlayerEntity player;
    protected final RawVRPlayerData data;


    public VRPlayerData(RawVRPlayerData data, PlayerEntity player) {
        this.player = player;
        this.data = data;
    }

    /**
     * Retrieves information about the position and rotation of the head(set) of the VR player.<br>
     * The position for this return value of this function is the position of the headset in the world.<br>
     * @return The aforementioned information about the headset position.
     */
    public VRObjectInfo getHead() {
        RawVRObjectInfo headExactPos = new RawVRObjectInfo();
        headExactPos.setRot(data.head.getRot());
        headExactPos.setPos(this.data.head.getPos().add(player.getPositionVec()));
        return new VRObjectInfo(headExactPos);
    }

    /**
     * Retrieves information about the position and rotation of the first controller of the VR player.<br>
     * The position for this return value of this function is the position of the controller in the world.<br>
     * @return The aforementioned information about the first controller.
     */
    public VRObjectInfo getController0() {
        RawVRObjectInfo controller0ExactPos = new RawVRObjectInfo();
        controller0ExactPos.setRot(data.controller0.getRot());
        controller0ExactPos.setPos(this.data.controller0.getPos().add(player.getPositionVec()));
        return new VRObjectInfo(controller0ExactPos);
    }

    /**
     * Retrieves information about the position and rotation of the second controller of the VR player.<br>
     * The position for this return value of this function is the position of the controller in the world.<br>
     * @return The aforementioned information about the second controller.
     */
    public VRObjectInfo getController1() {
        RawVRObjectInfo controller1ExactPos = new RawVRObjectInfo();
        controller1ExactPos.setRot(data.controller1.getRot());
        controller1ExactPos.setPos(this.data.controller1.getPos().add(player.getPositionVec()));
        return new VRObjectInfo(controller1ExactPos);
    }

    /**
     * Retrieves information about the position and rotation of the head(set) of the VR player.<br>
     * The position for this return value of this function is the position relative to the player's actual coordinates.<br>
     * @return The aforementioned information about the headset position.
     */
    public VRObjectInfo getHeadRelative() {
        return new VRObjectInfo(data.head);
    }

    /**
     * Retrieves information about the position and rotation of the controller of the VR player.<br>
     * The position for this return value of this function is the position relative to the player's actual coordinates.<br>
     * @return The aforementioned information about the headset position.
     */
    public VRObjectInfo getController0Relative() {
        return new VRObjectInfo(data.controller0);
    }

    /**
     * Retrieves information about the position and rotation of the controller of the VR player.<br>
     * The position for this return value of this function is the position relative to the player's actual coordinates.<br>
     * @return The aforementioned information about the headset position.
     */
    public VRObjectInfo getController1Relative() {
        return new VRObjectInfo(data.controller1);
    }

    /**
     * Whether the user has their hands configured to be reversed (left-handed controls).<br>
     * <br>
     * @return true if using reversed controls.
     */
    public boolean areHandsReversed() {
        return this.data.handsReversed;
    }

    /**
     * Retrieves the world scale of the user in VR.<br>
     * <br>
     * @return The world scale of the VR user.
     */
    public float getWorldScale() {
        return this.data.worldScale;
    }

    /**
     * Returns whether the VR user is seated or not.<br>
     * <br>
     * @return true if the VR user is seated, false otherwise.
     */
    public boolean isSeated() {
        return this.data.seated;
    }

    /**
     * Get a float representing the amount the bow is drawn.<br>
     * <br>
     * DEPRECATED: Although not actually deprecated, it seems to be non-functional.<br>
     * <br>
     * @return A float representation of the bow draw
     */
    @Deprecated
    public float getBowDraw() {
        return this.data.bowDraw;
    }

    /**
     * Gets the height of the user as a percentage of the Player's default height.<br>
     * <br>
     * For example, if the player is 5% taller than a normal Steve/Alex, this function would return 1.05.<br>
     * <br>
     * @return The aforementioned height of the user.
     */
    public float getHeight() {
        return this.data.height;
    }

    /**
     * Gets an integer representation of the hand currently in use.<br>
     * <br>
     * @return The integer representation of the currently in-use hand.
     */
    public int getActiveHand() {
        return this.areHandsReversed() ? 1 : 0;
    }

    /**
     * Gets the controller of the user's active hand.<br>
     * <br>
     * @return The controller of the user's active hand. This controller's data stores its actual position in the world.
     */
    public VRObjectInfo getActiveController() {
        return this.areHandsReversed() ? this.getController1() : this.getController0();
    }

    /**
     * Gets the controller of the user's active hand.<br>
     * <br>
     * @return The controller of the user's active hand. This controller's data stores its relative position to the player.
     */
    public VRObjectInfo getActiveControllerRelative() {
        return this.areHandsReversed() ? this.getController1Relative() : this.getController0Relative();
    }

    /**
     * Whether the user is currently crawling.<br>
     * <br>
     * @return true if the user is crawling, or false otherwise.
     */
    public boolean isCrawling() {
        return this.data.crawling;
    }

    /**
     * String representation of the data. Note that positions are relative to the player!!<br>
     * <br>
     * @return A string representation of the data.
     */
    @Override
    public String toString() {
        return data.toString();
    }
}
