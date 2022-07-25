package com.pyehouse.waymaker.common.handlers;

import com.pyehouse.waymaker.common.network.Network;
import com.pyehouse.waymaker.common.network.WaymakerMessage;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.api.WaystoneActivatedEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaystoneActivationHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onWaystoneActivation(WaystoneActivatedEvent event) {
        IWaystone waystone = event.getWaystone();
        WaymakerMessage message = new WaymakerMessage();
        message.blockPos = waystone.getPos();
        message.name = waystone.getName();
        Network.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), message);
    }
}
