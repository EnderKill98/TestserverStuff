package me.enderkill98.testserverstuff.listeners

import me.enderkill98.testserverstuff.Settings
import me.enderkill98.testserverstuff.TestserverStuff
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.vehicle.VehicleMoveEvent

class HumanMovementListener: Listener {

    @EventHandler fun onPlayerMove(e: PlayerMoveEvent) = validatePlayerPos(e.player)
    @EventHandler fun onVehicleMove(e: VehicleMoveEvent) = validatePlayerPos(e.vehicle.passenger as? Player)

    private fun validatePlayerPos(player: Player?) {
        val position: Location = player?.location ?: return
        val x = (position.x * 1000.0).toLong() % 10
        val z = (position.z * 1000.0).toLong() % 10
        if (x != 0L && z != 0L) {
            TestserverStuff.INSTANCE.logger.info("Human check failed for ${player.name}! x: $x, z: $z at ${position.x}, ${position.y}, ${position.z}")
            if(Settings.getBool(player, "humanMovementWarning"))
                player.sendMessage("§cERROR: Human Player Detected! xz: [$x / $z]")
            if(Settings.getBool(player, "humanMovementKick"))
                player.kickPlayer("xz: [$x / $z]\n" +
                        "§cERROR: Human Player Detected!\n" +
                        "§7Playing only allowed with bot movement\n" +
                        "§7------------------------------------------\n" +
                        "\n" +
                        "long x = ((long) position.x*1000)) % 10;\n" +
                        "long z = ((long) position.x*1000)) % 10;\n" +
                        "if(x!=0 && z!=0) player.kick();")
        }
    }

}