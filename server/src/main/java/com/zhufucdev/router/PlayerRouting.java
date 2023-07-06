package com.zhufucdev.router;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;

public class PlayerRouting {
    private final Configuration configuration;

    public PlayerRouting(Configuration configuration) {
        this.configuration = configuration;
    }

    @Subscribe
    public void onPreconnect(ServerPreConnectEvent event) {
        final var protocol = event.getPlayer().getProtocolVersion();
        var server = configuration.getDestination(protocol);
        if (server.isEmpty()) {
            final var option = configuration.getDefaultServer();
            if (option.isEmpty()) {
                return;
            }
            server = option;
        }
        event.setResult(ServerPreConnectEvent.ServerResult.allowed(server.get()));
    }
}
