package com.techjar.vivecraftforge.util;

import net.minecraft.util.math.vector.Vector3d;

public class RawVRPlayerData {
	public Vector3d offset = new Vector3d(0, 0, 0);
	public RawVRObjectInfo head = new RawVRObjectInfo();
	public RawVRObjectInfo controller0 = new RawVRObjectInfo();
	public RawVRObjectInfo controller1 = new RawVRObjectInfo();
	public boolean handsReversed;
	public float worldScale;
	public boolean seated;
	public boolean freeMove;
	public float bowDraw;
	public float height;
	public int activeHand;
	public boolean crawling;

	public RawVRObjectInfo getController(int c) {
		return c == 0 ? controller0 : controller1;
	}

}
