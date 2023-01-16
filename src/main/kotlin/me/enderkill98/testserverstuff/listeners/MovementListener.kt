package me.enderkill98.testserverstuff.listeners

import me.enderkill98.testserverstuff.Settings
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.vehicle.VehicleMoveEvent

class MovementListener: Listener {

    @EventHandler fun onPlayerMove(e: PlayerMoveEvent) = printMovement(e.player, e.from, e.to)
    @EventHandler fun onVehicleMove(e: VehicleMoveEvent) = printMovement(e.vehicle.passenger as? Player, e.from, e.to)

    private fun printMovement(player: Player?, from: Location, to: Location?) {
        if(player == null) return
        val printRel = Settings.getBool(player, "printMovementsRelCoords") && to != null
        val printFrom = Settings.getBool(player, "printMovementsFromCoords")
        val printTo = Settings.getBool(player, "printMovementsToCoords") && to != null

        if(printRel || printFrom || printTo) player.sendMessage("You moved!")
        if(printRel) player.sendMessage("§8rel.x: ${to!!.x - from.x}, rel.y: ${to.y - from.y}, rel.z: ${to.z - from.z}")
        if(printFrom) player.sendMessage("§8from.x: ${from.x}, from.y: ${from.y}, from.z: ${from.z}")
        if(printTo) player.sendMessage("§8to.x: ${to!!.x}, to.y: ${to.y}, to.z: ${to.z}")
    }

}