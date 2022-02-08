package gg.rsmod.plugins.content.areas.varrock.chat

on_npc_option(npc = Npcs.AMBASSADOR_FERRNOOK, option = "talk-to") {
    player.queue { dialog(this) }
}

suspend fun dialog(it: QueueTask) {
    it.chatPlayer("Hello Ambassador. Are you here visiting King Roald?")
    it.chatNpc("Well, in theory, but he always seems to be busy.")
    it.chatPlayer("You don't seem that upset by that, though...")
    it.chatNpc("Oh no, I like travelling, and if you become a diplomat patience is a vital skill.")
    it.chatPlayer("I'll try to remember that.")
}