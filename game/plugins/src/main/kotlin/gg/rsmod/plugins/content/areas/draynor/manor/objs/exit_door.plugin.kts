package gg.rsmod.plugins.content.areas.draynor.manor.objs

val OPEN_DOOR_1 = DynamicObject(11448, 0, 0, Tile(3123, 3360, 0))
val CLOSE_DOOR_1 = DynamicObject(136, 0, 3, Tile(3123, 3361, 0))
val CLOSE_DOOR_2 = StaticObject(136, 0, 3, Tile(3123, 3361, 0))
val INVISIBLE_DOOR_1 = DynamicObject(83, 0, 1, Tile(3123, 3361, 0))

val SFX = 56

on_obj_option(136, "Open") {
    if (player.tile.z >= 3361){
        player.enterHouse()
    } else {
        player.exitHouse()
    }
}

fun Player.enterHouse() {
    queue {
        player.lock()
        world.remove(CLOSE_DOOR_1)
        world.remove(CLOSE_DOOR_2)
        world.spawn(OPEN_DOOR_1)
        world.spawn(INVISIBLE_DOOR_1)
        player.playSound(SFX)
        player.moveTo(player.tile.x, player.tile.z - 1)
        player.faceTile(Tile(3123, 3361, 0))
        wait(3)
        world.remove(OPEN_DOOR_1)
        world.remove(INVISIBLE_DOOR_1)
        world.spawn(CLOSE_DOOR_1)
        player.unlock()
    }
}

fun Player.exitHouse() {
    queue {
        player.lock()
        world.remove(CLOSE_DOOR_1)
        world.remove(CLOSE_DOOR_2)
        world.spawn(OPEN_DOOR_1)
        world.spawn(INVISIBLE_DOOR_1)
        player.playSound(SFX)
        player.moveTo(player.tile.x, player.tile.z + 1)
        wait(3)
        world.remove(OPEN_DOOR_1)
        world.remove(INVISIBLE_DOOR_1)
        world.spawn(CLOSE_DOOR_1)
        player.unlock()
    }
}