package com.techjar.vivecraftforge.api.dataclass;

import com.techjar.vivecraftforge.util.RawVRObjectInfo;
import net.minecraft.util.math.vector.Vector3d;

public class VRObjectInfo {

    protected final RawVRObjectInfo data;

    public VRObjectInfo(RawVRObjectInfo info) {
        this.data = info;
    }

    /**
     * Get position of the headset/controller.<br>
     *<br>
     * @return The position of the headset/controller.
     */
    public Vector3d getPos() {
        return data.getPos();
    }

    /**
     * Get the rotation of the headset/controller as a Quaternion.<br>
     *<br>
     * @return The rotation of the headset/controller.
     */
    public ImmutableQuaternion getRotation() {
        return new ImmutableQuaternion(data.getRot());
    }

    /**
     * Gets the direction of the headset/controller as a look vector. Same as
     * {@link ImmutableQuaternion#asLookVector()}<br>
     * <br>
     * @return The rotation of this controller as a look vector.
     */
    public Vector3d asLookVec() {
        return this.data.asLookVec();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
