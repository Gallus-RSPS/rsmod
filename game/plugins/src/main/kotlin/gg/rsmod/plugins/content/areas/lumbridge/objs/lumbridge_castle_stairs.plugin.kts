package gg.rsmod.plugins.content.areas.lumbridge.objs

on_obj_option(16671, "Climb-up") {
    player.climbUp(2)
}

on_obj_option(16672, "Climb-up") {
    player.climbUp(3)
}
on_obj_option(16672, "Climb-down") {
    player.climbDown(1)
}

on_obj_option(16673, "Climb-down") {
    player.climbDown(2)
}

on_obj_option(16672, "Climb") {
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
        if (player.tile.z < 3218) {
            when(floor) {
                1 -> player.moveTo(3206, 3208, 0)
                2 -> player.moveTo(3206,3208,1)
            }
        } else if (player.tile.z > 3218) {
            when(floor) {
                1 -> player.moveTo(3205, 3228, 0)
                2 -> player.moveTo(3205,3228,1)
            }
        }
    }
}
fun Player.climbUp(floor: Int) {

    queue {

        if (player.tile.z < 3218) {
            when(floor) {
                2 -> player.moveTo(3205,3209,1)
                3 -> player.moveTo(3205, 3209, 2)
            }
        } else if (player.tile.z > 3218) {
            when(floor) {
                2 -> player.moveTo(3206,3229,1)
                3 -> player.moveTo(3206, 3229, 2)
            }
        }
    }
}