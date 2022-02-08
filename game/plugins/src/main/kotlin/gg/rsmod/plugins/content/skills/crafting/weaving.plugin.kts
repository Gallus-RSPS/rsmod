package gg.rsmod.plugins.content.skills.crafting

import gg.rsmod.plugins.content.skills.crafting.action.WeavingAction
import gg.rsmod.plugins.content.skills.crafting.data.Weaving

val LOOM = intArrayOf (
        Objs.LOOM, Objs.LOOM_30936, Objs.LOOM_8717
)

val weavingDefs = Weaving.weavingDefinitions
val weaveAction = WeavingAction()

val standardWeaveIds = intArrayOf(
        Items.EMPTY_SACK, Items.DRIFT_NET, Items.BASKET
)

LOOM.forEach { loom ->
    on_obj_option(obj = loom, option = "Weave") {
        player.queue{ produceItemBox(*standardWeaveIds, title = "How many do you wish to make?", logic = ::weaveItem) }
    }
}

fun weaveItem(player: Player, item: Int, amount: Int = 28) {
    val def = weavingDefs[item] ?: return
    player.queue { weaveAction.weave(this, def, amount) }
}