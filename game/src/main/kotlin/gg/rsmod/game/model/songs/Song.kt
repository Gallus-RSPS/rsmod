package gg.rsmod.game.model.songs

import gg.rsmod.game.model.varp.Varp

/**
 * @author Alan Simeon
 *
 * @param id : The [Song] slog id
 * @param unlocked : The [Song] unlock state. By default an unlocked [Song] will be state 0
 */
data class Song(val id: Int, var unlocked: Int)