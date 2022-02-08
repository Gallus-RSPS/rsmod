package gg.rsmod.plugins.content.areas.lumbridge.objs


on_obj_option(obj = Objs.SIGNPOST_18493, option = "Read") {
    player.setComponentText(135, 2, "North to farms and Varrock.")
    player.setComponentText(135, 8, "The River Lum lies to the south.")
    player.setComponentText(135, 11, "West to Lumbridge.")
    player.setComponentText(135, 7, "East to Al Kharid - toll gate; bring some money.")
    player.openInterface(135, InterfaceDestination.MAIN_SCREEN)
}