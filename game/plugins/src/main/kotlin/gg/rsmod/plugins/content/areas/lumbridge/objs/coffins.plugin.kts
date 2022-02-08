package gg.rsmod.plugins.content.areas.lumbridge.objs

import gg.rsmod.plugins.content.inter.quests.QuestTab
import gg.rsmod.plugins.content.inter.quests.free.restlessghost.TheRestlessGhost

val OPEN_SFX = 54
val CLOSE_SFX = 53

val GHOST_SPAWNED = AttributeKey<Boolean>()

val GHOST_SPAWN = TimerKey()
val GHOST_SPAWN_PLAYER = TimerKey()
val RG_COFFIN = TimerKey()

val COFFIN_TILE = Tile(3249,3192,0)

val COFFIN_CLOSED = DynamicObject(Objs.COFFIN_2145, 10, 0, COFFIN_TILE)
val COFFIN_OPEN = DynamicObject(Objs.COFFIN_15053, 10, 0, COFFIN_TILE)

val START_TILE = Tile(3250, 3192, 0)
val TARGET_TILE = Tile(3250, 3195, 0)

val moveToTile = Tile(3250,3194,0)
val projectileTile1 = Tile(3252,3190,0)
val projectileTile2 = Tile(3256,3186,0)

lateinit var GHOST_NPC: Npc

on_logout {
    player.attr.set(GHOST_SPAWNED, false)
}
on_login {
    player.attr.set(GHOST_SPAWNED, false)
}

on_npc_spawn(npc = Npcs.RESTLESS_GHOST) {
    val npc = npc
    npc.timers[GHOST_SPAWN] = 150 //about a 1.5 minutes to despawn
}

on_timer(GHOST_SPAWN) {
    val npc = npc
    world.npcs.remove(npc)
}
on_timer(GHOST_SPAWN_PLAYER) {
    player.attr.set(GHOST_SPAWNED, false)
}

on_timer(RG_COFFIN) {
    world.spawn(COFFIN_CLOSED)
}

on_obj_option(obj = Objs.COFFIN_2145, option = "open") {
    open(player, player.getInteractingGameObj())
}

on_obj_option(obj = Objs.COFFIN_15053, option = "close") {
    close(player, player.getInteractingGameObj())
}

on_obj_option(obj = Objs.COFFIN_15053, option = "search") {
    when (player.getVarp(107)) {
        0,1,2 -> player.message("You search the coffin and find some human remains.")
        3,4 -> {
            player.queue {
                messageBox("There's a skeleton without a skull in here.")
            }
        }
        5 -> {
            player.queue {
                messageBox("There's a nice and complete skeleton in here!")
            }
        }
    }
    if (player.getVarp(107) < 5) {
        spawn_ghost(player)
    }
}

fun open(p: Player, obj: GameObject) {
   p.playSound(OPEN_SFX)
    p.message("You open the coffin.")
    world.spawn(COFFIN_OPEN)
    world.timers[RG_COFFIN] = 300
    if (p.getVarp(107) < 5) {
        spawn_ghost(p)
    }
}

fun spawn_ghost(p: Player) {
    val spawnTile = Tile(3250,3195,0)
    val restGhost = Npc(p, Npcs.RESTLESS_GHOST, spawnTile, world)
    GHOST_NPC = restGhost
    val ghostProjectile = p.createProjectile(TARGET_TILE, gfx = 668, startHeight = 15, endHeight = 15, delay = 51, angle = 15, lifespan = 100)

    p.queue {
        if (p.attr.get(GHOST_SPAWNED) == false) {
            p.timers[GHOST_SPAWN_PLAYER] = 150
            p.attr.set(GHOST_SPAWNED, true)
            world.spawn(ghostProjectile)
            p.playSound(1743)
            wait(5)
            world.spawn(GHOST_NPC)
            p.playSound(1595)
            GHOST_NPC.faceTile(Tile(3250,3194,0))
            GHOST_NPC.animate(4019)
        } else {

        }
    }
}

fun close(p: Player, obj: GameObject) {
    p.playSound(CLOSE_SFX)
    p.message("You close the coffin.")
    world.spawn(COFFIN_CLOSED)

}

