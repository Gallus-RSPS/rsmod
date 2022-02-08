package gg.rsmod.plugins.content.inter.quests.free.earnestthechicken

import gg.rsmod.plugins.content.inter.quests.QuestTab

/**
 * Varp Stages
 * 0 - Not Started
 * 1 - Talked to Veronica
 * 2 - Talked to Prof. Oddenstein
 * 3 - Finished Quest
 */
val ERNEST_VARP = 32

val VERONICA = Npcs.VERONICA
val ERNEST = Npcs.ERNEST
val ERNEST_CHICKEN = Npcs.CHICKEN_3564
var ernestChickenNpc = Npc(ERNEST_CHICKEN, Tile(3110,3363,2), world)
var ernestHumanNpc = Npc(ERNEST, Tile(3110,3363,2), world)
val CLUCK_DELAY = TimerKey()
val ERNEST_DESPAWN = TimerKey()
val PROF_ODDENSTEIN = Npcs.PROFESSOR_ODDENSTEIN

val DOOR = 131
val COMPOST_HEAP = 152
val FOUNTAIN = 153

val PRESSURE_GAUGE = Items.PRESSURE_GAUGE
val RUBBER_TUBE = Items.RUBBER_TUBE
spawn_item(RUBBER_TUBE, 1, 3111,3367, 0, 10)
val OIL_CAN = Items.OIL_CAN
val KEY = 275
val FISH_FOOD = Items.FISH_FOOD
spawn_item(FISH_FOOD, 1, 3108, 3356, 1, 10)
val POISON = Items.POISON
spawn_item(POISON, 1, 3097, 3366, 0, 10)
val POISONED_FISH_FOOD = Items.POISONED_FISH_FOOD

/**
 * Attributes that store states of the quest
 */
val FOOD_ON_FOUNTAIN = AttributeKey<Boolean>(persistenceKey = "food_on_fountain")
val GIVEN_RUBBER_TUBE = AttributeKey<Boolean>(persistenceKey = "given_rubber_tube")
val GIVEN_PRESSURE_GAUGE = AttributeKey<Boolean>(persistenceKey = "given_pressure_gauge")
val GIVEN_OIL_CAN = AttributeKey<Boolean>(persistenceKey = "given_oil_can")
//player.attr.remove(attribute) to remove when no longer needed

spawn_npc(VERONICA, 3110, 3329, 0, 2)
spawn_npc(PROF_ODDENSTEIN, 3110,3367,2, 2)

on_world_init {
    val interdimentionalPortal = DynamicObject(id = 11355, type = 10, rot = 0, tile = Tile(3110,3362,2))
    world.spawn(interdimentionalPortal)
}

on_obj_option(11511, "Climb-up") {
    player.queue {
        ernestChickenNpc = Npc(player, ERNEST_CHICKEN, Tile(3110,3363,2), player.world)
        ernestChickenNpc.walkRadius = 5
        player.moveTo(3105, 3364, 2)
        player.faceTile(Tile(3105, 3363, 2))
        if(player.getVarp(ERNEST_VARP) != 3) {
            world.spawn(ernestChickenNpc)
        }
    }
}
on_obj_option(9584, "Climb-down") {
    player.queue {
        player.moveTo(3106, 3363, 1)
        player.faceTile(Tile(3055, 3312, 1))
        world.remove(ernestChickenNpc)
    }
}

on_item_on_item(POISON, FISH_FOOD) {
    player.queue {
        player.message("You poison the fish food.")
        player.inventory.remove(POISON, 1)
        player.inventory.remove(FISH_FOOD, 1)
        player.inventory.add(POISONED_FISH_FOOD, 1)
    }
}

on_obj_option(DOOR, "Open") {
    player.queue {
        if(!player.inventory.contains(KEY)) {
            player.message("The door is locked.")
        } else if (player.inventory.contains(KEY)){
            if(player.tile.x <= 3107) {
                player.moveTo(Tile(player.tile.x + 1, player.tile.z))
            } else if (player.tile.x >= 3108) {
                player.moveTo(Tile(player.tile.x - 1, player.tile.z))
            }
        }
    }
}

