package gg.rsmod.plugins.content.inter.quests.free.earnestthechicken

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab

/**
 * Varp Stages
 * 0 - Not Started
 * 1 - Talked to Veronica
 * 2 - Talked to Prof. Oddenstein
 * 3 - Finished Quest
 */

var nextLine = 4


object ErnestTheChicken {
    fun openJornal(p: Player, varp: Int) {
        when(varp) {
            0 -> p.notStarted(p)
            1 -> p.started(p)
            2 -> p.startedTwo(p)
            3 -> p.finished(p)
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun Player.notStarted(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Ernest the Chicken".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I can start this quest by speaking to ".blue() + "Veronica ".red() + "who is".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "outside ".blue() + "Draynor Manor ".red())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "")
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "There aren't any requirements for this quest".blue())
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.started(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Ernest the Chicken".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I have spoken with Veronica. Her fiancee, Ernest, went into".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Draynor Manor to get directions, and never returned. She has asked me".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to go and find him.".blue())
        nextLine +=1

        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.startedTwo(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Ernest the Chicken".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "In the manor, I've spoken to Professor Oddenstein and discovered that".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Ernest has been turned into a chicken. The machine to transform him back".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "is broken and the house gremlins have scattered the parts about the manor".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "and it's grounds.".blue())
        nextLine +=1

        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.finished(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Ernest the Chicken".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I have spoken to Veronica".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "I have collected all the parts for the machine".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 8, text = "Dr Oddenstein thanked me for helping fix his machine".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 9, text = "We turned Ernest back to normal and he rewarded me")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 10, text = "QUEST COMPLETE!".brightRed())
        for (x in 11..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.brightRed() = "<col=FF0000>$this</col>"
    fun String.strike() = "<str>$this</str>"
}
