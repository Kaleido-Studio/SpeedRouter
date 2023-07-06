package com.zhufucdev.router;

import com.google.inject.Inject;
import com.velocitypowered.api.event.lifecycle.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "router",
        name = "router",
        version = BuildConstants.VERSION
)
public class Router {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