on_obj_option(COMPOST_HEAP, "Search") {
    player.queue {
        if(!player.inventory.contains(Items.SPADE)) {
            player.message("Nothing interesting happens.")
            return@queue
        }
        player.message("You dig through the compost...")
        player.animate(830)
        wait(1)
        if (!player.inventory.contains(KEY)) {
            player.inventory.add(KEY, 1)
            player.message("...and find a small key.")
        } else {
            player.message("...and find nothing of interest.")
        }
    }
}

on_obj_option(FOUNTAIN, "Search") {
    player.queue {
        if(!player.attr.has(FOOD_ON_FOUNTAIN)) {
            player.animate(881)
            this.chatPlayer("There seems to be a pressure gauge in here...")
            player.hit(1)
            player.forceChat("Ow!")
            this.chatPlayer("... and a lot of piranhas!<br>I can't get the gauge out.")
        } else if(player.attr.has(FOOD_ON_FOUNTAIN)) {
            player.animate(881)
            this.chatPlayer("There seems to be a pressure gauge in here...")
            this.chatPlayer("...and a lot of dead fish.")
            player.inventory.add(PRESSURE_GAUGE, 1)
            player.message("You retrieve the pressure gauge from the fountain.")
        }
    }
}
on_item_on_obj(FOUNTAIN, POISONED_FISH_FOOD) {
    player.queue{
        player.attr[FOOD_ON_FOUNTAIN] = true
        player.inventory.remove(POISONED_FISH_FOOD, 1)
        player.lock()
        player.animate(537)
        player.message("You pour the poisoned fish food into the fountain.")
        wait(1)
        player.unlock()
        player.message("The piranhas start eating the food...")
        wait(2)
        player.message("...then die and float to the surface.")

    }
}

on_npc_option(npc = VERONICA, option = "Talk-to"){
    player.queue {
        when(player.getVarp(ERNEST_VARP)) {
            0 -> notStartedDialog(this)
            1 -> beforeOddenstein(this)
            2 -> afterOddenstein(this)
            3 -> finishedVeronica(this)
        }
    }
}

on_npc_option(npc = PROF_ODDENSTEIN, option = "Talk-to") {
    player.queue {
        when(player.getVarp(ERNEST_VARP)) {
            0 -> oddensteinNotStarted(this)
            1 -> oddensteinStart(this)
            2 -> oddensteinStarted(this)
            3 -> finishedOddenstein(this)
        }
    }
}
suspend fun missingDialog(it: QueueTask) {
    it.messageBox("This dialog is missing.")
    return
}

