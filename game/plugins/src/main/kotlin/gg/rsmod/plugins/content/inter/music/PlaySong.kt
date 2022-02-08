package gg.rsmod.plugins.content.inter.music

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.ext.playSong
import gg.rsmod.plugins.api.ext.setComponentText

/**
 * @author Alan Simeon
 */
object PlaySong {
    fun playSong(p: Player, song: MusicTracks) {
        p.setComponentText(interfaceId = 239, component = 6, text = "<col=0cb50c>${song.songName}</col>")
        p.playSong(song.id)
    }
}