package gg.rsmod.plugins.content.areas.draynor.manor.objs

val OPEN_BK1 = DynamicObject(Objs.BOOKCASE_157, 10, 0, Tile(3097, 3360, 0))
val OPEN_BK2 = DynamicObject(Objs.BOOKCASE_157, 10, 0, Tile(3097, 3357, 0))
val CLOSE_BK1 = DynamicObject(Objs.BOOKCASE_155, 10, 0, Tile(3097, 3358, 0))
val CLOSE_BK2 = DynamicObject(Objs.BOOKCASE_156, 10, 0, Tile(3097, 3359, 0))

on_obj_option(Objs.LEVER_160, "pull") {
    player.queue {
        player.lock()
        wait(1)
        player.animate(834)
        player.message("The lever opens the secret door!")
        wait(1)
        player.moveTo(3096,3358)
        wait(2)
        world.remove(CLOSE_BK1)
        world.remove(CLOSE_BK2)
        world.spawn(OPEN_BK1)
        world.spawn(OPEN_BK2)
        player.moveTo(3098, 3358, 0)
        wait(2)
        player.unlock()
        world.remove(OPEN_BK1)
        world.remove(OPEN_BK2)
        world.spawn(CLOSE_BK1)
        world.spawn(CLOSE_BK2)
    }
}

on_obj_option(Objs.BOOKCASE_155, "Search") {
    player.queue {
        player.lock()
        player.message("You've found a secret door!")
        world.remove(CLOSE_BK1)
        world.remove(CLOSE_BK2)
        world.spawn(OPEN_BK1)
        world.spawn(OPEN_BK2)
        player.moveTo(3096, 3358, 0)
        wait(3)
        world.remove(OPEN_BK1)
        world.remove(OPEN_BK2)
        world.spawn(CLOSE_BK1)
        world.spawn(CLOSE_BK2)
        wait(1)
        player.faceTile(Tile(3097,3358))
        player.unlock()
    }
}

on_obj_option(Objs.BOOKCASE_156, "Search") {
    player.queue {
        player.lock()
        player.message("You've found a secret door!")
        world.remove(CLOSE_BK1)
        world.remove(CLOSE_BK2)
        world.spawn(OPEN_BK1)
        world.spawn(OPEN_BK2)
        player.moveTo(3096, 3359, 0)
        wait(3)
        world.remove(OPEN_BK1)
        world.remove(OPEN_BK2)
        world.spawn(CLOSE_BK1)
        world.spawn(CLOSE_BK2)
        wait(1)
        player.faceTile(Tile(3097,3359))
        player.unlock()
    }
}