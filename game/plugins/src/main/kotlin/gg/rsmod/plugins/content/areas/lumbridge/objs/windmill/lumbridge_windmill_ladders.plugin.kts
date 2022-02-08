package gg.rsmod.plugins.content.areas.lumbridge.objs.windmill

on_obj_option(12964, "Climb-up") {//ground floor
    player.climbUp()
}
on_obj_option(12966, "Climb-down") {//top floor
    player.climbDown()
}
on_obj_option(12965, "Climb-up") {//2nd floor
    player.climbUp()
}
on_obj_option(12965, "Climb-down") {//2nd floor
    player.climbDown()
}
on_obj_option(12965, "Climb") {//2nd floor
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