package gg.rsmod.plugins.content.skills.farming


val CATH_NORTH_PATCH = AttributeKey<Boolean>(persistenceKey = "cath_north_patch")
val CATH_SOUTH_PATCH = AttributeKey<Boolean>(persistenceKey = "cath_south_patch")

val ALLOTMENT_VARBIT = 4771
var WEED_STATE = 0
val NEXT_WEED_GROWTH = TimerKey("next_weed_growth")
val CROP = intArrayOf()

val PATCHES = setOf(
        8576,8575,8574,8573,8558,8559,8560,
        8561,8562,8563,8564,8565,8566,
        8567,8568,8569,33673,8570
)

val WEEDS = setOf(
        8576, 8575, 8574
)
val WEEDED = 8573

val SEEDS = setOf(
        Items.POTATO_SEED, Items.ONION_SEED, Items.CABBAGE_SEED, Items.TOMATO_SEED,
        Items.SWEETCORN_SEED, Items.STRAWBERRY_SEED, Items.WATERMELON_SEED, Items.SNAPE_GRASS_SEED
)

val WATERING_CANS = arrayOf(
    Items.WATERING_CAN1, Items.WATERING_CAN2, Items.WATERING_CAN3, Items.WATERING_CAN4, Items.WATERING_CAN5,
    Items.WATERING_CAN6, Items.WATERING_CAN7, Items.WATERING_CAN8
)

val RAKE = Items.RAKE
val RAKE_ANIM = 2273
val SPADE = Items.SPADE
val HARVEST_ANIM = 830
val DIBBER = Items.SEED_DIBBER
val PLANT_ANIM = 2291
val COMPOST = Items.COMPOST
val COMPOST_ANIM = 2283

val WATER_ANIM = 2293

//testing other patches
//flowers
on_obj_option(obj = 7843, option = "Guide") {
    player.setVarbit(4372, 5) //Skill Subsection Varbit
    player.setVarbit(4371, 21) //Skill ID Varbit
    player.setInterfaceEvents(interfaceId = 214, component = 25, from = -1, to = -1, setting = 0)
    player.setInterfaceUnderlay(color = -1, transparency = -1)
    player.openInterface(interfaceId = 214, dest = InterfaceDestination.MAIN_SCREEN)
}
//herbs
on_obj_option(obj = 8135, option = "Guide") {
    player.setVarbit(4372, 6) //Skill Subsection Varbit
    player.setVarbit(4371, 21) //Skill ID Varbit
    player.setInterfaceEvents(interfaceId = 214, component = 25, from = -1, to = -1, setting = 0)
    player.setInterfaceUnderlay(color = -1, transparency = -1)
    player.openInterface(interfaceId = 214, dest = InterfaceDestination.MAIN_SCREEN)
}

//Guide
PATCHES.forEach { patch ->
    on_obj_option(obj = patch, option = "Guide") {
        player.setVarbit(4372, 0) //Skill Subsection Varbit
        player.setVarbit(4371, 21) //Skill ID Varbit
        player.setInterfaceEvents(interfaceId = 214, component = 25, from = -1, to = -1, setting = 0)
        player.setInterfaceUnderlay(color = -1, transparency = -1)
        player.openInterface(interfaceId = 214, dest = InterfaceDestination.MAIN_SCREEN)
    }
}

//Rake Weeds
WEEDS.forEach { weeds ->
    on_obj_option(obj = weeds, option = "Rake") {
        val curWeedState = player.getVarbit(ALLOTMENT_VARBIT)
        if(!player.inventory.contains(RAKE)){
            player.message("You need a rake to weed a farming patch.")
            return@on_obj_option
        }
        when (curWeedState) {
            -1 -> player.rakeWeeds(curWeedState)
            0 -> player.rakeWeeds(curWeedState)
            1 -> player.rakeWeeds(curWeedState)
            2 -> player.rakeWeeds(curWeedState)
        }

    }
}

fun Player.rakeWeeds(curWeedState: Int) {
    var curWeedState = curWeedState
    queue {
        if (curWeedState != 3) {
            player.animate(RAKE_ANIM)
            wait(5)
            player.addXp(Skills.FARMING, 4.0)
            player.inventory.add(Items.WEEDS, 1)
            WEED_STATE += 1
            player.setVarbit(ALLOTMENT_VARBIT, WEED_STATE)
            var newWeedState = player.getVarbit(ALLOTMENT_VARBIT)
            //player.timers[NEXT_WEED_GROWTH] = 5
            player.rakeWeeds(newWeedState)
        } else {
            return@queue
        }
    }
}

//Compost patch
on_item_on_obj(8573, Items.COMPOST) {
    player.queue {
        player.animate(2283)
        wait(5)
        player.inventory.remove(Items.COMPOST, 1)
        player.inventory.add(Items.EMPTY_BUCKET, 1)
        player.message("You treat the allotment with compost.")
    }
}

//Plant Seed
SEEDS.forEach{ seed ->
    on_item_on_obj(WEEDED, seed) p@ {
        val patchType = AllotmentType.values.firstOrNull { e -> e.seed == seed } ?: return@p
        if (player.getVarbit(4771) < 3){
            player.message("This patch needs weeding first.")
        } else if (!player.inventory.contains(Items.SEED_DIBBER)) {
            player.queue {
                messageBox("You need a seed dibber to plant seeds.")
            }
        } else {
            player.queue {
                player.animate(2291)
                wait(3)
                player.message("You plant " + patchType.requiredSeeds + " " + patchType.cropName.toLowerCase() + " seeds in the allotment.")
                player.inventory.remove(seed, patchType.requiredSeeds)
                player.setVarbit(4771, patchType.startVal)
            }
        }
    }
}

//Water Patch
WATERING_CANS.forEach { can ->
    on_item_on_obj(8558, can) {
        player.queue {
            player.animate(WATER_ANIM)
            wait(5)
            player.setVarbit(4771, 70)
            player.getItemReplacement(can, WATERING_CANS[WATERING_CANS.indexOf(can) - 1])
        }
    }
}

//Regrow Weeds
on_timer(NEXT_WEED_GROWTH) {
    if(WEED_STATE >= 1) {
        WEED_STATE -= 1
        player.setVarbit(ALLOTMENT_VARBIT, WEED_STATE)
        player.timers[NEXT_WEED_GROWTH] = 5
    } else {
        return@on_timer
    }
}


fun Player.getItemReplacement(remove: Int, replace: Int) {
    queue {
        val transaction = inventory.remove(item = remove, amount = 1)
        if (transaction.hasSucceeded()) {
            player.inventory.add(replace, 1)
        }
    }
}

//    player.message("Varbit: " + player.getInteractingGameObj().getDef(world.definitions).varbit +
//                    " Varp: " + player.getInteractingGameObj().getDef(world.definitions).varp)