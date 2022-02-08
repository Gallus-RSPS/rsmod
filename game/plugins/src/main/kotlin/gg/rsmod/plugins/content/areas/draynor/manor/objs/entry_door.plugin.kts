package gg.rsmod.plugins.content.areas.draynor.manor.objs

import gg.rsmod.game.model.attr.NO_CLIP_ATTR

val OPEN_DOOR_1 = DynamicObject(135, 0, 2, Tile(3109, 3354, 0))
val OPEN_DOOR_2 = DynamicObject(134, 0, 0, Tile(3108, 3354, 0))
val CLOSE_DOOR_1 = StaticObject(135, 0, 0, Tile(3109, 3353, 0))
val CLOSE_DOOR_2 = StaticObject(134, 0, 0, Tile(3108, 3353, 0))
val CLOSE_DOOR_3 = DynamicObject(135, 0, 1, Tile(3109, 3353, 0))
val CLOSE_DOOR_4 = DynamicObject(134, 0, 1, Tile(3108, 3353, 0))
val INVISIBLE_DOOR_1 = DynamicObject(83, 0, 1, Tile(3109, 3353, 0))
val INVISIBLE_DOOR_2 = DynamicObject(83, 0, 1, Tile(3108, 3353, 0))

val CHAIR_SPAWN_TILE = Tile(3122,3357,0)

val ENTRY_DOORS = listOf (135,134)

val ENTRY_SFX = listOf (46,45)

ENTRY_DOORS.forEach { door ->
    on_obj_option(door, "Open") {
        if (player.tile.z >= 3354){
            player.message("The doors won't open.")
            return@on_obj_option
        } else {
            player.openEntryDoor()
        }
    }
}

fun Player.openEntryDoor() {
    queue {
        val FOLLOW_CHAIR = Npc(player, 3567, CHAIR_SPAWN_TILE, world)
//        if (!FOLLOW_CHAIR.isSpawned()) {
//            world.spawn(FOLLOW_CHAIR)
//        }
        player.lock()
        world.remove(CLOSE_DOOR_1)
        world.remove(CLOSE_DOOR_2)
        world.remove(CLOSE_DOOR_3)
        world.remove(CLOSE_DOOR_4)
        world.spawn(OPEN_DOOR_1)
        world.spawn(OPEN_DOOR_2)
        world.spawn(INVISIBLE_DOOR_1)
        world.spawn(INVISIBLE_DOOR_2)
        player.moveTo(player.tile.x, player.tile.z + 1)
        wait(2)
        world.remove(OPEN_DOOR_1)
        world.remove(OPEN_DOOR_2)
        world.remove(INVISIBLE_DOOR_1)
        world.remove(INVISIBLE_DOOR_2)
        world.spawn(CLOSE_DOOR_3)
        world.spawn(CLOSE_DOOR_4)
        player.playSound(ENTRY_SFX[0])
        player.playSound(ENTRY_SFX[1])
        player.message("The doors slam shut behind you.")
        player.faceTile(Tile(player.tile.x, player.tile.z - 1))
        player.unlock()
    }
}