package com.pyehouse.waymaker.client.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final String VAR_customWaypointSet = "customWaypointSet";
    public static final String VAR_targetCustomWaypointSet = "targetCustomWaypointSet";

    public static final String LABEL_targetCustomWaypointSet = "Target Custom Waypoint Set";

    public static final String DEFAULT_customWaypointSet = "Waystones";

    static {
        final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }

    public static class ClientConfig {
        public final ForgeConfigSpec.ConfigValue<String> customWaypointSet;
        public final ForgeConfigSpec.BooleanValue targetCustomWaypointSet;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push(LABEL_targetCustomWaypointSet);
            targetCustomWaypointSet = builder
                    .comment("Should new waypoints be saved to a custom waypoint set? If false, new waypoints will be saved to the currently selected waypoint set.")
                    .define(VAR_targetCustomWaypointSet, false);
            builder.pop();

            builder.push("Custom Waypoint Set");
            customWaypointSet = builder
                    .comment(String.format("The custom waypoint set to save new waypoints to. Only takes effect if '%s' is true.", LABEL_targetCustomWaypointSet))
                    .define(VAR_customWaypointSet, DEFAULT_customWaypointSet);
            builder.pop();
        }

    }
}
