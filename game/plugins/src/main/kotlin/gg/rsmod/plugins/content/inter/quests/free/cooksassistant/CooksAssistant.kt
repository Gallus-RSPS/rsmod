package gg.rsmod.plugins.content.inter.quests.free.cooksassistant

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab

var nextLine = 4


object CooksAssistant {
    fun openJornal(p: Player, varp: Int) {
        when(varp) {
            0 -> p.notStarted(p)
            1,3,4,5,6,7,8,9 -> p.started(p, varp)
            2 -> p.finished(p)
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun Player.notStarted(p: Player) {
        p.message("Showing Cooks Assistant Journal")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Cook's Assistant".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I can start this quest by speaking to the ".blue() + "Cook ".red() + "in the".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Kitchen ".red() + "on the ground floor of ".blue() +  "Lumbridge Castle".red() + ".".blue())
        nextLine +=1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.started(p: Player, varp: Int) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Cook's Assistant".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "It's the ".blue() + "Duke of Lumbridge's".red() + " bithday and I have to help".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "his ".blue() + "Cook".red() + " make him a ".blue() + "birthday cake".red() + ". To do this I need to".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "bring him the following ingredients.".blue())
        nextLine +=1
        // gave milk | gave milk and flour | gave milk and egg | gave all 3
        if (varp == 3 || varp == 6 || varp == 7 || varp == 9){
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I have given the cook a bucket of milk.".strike())
            nextLine +=1
        } else {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I need to find a ".blue() + "bucket of milk".red() + ". There's a cattle field east".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge, I should make sure I take an empty bucket".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "with me.".blue())
            nextLine +=1
        }
        // gave flour | gave milk and flour | gave flour and egg | gave all 3
        if (varp == 4 || varp == 6 || varp == 8 || varp == 9){
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I have given the cook a pot of flour.".strike())
            nextLine +=1
        } else {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I need to find a ".blue() + "pot of flour".red() + ". There's a mill found north-".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "west of Lumbridge, I should take an empty pot with me.".blue())
            nextLine +=1
        }
        // gave egg | gave milk and egg | gave flour and egg | gave all 3
        if (varp == 5 || varp == 7 || varp == 8 || varp == 9) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I have given the cook an egg.".strike())
            nextLine +=1
        } else {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I need to find an ".blue() + "egg".red() + ". The cook normally gets his eggs from".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the Groats' farm, found just to the west of the cattle".blue())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "field.".blue())
            nextLine +=1
        }
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.finished(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Cook's Assistant".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "It was the Duke of Lumbridge's birthday, but his cook had".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "forgotten to buy the ingredients he needed to make him a".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "cake. I brought the cook an egg, some flour, and some milk".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "and the cook made a delicious looking cake with them".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 8, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 9, text = "As a reward he now lets me use his high quality range".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 10, text = "which lets me burn things less whenever I wish to cook".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 11, text = "there.".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 12, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 13, text = "QUEST COMPLETE!".red())
        for (x in 14..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
    }

    fun Player.completeQuest(p: Player) {

    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}