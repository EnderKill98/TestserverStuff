package me.enderkill98.testserverstuff.commands

import me.enderkill98.testserverstuff.Settings
import me.enderkill98.testserverstuff.TestserverStuff
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SettingsCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cThis command can only be used by players!")
            return false;
        }

        var foundSetting = false;
        for(booleanSettingName in arrayOf("printTeleports", "printMovementsRelCoords", "printMovementsFromCoords", "printMovementsToCoords", "humanMovementWarning", "humanMovementKick")) {
            if (args.size == 1 && args[0] == "$booleanSettingName:on") {
                Settings.setBool(sender, booleanSettingName, true)
                foundSetting = true
            }else if (args.size == 1 && args[0] == "$booleanSettingName:off") {
                Settings.setBool(sender, booleanSettingName, false)
                foundSetting = true
            }
        }

        if(!args.isEmpty() && !foundSetting) {
            sender.sendMessage(TestserverStuff.PREFIX + "§cUnknown argument / setting!");
            return false;
        }

        // Interface / Overview
        val filterSettingName = if (args.size == 1) args[0].split(":")[0] else null
        if (filterSettingName == null)
            sender.sendMessage("§6Your current §8/§6settings (click to change):")
        if (filterSettingName == null || filterSettingName == "printTeleports")
            sendBoolSetting(sender, "Print teleports", "Print recognized teleports.\nThese are only those seen by plugins.\nMost internal teleports won't show up!", "printTeleports")
        if (filterSettingName == null || filterSettingName == "printMovementsRelCoords")
            sendBoolSetting(sender, "Print Movements (Relative Coords):", "The relative coords of\nPlayerMoveEvents and VehicleMoveEvent", "printMovementsRelCoords")
        if (filterSettingName == null || filterSettingName == "printMovementsFromCoords")
            sendBoolSetting(sender, "Print Movements (From Coords)", "The \"from\" coords of\nPlayerMoveEvents and VehicleMoveEvent", "printMovementsFromCoords")
        if (filterSettingName == null || filterSettingName == "printMovementsToCoords")
            sendBoolSetting(sender, "Print Movements (To Coords)", "The \"to\" coords of\nPlayerMoveEvents and VehicleMoveEvent", "printMovementsToCoords")
        if (filterSettingName == null || filterSettingName == "humanMovementWarning")
            sendBoolSetting(sender, "Warn on human movement", "Warn you in chat, when\nhuman movement was detected", "humanMovementWarning")
        if (filterSettingName == null || filterSettingName == "humanMovementKick")
            sendBoolSetting(sender, "Kick on human movement", "Kick you, when\nhuman movement was detected", "humanMovementKick")
        return true
    }

    fun sendBoolSetting(player: Player, displayName: String, tooltip: String, settingName: String) {
        val currentState = Settings.getBool(player, settingName)

        //val msgPrefix = TextComponent.fromLegacyText(TestserverStuff.PREFIX)
        val msgPrefix = TextComponent.fromLegacyText("§2 - ")
        val msgSettingName = TextComponent(displayName).apply { color = ChatColor.GREEN
            hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(tooltip)) }
        val msgColon = TextComponent(": ").apply { color = ChatColor.GREEN }
        val msgBtnOn = TextComponent("[On]").apply { color = ChatColor.AQUA; isBold = currentState
            hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Click to turn this setting on."))
            clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/settings $settingName:on")
        }
        val msgBtnSpacer = TextComponent(" ")
        val msgBtnOff = TextComponent("[Off]").apply { color = ChatColor.AQUA; isBold = !currentState
            hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Click to turn this setting off."))
            clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/settings $settingName:off")
        }

        player.spigot().sendMessage(*msgPrefix, msgSettingName, msgColon, msgBtnOn, msgBtnSpacer, msgBtnOff)
    }

}
