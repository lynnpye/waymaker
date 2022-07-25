package com.pyehouse.waymaker;

import com.pyehouse.waymaker.common.CommonRegistrar;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WaymakerMod.MODID)
public class WaymakerMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "waymaker";

    public WaymakerMod() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        final CommonRegistrar commonRegistrar = new CommonRegistrar(modEventBus, forgeEventBus);
        commonRegistrar.registration();

    }
}
