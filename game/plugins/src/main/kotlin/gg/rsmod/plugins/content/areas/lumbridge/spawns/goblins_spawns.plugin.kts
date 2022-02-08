package gg.rsmod.plugins.content.areas.lumbridge.spawns

// Goblin Types

val GOBLINS = intArrayOf(
        Npcs.GOBLIN_3029,
        Npcs.GOBLIN_3030,
        Npcs.GOBLIN_3031,
        Npcs.GOBLIN_3032,
        Npcs.GOBLIN_3033,
        Npcs.GOBLIN_3034,
        Npcs.GOBLIN_3035,
        Npcs.GOBLIN_3036
)

// Easter goblins by Al Kharid Gate

val EAST_GOBLIN_SPAWNS = arrayOf(
        Tile(3253, 3230),
        Tile(3249, 3232),
        Tile(3255, 3237),
        Tile(3261, 3229),
        Tile(3262, 3221),
        Tile(3262, 3238),
        Tile(3259, 3245),
        Tile(3253, 3243),
        Tile(3247, 3241),
        Tile(3256, 3252),
        Tile(3257, 3245),
        Tile(3263, 3243),
        Tile(3259, 3238),
        Tile(3257, 3237)
)

val eastSpawnCount = 15

for(i in (1..eastSpawnCount)) {
    val rand = (0..(GOBLINS.size-1)).random()
    val randSpawn = (0..(EAST_GOBLIN_SPAWNS.size-1)).random()
    spawn_npc(GOBLINS[rand], EAST_GOBLIN_SPAWNS[randSpawn].x, EAST_GOBLIN_SPAWNS[randSpawn].z, 0, 8)
}


// Western goblins behind general store

val WEST_GOBLIN_SPAWNS = arrayOf(
        Tile(3201,3249),
        Tile(3205,3243),
        Tile(3199,3243),
        Tile(3196,3240),
        Tile(3190,3245),
        Tile(3190,3252),
        Tile(3181,3247),
        Tile(3187,3238)
)

val westSpawnCount = 15

for(i in (1..westSpawnCount)) {
    val rand = (0..(GOBLINS.size-1)).random()
    val randSpawn = (0..(WEST_GOBLIN_SPAWNS.size-1)).random()
    spawn_npc(GOBLINS[rand], WEST_GOBLIN_SPAWNS[randSpawn].x, WEST_GOBLIN_SPAWNS[randSpawn].z, 0, 8)
}

spawn_npc(Npcs.GOBLIN_3029, 3234, 3234, 0, 1)
spawn_npc(Npcs.GOBLIN_3031, 3237, 3228, 0, 1)

// By Sheep Pen
spawn_npc(Npcs.GOBLIN_3031, 3226, 3265, 0, 5)
spawn_npc(Npcs.GOBLIN_3031, 3214, 3274, 0, 10)