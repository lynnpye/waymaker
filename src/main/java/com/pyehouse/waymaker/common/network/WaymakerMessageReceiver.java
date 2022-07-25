package com.pyehouse.waymaker.common.network;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class WaymakerMessageReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onMessageReceived(final WaymakerMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
        final WaymakerMessageHandler handler = new WaymakerMessageHandler(message, contextSupplier);
        contextSupplier.get().enqueueWork(() -> DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> handler.handleMessage()));
        contextSupplier.get().setPacketHandled(true);
    }
}
