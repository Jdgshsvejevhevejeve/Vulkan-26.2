package com.nova.vulkan.violation;

import com.nova.vulkan.VulkanPlugin;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ViolationManager {
    private final VulkanPlugin plugin;
    private final Map<UUID, Map<String, Double>> data = new ConcurrentHashMap<>();

    public ViolationManager(VulkanPlugin plugin) {
        this.plugin = plugin;
    }

    public double add(UUID uuid, String check, double amount) {
        return data.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>())
                .merge(check, amount, Double::sum);
    }

    public double get(UUID uuid, String check) {
        return data.getOrDefault(uuid, Map.of()).getOrDefault(check, 0.0);
    }

    public void clear(UUID uuid) {
        data.remove(uuid);
    }
}
