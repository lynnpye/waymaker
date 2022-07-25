package com.pyehouse.waymaker.common;

import com.pyehouse.waymaker.common.network.Network;
import com.pyehouse.waymaker.common.handlers.WaystoneActivationHandler;
import com.pyehouse.waymaker.shared.util.ModEventRegistrar;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.WaystoneActivatedEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class CommonRegistrar extends ModEventRegistrar {
    public CommonRegistrar(IEventBus modEventBus, IEventBus forgeEventBus) {
        super(modEventBus, forgeEventBus);
    }

    @Override
    public void registration() {
        Balm.getEvents().onEvent(WaystoneActivatedEvent.class, WaystoneActivationHandler::onWaystoneActivation);
        modEventBus.addListener(Network::onCommonSetupEvent);
    }
}
