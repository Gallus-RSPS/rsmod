package gg.rsmod.plugins.content.inter.quests.free.cooksassistant

import gg.rsmod.plugins.content.inter.quests.QuestTab

spawn_npc(4626, 3208, 3215, 0, 3)

val COOK_VARP = 29
val COOK = 4626
val MILK = Items.BUCKET_OF_MILK
val FLOUR = Items.POT_OF_FLOUR
val EGG = Items.EGG

on_npc_option(npc = COOK, option = "Talk-to"){
    player.queue {
        when(player.getVarp(COOK_VARP)) {
            0 -> notStartedDialog(this)
            1,3,4,5,6,7,8,9 -> inProgressDialog(this)
            2 -> completedDialog(this)
        }
    }
}

suspend fun notStartedDialog(it: QueueTask) {
    it.chatNpc("What am I to do?")
    when (notStartedOptions(it)) {
        1 -> whatsWrong(it)
        2 -> makeMeACake(it)
        3 -> dontLookHappy(it)
        4 -> niceHat(it)
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("What's wrong?", "Can you make me a cake?", "You don't look very happy.", "Nice hat!")

suspend fun whatsWrong(it: QueueTask) {
    it.chatPlayer("What's wrong?")
    it.chatNpc("Oh dear, oh dear, oh dear, I'm in a terrible terrible mess! It's the Duke's birthday today, and I should be making him a lovely big birthday cake.")
    it.chatNpc("I've forgotten to buy the ingredients. I'll never get them in time now. He'll sack me! What will I do? I have four children and a goat to look after. Would you help me? Please?")
    when(wouldYouHelpOptions(it)) {
        1 -> {
            it.chatPlayer("Yes, I'll help you.")
            it.player.setVarp(COOK_VARP, 1)
            it.chatNpc("Oh thank you, thank you. I need milk, an egg and flour. I'd be very greatful if you can get them for me.")
            it.chatPlayer("So where do I find these ingredients then?")
            showInProgressOptions(it)
        }
        2 -> {
            it.chatPlayer("No, I don't feel like it. Maybe later.")
            it.chatNpc("Fine. I always knew you Adventurer types were callous beasts. Go on your merry way!")
            return
        }
    }
}
suspend fun wouldYouHelpOptions(it: QueueTask): Int = it.options("I'm always happy to help a cook in distress.", "I can't right now. Maybe later.")

suspend fun makeMeACake(it: QueueTask) {
    it.chatPlayer("You're a cook, why don't you bake me a cake?")
    it.chatNpc("*sniff* Don't talk to me about cakes...")
    whatsWrong(it)
}

suspend fun dontLookHappy(it: QueueTask) {
    it.chatPlayer("You don't look very happy.")
    it.chatNpc("No, I'm not. The world is caving in around me - I am overcome by dark feelings of impending doom.")
    when(dontLookHappyOptions(it)) {
        1 -> whatsWrong(it)
        2 -> {
            it.chatPlayer("I'd take the rest of the day off if I were you.")
            it.chatNpc("No, that's the worst thing I could do. I'd get in terrible trouble.")
            it.chatPlayer("Well maybe you need to take a holiday...")
            it.chatNpc("That would be nice, but the Duke doesn't allow holidays for core staff.")
            it.chatPlayer("Hmm, why not run away to the sea and start a new life as a Pirate?")
            it.chatNpc("My wife gets sea sick, and I have an irrational fear of eyepatches. I don't see it working myself.")
            it.chatPlayer("I'm afraid I've run out of ideas.")
            it.chatNpc("I know I'm doomed.")
            whatsWrong(it)
        }
    }
}
suspend fun dontLookHappyOptions(it: QueueTask): Int = it.options("What's wrong?", "I'd take the rest of the day off if I were you.")

suspend fun niceHat(it: QueueTask) {
    it.chatPlayer("Nice hat!")
    it.chatNpc("Err thank you. It's a pretty ordinary cooks hat really.")
    it.chatPlayer("Still, suits you. The trousers are pretty special too.")
    it.chatNpc("Its all standard cook's issue uniform...")
    it.chatPlayer("The whole hat, apron, stripey trousers ensemble - it works. It make you looks like a real cook.")
    it.chatNpc("I am a real cook! I haven't got time to be chatting about Culinary Fashion. I am in desperate need of help!")
    whatsWrong(it)
}

suspend fun inProgressDialog(it: QueueTask) {
    it.chatNpc("How are you getting on with finding the ingredients?")
    checkIngredients(it)
}

suspend fun showInProgressOptions(it: QueueTask) {
    when (inProgressOptions(it)) {
        1 -> {
            it.chatNpc("There is a Mill fairly close, go North and then West. Mill Lane Mill is just off the road to Draynor. I usually get my flour from there.")
            it.chatNpc("Talk to Millie, she'll help, she's a lovely girl and a fine Miller. Make sure you take a pot with you for the flour though, there should be one on the table in here.")
            showInProgressOptions(it)
        }
        2 -> {
            it.chatNpc("There is a cattle field on the other side of the river, just across the road from the Groats' Farm.")
            it.chatNpc("Talk to Gillie Groats, she looks after the Dairy cows - she'll tell you everything you need to know about milking cows!")
            it.chatNpc("You'll need an empty bucket for the milk itself. The general store just north of the castle will sell you one for a couple of coins.")
            showInProgressOptions(it)
        }
        3 -> {
            it.chatNpc("I normally get my eggs from the Groats' farm, on the other side of the river.")
            it.chatNpc("But any chicken should lay eggs.")
            showInProgressOptions(it)
        }
        4 -> {
            it.chatPlayer("I've got all the information I need. Thanks.")
            return
        }
    }
}
suspend fun inProgressOptions(it: QueueTask): Int = it.options("Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.")

suspend fun checkIngredients(it: QueueTask) {
    val player = it.player
    val inventory = player.inventory

    if (!inventory.contains(MILK) && !inventory.contains(FLOUR) && !inventory.contains(EGG) && player.getVarp(COOK_VARP) == 1) {
        it.chatPlayer("I haven't got any of them yet, I'm still looking.")
        it.chatNpc("Please get the ingredients quickly. I'm running out of time! The Duke will throw me into the streets!")
        it.messageBox("You still need to get:<br>A bucket of milk. A Pot of flour. An egg.")
        continueSearch(it)
    }

    if (inventory.contains(MILK)) {
        inventory.remove(MILK, 1)
        when(player.getVarp(COOK_VARP)) {
            1 -> { //gave nothing
                player.setVarp(COOK_VARP, 3) //gave milk
                it.chatPlayer("Here's a bucket of milk.")
            }
            4 -> { //gave flour
                player.setVarp(COOK_VARP, 6) //gave milk and flour
                it.chatPlayer("Here's a bucket of milk.")

            }
            5 -> { //gave egg
                player.setVarp(COOK_VARP, 7) //gave milk and egg
                it.chatPlayer("Here's a bucket of milk.")
            }
            8 -> { //gave flour and egg
                player.setVarp(COOK_VARP, 9) //gave everything
                it.chatPlayer("Here's a bucket of milk.")
            }
        }
    }
    if (inventory.contains(FLOUR)) {
        inventory.remove(FLOUR, 1)
        when(player.getVarp(COOK_VARP)) {
            1 -> { //gave nothing
                player.setVarp(COOK_VARP, 4) //gave flour
                it.chatPlayer("Here's a pot of flour.")
            }
            3 -> { //gave milk
                player.setVarp(COOK_VARP, 6) //gave milk and flour
                it.chatPlayer("Here's a pot of flour.")
            }
            5 -> { //gave egg
                player.setVarp(COOK_VARP, 8) //gave egg and flour
                it.chatPlayer("Here's a pot of flour.")
            }
            7 -> { //gave milk and egg
                player.setVarp(COOK_VARP, 9) //gave everything
                it.chatPlayer("Here's a pot of flour.")
            }
        }
    }
    if (inventory.contains(EGG)) {
        inventory.remove(EGG, 1)
        when(player.getVarp(COOK_VARP)) {
            1 -> { //gave nothing
                player.setVarp(COOK_VARP, 5) //gave egg
                it.chatPlayer("Here's a fresh egg.")
            }
            3 -> { //gave milk
                player.setVarp(COOK_VARP, 7) //gave milk and egg
                it.chatPlayer("Here's a fresh egg.")
            }
            4 -> { //gave flour
                player.setVarp(COOK_VARP, 8) //gave flour and egg
                it.chatPlayer("Here's a fresh egg.")
            }
            6 -> { //gave milk and flour
                player.setVarp(COOK_VARP, 9) //gave everything
                it.chatPlayer("Here's a fresh egg.")
            }
        }
    }
    if (player.getVarp(COOK_VARP) >= 3 && player.getVarp(COOK_VARP) <= 8) {
        it.chatNpc("Thanks for the ingredients you have got so far, please get the rest quickly. I'm running out of time! The Duke will throw me into the streets!")
        when(player.getVarp(COOK_VARP)) {
            3 -> {it.messageBox("You still need to get:<br>A pot of flour. An egg.")}
            4 -> {it.messageBox("You still need to get:<br>A bucket of milk. An egg.")}
            5 -> {it.messageBox("You still need to get:<br>A bucket of milk. A pot of flour.")}
            6 -> {it.messageBox("You still need to get:<br>An egg.")}
            7 -> {it.messageBox("You still need to get:<br>A pot of flour.")}
            8 -> {it.messageBox("You still need to get:<br>A bucket of milk.")}
        }
        continueSearch(it)
    } else if (player.getVarp(COOK_VARP) == 9) {
        allIngredients(it)
    }
}
suspend fun continueSearch(it: QueueTask) {
    when (continueOptions(it)) {
        1 -> {
            it.chatPlayer("I'll get right on it.")
            return
        }
        2 -> {
            it.chatPlayer("So where do I find these ingredients then?")
            showInProgressOptions(it)
        }
    }
}
suspend fun continueOptions(it: QueueTask): Int = it.options("I'll get right on it.", "Can you remind me how to find these things again?")

suspend fun allIngredients(it: QueueTask) {
    val player = it.player
    it.chatNpc("You've brought me everything I need! I am saved! Thank you!")
    it.chatPlayer("So do I get to go to the Duke's Party?")
    it.chatNpc("I'm afraid not, only the big cheeses get to dine with the Duke.")
    it.chatPlayer("Well, maybe one day I'll be important enough to sit on the Duke's table.")
    it.chatNpc("Maybe, but I won't be holding my breath.")
    player.setVarp(COOK_VARP, 2)
    questComplete(it)
}

suspend fun questComplete(it: QueueTask) {
    val player = it.player
    var qp = player.getVarp(QuestTab.QP_VARP)
    player.setVarp(QuestTab.QP_VARP, (qp + 1))
    player.addXp(Skills.COOKING, 300.0)
    player.message("Congratulations! Quest complete!")
    player.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = Items.CAKE, amountOrZoom = 250)
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed the Cook's Assistant Quest!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${player.getVarp(QuestTab.QP_VARP)}")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "1 Quest Point")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "300 Cooking XP")
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