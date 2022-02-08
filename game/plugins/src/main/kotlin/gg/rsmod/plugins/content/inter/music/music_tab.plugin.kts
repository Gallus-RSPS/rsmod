package gg.rsmod.plugins.content.inter.music

import gg.rsmod.plugins.content.inter.quests.Quest
import gg.rsmod.plugins.content.inter.quests.QuestTab
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.DecimalFormat

/**
 * @author Alan Simeon
 */

val musicDefs = MusicTracks.musicDefinitions

val varpId = intArrayOf(0, 20, 21, 22, 23, 24, 25, 298, 311, 346, 414, 464, 598, 662, 721, 906, 1009, 1338, 1681, 2065, 2237)

on_login {
    player.setInterfaceEvents(interfaceId = 239, component = 3, range = 0 until 600, setting = 6)
    player.setComponentHidden(interfaceId = 239, component = 0, hidden = false)
}

musicDefs.values.forEach { regions ->

    /*on_enter_region(regionID) {
        if (getVarp(20) >> 17 == 0){
        setVarp(20, getVarp(20) | (1 << 17))
    }
    }*/


    on_enter_region(regions.regionOptions.iterator().next()) {
        //player.message("You've entered a new region: " + player.tile.regionId);
        val bit = (1 shl regions.bitId)
        val varp = varpId[regions.varpId]
        if (regions.bitId != -1) {
            if ((player.getVarp(varp) and bit) == 0) {
                player.setVarp(varp, player.getVarp(varp) or bit)
                player.message("You have unlocked a new music track: ".red() + regions.songName.red() + ".".red())
                player.setSong((regions.slot - 1), 1)
            }
        }
        PlaySong.playSong(player, regions)
    }
}

on_button(interfaceId = 239, component = 3) p@ {
    val slot = player.getInteractingSlot()
    val song = MusicTracks.values.firstOrNull { selectedSong -> selectedSong.slot == (slot + 1) } ?: return@p
    val bit = (1 shl song.bitId)
    val varp = varpId[song.varpId]

    if (song.bitId != -1) {
        if ((player.getVarp(varp) and bit) == 0){
            player.message("This song is not unlocked yet.")
        } else {
            PlaySong.playSong(player, song)
        }
    } else {
        PlaySong.playSong(player, song)
    }

    /*if (player.getSong(slot) == 1) {
        PlaySong.playSong(player, song)
    } else {
        player.message("This song is not unlocked yet.")
    }*/
}



on_button(interfaceId = 239, component = 11) { // Loop Button
    if (player.getVarbit(4137) == 0){
        player.setVarbit(4137, 1)
    } else {
        player.setVarbit(4137, 0)
    }
}
on_button(interfaceId = 239, component = 7) { // Auto Button
    player.setVarp(18, 1)
}
on_button(interfaceId = 239, component = 9) { // Manual Button
    player.setVarp(18, 0)
}


fun String.blue() = "<col=0845c9>$this</col>"
fun String.red() = "<col=a61005>$this</col>"
fun String.strike() = "<str>$this</str>"