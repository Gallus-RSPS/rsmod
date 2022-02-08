package gg.rsmod.plugins.content.areas.lumbridge.objs.windmill

on_obj_option(obj = 24964, option = "Operate") {
    player.operateLever()
}

fun Player.operateLever() {
    queue {
        val obj = player.getInteractingGameObj()
        val pulled = DynamicObject(obj, 24967)
        player.lock()
        player.animate(3571)
        player.world.remove(obj)
        player.world.spawn(pulled)
        if (hopperGrain == 1){
            if (player.regularFlour == 30) {
                player.message("The flour bin downstairs is full, I should empty it first.")
            } else if (player.regularFlour < 30) {
                player.message("You operate the hopper. The grain slides down the chute.")
                regularFlour += 1
                hopperGrain -= 1
                player.setVarbit(5325, 1)
            }
        } else {
            player.message("You operate the empty hopper. Nothing interesting happens.")
        }
        wait(2)
        player.world.remove(pulled)
        player.world.spawn(DynamicObject(obj))
        wait(2)
        player.unlock()
    }
}