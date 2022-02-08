package gg.rsmod.plugins.content.objs.shortcuts

import gg.rsmod.plugins.content.mechanics.run.RunEnergy

val VARROCK_FENCE = 16518
val RUNNING_ANIMATION = 1995
val JUMPING_ANIMATION = 1603

on_obj_option(obj = VARROCK_FENCE, option = "Jump-over") {
    val START_TILE_SOUTH = Tile(3240, 3332, 0)
    val START_TILE_NORTH = Tile(3240, 3337, 0)
    val FENCE_TILE_NORTH = Tile(3240, 3335, 0)
    val FENCE_TILE_SOUTH = Tile(3240, 3334, 0)
    val JUMP_TILE_SOUTH = Tile(3240, 3333, 0)
    val JUMP_TILE_NORTH = Tile(3240, 3336, 0)
    var directionAngle: Int
    lateinit var movement: ForcedMovement
    lateinit var movement2: ForcedMovement
    player.queue{
        player.lock()
        if (player.tile.z > 3334) {
            player.moveTo(START_TILE_NORTH)
            directionAngle = Direction.SOUTH.angle
            movement = ForcedMovement.of(player.tile, FENCE_TILE_NORTH, clientDuration1 = 2, clientDuration2 = 40, directionAngle = directionAngle)
            movement2 = ForcedMovement.of(JUMP_TILE_NORTH, FENCE_TILE_SOUTH, clientDuration1 = 0, clientDuration2 = 45, directionAngle = directionAngle)
        } else {
            player.moveTo(START_TILE_SOUTH)
            directionAngle = Direction.NORTH.angle
            movement = ForcedMovement.of(player.tile, FENCE_TILE_SOUTH, clientDuration1 = 2, clientDuration2 = 40, directionAngle = directionAngle)
            movement2 = ForcedMovement.of(JUMP_TILE_SOUTH, FENCE_TILE_NORTH, clientDuration1 = 0, clientDuration2 = 45, directionAngle = directionAngle)
        }
        this.wait(2)
        player.faceTile(FENCE_TILE_SOUTH)
        this.wait(4)
        player.animate(RUNNING_ANIMATION)
        player.forceMove(this, movement)
        player.animate(JUMPING_ANIMATION)
        player.forceMove(this, movement2)
        player.unlock()
    }
}