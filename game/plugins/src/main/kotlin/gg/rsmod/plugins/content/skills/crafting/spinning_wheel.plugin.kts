package gg.rsmod.plugins.content.skills.crafting

import gg.rsmod.plugins.content.skills.crafting.data.Spin

val SPINNING_WHEEL = 14889


val unSpunDefs = Spin.spinDefinitions

val standardSpinIds = intArrayOf(
        Items.BALL_OF_WOOL, Items.GOLDEN_WOOL, Items.BOW_STRING, Items.CROSSBOW_STRING,
        Items.ROPE, Items.MAGIC_STRING
)

on_obj_option(obj = SPINNING_WHEEL, option = "Spin") {
    player.queue { produceItemBox(*standardSpinIds, title = "What would you like to spin?", logic = ::spinItem) }
}

fun spinItem(player:Player, item: Int, amount: Int = 28) {
    val def = unSpunDefs[item] ?: return
    player.queue { spin(player, def, amount) }
}

suspend fun spin(player: Player, spin: Spin, amount: Int) {
    if (!canSpin(player, spin))
        return

    val inventory = player.inventory

    val primaryCount = inventory.getItemCount(spin.unSpun)

    val maxCount = Math.min(amount, primaryCount)

    repeat(maxCount) {
        player.queue {
            player.animate(spin.animation)
            wait(5)
            player.lock()
            if (!canSpin(player, spin)) {
                player.animate(-1)
                return@queue
            }
            val removeUnSpun = inventory.remove(item = spin.unSpun)

            if (removeUnSpun.hasSucceeded()) {
                inventory.add(spin.id)
                player.addXp(Skills.CRAFTING, spin.craftXp)
            }
            player.unlock()
            wait(1)
        }
    }
}

fun canSpin(player: Player, spin: Spin): Boolean {
    val inventory = player.inventory

    if (!inventory.contains(spin.unSpun)) {
        player.message("You don't have any ${spin.unSpun} to spin.")
        return false
    }

    if (player.getSkills().getCurrentLevel(Skills.CRAFTING) < spin.level) {
        player.filterableMessage("You need a Crafting level of ${spin.level} to spin ${spin.prefix}.")
        return false
    }
    return true
}