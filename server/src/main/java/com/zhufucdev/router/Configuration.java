package com.zhufucdev.router;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Configuration {
    private final Map<VersionRange, RegisteredServer> routes;
    private final RegisteredServer defaultServer;

    public Configuration(Map<VersionRange, RegisteredServer> mapping, RegisteredServer defaultServer) {
        this.routes = mapping;
        this.defaultServer = defaultServer;
    }

    @NotNull
    public Map<VersionRange, RegisteredServer> getRoutes() {
        return routes;
    }

    @NotNull
    public Optional<RegisteredServer> getDestination(ProtocolVersion version) {
        for (var entry : routes.entrySet()) {
            if (entry.getKey().contains(version)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    @NotNull
    public Optional<RegisteredServer> getDefaultServer() {
        if (defaultServer != null) {
            return Optional.of(defaultServer);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(routes, that.routes) && Objects.equals(defaultServer, that.defaultServer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes, defaultServer);
    }
}
