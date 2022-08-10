package com.pyehouse.waymaker.client;

import com.pyehouse.waymaker.client.handlers.KnownWaystonesHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientRegistrar {

    public void registration() {
        MinecraftForge.EVENT_BUS.addListener(KnownWaystonesHandler::handleMessage);
    }
}
