package gg.rsmod.plugins.content.inter.quests.free

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab



object BlackKnightsFortress {
    fun openJornal(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Black Knights' Fortress".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I can start this quest by speaking to ".blue() + "Sir Amik Varze ".red() + "at the".blue())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "White Knights' Castle ".red() + "in ".blue() +  "Falador".red() + ".".blue())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "I must have a total of at least 12 Quest Points".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "I would have an advantage if I could fight ".blue() +  "Level 33 Knights".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 8, text = "and if I had a smithing level of ".blue() + "26".red() + ".".blue())
        for (x in 9..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}