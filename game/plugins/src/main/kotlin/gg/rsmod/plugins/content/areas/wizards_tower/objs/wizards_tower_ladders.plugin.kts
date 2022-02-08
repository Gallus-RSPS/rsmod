package gg.rsmod.plugins.content.areas.wizards_tower.objs


on_obj_option(2147, "Climb-down") {
    player.climbDown()
}


fun Player.climbDown() {
    queue {
        player.moveTo(3104, 9576, 0)
    }
}