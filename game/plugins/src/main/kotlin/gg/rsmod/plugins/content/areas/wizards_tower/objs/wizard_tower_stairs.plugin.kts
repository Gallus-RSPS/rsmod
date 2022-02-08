package gg.rsmod.plugins.content.areas.wizards_tower.objs

on_obj_option(12536, "Climb-up") {
    player.climbUp(2)
}

on_obj_option(12537, "Climb-up") {
    player.climbUp(3)
}
on_obj_option(12537, "Climb-down") {
    player.climbDown(1)
}

on_obj_option(12538, "Climb-down") {
    player.climbDown(2)
}

on_obj_option(12537, "Climb") {
    player.queue {
        dialog(this)
    }
}

suspend fun dialog(it: QueueTask) {
    when (options(it)) {
        1 -> it.player.climbUp(3)
        2 -> it.player.climbDown(1)
    }
}

suspend fun options(it: QueueTask): Int = it.options("Climb up.", "Climb down.")

fun Player.climbDown(floor: Int) {
    queue {
        when(floor) {
            1 -> player.moveTo(3104, 3161, 0)
            2 -> player.moveTo(3104,3161,1)
        }
    }
}
fun Player.climbUp(floor: Int) {

    queue {
        when(floor) {
            2 -> player.moveTo(3104,3161,1)
            3 -> player.moveTo(3104, 3161, 2)
        }
    }
}