suspend fun notStartedDialog(it: QueueTask) {
    it.chatNpc("Can you please help me? I'm in a terrible spot of trouble.")
    when (notStartedOptions(it)) {
        1 -> illHelp(it)
        2 -> killSomething(it)
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("Aha, sounds like a quest. I'll help.", "No, I'm looking for something to kill.")

suspend fun finishedVeronica(it: QueueTask) {
    it.chatNpc("Thank you for rescuing Ernest.")
    it.chatPlayer("Where is he now?")
    it.chatNpc("Oh he went off to talk to some green warty guy. I'm sure he'll be back soon.")
}

suspend fun finishedOddenstein(it: QueueTask) {
    when (finishedOddensteinOptions(it)) {
        1 -> portalMachine(it)
        2 -> isThisYourHouse(it)
    }
}
suspend fun finishedOddensteinOptions(it: QueueTask): Int = it.options("What does the portal machine do?", "Is this your house?")

suspend fun portalMachine(it: QueueTask) {
    it.chatPlayer("What does the portal machine do?")
    it.chatNpc("Ah, an interesting question! I believe it should create a rift in the very fabric of space! Opening a portal to some, hitherto undiscovered plane that exists in parallel to our own.")
    it.chatPlayer("Huh?")
    it.chatNpc("It's a teleport machine.")
}

suspend fun illHelp(it: QueueTask) {
    val player = it.player
    it.chatPlayer("Aha, sounds like a quest. I'll help.")
    it.chatNpc("Seeing as we were a little lost Ernest decided to go in and ask for directions.")
    it.chatNpc("That was an hour ago. That house looks spooky, can you go and see if you can find him for me?")
    it.chatPlayer("Ok, I'll see what I can do.")
    player.setVarp(ERNEST_VARP, 1)

    it.chatNpc("Thank you, thank you. I'm very grateful.")
    return
}

suspend fun killSomething(it: QueueTask) {
    it.chatPlayer("No, I'm looking for something to kill.")
    it.chatNpc("Oh. I'm so worried. I hope someone will help me soon.")
}

suspend fun beforeOddenstein(it: QueueTask) {
    it.chatNpc("Have you found my sweetheart yet?")
    it.chatPlayer("No, not yet.")
}
suspend fun afterOddenstein(it: QueueTask) {
    it.chatNpc("Have you found my sweetheart yet?")
    it.chatPlayer("Yes, he's a chicken.")
    it.chatNpc("I know he's not exactly brave, but I think you're being a bit harsh.")
    it.chatPlayer("No, no, he's been turned into an actual chicken by a mad scientist.")
    it.chatNpc("Eeeeeek! My poor darling! Why must these things happen to us?")
    it.chatPlayer("I'm doing my best to turn him back.")
    it.chatNpc("Well, be quick. I'm sure being a chicken can't be good for him.")
}

suspend fun oddensteinNotStarted(it: QueueTask) {
    when(rand(1,3)) {
        1 -> it.chatNpc("Be careful in here, there's lots of dangerous equipment.")
        2 -> it.chatNpc("Woah, careful there, that equipment is dangerous.")
        3 -> it.chatNpc("Careful now, there's dangerous equipment in here.")
    }
}

suspend fun oddensteinStart(it: QueueTask) {
    it.chatNpc("Be careful in here, there's lots of dangerous equipment.")
    when (oddensteinStartOptions(it)) {
        1 -> lookingForErnest(it)
        2 -> whatDoesThisMachineDo(it)
        3 -> isThisYourHouse(it)
    }
}
suspend fun oddensteinStartOptions(it: QueueTask): Int = it.options("I'm looking for a guy called Ernest.", "What does this machine do?", "Is this your house?")

suspend fun lookingForErnest(it: QueueTask) {
    it.chatPlayer("I'm looking for a guy called Ernest.")
    it.chatNpc("Ah, Ernest - top notch bloke - he's helping me with my experiments.")
    it.chatPlayer("So you know where he is?")
    it.chatNpc("He's that chicken over there.")
    it.chatPlayer("Ernest is a chicken? Are you sure?")
    it.chatNpc("Oh, he isn't normally a chicken, or, at least, he wasn't until he helped me test my pouletmorph machine.")
    it.chatNpc("It was originally going to be called the transmutation machine, but after testing it, pouletmorph seems more appropriate.")
    when (lookingForErnestOptions(it)) {
        1 -> {
            it.chatPlayer("I'm glad Veronica didn't actually get engaged to a chicken.")
            it.chatNpc("Who's Veronica?")
            it.chatPlayer("Ernest's fiancee. She probably doesn't want to marry a chicken.")
            it.chatNpc("Oh, I dunno. She could have free eggs for breakfast every morning.")
            it.chatPlayer("I think you'd better change him back.")
            oddensteinContinued(it)
        }
        2 -> {
            it.chatPlayer("Change him back this instant!")
            oddensteinContinued(it)
        }
    }
}
suspend fun lookingForErnestOptions(it: QueueTask): Int = it.options("I'm glad Veronica didn't actually get engaged to a chicken.", "Change him back this instant!")

suspend fun whatDoesThisMachineDo(it: QueueTask) {
    it.chatNpc("Nothing at the moment...it's broken. It's mean to be a transmutation machine.")
    it.chatNpc("It has also spend time as a time travel machine, a dramatic lightning generator, and a thing for generating monsters.")
}
suspend fun isThisYourHouse(it: QueueTask) {
    it.chatNpc("No, I'm just one of the tenants. It belongs to the count who lives in the basement.")
}

suspend fun oddensteinContinued(it: QueueTask) {
    val player = it.player

    it.chatNpc("Umm... It's not so easy...")
    it.chatNpc("My machine is broken, and the house gremlins have run off with some vital bits.")
    it.chatPlayer("Well I can look for them.")
    it.chatNpc("That would be a help. They'll be somewhere in the manor house or its grounds, the gremlins never get further than the entrance gate.")
    it.chatNpc("I'm missing the pressure gauge and a rubber tube. They've also taken my oil can, which I'm going to need to get this thing started again.")
    player.setVarp(ERNEST_VARP, 2)
}

suspend fun oddensteinStarted(it: QueueTask) {
    val player = it.player
    val inventory = player.inventory
    it.chatNpc("Have you found anything yet?")
    when(checkItems(it)) {
        0 -> oddensteinNoItems(it)
        1 -> oddensteinOneItem(it)
        2 -> oddensteinTwoItems(it)
        3 -> oddensteinAllItems(it)
    }
}

suspend fun oddensteinNoItems(it: QueueTask) {
    it.chatPlayer("I'm afraid I don't have any of them yet.")
    it.chatNpc("I need a rubber tube, a pressure gauge, and a can of oil. Then your friend can stop being a chicken.")
}

suspend fun oddensteinOneItem(it: QueueTask) {
    val player = it.player
    val inventory = player.inventory

    if (inventory.contains(RUBBER_TUBE)) {
        it.chatPlayer("I've only got the rubber tube so far.")
        it.chatNpc("It's a good start, but I'll need my pressure gauge and oil can too.")
    }
    if (inventory.contains(PRESSURE_GAUGE)) {
        it.chatPlayer("I've only got the pressure gauge so far.")
        it.chatNpc("It's a good start, but I'll need my oil can and rubber tube too.")
    }
    if (inventory.contains(OIL_CAN)) {
        it.chatPlayer("I've only got the oil can so far.")
        it.chatNpc("It's a good start, but I'll need my pressure gauge and rubber tube too.")
    }
    it.chatPlayer("Okay, I'll go look for them.")
}
suspend fun oddensteinTwoItems(it: QueueTask) {
    val player = it.player
    val inventory = player.inventory

    if (inventory.contains(RUBBER_TUBE) && inventory.contains(PRESSURE_GAUGE)) {
        it.chatPlayer("I've got the rubber tube and the pressure gauge.")
        it.chatNpc("That's good, but I still need my oil can.")
    }
    if (inventory.contains(PRESSURE_GAUGE) && inventory.contains(OIL_CAN)) {
        it.chatPlayer("I've got the pressure gauge and the oil can.")
        it.chatNpc("That's good, but I still need my rubber tube.")
    }
    if (inventory.contains(RUBBER_TUBE) && inventory.contains(OIL_CAN)) {
        it.chatPlayer("I've got the rubber tube and the oil can.")
        it.chatNpc("That's good, but I still need my pressure gauge.")
    }
    it.chatPlayer("Okay, I'll go look for it.")
}
suspend fun oddensteinAllItems(it: QueueTask) {
    val player = it.player
    val inventory = player.inventory
    val profNpc = player.getInteractingNpc()
    val machineTile = Tile(3112,3366,2)
    val ernestSpawn = ernestChickenNpc.tile
    ernestHumanNpc = Npc(player, ERNEST, ernestSpawn, player.world)
    ernestHumanNpc.walkRadius = 5
    val gender = when(player.appearance.gender) {
        Gender.MALE -> "sir"
        Gender.FEMALE -> "m'lady"
    }

    it.chatPlayer("I have everything!")
    it.chatNpc("Give 'em here then.")
    inventory.remove(RUBBER_TUBE, 1)
    inventory.remove(PRESSURE_GAUGE, 1)
    inventory.remove(OIL_CAN, 1)
    player.message("You give a rubber tube, a pressure gauge,")
    player.message("and a can of oil to the professor.")
    profNpc.faceTile(machineTile)
    it.wait(1)
    profNpc.animate(881)
    player.message("Oddenstein starts up the machine.")
    it.wait(2)
    player.message("The machine hums and shakes.")
    it.wait(3)
    world.remove(ernestChickenNpc)
    world.spawn(ernestHumanNpc)
    it.chatNpc("Thank you ${gender}. It was dreadfully irritating being a chicken. How can I ever thank you?", ERNEST)
    it.chatPlayer("Well a cash reward is always nice...")
    it.chatNpc("Of course, of course.", ERNEST)
    player.message("Ernest hands you 300 coins.")
    inventory.add(995, 300)
    player.setVarp(ERNEST_VARP, 3)
    it.wait(3)
    player.timers[ERNEST_DESPAWN] = 5
    questComplete(it)

}

suspend fun questComplete(it: QueueTask) {
    val player = it.player
    var qp = player.getVarp(QuestTab.QP_VARP)
    player.setVarp(QuestTab.QP_VARP, (qp + 4))
    player.message("Congratulations! Quest complete!")
    player.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = Items.FEATHER, amountOrZoom = 250)
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed the Ernest The Chicken Quest!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${player.getVarp(QuestTab.QP_VARP)}")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "4 Quest Point")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "300 coins")
    for (x in 10..14) {
        player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = x, text = "")
    }
    player.openInterface(QuestTab.REWARD_ID, InterfaceDestination.MAIN_SCREEN)
}

