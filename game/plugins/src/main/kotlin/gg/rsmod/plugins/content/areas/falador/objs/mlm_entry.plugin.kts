package gg.rsmod.plugins.content.areas.falador.objs

//missing sound fx for enter/exit MLM cave

on_obj_option(obj = 26654, option = "Enter") {
    player.queue {
        player.animate(2796)
        wait(2)
        player.moveTo(3728, 5692, 0)
        player.animate(-1)
    }
}

on_obj_option(obj = 26655, option = "Exit") {
    player.queue {
        player.animate(2796)
        wait(2)
        player.moveTo(3060, 9766, 0)
        player.animate(-1)
    }
}
