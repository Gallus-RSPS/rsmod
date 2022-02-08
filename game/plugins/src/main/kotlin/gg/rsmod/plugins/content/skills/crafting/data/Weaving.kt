package gg.rsmod.plugins.content.skills.crafting.data

import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.content.skills.smithing.data.Bar

/**
 * @author Gallus
 *
 * @param id : id of the finished item
 * @param prefix : name of item
 * @param unWoven : material needed to make
 * @param amount : amount of unWoven needed to make
 * @param level : level needed to make
 * @param craftXp : amount of xp given
 * @param animation : making animation
 *
 */
enum class Weaving(val id: Int,val prefix: String, val unWoven: Int, val amount: Int, val level: Int, val craftXp: Double = 0.0, val animation: Int) {
    EMPTY_SACK(id = Items.EMPTY_SACK, prefix = "empty sack", unWoven = Items.JUTE_FIBRE, amount = 4, level = 21, craftXp = 38.0, animation = 2270),
    DRIFT_NET(id = Items.DRIFT_NET, prefix = "drift net", unWoven = Items.JUTE_FIBRE, amount = 2, level = 26, craftXp = 55.0, animation = 2270),
    BASKET(id = Items.BASKET, prefix = "basket", unWoven = Items.WILLOW_BRANCH, amount = 6, level = 36, craftXp = 56.0, animation = 2270);


    companion object {
        /**
         * The cached array of enum definitions
         */
        val values = enumValues<Weaving>()

        val weavingDefinitions = Weaving.values.associate { it.id to it }
    }
}