package gg.rsmod.plugins.content.areas.lumbridge.objs

on_obj_option(14880, "Climb-down") {
    //if (player.tile.sameAs(3210, 3216)){
        player.enterCellar()
    //} else {
    //    player.moveTo(3210, 3216)
    //    player.enterCellar()
    //}
}

on_obj_option(17385, "Climb-up") {
    //if (player.tile.sameAs(3209, 9617)){
        player.exitCellar()
    //} else {
    //    player.moveTo(3209, 9617)
    //    player.exitCellar()
   // }
}

fun Player.exitCellar() {
    queue {
        player.animate(828)
        wait(2)
        player.moveTo(3210, 3216, 0)
    }
}

fun Player.enterCellar() {
    queue {
        player.animate(827)
        wait(2)
        player.moveTo(3210, 9616, 0)
    }
}