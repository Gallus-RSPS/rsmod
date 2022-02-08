package gg.rsmod.plugins.content.skills.cooking.special

import gg.rsmod.plugins.content.skills.cooking.cookingMessageBox
import gg.rsmod.plugins.content.skills.cooking.data.CookingFood
import gg.rsmod.plugins.content.skills.cooking.data.CookingObj

val WINE_EXP_TIMER = TimerKey(persistenceKey = "wine_exp_timer")


fun makeWine(player: Player, item: Int, amount: Int = 28) {
    player.queue { cook(this, amount) }
}


on_item_on_item(Items.JUG_OF_WATER, Items.GRAPES) {
    player.queue{ produceItemBox(Items.JUG_OF_WINE, title = "How many do you wish to make?", logic = ::makeWine) }
}


suspend fun cook(task: QueueTask, amount: Int) {
    val player = task.player


    repeat(amount) {

        if(!canCook(player)) {
            return
        }

        player.animate(7529)
        player.graphic(47)
        task.wait(3)
        player.inventory.remove(Items.JUG_OF_WATER, 1)
        player.inventory.remove(Items.GRAPES, 1)
        player.inventory.add(Items.UNFERMENTED_WINE, 1)
        player.filterableMessage("You squeeze the grapes into the jug. The wine begins to ferment.")
        player.animate(-1)
        player.timers[WINE_EXP_TIMER] = 19
    }
}


fun canCook(player: Player): Boolean {

    if(!player.inventory.contains(Items.JUG_OF_WATER) || !player.inventory.contains(Items.GRAPES)) {
        return false
    }

    if(player.getSkills().getCurrentLevel(Skills.COOKING) < 35) {
        player.filterableMessage("You need a ${Skills.getSkillName(player.world, Skills.COOKING)} level of at least 35 to make Wine.")
        return false
    }
    return true
}

on_timer(WINE_EXP_TIMER) {
    val inventoryWines = player.inventory.getItemCount(Items.UNFERMENTED_WINE)
    val bankWines = player.bank.getItemCount(Items.UNFERMENTED_WINE)
    val totalWines = inventoryWines + bankWines

    player.bank.remove(Items.UNFERMENTED_WINE, bankWines)
    player.bank.add(Items.JUG_OF_WINE, bankWines)
    player.inventory.remove(Items.UNFERMENTED_WINE, inventoryWines)
    player.inventory.add(Items.JUG_OF_WINE, inventoryWines)
    player.message("Total Wines: " + totalWines)
    player.addXp(Skills.COOKING, (200.00 * totalWines))
}