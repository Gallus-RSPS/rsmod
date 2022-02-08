package gg.rsmod.plugins.content.areas.draynor.manor.objs

on_obj_option(15645, "Climb-up") {
    player.queue {
        player.moveTo(3102, 3267, 1)
        player.faceTile(Tile(player.tile.x-1, player.tile.z))
    }
}
on_obj_option(15648, "Climb-down") {
    player.queue {
        player.moveTo(3098, 3267, 0)
        player.faceTile(Tile(player.tile.x+1, player.tile.z))
    }
}

on_obj_option(2616, "Walk-down") {
    player.message("This area is not yet added.")
}

on_obj_option(16670, "Climb-Up") {
    player.queue {
        player.moveTo(3093, 3251, 1)
        player.faceTile(Tile(player.tile.x-1, player.tile.z))
    }
}

on_obj_option(16669, "Climb-down") {
    player.queue {
        player.moveTo(3089, 3251, 0)
        player.faceTile(Tile(player.tile.x+1, player.tile.z))
    }
}