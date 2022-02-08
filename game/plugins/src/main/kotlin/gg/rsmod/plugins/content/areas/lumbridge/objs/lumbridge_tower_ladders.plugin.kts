package gg.rsmod.plugins.content.areas.lumbridge.objs

on_obj_option(16684, "Climb-up") {
    player.climbUp()
}
on_obj_option(16684, "Climb-down") {
    player.climbDown()
}
on_obj_option(16683, "Climb-up") {
    player.climbUp()
}
on_obj_option(16679, "Climb-down") {
    player.climbDown()
}
on_obj_option(16684, "Climb") {
    player.queue {
        dialog(this)
    }
}

suspend fun dialog(it: QueueTask) {
    when (options(it)) {
        1 -> it.player.climbUp()
        2 -> it.player.climbDown()
    }
}

suspend fun options(it: QueueTask): Int = it.options("Climb up.", "Climb down.")

fun Player.climbDown() {
    queue {
        player.animate(827)
        wait(2)
        player.moveTo(player.tile.x, player.tile.z, player.tile.height - 1)
    }
}
fun Player.climbUp() {
    queue {
        player.animate(828)
        wait(2)
        player.moveTo(player.tile.x, player.tile.z, player.tile.height + 1)
    }
}