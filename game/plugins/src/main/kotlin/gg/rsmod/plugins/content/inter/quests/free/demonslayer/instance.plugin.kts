package gg.rsmod.plugins.content.inter.quests.free.demonslayer
/*

import gg.rsmod.game.model.instance.InstancedChunkSet
import gg.rsmod.game.model.instance.InstancedMapAttribute
import gg.rsmod.game.model.instance.InstancedMapConfiguration
import gg.rsmod.game.model.region.ChunkSet

val chunk = world.chunks.get(Tile(3227, 3371).chunkCoords, createIfNeeded = false).hashCode()

*/
/*on_enter_chunk(chunk) {
    player.queue {
        player.message("entered the chunk")
        generateDelrithZone(player)
    }
}*//*


*/
/*on_exit_chunk(chunk) {
    player.queue {
        player.message("exited the chunk")
    }
}*//*


fun generateDelrithZone(player: Player) {
    val startTile = Tile(3216, 3360)
    val endTile = Tile(3240, 3384)

    val instance = generateInstance(startTile, endTile)
    val instanceConfig = InstancedMapConfiguration.Builder()

    instanceConfig.setExitTile(Tile(3219, 3377, 0))
    instanceConfig.setOwner(player.uid)
    instanceConfig.addAttribute(InstancedMapAttribute.DEALLOCATE_ON_LOGOUT)
    instanceConfig.addAttribute(InstancedMapAttribute.DEALLOCATE_ON_DEATH)

    world.instanceAllocator.allocate(world, instance, instanceConfig.build())?.let { map ->
        val bottomLeft = map.area.bottomLeft
        //val target = Tile(3222,3372)
        val target = player.tile
        val entry = Tile(x = (bottomLeft.x + (target.x - startTile.x)), z = (bottomLeft.z + (target.z - startTile.z)))
        player.moveTo(entry)
    }
}

fun generateInstance(startTile: Tile, endTile: Tile): InstancedChunkSet {
    val numChunksX = (endTile.x - startTile.x) / 8
    val numChunksZ = (endTile.z - startTile.z) / 8

    val instanceChunkSet = InstancedChunkSet.Builder()

    for (z in (0..(numChunksZ - 1))) {
        for (x in (0..(numChunksX - 1))) {
            instanceChunkSet.set(chunkX = (x), chunkZ = (z), height = 0, rot = 0, copy = Tile(x = (startTile.x + (x * 8)), z = (startTile.z + (z * 8)), height = 0))
            //instanceChunkSet.set(chunkX = (x), chunkZ = (z), height = 0, rot = 0, copy = Tile(x = 6400 + (x * 8), z = 0 + (z * 8), height = 0))
        }
    }

    return instanceChunkSet.build()
}*/
