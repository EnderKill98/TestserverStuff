package me.enderkill98.testserverstuff.listeners

import me.enderkill98.testserverstuff.Settings
import me.enderkill98.testserverstuff.TestserverStuff
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener: Listener {

    @EventHandler fun onJoin(e: PlayerJoinEvent) {
        // Welcome messages
        e.player.sendMessage(TestserverStuff.PREFIX + "§aWelcome to EnderKill98's Testserver.")
        e.player.sendMessage(TestserverStuff.PREFIX + "§aThis server is meant for testing out various things related to the LO Server.")
        e.player.spigot().sendMessage(*TextComponent.fromLegacyText(TestserverStuff.PREFIX + "§aTo view server logs, "), TextComponent("[click here]").apply {
            color = ChatColor.AQUA
            clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://cosmos-ink.net/testpaper/log.php")
        })
        e.player.sendMessage(TestserverStuff.PREFIX + "§6Note: The overworld and player data get automatically reset when the server crashes / restarts!")

        // Default settings
        Settings.setBool(e.player, "printTeleports", true)
        Settings.setBool(e.player, "humanMovementWarning", true)

        // Show settings
        e.player.performCommand("settings")
    }

}