package com.nova.vulkan.crystal;

import com.nova.vulkan.VulkanPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class CrystalProtection implements Listener {
    private final VulkanPlugin plugin;

    public CrystalProtection(VulkanPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (!plugin.getConfig().getBoolean("crystal-protection.enabled", true)) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof EnderCrystal)) return;

        if (plugin.getConfig().getBoolean("crystal-protection.protect-blocks", true)) {
            event.blockList().clear();
        }
    }
}
