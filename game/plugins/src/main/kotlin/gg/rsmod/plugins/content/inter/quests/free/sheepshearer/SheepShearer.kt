package gg.rsmod.plugins.content.inter.quests.free.sheepshearer

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab
import kotlin.properties.Delegates

var nextLine = 4

/**
 * Varp 179
 * 0 - Not Started
 * 1 - Started 20b of wool left
 * 2-20 - Turning in balls of wool
 * 21 - Finished
 */

object SheepShearer {
    fun openJornal(p: Player, varp: Int) {
        when(varp) {
            0 -> p.notStarted(p)
            21 -> p.finished(p)
            in 1..20 -> p.started(p, varp)
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun Player.notStarted(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Sheep Shearer".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I can start this quest by speaking to  ".blue() + "Farmer Fred ".red() + "at his".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "farm ".red() + "just a little way ".blue() +  "North West of Lumbridge.".red())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "This quest has no requirements ".blue())
        nextLine +=1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.started(p: Player, varp: Int) {
        var ballsLeft = 0

        when(varp) {
            1 -> ballsLeft = 20 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            2 -> ballsLeft = 19 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            3 -> ballsLeft = 18 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            4 -> ballsLeft = 17 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            5 -> ballsLeft = 16 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            6 -> ballsLeft = 15 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            7 -> ballsLeft = 14 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            8 -> ballsLeft = 13 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            9 -> ballsLeft = 12 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            10 -> ballsLeft = 11 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            11 -> ballsLeft = 10 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            12 -> ballsLeft = 9 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            13 -> ballsLeft = 8 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            14 -> ballsLeft = 7 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            15 -> ballsLeft = 6 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            16 -> ballsLeft = 5 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            17 -> ballsLeft = 4 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            18 -> ballsLeft = 3 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            19 -> ballsLeft = 2 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
            20 -> ballsLeft = 1 - p.inventory.getItemCount(Items.BALL_OF_WOOL)
        }

        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Sheep Shearer".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I asked Farmer Fred, near Lumbridge, for a quest. Fred".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "said he'd pay me for shearing his sheep for him!".strike())
        nextLine +=1
        if (ballsLeft < 1) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Fred gave me some shears to use, to get the job done.".strike())
        } else {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Fred gave me some shears to use, to get the job done.".blue())
        }
        nextLine +=1
        if (ballsLeft > 1) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text ="Farmer Fred ".red() + "said there was a ".blue() + "spinning wheel ".red() + "in ".blue() + "Lumbridge".red())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "castle ".red() + "I could use to make the balls of wool.".blue())
            nextLine +=1
        }
        if (ballsLeft < 1) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I have enough ".blue() +"balls of wool ".red() + "to give ".blue() + "Fred ".red() + "and get my ".blue() + "reward".red())
            nextLine +=1
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "money!".red())
        } else {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I need to collect ${ballsLeft} more ".blue() +"balls of wool.".red())
        }
        nextLine +=1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.finished(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "Sheep Shearer".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 4, text = "I brought Farmer Fred 20 balls of wool, and he paid me for".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 5, text = "it!".strike())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 6, text = "")
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = 7, text = "QUEST COMPLETE!".red())
        for (x in 8..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
    }

    fun Player.completeQuest(p: Player) {

    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}