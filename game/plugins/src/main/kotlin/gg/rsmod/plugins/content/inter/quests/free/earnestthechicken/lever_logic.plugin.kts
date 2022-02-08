package gg.rsmod.plugins.content.inter.quests.free.earnestthechicken

spawn_item(Items.OIL_CAN, 1, 3093, 9755, 0, 10)

//                      1    2    3    4    5    6    7    8    9
val DOOR = intArrayOf(0,1801,1796,1802,1797,1800,1795,1794,1799,1798)
val DOOR_TILE = setOf(Tile(3108,9758), Tile(3105,9760), Tile(3102,9758),
        Tile(3100,9760), Tile(3097,9763), Tile(3100,9765), Tile(3105,9765),
        Tile(3102,9763), Tile(3100,9755))
val OPEN = 1
val CLOSED = 0
//                             A     B     C     D     E     F
val LEVER_UP_ID = intArrayOf(11451,11453,11455,11457,11459,11461)
val LEVER_DOWN_ID = intArrayOf(11452,11454,11456,11458,11460,11462)
//                          A     B    C    D    E    F
val LEVER_VAR = intArrayOf(1788,1789,1790,1791,1792,1793)
val LEVER_LETTER = arrayOf("A", "B", "C", "D", "E", "F")
val UP = 0
val DOWN = 1


var leverVal = 0
val DOOR_ID = 11450


on_obj_option(DOOR_ID, "Open") {
    var clickedDoor = player.getInteractingGameObj()
    when(clickedDoor.tile) {
        DOOR_TILE.elementAt(0) -> player.passDoor(player, 1, clickedDoor.tile)
        DOOR_TILE.elementAt(1) -> player.passDoor(player, 2, clickedDoor.tile)
        DOOR_TILE.elementAt(2) -> player.passDoor(player, 1, clickedDoor.tile)
        DOOR_TILE.elementAt(3) -> player.passDoor(player, 2, clickedDoor.tile)
        DOOR_TILE.elementAt(4) -> player.passDoor(player, 1, clickedDoor.tile)
        DOOR_TILE.elementAt(5) -> player.passDoor(player, 2, clickedDoor.tile)
        DOOR_TILE.elementAt(6) -> player.passDoor(player, 2, clickedDoor.tile)
        DOOR_TILE.elementAt(7) -> player.passDoor(player, 1, clickedDoor.tile)
        DOOR_TILE.elementAt(8) -> player.passDoor(player, 2, clickedDoor.tile)
    }
}

fun Player.passDoor(p: Player, direction: Int, tile: Tile) { // 1 = Up/Down || 2 = Left/Right
    when(direction) {
        1 -> {if(p.tile.equals(Tile(tile.x, tile.z + 1))){p.moveTo(p.tile.x, p.tile.z - 2)}else if(p.tile.equals(Tile(tile.x, tile.z - 1))){p.moveTo(p.tile.x, p.tile.z + 2)}}
        2 -> {if(p.tile.equals(Tile(tile.x + 1, tile.z))){p.moveTo(p.tile.x - 2, p.tile.z)}else if(p.tile.equals(Tile(tile.x - 1, tile.z))){p.moveTo(p.tile.x + 2, p.tile.z)}}
    }
}
LEVER_DOWN_ID.forEach { lever ->
    on_obj_option(lever, "Pull") {
        for (x in 0..5) {
            if(LEVER_DOWN_ID[x].equals(lever))
                leverVal = x
        }
        player.pullLever(player, leverVal)
    }
}

LEVER_UP_ID.forEach { lever ->
    on_obj_option(lever, "Pull") {
        for (x in 0..5) {
            if (LEVER_UP_ID[x].equals(lever))
                leverVal = x
        }
        player.pullLever(player, leverVal)
    }
}

