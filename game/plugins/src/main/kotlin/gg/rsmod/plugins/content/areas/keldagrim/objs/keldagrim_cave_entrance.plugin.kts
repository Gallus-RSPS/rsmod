package gg.rsmod.plugins.content.areas.keldagrim.objs

on_obj_option(5008, "Enter") {
    player.moveTo(2773, 10162, 0)
}

on_obj_option(5014, "Enter") {
    player.moveTo(2730, 3713, 0)
}

on_obj_option(5973, "Go-through") {
    player.moveTo(2838, 10124, 0)
    player.filterableMessage("You're just about able to squeeze through.")
}

on_obj_option(5998, "Go-through") {
    player.filterableMessage("You're just about able to squeeze through.")
    player.moveTo(2780, 10161, 0)
}