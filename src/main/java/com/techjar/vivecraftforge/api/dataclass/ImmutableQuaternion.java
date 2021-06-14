package com.techjar.vivecraftforge.api.dataclass;

import com.techjar.vivecraftforge.util.Quaternion;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;

public class ImmutableQuaternion {

    protected final Quaternion data;

    public ImmutableQuaternion(Quaternion data) {
        this.data = data;
    }

    /**
     * Returns a copy of the provided Quaternion<br>
     * <br>
     * @return A copy of the provided Quaternion.
     */
    public Quaternion copy() {
        return this.data.copy();
    }

    /**
     * Get w value of this Quaternion.
     *
     * @return The w value of this Quaternion.
     */
    public float getW() {
        return this.data.getW();
    }

    /**
     * Get x value of this Quaternion.
     *
     * @return The x value of this Quaternion.
     */
    public float getX() {
        return this.data.getX();
    }

    /**
     * Get y value of this Quaternion.
     *
     * @return The y value of this Quaternion.
     */
    public float getY() {
        return this.data.getY();
    }

    /**
     * Get z value of this Quaternion.
     *
     * @return The z value of this Quaternion.
     */
    public float getZ() {
        return this.data.getZ();
    }

    /**
     * Get the normalized form of this Quaternion.<br>
     * <br>
     * @return The normalized form of this Quaternion.
     */
    public Quaternion normalized() {
        return this.data.normalized();
    }

    /**
     * Multiply this Quaternion by another.<br>
     * <br>
     * @param other Quaternion to multiply by.
     * @return A new Quaternion representing the multiplication.
     */
    public Quaternion multiply(Quaternion other) {
        return this.data.multiply(other);
    }

    /**
     * Multiply this Quaternion by another.<br>
     * <br>
     * @param other Quaternion to multiply by.
     * @return A new Quaternion representing the multiplication.
     */
    public Quaternion multiply(ImmutableQuaternion other) {
        return this.data.multiply(other.data);
    }

    /**
     * Multiplies a Vector3d by the Quaternion.<br>
     * <br>
     * @param vec The vector to multiply with this Quaternion<br>
     * @return The result of the multiplication as a Vector3d<br>
     */
    public Vector3d multiply(Vector3d vec) {
        return this.data.multiply(vec);
    }

    /**
     * Gets this Quaternion as a look vector. Same as {@link Entity#getLookVec()}, but for the controller/head.<br>
     * <br>
     * @return A Vector3d representing the direction the controller/headset is looking/pointing.
     */
    public Vector3d asLookVector() {
        return this.data.asLookVector();
    }

    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        final ImmutableQuaternion other = (ImmutableQuaternion) obj;
        if (Float.floatToIntBits(this.getW()) != Float.floatToIntBits(other.getW())) {
            return false;
        } else if (Float.floatToIntBits(this.getX()) != Float.floatToIntBits(other.getX())) {
            return false;
        } else if (Float.floatToIntBits(this.getY()) != Float.floatToIntBits(other.getY())) {
            return false;
        } else if (Float.floatToIntBits(this.getZ()) != Float.floatToIntBits(other.getZ())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Immutable Quaternion: {" + "w=" + data.w + ", x=" + data.x + ", y=" + data.y + ", z=" + data.z + '}';
    }
}
