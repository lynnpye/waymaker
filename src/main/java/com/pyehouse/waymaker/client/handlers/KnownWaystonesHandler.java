package com.pyehouse.waymaker.client.handlers;

import com.pyehouse.waymaker.client.config.Config;
import com.pyehouse.waymaker.shared.util.Waymaker;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.api.KnownWaystonesEvent;
import net.blay09.mods.waystones.core.InvalidWaystone;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xaero.common.XaeroMinimapSession;
import xaero.common.core.IXaeroMinimapClientPlayNetHandler;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.minimap.waypoints.WaypointSet;
import xaero.common.minimap.waypoints.WaypointsManager;
import xaero.minimap.XaeroMinimap;

import java.io.IOException;
import java.util.List;

public class KnownWaystonesHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void handleMessage(KnownWaystonesEvent event) {
        List<IWaystone> waystones = event.getWaystones();

        XaeroMinimapSession session = ((IXaeroMinimapClientPlayNetHandler) Minecraft.getInstance().player.connection).getXaero_minimapSession();
        WaypointsManager waypointsManager = session.getWaypointsManager();
        if (waypointsManager.getCurrentWorld() == null) {
            return;
        }

        WaypointSet waypointSet = null;
        String customWaypointSetName = Config.CLIENT.customWaypointSet.get();
        if (Config.CLIENT.targetCustomWaypointSet.get() && (customWaypointSetName != null && !customWaypointSetName.trim().isEmpty())) {
            if (!waypointsManager.getCurrentWorld().getSets().containsKey(customWaypointSetName)) {
                waypointsManager.getCurrentWorld().addSet(customWaypointSetName);
            }
            waypointSet = waypointsManager.getCurrentWorld().getSets().get(customWaypointSetName);
        } else {
            waypointSet = waypointsManager.getCurrentWorld().getCurrentSet();
        }

        for (IWaystone waystone : waystones) {
            boolean matched = false;

            if (waystone instanceof InvalidWaystone) {
                continue;
            }

            String name = waystone.hasName() ? waystone.getName() : "New Waystone";
            for (Waypoint waypoint : waypointSet.getList()) {
                if (Waymaker.matching(waystone, waypoint)) {
                    matched = true;
                    waypoint.setName(name);
                    break;
                }
            }

            if (!matched) {
                Waypoint waypoint = Waymaker.makeWaypoint(waystone.getPos(), name);
                waypointSet.getList().add(waypoint);
            }
        }

        try {
            XaeroMinimap.instance.getSettings().saveWaypoints(waypointsManager.getCurrentWorld());
        } catch (IOException e) {
            LOGGER.error(String.format("Error occurred while saving Xaeros' Minimap waypoints for current world: %s", e.getMessage()));
        }
    }
}
