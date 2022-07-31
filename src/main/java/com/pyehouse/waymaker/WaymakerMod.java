package com.pyehouse.waymaker;

import com.pyehouse.waymaker.client.ClientRegistrar;
import com.pyehouse.waymaker.client.config.Config;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WaymakerMod.MODID)
public class WaymakerMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "waymaker";

    public WaymakerMod() {

        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        final ClientRegistrar clientRegistrar = new ClientRegistrar();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> clientRegistrar::registration);

    }
}
