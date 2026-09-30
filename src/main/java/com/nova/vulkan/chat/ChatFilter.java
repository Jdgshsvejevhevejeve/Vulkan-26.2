package com.nova.vulkan.chat;

import com.nova.vulkan.VulkanPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Locale;

public final class ChatFilter implements Listener {
    private final VulkanPlugin plugin;

    public ChatFilter(VulkanPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!plugin.getConfig().getBoolean("chat-filter.enabled", true)) return;
        if (event.getPlayer().hasPermission("vulkan.bypass")) return;

        String normalized = event.getMessage()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^\\p{L}\\p{N}]", "");

        List<String> terms = plugin.getConfig().getStringList("chat-filter.blocked-terms");
        for (String term : terms) {
            String t = term.toLowerCase(Locale.ROOT).replaceAll("[^\\p{L}\\p{N}]", "");
            if (!t.isEmpty() && normalized.contains(t)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Please keep the chat respectful.");
                return;
            }
        }
    }
}
