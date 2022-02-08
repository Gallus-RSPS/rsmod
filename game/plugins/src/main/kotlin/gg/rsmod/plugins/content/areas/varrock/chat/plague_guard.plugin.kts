package gg.rsmod.plugins.content.areas.varrock.chat

on_npc_option(npc = 1147, option = "talk-to") {
    player.queue { dialog(this) }
}

suspend fun dialog(it: QueueTask) {
    it.chatNpc("Please don't disturb me, I've got to keep any eye out for suspicious individuals.")
}