package gg.rsmod.plugins.content.areas.varrock.npcs

val ATTACK_TIMER = TimerKey()
val SHOUT_TIMER = TimerKey()

val attackAnim = 6489

val CAPTAIN_QUOTES = arrayOf<String>(
    "Just hit the thing already!",
    "Hit the dummy before I come over there and hit you!",
    "Show some backbone!",
    "Let's get some proper bloodlust here!",
    "Make those dummies suffer!"
)

on_npc_spawn(npc = 3012) {
    val npc = npc
    npc.timers[ATTACK_TIMER] = world.random(15..25)
}
on_npc_spawn(npc = 3013) {
    val npc = npc
    npc.timers[SHOUT_TIMER] = world.random(9..12)
}

on_timer(ATTACK_TIMER) {
    val npc = npc
    npc.animate(attackAnim)
    npc.timers[ATTACK_TIMER] = world.random(5..10)
}

on_timer(SHOUT_TIMER) {
    val npc = npc
    when (world.random(4)) {
        0 -> {
            npc.forceChat(CAPTAIN_QUOTES[0])
        }
        1 -> {
            npc.forceChat(CAPTAIN_QUOTES[1])
        }
        2 -> {
            npc.forceChat(CAPTAIN_QUOTES[2])
        }
        3 -> {
            npc.forceChat(CAPTAIN_QUOTES[3])
        }
        4 -> {
            npc.forceChat(CAPTAIN_QUOTES[4])
        }
    }
    npc.timers[SHOUT_TIMER] = world.random(9..12)
}