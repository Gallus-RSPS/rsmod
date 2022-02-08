package gg.rsmod.plugins.content.skills.crafting.data

import gg.rsmod.plugins.api.cfg.Items

/**
 * @author Gallus
*/

enum class PotSpin(val clay: Int = Items.SOFT_CLAY, val unfiredId: Int, val firedId: Int, val prefix: String, val unSpun: Int, val level: Int, val spinCraftXp: Double = 0.0, val fireCraftXp: Double = 0.0) {
    POT(unfiredId = Items.UNFIRED_POT, firedId = Items.POT,prefix = "pot", unSpun = Items.SOFT_CLAY, level = 1, spinCraftXp = 6.3, fireCraftXp = 6.3),
    PIE_DISH(unfiredId = Items.UNFIRED_PIE_DISH, firedId = Items.PIE_DISH, prefix = "pie dish", unSpun = Items.SOFT_CLAY, level = 1, spinCraftXp = 15.0, fireCraftXp = 10.0),
    BOWL(unfiredId = Items.UNFIRED_BOWL, firedId = Items.BOWL, prefix = "bowl", unSpun = Items.SOFT_CLAY, level = 1, spinCraftXp = 18.0, fireCraftXp = 15.0),
    EMPTY_PLANT_POT(unfiredId = Items.UNFIRED_PLANT_POT, firedId = Items.PLANT_POT, prefix = "plant pot", unSpun = Items.SOFT_CLAY, level = 1, spinCraftXp = 20.0, fireCraftXp = 17.5),
    POT_LID(unfiredId = Items.UNFIRED_POT_LID, firedId = Items.POT_LID, prefix = "pot lid", unSpun = Items.SOFT_CLAY, level = 1, spinCraftXp = 20.0, fireCraftXp = 20.0);

    companion object {
        /**
         * The cached array of enum definitions
         */
        val values = enumValues<PotSpin>()

        val potSpinDefinitions = PotSpin.values.associate { it.unfiredId to it }
        val potFireDefinitions = PotSpin.values.associate { it.firedId to it }
    }
}