package gg.rsmod.plugins.content.inter.quests

/**
 * @author Alan Simeon
 */

/**
 * on_login handles setting interface buttons clickable
 */
on_login {
    player.setInterfaceEvents(interfaceId = 399, component = 6, range = 0 until 21, setting = 6)
    player.setInterfaceEvents(interfaceId = 399, component = 7, range = 0 until 188, setting = 6)
    player.setInterfaceEvents(interfaceId = 399, component = 8, range = 0 until 20, setting = 6)
}

/**
 * Free To Play Quests
 *
 * on_button handles what happens when a player clicks on a
 * specific interface element within the Quest Tab
 *
 * @param slot : The Slot ID for the clicked on Quest in the Quest Tab
 * @param quest : The data for the Quest that was clicked on
 */
on_button(interfaceId = QuestTab.COMPONENT_ID, component = 6) p@ {
    val slot = player.getInteractingSlot()
    val quest = Quest.values.firstOrNull { selectedQuest -> selectedQuest.slot == slot } ?: return@p
    QuestTab.openFreeQuestLog(player, quest)
}

/**
 * Members Quests
 *
 * on_button handles what happens when a player clicks on a
 * specific interface element within the Quest Tab
 *
 * @param slot : The Slot ID for the clicked on Quest in the Quest Tab
 * @param quest : The data for the Quest that was clicked on
 */
on_button(interfaceId = QuestTab.COMPONENT_ID, component = 7) p@ {
    val slot = player.getInteractingSlot()
    val quest = Quest.values.firstOrNull { selectedQuest -> selectedQuest.slot == slot } ?: return@p
    QuestTab.openMembersQuestLog(player, quest)
}