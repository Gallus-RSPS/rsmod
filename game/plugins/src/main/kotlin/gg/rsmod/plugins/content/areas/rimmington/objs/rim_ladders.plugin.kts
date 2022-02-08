package gg.rsmod.plugins.content.areas.rimmington.objs

on_obj_option(9558, "Climb-up") {
    player.queue {
        player.animate(828)
        wait(2)
        player.moveTo(player.tile.x, player.tile.z, player.tile.height+1)
    }
}
on_obj_option(9559, "Climb-down") {
    player.queue {
        player.animate(827)
        wait(2)
        player.moveTo(player.tile.x, player.tile.z, player.tile.height-1)
    }
}