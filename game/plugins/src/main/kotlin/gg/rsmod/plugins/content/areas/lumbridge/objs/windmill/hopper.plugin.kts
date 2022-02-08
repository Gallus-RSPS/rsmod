package gg.rsmod.plugins.content.areas.lumbridge.objs.windmill

var grain = Items.GRAIN

on_obj_option(obj = 24961, option = "Fill") {
    var currentWheat = player.hopperGrain
    var grainCount = player.inventory.getItemCount(grain)
    if (currentWheat != 0) {
        player.cantFill()
    } else {
        if (grainCount == 0) {
            player.queue {
                noWheat(this)
            }
        } else {
            player.fillHopper()
        }
    }
}

suspend fun noWheat(it: QueueTask) {
    it.itemMessageBox("You haven't got anything to fill the hopper with.", Items.GRAIN)
}

fun Player.cantFill() {
    queue {
        player.message("There is already grain in the hopper.")
    }
}


fun Player.fillHopper() {
    queue {
        lock()
        animate(3572)
        message("You put the grain in the hopper. You should now pull the lever nearby to operate the hopper.")
        inventory.remove(item = grain, amount = 1)
        hopperGrain ++
        wait(3)
        unlock()
    }
}