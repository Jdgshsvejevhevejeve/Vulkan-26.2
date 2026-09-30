package com.nova.vulkan;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.*;

public final class VulkanCommand implements CommandExecutor, TabCompleter {
    private final VulkanPlugin plugin;

    public VulkanCommand(VulkanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("vulkan.admin")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.RED + "Vulkan commands:");
            sender.sendMessage(ChatColor.GRAY + "/vulkan reload");
            sender.sendMessage(ChatColor.GRAY + "/vulkan alerts");
            sender.sendMessage(ChatColor.GRAY + "/vulkan debug");
            sender.sendMessage(ChatColor.GRAY + "/vulkan info <player>");
            sender.sendMessage(ChatColor.GRAY + "/vulkan violations <player>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                plugin.reloadVulkan();
                sender.sendMessage(ChatColor.GREEN + "Vulkan configuration reloaded.");
            }
            case "alerts", "debug" -> sender.sendMessage(ChatColor.YELLOW + "This control is reserved for the next check modules.");
            case "info", "violations" -> {
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /vulkan " + args[0] + " <player>");
                    return true;
                }
                sender.sendMessage(ChatColor.GRAY + "No violations recorded for " + args[1] + ".");
            }
            default -> sender.sendMessage(ChatColor.RED + "Unknown subcommand.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("reload", "alerts", "debug", "info", "violations");
        }
        return Collections.emptyList();
    }
}
