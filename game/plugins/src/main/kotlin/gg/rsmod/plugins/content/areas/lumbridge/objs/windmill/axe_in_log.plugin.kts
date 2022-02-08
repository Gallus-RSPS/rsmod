package gg.rsmod.plugins.content.areas.lumbridge.objs.windmill

val AXE_IN_LOG = intArrayOf(5581, 5582)
val BRONZE_AXE = 1351
val RESPAWN_DELAY = 316
val MESSAGE = "Someone's been chopping logs."

on_obj_option(obj = AXE_IN_LOG[0], option = "Take-axe") {
        val obj = player.getInteractingGameObj()
        val EMPTY_LOG = DynamicObject(obj, AXE_IN_LOG[1])

        player.queue {
                if (player.inventory.isFull) {
                        player.message("You don't have room for this axe.")
                } else{
                        player.inventory.add(BRONZE_AXE, 1)
                        player.message(MESSAGE)
                        world.remove(obj)
                        world.spawn(EMPTY_LOG)
                        world.queue {
                                wait(RESPAWN_DELAY)
                                world.remove(EMPTY_LOG)
                                world.spawn(DynamicObject(obj))
                        }
                }
        }

}