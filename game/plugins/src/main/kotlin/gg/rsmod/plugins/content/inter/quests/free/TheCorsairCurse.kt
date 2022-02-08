package gg.rsmod.plugins.content.inter.quests.free

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab



object TheCorsairCurse {
    fun openJornal(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Corsair Curse".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I can start this quest by speaking to the ".blue() + "Corsair Captain".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "at the ".blue() + "crossroads ".red() + "north of ".blue() + "Port Sarin".red() + ".".blue())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "I will need to fight a ".blue() + "level 35 mage ".red() + "and must nt be affraid".blue())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "of ".blue() + "level 82 ogres".red() + ".".blue())
        for (x in 8..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}