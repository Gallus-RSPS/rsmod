package gg.rsmod.plugins.content.areas.lumbridge.objs

private val ALTARS = setOf(
        Objs.ALTAR_409)

ALTARS.forEach { altar ->
    on_obj_option(obj = altar, option = "Pray-at") {
        player.prayAltar()
    }
}

fun Player.prayAltar() {
    queue {
        player.lock()
        player.animate(645)
        player.message("You recharge your Prayer points.")
        player.getSkills().restore(Skills.PRAYER)
        wait(4)
        player.unlock()
    }
}