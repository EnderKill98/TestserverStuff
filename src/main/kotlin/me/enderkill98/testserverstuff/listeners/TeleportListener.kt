package me.enderkill98.testserverstuff.listeners

import me.enderkill98.testserverstuff.Settings
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerTeleportEvent

class TeleportListener: Listener {

    @EventHandler fun onTeleport(e: PlayerTeleportEvent) {
        if(!Settings.getBool(e.player, "printTeleports"))
            return
        e.player.sendMessage("You got teleported to x: ${e.to?.x}, y: ${e.to?.y}, z: ${e.to?.z}, cause: ${e.cause}")
    }

}