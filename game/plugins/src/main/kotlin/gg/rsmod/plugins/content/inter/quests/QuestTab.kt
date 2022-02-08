package gg.rsmod.plugins.content.inter.quests

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.ext.getVarbit
import gg.rsmod.plugins.api.ext.getVarp
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.content.inter.quests.free.cooksassistant.CooksAssistant
import gg.rsmod.plugins.content.inter.quests.free.earnestthechicken.ErnestTheChicken
import gg.rsmod.plugins.content.inter.quests.free.restlessghost.TheRestlessGhost
import gg.rsmod.plugins.content.inter.quests.free.runemysteries.RuneMysteries
import gg.rsmod.plugins.content.inter.quests.free.sheepshearer.SheepShearer
import gg.rsmod.plugins.content.inter.quests.members.giantdwarf.GiantDwarf

/**
 * @author Alan Simeon
 */
object QuestTab {
    const val COMPONENT_ID = 399  // Quest Tab Component ID
    const val JOURNAL_ID = 119  // Journal Component ID
    const val QUEST_TITLE = 2   // Quest Title ID
    const val REWARD_ID = 277   // Quest Reward ID
    const val QP_VARP = 101     // Quest Points Varp ID

    /**
     * Opens Quest Log for Free to Play Quests
     */
    fun openFreeQuestLog(p: Player, quest: Quest) {
        p.message("Quest Slot: " + quest.slot)
        when (quest.slot) {
            1 -> CooksAssistant.openJornal(p, p.getVarp(quest.varp))
            6 -> ErnestTheChicken.openJornal(p, p.getVarp(quest.varp))
            13 -> TheRestlessGhost.openJornal(p, p.getVarp(quest.varp))
            15 -> RuneMysteries.openJornal(p, p.getVarp(quest.varp))
            16 -> SheepShearer.openJornal(p, p.getVarp(quest.varp))
            else -> p.message("This quest has not yet been added.")
        }
    }

    /**
     * Opens Quest Log for Members Quests
     */
    fun openMembersQuestLog(p: Player, quest: Quest) {
        fun openMembersQuestLog(p: Player, quest: Quest) {
            p.message("Quest Slot: " + quest.slot)
            when (quest.slot) {
                41 -> GiantDwarf.openJornal(p, p.getVarbit(quest.varbit))
                else -> p.message("This quest has not yet been added.")
            }
        }
    }
}