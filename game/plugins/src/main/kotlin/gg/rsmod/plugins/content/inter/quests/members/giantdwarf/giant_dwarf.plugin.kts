package gg.rsmod.plugins.content.inter.quests.members.giantdwarf

import gg.rsmod.plugins.content.inter.quests.QuestTab

spawn_npc(4626, 3208, 3215, 0, 3)

val TGD_VARBIT = 571
val BOATMAN = 2433
val MILK = Items.BUCKET_OF_MILK
val FLOUR = Items.POT_OF_FLOUR
val EGG = Items.EGG

on_npc_option(npc = BOATMAN, option = "Talk-to"){
    player.queue {
        when(player.getVarp(TGD_VARBIT)) {
            0 -> notStartedDialog(this)

        }
    }
}

suspend fun notStartedDialog(it: QueueTask) {
    it.chatNpc("Ho there, human!")
    it.chatPlayer(it.player.username.capitalize())
    it.chatNpc("Ho there, " + it.player.username.capitalize() + "! Want to take a ride with me?")
    it.chatPlayer("Where are you going? Across the river?")
    it.chatNpc("No no, thats what the ferryman is for! I'm going to Keldagrim, my home!")
    it.chatPlayer("How much will that cost me then?")
    it.chatNpc("For a human like you, I can do it for free!")
    when (notStartedOptions(it)) {
        1 -> thatsADeal(it)
        2 -> {
            it.chatPlayer("I prefer the mines to the city.")
        }
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("That's a deal!", "I prefer the mines to the city.")

suspend fun thatsADeal(it: QueueTask) {
    it.chatPlayer("That's a deal!")
    it.chatNpc("Excellent! I'm just waiting for my ship to arrive and then we can go. Mind, this trip could take a few minutes! Are you sure you're ready to go as well?")
    when (readyToGoOption(it)) {
        1 -> yesImReady(it)
        2 -> {
            it.chatPlayer("No, I don't have time right now, I'll be back later.")
        }
    }
}
suspend fun readyToGoOption(it: QueueTask): Int = it.options("Yes, I'm ready and don't mind it taking a few minutes.", "No, I don't have time right now, I'll be back later.")

suspend fun yesImReady(it: QueueTask) {
    it.chatPlayer("Yes, I'm ready and don't mind it taking a few minutes.")
    it.chatNpc("Well, let's not waste any more time then!")

}

suspend fun questComplete(it: QueueTask) {
    val player = it.player
    var qp = player.getVarp(QuestTab.QP_VARP)
    player.setVarp(QuestTab.QP_VARP, (qp + 1))
    player.addXp(Skills.COOKING, 300.0)
    player.message("Congratulations! Quest complete!")
    player.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = Items.CAKE, amountOrZoom = 250)
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed the Cook's Assistant Quest!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${player.getVarp(QuestTab.QP_VARP)}")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "1 Quest Point")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "300 Cooking XP")
    for (x in 10..14) {
        player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = x, text = "")
    }
    player.openInterface(QuestTab.REWARD_ID, InterfaceDestination.MAIN_SCREEN)
}