package me.enderkill98.testserverstuff

import org.bukkit.entity.Player

class Settings {

    companion object {
        val SETTINGS = HashMap<String, String>()

        fun toKey(player: Player, setting: String) = "${player.uniqueId},${player.name}:${setting}"

        fun setBool(player: Player, setting: String, value: Boolean) {
            SETTINGS[toKey(player, setting)] = if (value) "true" else "false"
        }

        fun getBool(player: Player, setting: String): Boolean {
            return SETTINGS.getOrDefault(toKey(player, setting), "false") == "true"
        }

        // TODO: Cleanup old entries
    }

}