package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class AutoHubMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (!server.getPlayerManager().isWhitelisted(player.getGameProfile())) {
                    for (ServerPlayerEntity other : server.getPlayerManager().getPlayerList()) {
                        if (player != other && player.distanceTo(other) <= 35.0D) {
                            server.getCommandManager().executeWithPrefix(player.getCommandSource(), "hub");
                            break;
                        }
                    }
                }
            }
        });
    }
}
