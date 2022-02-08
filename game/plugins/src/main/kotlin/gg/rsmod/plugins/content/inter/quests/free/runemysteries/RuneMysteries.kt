package gg.rsmod.plugins.content.inter.quests.free.runemysteries

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab

var nextLine = 4


object RuneMysteries {
    fun openJornal(p: Player, varp: Int) {
        when(varp) {
            0 -> p.notStarted(p)
            1,2,3,4,5 -> p.started(p, varp)
            6 -> p.finished(p)
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun Player.notStarted(p: Player) {
        p.message("Showing Rune Mysteries Journal")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Rune Mysteries".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I can start this quest by speaking to  ".blue() + "Duke Horacio of ".red())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Lumbridge ".red() + ", upstairs in ".blue() +  "Lumbridge Castle".red() + ".".blue())
        nextLine +=1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.started(p: Player, varp: Int) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Rune Mysteries".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I spoke to Duke Horacio and he showed me a strange".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "talisman that had been found by one of his subjects.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I agreed to take it to the Wizards' Tower, South West of".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text ="Lumbridge for further examination by the wizards.".strike())
        if(varp == 1) {
            nextLine += 1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I need to find the ".blue() + "Head Wizard ".red() + "and give him the ".blue() + "Talisman.".red())
        }
        else if (varp == 2) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I gave the Talisman to the Wizard but I didn't want to help".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "him in his research right now.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should talk to ".blue() + "Sedridor ".red() + "again to continue this quest.".blue())
        }
        else if (varp == 3) {
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I gave the Talisman to the Head Wizard of the Tower and".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "agreed to help him with his research into rune stones.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should take this ".blue() + "Research Package ".red() + "to ".blue() + "Aubury ".red() + "in ".blue() + "Varrock".red() + ",".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "he owns the rune shop.".blue())
        }
        else if (varp == 4) {
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I gave the Talisman to the Head Wizard of the Tower and".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "agreed to help him with his research into rune stones.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I took the research package to Varrock and delivered it.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should speak to ".blue() + "Aubury ".red() + "again when he has finished".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "examining the ".blue() + "research package ".red() + "I have delivered to him.".blue())
        }
        else if (varp == 5) {
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I gave the Talisman to the Head Wizard of the Tower and".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "agreed to help him with his research into rune stones.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I took the research package to Varrock and delivered it.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Aubury was interested in the research package and gave".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "me his own research notes to deliver to Sedridor.".strike())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should take the ".blue() + "notes ".red() + "to ".blue() + "Sedridor ".red() + "and see what he says".blue())
        }
            nextLine +=1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.finished(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Rune Mysteries".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I spoke to Duke Horacio and he showed me a strange".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "talisman that had been found by one of his subjects.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "I agreed to take it to the Wizards' Tower, South West of".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "Lumbridge for further examination by the wizards.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 8, text = "I gave the Talisman to the Head Wizard of the Tower and".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 9, text = "agreed to help him with his research into rune stones.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 10, text = "I took the research package to Varrock and delivered it".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 11, text = "Aubury was interested in the research package and gave".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 12, text = "me his own research notes to deliver to Sedridor.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 13, text = "I brought Sedridor the research notes that Aubury had".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 14, text = "compiled so that he could compare their research. They".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 15, text = "discovered that it was now possible to create new rune".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 16, text = "stones, a skill that had been thought lost forever.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 17, text = "In return for all of my help they taught me how to do this,".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 18, text = "and will teleport me to mine blank runes anytime".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 19, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 20, text = "QUEST COMPLETE!".red())
        for (x in 21..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
    }

    fun Player.completeQuest(p: Player) {

    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}