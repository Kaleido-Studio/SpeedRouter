package com.zhufucdev.router;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

@Plugin(
        id = "router",
        name = "router",
        authors = {"zhufucdev"},
        description = "A custom router",
        version = BuildConstants.VERSION
)
public class Router {
    private final Logger logger;
    private final ProxyServer server;

    @Inject
    public Router(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {

    }
}
