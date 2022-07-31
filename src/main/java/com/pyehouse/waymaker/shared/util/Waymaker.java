package com.pyehouse.waymaker.shared.util;

import net.blay09.mods.waystones.api.IWaystone;
import net.minecraft.core.BlockPos;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.settings.ModSettings;

public class Waymaker {

    public static final int POINT_HEIGHT_ABOVE_STONE = 2;

    public static BlockPos stone2point(BlockPos stonePos) {
        return stonePos.above(POINT_HEIGHT_ABOVE_STONE);
    }

    public static BlockPos pointPos(Waypoint waypoint) {
        return new BlockPos(waypoint.getX(), waypoint.getY(), waypoint.getZ());
    }

    public static boolean matching(IWaystone waystone, Waypoint waypoint) {
        if (waystone == null || waypoint == null) return false;
        BlockPos stonePos = waystone.getPos();
        BlockPos pointPos = pointPos(waypoint);
        return pointPos.equals(stone2point(stonePos));
    }

    public static Waypoint makeWaypoint(BlockPos stoneBlockPos, String name) {
        BlockPos pointPos = stone2point(stoneBlockPos);
        return new Waypoint(pointPos.getX(), pointPos.getY(), pointPos.getZ(), name, name.substring(0, 1),
                (int) (Math.random() * (double) ModSettings.ENCHANT_COLORS.length), 0, false);
    }
}