fun Player.createProjectile(target: Tile, gfx: Int, startHeight: Int, endHeight: Int, delay: Int, angle: Int, lifespan: Int): Projectile {
    val builder = Projectile.Builder()
        .setTiles(start = START_TILE, target = target)
        .setGfx(gfx = gfx)
        .setHeights(startHeight = startHeight, endHeight = endHeight)
        .setSlope(angle = angle, steepness = Math.min(255, ((getSize() shr 1) + 1) * 32))
        .setTimes(delay = delay, lifespan = lifespan)

    return builder.build()
}

fun Player.createProjectile2(target: Tile, gfx: Int, startHeight: Int, endHeight: Int, delay: Int, angle: Int, lifespan: Int): Projectile {
    val builder = Projectile.Builder()
        .setTiles(start = GHOST_NPC.tile, target = target)
        .setGfx(gfx = gfx)
        .setHeights(startHeight = startHeight, endHeight = endHeight)
        .setSlope(angle = angle, steepness = Math.min(255, ((getSize() shr 1) + 1) * 32))
        .setTimes(delay = delay, lifespan = lifespan)

    return builder.build()
}

fun Player.createProjectile3(target: Tile, gfx: Int, startHeight: Int, endHeight: Int, delay: Int, angle: Int, lifespan: Int): Projectile {
    val builder = Projectile.Builder()
        .setTiles(start = GHOST_NPC.tile, target = target)
        .setGfx(gfx = gfx)
        .setHeights(startHeight = startHeight, endHeight = endHeight)
        .setSlope(angle = angle, steepness = Math.min(255, ((getSize() shr 1) + 1) * 32))
        .setTimes(delay = delay, lifespan = lifespan)

    return builder.build()
}

fun Player.createProjectile4(target: Tile, gfx: Int, startHeight: Int, endHeight: Int, delay: Int, angle: Int, lifespan: Int): Projectile {
    val builder = Projectile.Builder()
        .setTiles(start = projectileTile1, target = target)
        .setGfx(gfx = gfx)
        .setHeights(startHeight = startHeight, endHeight = endHeight)
        .setSlope(angle = angle, steepness = Math.min(255, ((getSize() shr 1) + 1) * 32))
        .setTimes(delay = delay, lifespan = lifespan)

    return builder.build()
}


// FINISH RESTLESS GHOST
on_item_on_obj(15053, 553) {

    if (!GHOST_NPC.isSpawned()) {
        spawn_ghost(player)
    }
    player.queue {
        player.animate(832)
        player.lock()
        player.inventory.remove(553)
        wait(2)
        player.facePawn(GHOST_NPC)
        GHOST_NPC.facePawn(player)
        GHOST_NPC.forceChat("Release! Thank you")
        wait(2)
        GHOST_NPC.forceChat("stranger..")
        wait(1)
        val ghostProjectile1 = player.createProjectile2(GHOST_NPC.tile, gfx = 668, startHeight = 15, endHeight = 15, delay = 51, angle = 15, lifespan = 100)
        val ghostProjectile2 = player.createProjectile3(projectileTile1, gfx = 668, startHeight = 15, endHeight = 15, delay = 51, angle = 15, lifespan = 100)
        val ghostProjectile3 = player.createProjectile4(projectileTile2, gfx = 668, startHeight = 15, endHeight = 15, delay = 51, angle = 15, lifespan = 100)
        //world.spawn(ghostProjectile1)
        GHOST_NPC.animate(4018)
        GHOST_NPC.graphic(668)
        wait(2)
        world.remove(GHOST_NPC)
        world.spawn(ghostProjectile2)
        wait(3)
        world.spawn(ghostProjectile3)
        wait(3)
        player.unlock()
        player.setVarp(107, 5)
        questComplete(this)
    }
}

suspend fun questComplete(it: QueueTask) {
    val player = it.player
    var qp = player.getVarp(QuestTab.QP_VARP)
    player.setVarp(QuestTab.QP_VARP, (qp + 1))
    player.message("Congratulations! Quest complete!")
    player.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = 553, amountOrZoom = 250)
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed The Restless Ghost!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${player.getVarp(QuestTab.QP_VARP)}")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "1 Quest Point")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "1,125 Prayer XP")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 10, text = "A Ghostspeak Amulet")
    for (x in 11..14) {
        player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = x, text = "")
    }
    player.openInterface(QuestTab.REWARD_ID, InterfaceDestination.MAIN_SCREEN)
}