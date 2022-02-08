package gg.rsmod.plugins.content.inter.quests.free

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab



object DemonSlayer {
    fun openJornal(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Demon Slayer".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I can start this quest by speaking to the ".blue() + "Gypsy ".red() + "in the ".blue() + "tend".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "in ".blue() +  "Varrock's main square".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 8, text = "I must be able to deeat a level 27 ".blue() + "apocalyptic demon".red() + "!".blue())
        for (x in 9..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}