suspend fun completedDialog(it: QueueTask) {
    it.chatNpc("Hello friend, how is the adventuring going?")
    when (completedOptions(it)) {
        1 -> {
            it.chatPlayer("I am getting strong and mighty. Grrr")
            it.chatNpc("Glad to hear it.")
            return
        }
        2 -> {
            it.chatPlayer("I keep on dying.")
            it.chatNpc("Ah well, at least you keep coming back to life!")
            return
        }
        3 -> {
            it.chatPlayer("Can I use your range?")
            it.chatNpc("Go ahead - it's a very good range. It's easier to use than most other ranges.")
            it.chatNpc("Its called the Cook-o-matic 100. It uses a combination of state of the art temperature regulation and magic.")
            it.chatPlayer("Will it mean my food will burn less often?")
            it.chatNpc("Well, that's what the salesman told us anyway...")
            it.chatPlayer("Thanks?")
            return
        }
    }
}
suspend fun completedOptions(it: QueueTask): Int = it.options("I am getting strong and mighty.", "I keep on dying.", "Can I use your range?")

fun checkItems(it: QueueTask): Int {
    val player = it.player
    val inventory = player.inventory
    var itemCount = 0
    if (inventory.contains(PRESSURE_GAUGE))
        itemCount += 1
    if (inventory.contains(RUBBER_TUBE))
        itemCount += 1
    if (inventory.contains(OIL_CAN))
        itemCount += 1
    return itemCount
}


on_npc_spawn(npc = ERNEST_CHICKEN) {
    ernestChickenNpc.timers[CLUCK_DELAY] = world.random(15..25)
}

on_npc_spawn(npc = ERNEST) {
    ernestHumanNpc.timers[ERNEST_DESPAWN] = 5
}

on_timer(CLUCK_DELAY) {
    val npc = npc
    npc.forceChat("Cluck!")
    npc.timers[CLUCK_DELAY] = world.random(15..25)
}

on_timer(ERNEST_DESPAWN) {
    world.remove(ernestHumanNpc)
}

fun rand(start: Int, end: Int): Int {
    require(start <= end) { "Illegal Argument" }
    return (start..end).random()
}