fun Player.pullLever(p: Player, lever: Int) {
    queue{
        var leverState = p.getVarbit(LEVER_VAR[lever])
        player.animate(834)
        wait(1)
        when (p.getVarbit(LEVER_VAR[lever])){
            0 -> {
                p.setVarbit(LEVER_VAR[lever], 1)
                p.message("You pull lever ${LEVER_LETTER[lever]} down.")
            }
            1 -> {
                p.setVarbit(LEVER_VAR[lever], 0)
                p.message("You pull lever ${LEVER_LETTER[lever]} up.")
            }
        }
        p.message("You hear a clunk.")
        p.checkDoors(p)
    }
}

fun Player.openDoor(p: Player, door: Int) {
    p.setVarbit(DOOR[door], OPEN)
}
fun Player.closeDoor(p: Player, door: Int) {
    p.setVarbit(DOOR[door], CLOSED)
}

fun Player.checkDoors(p: Player) {
    val leverA = p.getVarbit(LEVER_VAR[0])
    val leverB = p.getVarbit(LEVER_VAR[1])
    val leverC = p.getVarbit(LEVER_VAR[2])
    val leverD = p.getVarbit(LEVER_VAR[3])
    val leverE = p.getVarbit(LEVER_VAR[4])
    val leverF = p.getVarbit(LEVER_VAR[5])

    //Door 2 open
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(UP) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 2)
    }
    //All Doors Shut
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(UP) && leverE.equals(UP) && leverF.equals(UP) ||
            leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(DOWN) && leverD.equals(UP) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
    }
    //Doors 1 and 2 open
    if(leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(UP) && leverD.equals(UP) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 1)
        p.openDoor(p, 2)
    }
    //Doors 3 and 4 open
    if(leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP) ||
            leverA.equals(UP) && leverB.equals(DOWN) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 4)
    }
    //Doors 3 and 4 open
    if(leverA.equals(UP) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(DOWN) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 6)
        p.openDoor(p, 7)
    }
    //Doors 1, 2, 3, 4 open
    if(leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 1)
        p.openDoor(p, 2)
        p.openDoor(p, 3)
        p.openDoor(p, 4)
    }
    //Doors 2, 3, 4, 5 open
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 2)
        p.openDoor(p, 3)
        p.openDoor(p, 4)
        p.openDoor(p, 5)
    }
    //Doors 3, 4, 5 open
    if(leverA.equals(UP) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP) ||
            leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP) ||
            leverA.equals(UP) && leverB.equals(UP) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 4)
        p.openDoor(p, 5)
    }
    //Doors 5 open
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(DOWN) && leverF.equals(UP) ||
            leverA.equals(UP) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(DOWN) && leverF.equals(UP) ||
            leverA.equals(UP) && leverB.equals(UP) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(DOWN) && leverF.equals(UP)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 5)
    }
    //Doors 6 open
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(DOWN) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 6)
    }
    //Doors 7 open
    if(leverA.equals(UP) && leverB.equals(UP) && leverC.equals(DOWN) && leverD.equals(UP) && leverE.equals(DOWN) && leverF.equals(DOWN) ||
            leverA.equals(UP) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(UP) && leverE.equals(DOWN) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 7)
    }
    //Doors 3, 6, 8 open
    if(leverA.equals(DOWN) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN) ||
            leverA.equals(UP) && leverB.equals(UP) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 6)
        p.openDoor(p, 8)
    }
    //Doors 3, 8 open
    if(leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN) ||
            leverA.equals(UP) && leverB.equals(DOWN) && leverC.equals(UP) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 8)
    }
    //Doors 3, 8, 9 open
    if(leverA.equals(DOWN) && leverB.equals(DOWN) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN) ||
            leverA.equals(UP) && leverB.equals(DOWN) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 8)
        p.openDoor(p, 9)
    }
    //Doors 3, 6, 8, 9 open
    if(leverA.equals(UP) && leverB.equals(UP) && leverC.equals(DOWN) && leverD.equals(DOWN) && leverE.equals(UP) && leverF.equals(DOWN)) {
        for (x in 1..9)
            p.closeDoor(p, x)
        p.openDoor(p, 3)
        p.openDoor(p, 6)
        p.openDoor(p, 8)
        p.openDoor(p, 9)
    }
}