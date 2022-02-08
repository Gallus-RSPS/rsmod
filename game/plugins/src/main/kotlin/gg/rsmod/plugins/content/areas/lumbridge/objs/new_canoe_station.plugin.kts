package gg.rsmod.plugins.content.areas.lumbridge.objs

//val CHOP_TREE_RESPAWN = TimerKey("chop_tree_respawn")

val CANOE_VARBIT = 1839

val CHOP_SHAPE_VARBIT = intArrayOf (0, 10) // Chop & Shape
val FLOAT_CANOE_VARBIT = intArrayOf (0, 1, 2, 3, 4) // NOTHING, Log, Dugout, Stable, Waka
val PADDLE_CANOE_VARBIT = intArrayOf (0, 11, 12, 13, 14) // NOTHING, Log, Dugout, Stable, Waka

val CHOP_SHAPE_ID = intArrayOf (12144, 12146) //Chop & Shape
val FLOAT_CANOE_ID = intArrayOf (2147, 12148, 12149, 12150) // Log, Dugout, Stable, Waka
val PADDLE_CANOE_ID = setOf (12155, 12156, 12157, 12158) // Log, Dugout, Stable, Waka

val ANIMATIONS = intArrayOf (2846, 3292, 3301, 3302) // Chop, Shape, Float, Paddle

val CHOP_TILE = Tile(3242, 3235)
val SHAPE_TILE = Tile(3242, 3237)

//on_obj_option(obj = CHOP_SHAPE_ID[0], option = "Chop-down") {
//    if(player.getSkills().getCurrentLevel(Skills.WOODCUTTING) < 12) {
//        player.message("You need a Woodcutting level of 12 to make a canoe.")
//        return@on_obj_option
//    } else {
//        player.chopTree(player.getInteractingGameObj())
//    }
//}
//
//on_obj_option(obj = CHOP_SHAPE_ID[1], option = "Shape-Canoe") {
//
//}
//
//on_obj_option(obj = FLOAT_CANOE_ID[0], option = "Float Log") {
//
//}
//
//for (i in 1..3) {
//    on_obj_option(obj = FLOAT_CANOE_ID[i], option = "Float Canoe") {
//
//    }
//}

//PADDLE_CANOE_ID.forEach { paddle ->
//    on_obj_option(obj = paddle, option = "Paddle-Canoe") {
//
//    }
//}

fun Player.chopTree(obj: GameObject) {
    queue{

    }
}

suspend fun options(it: QueueTask): Int = it.options("Make Log Canoe", "Make Dugout Canoe", "Make Stable Canoe", "Make Waka Canoe")

suspend fun makeCanoe(it: QueueTask, obj: GameObject) {
//    when(options(it)){
//        1 -> it.player.shapeCanoe(obj, 1)
//        2 -> it.player.shapeCanoe(obj, 2)
//        3 -> it.player.shapeCanoe(obj, 3)
//        4 -> it.player.shapeCanoe(obj, 4)
//    }
}

fun Player.shapeCanoe(canoe: Int) {
    queue {
        player.animate(ANIMATIONS[1])
        wait(6)
        player.animate(-1)
        player.setVarbit(CANOE_VARBIT, CHOP_SHAPE_VARBIT[1])
        //player.timers[CHOP_TREE_RESPAWN] = 10
        return@queue
    }
}

//Respawn Original Tree State
//on_timer(CHOP_TREE_RESPAWN) {
//    player.world.queue {
//        val INITIAL_TREE = DynamicObject(lastInteractedTree, TREE_CHOP)
//        world.remove(lastInteractedTree)
//        world.spawn(INITIAL_TREE)
//    }
//}