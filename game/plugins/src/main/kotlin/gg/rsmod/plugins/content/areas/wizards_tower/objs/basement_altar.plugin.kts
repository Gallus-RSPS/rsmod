package gg.rsmod.plugins.content.areas.wizards_tower.objs

import gg.rsmod.game.model.attr.INTERACTING_ITEM_SLOT

val SKULL_VARBIT = 2130
val SKELETON_VARBIT = 2128

val SKELETON_TILE = Tile(3120,9569,0)
val SKELETON_SPAWNED = AttributeKey<Boolean>()
val SKELETON_OBJECT = DynamicObject(15060, 10, 0, SKELETON_TILE)


lateinit var SKELETON_NPC: Npc

val ALTAR_TIMER = TimerKey()
val SKELETON_TIMER = TimerKey()
val SKELETON_TIMER_PLAYER = TimerKey()

on_world_init {
    world.spawn(SKELETON_OBJECT)
}

on_logout {
    player.attr.set(SKELETON_SPAWNED, false)
    player.setVarbit(SKELETON_VARBIT, 0)
    player.setVarbit(SKULL_VARBIT, 0)
}
on_login {
    player.attr.set(SKELETON_SPAWNED, false)
    player.setVarbit(SKELETON_VARBIT, 0)
    player.setVarbit(SKULL_VARBIT, 0)
}

can_drop_item(553) {
    val slot = player.attr[INTERACTING_ITEM_SLOT] ?: return@can_drop_item false

    player.queue {
        itemMessageBox("Dropping this skull here will destroy it!", 553, 400)
        DestroySkull(this)
        /*val destroy = destroyItem("Are you sure you want to drop the skull?", "test", 553, 1)
        if (destroy) {
            player.inventory.remove(item = 553, amount = 1, beginSlot = slot)
            player.setVarbit(SKULL_VARBIT, 0)
        }*/
    }
    return@can_drop_item false
}

suspend fun DestroySkull(it: QueueTask) {
    when (destroyOptions(it)) {
        1 -> {
            it.player.inventory.remove(553)
            it.player.setVarbit(2130, 0)
        }
        2 -> {}
    }
}
suspend fun destroyOptions(it: QueueTask): Int = it.options("Yes", "No", title = "Are you sure you want to drop the skull?")


on_timer(SKELETON_TIMER) {
    player.setVarbit(SKELETON_VARBIT, 0)
    world.remove(SKELETON_NPC)
}

on_timer(SKELETON_TIMER_PLAYER) {
    player.attr.set(SKELETON_SPAWNED, false)
}

on_obj_option(2148, "Climb-up") {
    player.climbUp()
}

on_obj_option(15050, "search") {
    when (player.getVarp(107)) {
        0,1,2 -> {
            player.queue {
                messageBox("That skull looks scary. I've got no reason to take it, I think I'll leave it alone.")
            }
        }
        3,4 -> {
            search(player, player.getInteractingGameObj())
        }
    }
}

on_obj_option(15051, "search") {
    when (player.getVarp(107)) {
        1,2,3,4 -> {
            player.queue {
                messageBox("You already have the Ghost's skull.")
            }
        }
        5 -> {
            player.queue {
                messageBox("Surprisingly, this altar is empty.\nProbably because you took the skull off it earlier.")
            }
        }
    }
}

fun search(p: Player, obj: GameObject) {
    val skeletonNpc = Npc(p, 924, SKELETON_TILE, world)
    SKELETON_NPC = skeletonNpc
    if (!p.inventory.contains(553) && p.inventory.hasSpace && p.getVarp(107) >= 3){
        p.setVarp(107, 4)
        p.setVarbit(SKULL_VARBIT, 1)
        p.inventory.add(553, 1)
    }
    if (p.attr[SKELETON_SPAWNED] == false)
        SpawnSkeleton(p)
}

fun SpawnSkeleton(p: Player) {
    if (p.attr[SKELETON_SPAWNED] == true){

    } else {
        p.queue {
            player.message("The skeleton in the corner suddenly comes to life!")
            p.attr.set(SKELETON_SPAWNED, true)
            p.timers[SKELETON_TIMER_PLAYER] = 150
            p.setVarbit(SKELETON_VARBIT, 1)
            p.timers[SKELETON_TIMER] = 150
            world.spawn(SKELETON_NPC)
            SKELETON_NPC.respawns = false
            SKELETON_NPC.walkRadius = 6
            SKELETON_NPC.animate(4017)
            wait(3)
            SKELETON_NPC.attack(p)
        }
    }
}

fun Player.climbUp() {
    queue {
        player.moveTo(3105, 3162, 0)
        player.attr.set(SKELETON_SPAWNED, false)
    }
}