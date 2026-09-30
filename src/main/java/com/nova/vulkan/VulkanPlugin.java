package com.nova.vulkan;

import com.nova.vulkan.chat.ChatFilter;
import com.nova.vulkan.crystal.CrystalProtection;
import com.nova.vulkan.violation.ViolationManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VulkanPlugin extends JavaPlugin {
    private ViolationManager violations;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        violations = new ViolationManager(this);

        getServer().getPluginManager().registerEvents(new ChatFilter(this), this);
        getServer().getPluginManager().registerEvents(new CrystalProtection(this), this);

        VulkanCommand command = new VulkanCommand(this);
        if (getCommand("vulkan") != null) {
            getCommand("vulkan").setExecutor(command);
            getCommand("vulkan").setTabCompleter(command);
        }

        getLogger().info("Vulkan 26.2 enabled.");
    }

    public ViolationManager getViolations() {
        return violations;
    }

    public void reloadVulkan() {
        reloadConfig();
        saveDefaultConfig();
    }
}
