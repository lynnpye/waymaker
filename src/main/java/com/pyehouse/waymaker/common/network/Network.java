package com.pyehouse.waymaker.common.network;

import com.pyehouse.waymaker.WaymakerMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class Network {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MESSAGE_PROTOCOL_VERSION = "1";
    public static final String CHANNEL_NAME = "waymakerchannel";
    public static final ResourceLocation simpleChannelURL =
            new ResourceLocation(WaymakerMod.MODID, CHANNEL_NAME);
    public static final byte ADD_WAYPOINT_ID = 1;

    public static SimpleChannel simpleChannel;

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> registration());
    }

    public static void registration() {
        simpleChannel = NetworkRegistry.newSimpleChannel(
                simpleChannelURL,
                () -> MESSAGE_PROTOCOL_VERSION,
                MESSAGE_PROTOCOL_VERSION::equals,
                MESSAGE_PROTOCOL_VERSION::equals
        );

        simpleChannel.registerMessage(
                ADD_WAYPOINT_ID,
                WaymakerMessage.class,
                WaymakerMessage::encode,
                WaymakerMessage::decode,
                WaymakerMessageReceiver::onMessageReceived,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );
    }
}
