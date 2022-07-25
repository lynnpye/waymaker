package com.pyehouse.waymaker.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.ExceptionUtils;

public class WaymakerMessage {
    private static final Logger LOGGER = LogManager.getLogger();

    public BlockPos blockPos;
    public String name;

    public WaymakerMessage() {
        this.blockPos = blockPos;
        this.name = name;
    }

    public static WaymakerMessage decode(FriendlyByteBuf buf) {
        WaymakerMessage message = new WaymakerMessage();
        try {
            message.blockPos = buf.readBlockPos();
            message.name = buf.readUtf();
        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
        return message;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeUtf(this.name);
    }
}
