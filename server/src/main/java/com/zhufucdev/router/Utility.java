package com.zhufucdev.router;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Utility {
    public static Map<String, ProtocolVersion> getVersionConvenience() {
        final var map = new HashMap<String, ProtocolVersion>();
        for (var version : ProtocolVersion.values()) {
            for (var name : version.getVersionsSupportedBy()) {
                map.put(name, version);
            }
        }
        return map;
    }

    public static Map<VersionRange, RegisteredServer> getRoute(String version, RegisteredServer server, Map<String, ProtocolVersion> cache) {
        final var mapping = new HashMap<VersionRange, RegisteredServer>();
        if (version.contains("+")) {
            Arrays.stream(version.split("\\+"))
                    .forEach(split -> mapping.putAll(getRoute(split, server, cache)));
        } else if (version.contains("..")) {
            final var inclusive = version.contains("..=");
            final var split = version.split("\\.\\.=?");
            final var lower = cache.get(split[0]);
            final var higher = split.length > 1 ? cache.get(split[1]) : null;
            if (inclusive) {
                mapping.put(VersionRange.ofClosed(lower, higher), server);
            } else {
                mapping.put(VersionRange.ofOpen(lower, higher), server);
            }
        } else {
            final var protocol = cache.get(version);
            if (protocol != null) {
                mapping.put(VersionRange.of(protocol), server);
            }
        }

        return mapping;
    }
}
