package com.techjar.vivecraftforge.util;

import net.minecraft.util.math.vector.Vector3d;

public class RawVRObjectInfo {
    public double posX;
    public double posY;
    public double posZ;
    public float rotW;
    public float rotX;
    public float rotY;
    public float rotZ;

    public Vector3d getPos() {
        return new Vector3d(posX, posY, posZ);
    }

    public void setPos(Vector3d pos) {
        posX = pos.getX();
        posY = pos.getY();
        posZ = pos.getZ();
    }

    public Quaternion getRot() {
        return new Quaternion(rotW, rotX, rotY, rotZ);
    }

    public void setRot(Quaternion quat) {
        rotW = quat.w;
        rotX = quat.x;
        rotY = quat.y;
        rotZ = quat.z;
    }

    public Vector3d asLookVec() {
        return this.getRot().asLookVector();
    }
}
