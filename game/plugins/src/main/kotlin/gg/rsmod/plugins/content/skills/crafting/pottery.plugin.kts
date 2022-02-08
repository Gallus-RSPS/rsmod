package gg.rsmod.plugins.content.skills.crafting

import gg.rsmod.plugins.content.skills.crafting.data.PotSpin

val POTTERY_OVEN = 11601
val POTTERY_WHEEL = 14887
val SPIN_ANIM = 883
val FIRE_ANIM = 1317


val unfiredDefs = PotSpin.potSpinDefinitions
val firedDefs = PotSpin.potFireDefinitions

val standardFiredIds = intArrayOf(Items.POT, Items.PIE_DISH, Items.BOWL, Items.PLANT_POT, Items.POT_LID)
val standardUnfiredIds = intArrayOf(Items.UNFIRED_POT, Items.UNFIRED_PIE_DISH, Items.UNFIRED_BOWL,
        Items.UNFIRED_PLANT_POT, Items.UNFIRED_POT_LID)

on_item_on_obj(obj = POTTERY_WHEEL, item = Items.SOFT_CLAY) {
    player.queue { produceItemBox(*standardUnfiredIds, title = "What would you like to spin on the pottery wheel?", logic = ::spinItem) }
}

on_obj_option(obj = POTTERY_OVEN, option = "Fire") {
    player.queue { produceItemBox(*standardFiredIds, title = "What would you like to fire in the oven?", logic = ::fireItem) }
}

fun spinItem(player: Player, item: Int, amount: Int = 28) {
    val def = unfiredDefs[item] ?: return
    player.queue { spinTask(this, def, amount) }
}

suspend fun spinTask(task: QueueTask, spin: PotSpin, amount: Int) {
    val player = task.player
    if (!canSpin(player, spin, true))
        return

    val inventory = player.inventory
    val primaryCount = inventory.getItemCount(spin.clay)
    val maxCount = Math.min(amount, primaryCount)

    var completed = 0
    while(completed < maxCount) {
        player.animate(SPIN_ANIM)
        task.wait(5)
        player.lock()

        if(!canSpin(player, spin, false)) {
            player.unlock()
            break;
        }
        val removeClay = inventory.remove(item = Items.SOFT_CLAY, amount = 1, assureFullRemoval = true)
        if(removeClay.hasFailed()){
            player.unlock()
            break
        }

        inventory.add(spin.unfiredId, amount = 1)
        player.addXp(Skills.CRAFTING, spin.spinCraftXp)
        completed++
        player.unlock()
        task.wait(2)
    }
}



fun fireItem(player: Player, item: Int, amount: Int = 28) {
    val def = firedDefs[item] ?: return
    player.queue { fireTask(this, def, amount) }
}

suspend fun fireTask(task: QueueTask, fire: PotSpin, amount: Int) {
    val player = task.player
    if (!canFire(player, fire, true))
        return

    val inventory = player.inventory
    val primaryCount = inventory.getItemCount(fire.unfiredId)
    val maxCount = Math.min(amount, primaryCount)

    var completed = 0
    while(completed < maxCount) {
        player.animate(SPIN_ANIM)
        task.wait(5)
        player.lock()

        if(!canSpin(player, fire)) {
            player.unlock()
            break;
        }
        val removeClay = inventory.remove(item = fire.unfiredId, amount = 1, assureFullRemoval = true)
        if(removeClay.hasFailed()){
            player.unlock()
            break
        }

        inventory.add(fire.firedId, amount = 1)
        player.addXp(Skills.CRAFTING, fire.fireCraftXp)
        completed++
        player.unlock()
        task.wait(2)
    }
}

fun canSpin(player: Player, spin: PotSpin, sendMessageBox: Boolean = true): Boolean {
    val inventory = player.inventory
    if (inventory.getItemCount(spin.clay) < 1) {
        return false
    }
    if (player.getSkills().getCurrentLevel(Skills.CRAFTING) < spin.level) {
        if(sendMessageBox){
            player.filterableMessage("You need a Crafting level of ${spin.level} to make ${spin.prefix}s.")
            return false
        }
    }
    return true
}

fun canFire(player: Player, fire: PotSpin, sendMessageBox: Boolean = true): Boolean {
    val inventory = player.inventory
    if (inventory.getItemCount(fire.unfiredId) < 1) {
        if(sendMessageBox) {
            player.queue { messageBox("You don't have any ${fire.prefix}s that need firing.") }
            return false
        }
    }
    if (player.getSkills().getCurrentLevel(Skills.CRAFTING) < fire.level) {
        if(sendMessageBox){
            player.filterableMessage("You need a Crafting level of ${fire.level} to make ${fire.prefix}s.")
            return false
        }
    }
    return true
}