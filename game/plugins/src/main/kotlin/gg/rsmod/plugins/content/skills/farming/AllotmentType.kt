package gg.rsmod.plugins.content.skills.farming

import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.content.inter.music.MusicTracks

/**
 * @author Gallus
 */
enum class AllotmentType(val seed: Int, val requiredSeeds: Int, val level: Int, val xp: Double, val cycles: Int, val harvestItem: Int, val cropName: String, val startVal: Int, val wateredVal: Int) {
    POTATO(seed = Items.POTATO_SEED, requiredSeeds = 3, level = 1, xp = 8.0, cycles = 7, harvestItem = Items.POTATO, cropName = "Potato", startVal = 6, wateredVal = 70),
    ONION(seed = Items.ONION_SEED, requiredSeeds = 3, level = 5, xp = 9.5, cycles = 4, harvestItem = Items.ONION, cropName = "Onion", startVal = 6, wateredVal = 70),
    CABBAGE(seed = Items.CABBAGE_SEED, requiredSeeds = 3, level = 7, xp = 10.0, cycles = 4, harvestItem = Items.CABBAGE, cropName = "Cabbage", startVal = 6, wateredVal = 70),
    TOMATO(seed = Items.TOMATO_SEED, requiredSeeds = 3, level = 12, xp = 12.5, cycles = 4, harvestItem = Items.TOMATO, cropName = "Tomato", startVal = 6, wateredVal = 70),
    SWEETCORN(seed = Items.SWEETCORN_SEED, requiredSeeds = 3, level = 20, xp = 17.0, cycles = 6, harvestItem = Items.SWEETCORN, cropName = "Sweetcorn", startVal = 6, wateredVal = 70),
    STRAWBERRY(seed = Items.STRAWBERRY_SEED, requiredSeeds = 3, level = 31, xp = 26.0, cycles = 6, harvestItem = Items.STRAWBERRY, cropName = "Starwberry", startVal = 6, wateredVal = 70),
    WATERMELON(seed = Items.WATERMELON_SEED, requiredSeeds = 3, level = 47, xp = 48.5, cycles = 8, harvestItem = Items.WATERMELON, cropName = "Watermelon", startVal = 6, wateredVal = 70),
    SNAPEGRASS(seed = Items.SNAPE_GRASS_SEED, requiredSeeds = 3, level = 61, xp = 82.0, cycles = 7, harvestItem = Items.SNAPE_GRASS, cropName = "Snape grass", startVal = 6, wateredVal = 70);

    companion object {
        /**
         * The cached array of enum definitions
         */

        val values = enumValues<AllotmentType>()

        val musicDefinitions = AllotmentType.values.associate { it.seed to it }
    }
}