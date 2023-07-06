package com.zhufucdev.router;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Plugin(
        id = "speed-router",
        name = "Speed Router",
        authors = {"zhufucdev"},
        description = "A custom router for velocity",
        version = BuildConstants.VERSION
)
public class Main {
    private final Logger logger;
    private final Path dataDir;
    private final ProxyServer server;

    @Inject
    public Main(ProxyServer server, Logger logger, @DataDirectory Path dataDir) {
        this.server = server;
        this.logger = logger;
        this.dataDir = dataDir;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        var defaultServer = server.getAllServers().stream().findFirst();
        final var mapping = new HashMap<VersionRange, RegisteredServer>();

        final var file = new File(dataDir.toFile(), "config.yaml");
        if (!file.exists()) {
            logger.error("Configuration {} doesn't exist.", file.getPath());
            return;
        }

        final Map<String, String> config;
        try {
            final var reader = new FileReader(file);
            config = new Yaml().load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final var defServerName = config.remove("default_server");

        if (defServerName == null || defServerName.isEmpty()) {
            logger.warn("No default server set. The proxy can be in a lost.");
        }

        final var convenience = Utility.getVersionConvenience();
        config.forEach((version, value) -> {
            if (version.equals("default_server")) {
                return;
            }
            final var server = this.server.getServer(value);
            if (server.isEmpty()) {
                logger.warn("server {} is not present", value);
                return;
            }
            mapping.putAll(Utility.getRoute(version, server.get(), convenience));
        });

        logger.info("{} effective routes loaded", mapping.size());

        final var configuration = new Configuration(mapping, defaultServer.orElse(null));
        server.getEventManager().register(this, new PlayerRouting(configuration));
    }
}
