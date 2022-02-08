package gg.rsmod.plugins.content.inter.quests.free

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab



object DoricsQuest {
    fun openJornal(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Doric's Quest".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I can start this quest by speaking to ".blue() + "Doric ".red() + "who is ".blue() + "North of".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "Falador".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "There aren't any requirements but ".blue() + "Level 15 Mining ".red() + "will help".blue())
        for (x in 8..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
    fun String.underline() = "<u>$this</u>"
}