package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.PlayerManager;

public class AutoHubMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            PlayerManager playerManager = server.getPlayerManager();
            for (ServerPlayerEntity player : playerManager.getPlayers()) {
                if (!playerManager.getUserWhiteList().isWhitelisted(player.getGameProfile())) {
                    for (ServerPlayerEntity other : playerManager.getPlayers()) {
                        if (player != other && player.distanceTo(other) <= 35.0D) {
                            // Правильный вызов команды через ServerCommandManager
                            server.getCommandManager().executeWithPrefix(player.getCommandSource(), "hub");
                            break;
                        }
                    }
                }
            }
        });
    }
}
