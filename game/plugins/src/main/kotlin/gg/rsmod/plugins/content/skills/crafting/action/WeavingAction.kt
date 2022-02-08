package gg.rsmod.plugins.content.skills.crafting.action

import gg.rsmod.game.fs.def.ItemDef
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.Skills
import gg.rsmod.plugins.api.ext.*
import gg.rsmod.plugins.content.skills.crafting.data.Weaving

/**
 * @author Gallus
 */

class WeavingAction {

    suspend fun weave(task: QueueTask, weave: Weaving, amount: Int) {
        val player = task.player
        if (!canWeave(player, weave))
            return

        val inventory = player.inventory
        val primaryCount = inventory.getItemCount(weave.unWoven)/weave.amount
        val maxCount = Math.min(amount, primaryCount)

        var completed = 0
        while(completed < maxCount) {
            player.animate(WEAVING_ANIM)
            task.wait(3)
            player.lock()

            if (!canWeave(player, weave, sendMessageBox = false)){
                player.unlock()
                break
            }
            val removeUnwoven = inventory.remove(item = weave.unWoven, amount = weave.amount, assureFullRemoval = true)
            if(removeUnwoven.hasFailed()) {
                player.unlock()
                break
            }
            var amountMade = 1

            inventory.add(weave.id, amount = amountMade)
            player.addXp(Skills.CRAFTING, weave.craftXp * amountMade)
            completed++
            player.unlock()
            task.wait(2)
        }
    }

    fun canWeave (player: Player, weave: Weaving, sendMessageBox: Boolean = true): Boolean {
        val inventory = player.inventory
        var unWovenName = player.world.definitions.get(ItemDef::class.java, weave.unWoven).name
        if (inventory.getItemCount(weave.unWoven) < weave.amount) {
            if(sendMessageBox)
                player.message("You don't have enough ${unWovenName} to weave.")
            return false
        }
        if (player.getSkills().getCurrentLevel(Skills.CRAFTING) < weave.level) {
            if(sendMessageBox)
                player.filterableMessage("You need a Crafting level of ${weave.level} to weave ${weave.prefix}'s.")
            return false
        }
        return true
    }

    companion object {
        const val WEAVING_ANIM = 2270
    }

}