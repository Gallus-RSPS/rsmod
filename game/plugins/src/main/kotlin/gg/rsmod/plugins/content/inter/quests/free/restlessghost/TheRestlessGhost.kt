package gg.rsmod.plugins.content.inter.quests.free.restlessghost

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.ext.openInterface
import gg.rsmod.plugins.api.ext.setComponentText
import gg.rsmod.plugins.content.inter.quests.QuestTab
import gg.rsmod.plugins.content.inter.quests.free.earnestthechicken.nextLine


object TheRestlessGhost {
    fun openJornal(p: Player, varp: Int) {
        when(varp) {
            0 -> p.notStarted(p)
            1 -> p.started(p)
            2 -> p.startedTwo(p)
            3 -> p.startedThree(p)
            4 -> {
                if (p.inventory.contains(553)) {
                    p.startedFourTwo(p)
                } else {
                    p.startedFourOne(p)
                }
            }
            5 -> p.finished(p)
        }
        p.openInterface(QuestTab.JOURNAL_ID, InterfaceDestination.MAIN_SCREEN)
    }

    fun Player.notStarted(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I can start this quest by speaking to ".blue() + "Father Aereck ".red() + "in the".blue())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "church ".red() + "next to ".blue() + "Lumbridge Castle.".red())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I must be able to defeat or run away from a ".blue() + "Level 13".red())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Skeleton.".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.started(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should find ".blue() + "Father Urhney ".red() + "who is an expert on ".blue() + "ghosts.".red())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "He lives in a ".blue() + "shack ".red() + "in the far west of ".blue() + "Lumbridge Swamp.".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.startedTwo(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found Father Urhney in the far west of the swamp south".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge. He gave me an Amulet of Ghostspeak to talk".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to the ghost.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should talk to the ".blue() + "Ghost ".red() + "in the graveyard next to the".blue())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "church in Lumbridge to find out why it is haunting the".blue())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "graveyard crypt.".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.startedThree(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found Father Urhney in the far west of the swamp south".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge. He gave me an Amulet of Ghostspeak to talk".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to the ghost.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I spoke to the Ghost and he told me he could not rest in".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "peace because an evil wizard had stolen his skull.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should go and search the ".blue() + "Wizards' Tower South West of".red())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Lumbridge ".red() + "for the ".blue() + "Ghost's Skull.".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.startedFourOne(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found Father Urhney in the far west of the swamp south".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge. He gave me an Amulet of Ghostspeak to talk".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to the ghost.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I spoke to the Ghost and he told me he could not rest in".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "peace because an evil wizard had stolen his skull.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should go and search the ".blue() + "Wizards' Tower South West of".red())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Lumbridge ".red() + "for the ".blue() + "Ghost's Skull.".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.startedFourTwo(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found Father Urhney in the far west of the swamp south".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge. He gave me an Amulet of Ghostspeak to talk".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to the ghost.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I spoke to the Ghost and he told me he could not rest in".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "peace because an evil wizard had stolen his skull.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found the Ghost's Skull in the basement of the Wizards'".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Tower. It was guarded by a skeleton, but I took it anyway.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I should take the ".blue() + "Skull ".red() + "back to the ".blue() + "Ghost ".red() + "coffin so it can".blue())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "rest.".blue())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun Player.finished(p: Player) {
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = QuestTab.QUEST_TITLE, text = "The Restless Ghost".red())
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Father Aereck asked me to help him deal with the Ghost in".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "the graveyard next to the church.".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found Father Urhney in the far west of the swamp south".strike())
        nextLine +=1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "of Lumbridge. He gave me an Amulet of Ghostspeak to talk".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "to the ghost.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I spoke to the Ghost and he told me he could not rest in".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "peace because an evil wizard had stolen his skull.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I found the Ghost's Skull in the basement of the Wizards'".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "Tower. It was guarded by a skeleton, but I took it anyway.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "I placed the Skull in the Ghost's coffin, and allowed it to".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "rest in peace once more, with gratitude for my help.".strike())
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "")
        nextLine += 1
        p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = nextLine, text = "QUEST COMPLETE!".red())
        nextLine += 1
        for (x in nextLine..178) {
            p.setComponentText(interfaceId = QuestTab.JOURNAL_ID, component = x, text = "")
        }
        nextLine = 4
    }

    fun String.blue() = "<col=0845c9>$this</col>"
    fun String.red() = "<col=a61005>$this</col>"
    fun String.strike() = "<str>$this</str>"
}