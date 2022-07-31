package com.pyehouse.waymaker.client;

import com.pyehouse.waymaker.client.handlers.KnownWaystonesHandler;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.KnownWaystonesEvent;

public class ClientRegistrar {

    public void registration() {
        Balm.getEvents().onEvent(KnownWaystonesEvent.class, KnownWaystonesHandler::handleMessage);
    }
}
