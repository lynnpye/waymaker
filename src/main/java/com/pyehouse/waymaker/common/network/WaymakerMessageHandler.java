package com.pyehouse.waymaker.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.ExceptionUtils;
import xaero.common.XaeroMinimapSession;
import xaero.common.core.IXaeroMinimapClientPlayNetHandler;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.minimap.waypoints.WaypointsManager;
import xaero.common.settings.ModSettings;
import xaero.minimap.XaeroMinimap;

import java.io.IOException;
import java.util.function.Supplier;

public class WaymakerMessageHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    private final WaymakerMessage message;
    private final Supplier<NetworkEvent.Context> contextSupplier;

    public WaymakerMessageHandler(WaymakerMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        this.message = message;
        this.contextSupplier = contextSupplier;
    }

    public void handleMessage() {
        BlockPos blockPos = message.blockPos;
        String name = message.name;
        XaeroMinimapSession session = ((IXaeroMinimapClientPlayNetHandler) Minecraft.getInstance().player.connection).getXaero_minimapSession();
        WaypointsManager waypointsManager = session.getWaypointsManager();
        Waypoint waypoint = new Waypoint(blockPos.getX(), blockPos.getY() + 2, blockPos.getZ(), name, name.substring(0, 1),
                (int) (Math.random() * (double) ModSettings.ENCHANT_COLORS.length), 0, false);
        waypointsManager.getWaypoints().getList().add(waypoint);
        try {
            XaeroMinimap.instance.getSettings().saveWaypoints(waypointsManager.getCurrentWorld());
        } catch (IOException e) {
            LOGGER.error("Waymaker unable to save waypoint to Xaeros' Minimap");
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
