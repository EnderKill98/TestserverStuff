package me.enderkill98.testserverstuff

import me.enderkill98.testserverstuff.commands.SettingsCommand
import me.enderkill98.testserverstuff.listeners.HumanMovementListener
import me.enderkill98.testserverstuff.listeners.JoinListener
import me.enderkill98.testserverstuff.listeners.MovementListener
import me.enderkill98.testserverstuff.listeners.TeleportListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestserverStuff: JavaPlugin() {

    companion object {
        @JvmStatic lateinit var INSTANCE: TestserverStuff
        const val PREFIX = "§6TestserverStuff §8§l| §f"
    }

    override fun onEnable() {
        INSTANCE = this

        getCommand("settings")?.setExecutor(SettingsCommand())

        Bukkit.getPluginManager().registerEvents(JoinListener(), this)
        Bukkit.getPluginManager().registerEvents(HumanMovementListener(), this)
        Bukkit.getPluginManager().registerEvents(MovementListener(), this)
        Bukkit.getPluginManager().registerEvents(TeleportListener(), this)
    }

}