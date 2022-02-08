package gg.rsmod.plugins.content.areas.lumbridge.objs

val CHOP_TREE_RESPAWN = TimerKey("chop_tree_respawn")

// log 20, dugout 18, stable 12, waka 11
val shapeInterface = 416

val travelInterface = 647

/*
Tree varbit values
9 start of fall 10 fallen
1 log > 5 start float > 11 floated
2 dugout > 6 start float > 12 floated
3 stable dugout > 7 start float > 13 floated
4 waka > 8 start float > 14 floated
 */

val treeVarbit = 1839

// changes to 1 when shaping canoe and back to 0 once finished
val canoeShape = 1844

//unknown but changes to 1 when start to chop tree
val makingCanoe = 1846

// what canoe you made
val canoeMade = 1843

val SHAPE_TILE = Tile(3242, 3237)

val CHOP_ANIM = 2846
val SHAPE_ANIM = 3292
val FLOAT_ANIM = 3301
val PADDLE_ANIM = 3302

val TREE_CHOP = 12144
val TREE_FALLEN = 12145
val TREE_SHAPE = 12146

val CANOES = intArrayOf (0, 12147, 12148, 12149, 12150)

val CANOE_LOG = 12147
val CANOE_DUGOUT = 12148
val CANOE_STABLE = 12149
val CANOE_WAKA = 12150


on_login {
    player.setInterfaceEvents(interfaceId = shapeInterface, component = 3, range = 4 until 20, setting = 6)
}

on_button(interfaceId = shapeInterface, component = 3) p@ {
    val slot = player.getInteractingSlot()
    player.message("Slot: " + slot)
}

on_obj_option(obj = TREE_CHOP, option = "Chop-down") {
    player.queue {
        player.moveTo(3243, 3235)
        wait(2)
        player.faceTile(Tile(3242,3235,0), 1, 1)
        wait(2)
        if(player.getSkills().getCurrentLevel(Skills.WOODCUTTING) < 12) {
            player.message("You need a Woodcutting level of 12 to make a canoe.")
            return@queue
        } else {
            player.chopTree(player.getInteractingGameObj())
        }
    }
}

on_obj_option(obj = TREE_SHAPE, option = "Shape-Canoe") {
    player.queue {
        player.moveTo(3243, 3237)
        wait(2)
        player.faceTile(SHAPE_TILE, 1, 1)
        wait(3)
        player.openInterface(shapeInterface, InterfaceDestination.MAIN_SCREEN)
    }
}

fun Player.chopTree(obj: GameObject) {
    queue{
        player.setVarbit(makingCanoe, 1)
        while(true) {
            player.animate(CHOP_ANIM)
            wait(2)
            val level = player.getSkills().getCurrentLevel(Skills.WOODCUTTING)
            if (level.interpolate(minChance = 60, maxChance = 190, minLvl = 1, maxLvl = 99, cap = 255)) {
                player.animate(-1)
                player.setVarbit(treeVarbit, 9)
                wait(2)
                player.playSound(2734)
                player.setVarbit(treeVarbit, 10)
                player.timers[CHOP_TREE_RESPAWN] = 500
            }
            break
        }
        wait(2)
    }
}

fun Player.shapeCanoe(obj: GameObject, canoe: Int) {
    queue{
        var CANOE = DynamicObject(obj, CANOES[canoe])
        player.animate(SHAPE_ANIM)
        wait(6)
        player.animate(-1)
        player.world.remove(obj)
        player.world.spawn(CANOE)
        player.timers[CHOP_TREE_RESPAWN] = 10
        return@queue
    }
}

//Respawn Original Tree State
on_timer(CHOP_TREE_RESPAWN) {
    player.setVarbit(treeVarbit, 0)
    player.setVarbit(makingCanoe, 0)
}

on_logout {
    player.setVarbit(treeVarbit, 0)
    player.setVarbit(makingCanoe, 0)
    player.setVarbit(canoeMade, 0)
}