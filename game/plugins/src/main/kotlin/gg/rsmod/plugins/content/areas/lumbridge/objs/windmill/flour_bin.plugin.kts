package gg.rsmod.plugins.content.areas.lumbridge.objs.windmill

val emptyBin = 5792
val emptyBin1 = 1781
val fullBin = 1782

on_login {
    player.checkFlour()
}

on_obj_option(obj = emptyBin, option = "Empty") {
//    player.message("Object Def: " + lumbyBinEmpty.getDef(world.definitions).varbit)
//    player.message("Object Def: " + lumbyBinEmpty.getDef(world.definitions).varp)
    player.message("The flour bin is already empty. You need to place wheat in the hopper upstairs first.")
}

on_obj_option(obj = fullBin, option = "Empty"){
    val emptyPotCount = player.inventory.getItemCount(1931)
    if (emptyPotCount >= 1) {
        player.emptyBin()
    } else {
        player.message("You need an empty pot to hold the flour in.")
    }
}


fun Player.emptyBin() {
    queue {
        if (player.regularFlour > 1){
            message("You fill a pot with flour from the bin.")
        } else if (player.regularFlour == 1) {
            message("You fill a pot with the last of the flour in the bin.")
        }
        regularFlour -= 1
        val transaction = inventory.remove(item = Items.POT, amount = 1)
        if (transaction.hasSucceeded()) {
            player.inventory.add(Items.POT_OF_FLOUR, 1)
        }
        player.checkFlour()
    }
}

fun Player.checkFlour() {
    queue {
        if (player.regularFlour >= 1) {
            player.setVarbit(5325, 1)
        } else {
            player.setVarbit(5325, 0)
        }
    }
}