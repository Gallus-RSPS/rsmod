package gg.rsmod.game.model.songs

import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet

/**
 * Holds song data
 *
 * @author Alan Simeon
 */
class SongSet(val maxSongs: Int) {

    private val songs = mutableListOf<Song>().apply {
        for (i in 0 until maxSongs) {
            add(Song(id = i, unlocked = 0))
        }
    }

    /**
     * A collection of dirty varps which will be sent to the client on the next cycle.
     * This collection should be used only if the revision you are trying to
     * support uses them on the client.
     */
    private val dirty = ShortOpenHashSet(maxSongs)

    operator fun get(id: Int): Song = songs[id]

    fun getUnlocked(id: Int): Int = songs[id].unlocked

    fun setUnlocked(id: Int, unlocked: Int): SongSet {
        songs[id].unlocked = unlocked
        dirty.add(id.toShort())
        return this
    }

    fun isDirty(id: Int): Boolean {
        return dirty.contains(id.toShort())
    }

    fun clean() {
        dirty.clear()
    }

    fun getAll(): List<Song> = songs
}