package gg.rsmod.plugins.content.inter.quests

/**
 * @author Alan Simeon
 */
enum class Quest(val slot: Int, val varp: Int = 0, val varbit: Int = 0) {
    //f2p component 6
    BLACK_KNIGHTS_FORTRESS(slot = 0, varp = 130),
    COOKS_ASSISTANT(slot = 1, varp = 29),
    THE_CORSAIR_CURSE(slot = 2, varbit = 6071),
    DEMON_SLAYER(slot = 3, varbit = 2561),
    DORICS_QUEST(slot = 4, varp = 31),
    DRAGON_SLAYER(slot = 5, varp = 176),
    ERNEST_THE_CHICKEN(slot = 6, varp = 32),
    GOBLIN_DIPLOMACY(slot = 7, varbit = 2378),
    IMP_CATCHER(slot = 8, varp = 160),
    THE_KNIGHTS_SWORD(slot = 9, varp = 122),
    MISTHALIN_MYSTERY(slot = 10, varbit = 3468),
    PIRATES_TREASURE(slot = 11, varp = 71),
    PRINCE_ALI_RESCUE(slot = 12, varp = 273),
    THE_RESTLESS_GHOST(slot = 13, varp = 107),
    ROMEO_AND_JULIET(slot = 14, varp = 144),
    RUNE_MYSTERIES(slot = 15, varp = 63),
    SHEEP_SHEARER(slot = 16, varp = 179),
    SHIELD_OF_ARRAV(slot = 17, varp = 145),
    VAMPIRE_SLAYER(slot = 18, varp = 178),
    WITCHS_POTION(slot = 19, varp = 67),
    THE_GIANT_DWARF(slot = 41, varbit = 571);

    companion object {
        val values = enumValues<Quest>()
    }
}