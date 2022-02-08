package gg.rsmod.plugins.api.ext

import gg.rsmod.game.model.Tile
import gg.rsmod.game.model.World

fun Tile.isMulti(world: World): Boolean {
    val region = regionId
    val chunk = chunkCoords.hashCode()
    return world.getMultiCombatChunks().contains(chunk) || world.getMultiCombatRegions().contains(region)
}

fun Tile.getWildernessLevel(): Int {
    if (x !in 2941..3392 || z !in 3524..3968) {
        return 0
    }

    val z = if (this.z > 6400) this.z - 6400 else this.z
    return (((z - 3525) shr 3) + 1)
}

fun Tile.inBank(): Boolean {
    if (x in 3207..3210 && z in 3215..3222 && height == 2){ //Lumby Bank
        return true
    }
    if (x in 3265..3272 && z in 3161..3173){ //Al Kharid
        return true
    }
    if (x in 3092..3097 && z in 3240..3246){ //Draynor
        return true
    }
    if (x in 3250..3257 && z in 3416..3423){ //Varrock East
        return true
    }
    if (x in 3180..3190 && z in 3433..3447){ //Varrock West
        return true
    }
    if (x in 3091..3098 && z in 3488..3499){ //Edgeville
        return true
    }
    if (x in 3009..3018 && z in 3353..3358 || x == 3019 && z == 3355){ //Fally East
        return true
    }
    if (x in 2943..2949 && z in 3368..3369 || x in 2943..2947 && z in 3370..3373){ //Fally West
        return true
    }
    if (x in 3012..3014 && z in 9716..9721){ //Dwarven Mine
        return true
    }
    return false
}