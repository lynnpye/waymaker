package com.pyehouse.waymaker.shared.util;

import net.minecraftforge.eventbus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ModEventRegistrar {
    private static final Logger LOGGER = LogManager.getLogger();

    protected final IEventBus modEventBus;
    protected final IEventBus forgeEventBus;

    public ModEventRegistrar(IEventBus modEventBus, IEventBus forgeEventBus) {
        this.modEventBus = modEventBus;
        this.forgeEventBus = forgeEventBus;
    }

    public abstract void registration();
}
