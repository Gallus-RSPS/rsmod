package gg.rsmod.plugins.content.areas.waterbirth

val CAVE_ENTRANCE = 8929
val CAVE_EXIT = 8966
val ENTER_TILE = Tile(2442,10146,0)
val EXIT_TILE = Tile(2523,3739,0)

on_obj_option(CAVE_ENTRANCE, option = "Enter") {
    player.moveTo(ENTER_TILE)
}
on_obj_option(CAVE_EXIT, option = "Climb") {
    player.moveTo(EXIT_TILE)
}