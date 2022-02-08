package gg.rsmod.plugins.content.objs.shortcuts

import gg.rsmod.plugins.content.mechanics.run.RunEnergy

val STEPPING_STONE = 16533
val JUMPING_ANIMATION = 769

val LAND_TILE_WEST = Tile(3149, 3363, 0)
val LAND_TILE_EAST = Tile(3154, 3363, 0)

val STONE_EAST_ONE = Tile(3152, 3363, 0)
val STONE_EAST_TWO = Tile(2153, 3363, 0)
val STONE_WEST_ONE = Tile(3150, 3363, 0)
val STONE_WEST_TWO = Tile(3151, 3363, 0)

//on_obj_option(obj = STEPPING_STONE, option = "Jump-onto", lineOfSightDistance = 0) {
//    val STONE = player.getInteractingGameObj()
//
//    var directionAngle: Int
//
//    lateinit var movement: ForcedMovement
//
//    player.queue {
//        player.lock()
//        player.faceTile(STONE.tile)
//        if (player.tile.x > STONE.tile.x) {
//            directionAngle = Direction.WEST.angle
//            movement = ForcedMovement.of(player.tile, STONE.tile, clientDuration1 = 0, clientDuration2 = 45, directionAngle = directionAngle)
//        } else {
//            directionAngle = Direction.EAST.angle
//            movement = ForcedMovement.of(player.tile, STONE.tile, clientDuration1 = 0, clientDuration2 = 45, directionAngle = directionAngle)
//        }
//        player.animate(JUMPING_ANIMATION)
//        player.forceMove(this, movement)
//        this.wait(1)
//        player.unlock()
//    }
//}