package gg.rsmod.plugins.content.inter.quests.free.earnestthechicken

val levers = intArrayOf(1788,1789,1790,1791,1792,1793)
val doors = intArrayOf(1794,1795,1796,1797,1798,1799,1800,1801,1802)

on_obj_option(133, "Climb-down") {
    player.queue {
        player.animate(827)
        wait(2)
        player.moveTo(3117, 9753)
        for(x in 1788..1802) {
            player.setVarbit(x, 0)
        }
    }
}

on_obj_option(132, "Climb-up") {
    player.queue {
        player.animate(828)
        wait(2)
        player.moveTo(3092,3361)
        for(x in 1788..1802) {
            player.setVarbit(x, 0)
        }
    }
}