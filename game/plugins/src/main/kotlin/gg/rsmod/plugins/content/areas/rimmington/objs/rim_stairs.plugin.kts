package gg.rsmod.plugins.content.areas.rimmington.objs

on_obj_option(18991, "Climb-up") {
    player.queue {
        player.moveTo(2968, 3216, 1)
        player.faceTile(Tile(player.tile.x-1, player.tile.z))
    }
}

on_obj_option(18992, "Climb-down") {
    player.queue {
        player.moveTo(2964, 3216, 0)
        player.faceTile(Tile(player.tile.x+1, player.tile.z))
    }
}