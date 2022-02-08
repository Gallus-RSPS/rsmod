package gg.rsmod.plugins.content.inter.quests.free.restlessghost

/**
 * Varp Stages
 * 0 - Not Started
 * 1 - Talked to Father Aerek
 * 2 - Talked to Father Urhney
 * 3 - Talked to Ghost
 * 4 - Got Skull
 * 5 - Finished Quest
 */
val RG_VARP = 107

val FATHER_AERECK = Npcs.FATHER_AERECK
val FATHER_URHNEY = Npcs.FATHER_URHNEY
val RESTLESS_GHOST = Npcs.RESTLESS_GHOST
val SKULL = Items.SKULL

on_npc_option(RESTLESS_GHOST, "talk-to") {
    player.queue {
        when(player.getVarp(RG_VARP)) {
            0 -> ghostNotStarted(this)
            1,2,3,4 -> ghostStarted(this)
        }
    }
}

on_npc_option(FATHER_AERECK, "talk-to") {
    player.queue {
        when(player.getVarp(RG_VARP)) {
            0 -> aereckNotStarted(this)
            1 -> aereckStarted(this)
            2 -> aereckStarted(this)
            3 -> aereckStarted(this)
            4 -> aereckStarted(this)
            5 -> {}
        }
    }
}

on_npc_option(FATHER_URHNEY, "talk-to") {
    player.queue {
        urhneyDialog(this)
    }
}

suspend fun missingDialog(it: QueueTask) {
    it.messageBox("This dialog is missing.")
    return
}


//FATHER AERECK DIALOG
suspend fun aereckNotStarted(it: QueueTask) {
    it.chatNpc("Welcome to the church of holy Saradomin.")

    when (notStartedOptions(it)) {
        1 -> whosSaradomin(it)
        2 -> nicePlace(it)
        3 -> lookingForQuest(it)
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("Who's Saradomin?", "Nice place you've got here.", "I'm looking for a quest!")

suspend fun whosSaradomin(it: QueueTask) {
    it.chatPlayer("Who's Saradomin?")
    it.chatNpc("Surely you have heard of the god, Saradomin?")
    it.chatNpc("He who creates the forces of goodness and purity in this world? I cannot believe your ignorance!")
    it.chatNpc("This is the god with more followers than any other! ...At lesst in this part of the world.")
    it.chatNpc("He who created this world along with his brothers Guthix and Zamorak?")

    when (whosSaradominOptions(it)) {
        1 -> ohThatSara(it)
        2 -> notFromThisWorld(it)
    }
}
suspend fun whosSaradominOptions(it: QueueTask): Int = it.options("Oh, THAT Saradomin...", "Oh, sorry. I'm not from this world.")

suspend fun ohThatSara(it: QueueTask) {
    it.chatPlayer("Oh, THAT Saradomin...")
    it.chatNpc("There... is only one Saradomin...")
    it.chatPlayer("Yeah... I, uh, thought you said something else.")
}
suspend fun notFromThisWorld(it: QueueTask) {
    it.chatPlayer("Oh, sorry. I'm not from this world.")
    it.chatNpc("...")
    it.chatNpc("That's... strange.")
    it.chatNpc("I thought things not from this world were all... You know. Slime and tentacles.")

    when (notFromThisWorldOptions(it)) {
        1 -> onlineGame(it)
        2 -> likeMyDisguise(it)
    }
}
suspend fun notFromThisWorldOptions(it: QueueTask): Int = it.options("You don't understand. This is an online game!", "I am - do you like my disguise?")

suspend fun onlineGame(it: QueueTask) {
    it.chatPlayer("You don't understand. This is an online game!")
    it.chatNpc("I... beg your pardon?")
    it.chatPlayer("Never mind.")
}
suspend fun likeMyDisguise(it: QueueTask) {
    it.chatPlayer("I am - do you like my disguise?")
    it.chatNpc("Aargh! Avaunt foul creature from another dimension! Avaunt! Begon in the name of Saradomin!")
    it.chatPlayer("Ok, ok, I was only joking...")
}

suspend fun nicePlace(it: QueueTask) {
    it.chatPlayer("Nice place you've got here.")
    it.chatNpc("It is, isn't it? It was built over 230 years ago.")
}
suspend fun lookingForQuest(it: QueueTask) { //this is where the actual quest will start
    it.chatPlayer("I'm looking for a quest.")
    it.chatNpc("That's lucky, I need someone to do a quest for me.")
    when (startQuestOptions(it)) {
        1 -> startQuestYes(it)
        2 -> startQuestNo(it)
    }
}
suspend fun startQuestOptions(it: QueueTask): Int = it.options("Yes", "No", title = "Start The Restless Ghost quest?")

suspend fun startQuestYes(it: QueueTask) {
    it.player.setVarp(RG_VARP, 1)
    it.player.message("You've started a new quest: " + "The Restless Ghost".blue())
    it.chatPlayer("Okay, let me help then.")
    it.chatNpc("Thank you. The problem is, there is a ghost in the church graveyard. I would like you to get rid of it.")
    it.chatNpc("If you need any help, my friend Father Urhney is an expert on ghosts.")
    it.chatNpc("I believe he is currently living as a hermit in Lumbridge swamp. He has a little shack in the south-west of the swamps.")
    it.chatNpc("Exit the graveyard through the south gate to reach the swamp. I'm sure if you told him that I sent you he'd be willing to help.")
    it.chatPlayer("Likewise.")
    it.chatNpc("Take care travelling through the swamps, I have heard they can be quite dangerous.")
    it.chatPlayer("I will, thanks.")
}
suspend fun startQuestNo(it: QueueTask) {
    it.chatPlayer("Sorry, I don't have time right now.")
    it.chatNpc("Oh well. If you do have some spare time on your hands, come back and talk to me.")
}

suspend fun aereckStarted(it: QueueTask) {
    it.chatNpc("Have you got rid of the ghost yet?")
    when (it.player.getVarp(RG_VARP)) {
        1 -> {
            it.chatPlayer("I can't find Father Urhney at the moment.")
            it.chatNpc("Well, you can get to the swamp he lives in by going south through the cemetery.")
            it.chatNpc("You'll have to go right into the far western depths of the swamp, near the coastline. That is where his house is.")
        }
        2 -> {
            it.chatPlayer("I had a talk with Father Urhney. He has given me this funny amulet to talk to the ghost with.")
            it.chatNpc("I always wondered what that amulet was... Well, I hope it's useful. Tell me when you get rid of the ghost!")
        }
        3 -> {
            it.chatPlayer("I've found out that the ghost's corpse has lost its skull. If I can find the skull, the ghost should leave.")
            it.chatNpc("That WOULD explain it.")
            it.chatNpc("Hmmmmm. Well, I haven't seen any skulls.")
            it.chatPlayer("Yes, I think a warlock has stolen it.")
            it.chatNpc("I hate warlocks.")
            it.chatNpc("Ah well, good luck!")
        }
        4 -> {
            it.chatPlayer("I've finally found the ghost's skull!")
            it.chatNpc("Great! Put it in the ghost's coffin and see what happens!")
        }
    }
}

suspend fun aereckFinished(it: QueueTask) {
    it.chatNpc("Thank you for getting rid of that awful ghost for me! May Saradomin always smile upon you!")
    it.chatPlayer("I'm looking for a new quest.")
    it.chatNpc("Sorry, I only had the one quest.")
}

//RESTLESS GHOST DIALOG
suspend fun ghostNotStarted(it: QueueTask) {
    it.chatNpc("Wooooo! Ooooooh!")
    it.chatPlayer("I can't understand a word you are saying. Maybe Father Aereck will be able to help.")
}

suspend fun ghostStarted(it: QueueTask) {
    it.chatPlayer("Hello ghost, how are you?")
    if (it.player.hasEquipped(EquipmentType.AMULET, Items.GHOSTSPEAK_AMULET)){
        when(it.player.getVarp(RG_VARP)) {
            0,1,2 -> {
                it.chatNpc("Not very good actually.")
                it.chatPlayer("What's the problem then?")
                it.chatNpc("Did you just understand what i said???")
                when (ghostAmmyOptions(it)) {
                    1 -> yepNowTell(it)
                    2 -> nonsenseToMe(it)
                    3 -> amuletWorks(it)
                }
            }
            3 -> {
                it.chatNpc("How are you doing finding my skull?")
                it.chatPlayer("Sorry, I can't find it at the moment.")
                it.chatNpc("Ah well. Keep on looking.")
                it.chatNpc("I'm pretty sure it's somewhere in the tower south-west from here. There's a lot of levels to the tower, though. I suppose it might take a little while to find.")
            }
            4 -> {
                it.chatNpc("How are you doing finding my skull?")
                it.chatPlayer("I have found it!")
                it.chatNpc("Hurrah! Now I can stop being a ghost! You just need to put it in my coffin there, and I will be free!")
            }
        }

    } else {
        it.chatNpc("Wooo wooo wooooo!")
        when (ghostNoAmmyOptions(it)) {
            1 -> sorryDontSpeakGhost(it)
            2 -> thatsInteresting(it)
            3 -> anyHintsTreasure(it)
        }
    }
}
suspend fun ghostNoAmmyOptions(it: QueueTask): Int = it.options("Sorry, I don't speak ghost.", "Ooh... THAT'S interesting.", "Any hints where I can find some treasure?")
suspend fun ghostAmmyOptions(it: QueueTask): Int = it.options("Yep, now tell me what the problem is.", "No, you sound like you're speaking nonsense to me.", "Wow, this amulet works!")

suspend fun yepNowTell(it: QueueTask) {
    it.chatPlayer("Yep, now tell me what the problem is.")
    it.chatNpc("WOW! This is INCREDIBLE! I didn't expect anyone to ever understand me again!")
    it.chatPlayer("Ok, Ok, I can understand you!")
    it.chatPlayer("But have you any idea WHY you're doomed to be a ghost?")
    it.chatNpc("Well, to be honest... I'm not sure.")
    certainTask(it)
}
suspend fun nonsenseToMe(it: QueueTask) {
    it.chatPlayer("No, you sound like you're speaking nonsense to me.")
    it.chatNpc("Oh that's a pity. You got my hopes up there.")
    it.chatPlayer("Yeah, it is a pity. Sorry about that.")
    it.chatNpc("Hang on a second... you CAN understand me!")
    when (nonsenseToMeOptions(it)) {
        1 -> noICant(it)
        2 -> cleverArentI(it)
    }
}
suspend fun nonsenseToMeOptions(it: QueueTask): Int = it.options("No I can't.", "Yep, clever aren't I?")

suspend fun noICant(it: QueueTask) {
    it.chatPlayer("No I can't.")
    greatMoron(it, 1)
}

suspend fun greatMoron(it: QueueTask, number: Int) {
    it.chatNpc("Great.")
    it.chatNpc("The first person I can speak to in ages...")
    when (number) {
        1 -> it.chatNpc("..and they're a moron.")
        2 -> it.chatNpc("..and they're an idiot.")
    }
}
suspend fun cleverArentI(it: QueueTask) {
    it.chatPlayer("Yep, clever aren't I?")
    it.chatNpc("I'm impressed. You must be very powerful. I don't suppose you can stop me being a ghost?")
    when (cleverArentIOptions(it)) {
        1 -> whyYoureGhost(it)
        2 -> youreScary(it)
    }
}
suspend fun cleverArentIOptions(it: QueueTask): Int = it.options("Yes, ok. Do you know WHY you're a ghost?", "No, you're scary!")

suspend fun whyYoureGhost(it: QueueTask) {
    it.chatPlayer("Yes, ok. Do you know WHY you're a ghost?")
    it.chatNpc("Nope. I just know I can't do much of anything like this!")
    certainTask(it)
}
suspend fun certainTask(it: QueueTask) {
    it.chatPlayer("I've been told a certain task may need to be completed so you can rest in peace.")
    it.chatNpc("I should think it is probably because a warlock has come along and stolen my skull. If you look inside my coffin there, you'll find my corpse without a head on it.")
    it.chatPlayer("Do you know where this warlock might be now?")
    it.chatNpc("I think it was one of the warlocks who lives in the big tower by the sea south-west from here.")
    it.chatPlayer("Ok. I will try and get the skull back for you, then you can rest in peace.")
    it.chatNpc("Ooh, thank you. That would be such a great relief!")
    it.player.setVarp(RG_VARP, 3)
    it.chatNpc("It is so dull being a ghost...")
}
suspend fun youreScary(it: QueueTask) {
    it.chatPlayer("No, you're scary!")
    greatMoron(it, 2)
}

suspend fun amuletWorks(it: QueueTask) {
    it.chatPlayer("Wow, this amulet works!")
    it.chatNpc("Oh! It's your amulet that's doing it! I did wonder. I don't suppose you can help me? I don't like being a ghost.")
    when (amuletWorksOptions(it)) {
        1 -> whyYoureGhost(it)
        2 -> youreScary(it)
    }
}
suspend fun amuletWorksOptions(it: QueueTask): Int = it.options("Yes, ok. Do you know why you're a ghost?", "No, you're scary!")

suspend fun sorryDontSpeakGhost(it: QueueTask) {
    it.chatPlayer("Sorry, I don't speak ghost.")
    it.chatNpc("Woo woo?")
    it.chatPlayer("Nope, still don't understand you.")
    it.chatNpc("WOOOOOOOOO!")
    it.chatPlayer("Never mind.")
}
suspend fun thatsInteresting(it: QueueTask) {
    it.chatPlayer("Ooh... THAT'S interesting.")
    it.chatNpc("Woo wooo. Woooooooooooooooooo!")
    when (thatsInterestingOptions(it)) {
        1 -> didHeReally(it)
        2 -> whatIThought(it)
    }
}
suspend fun thatsInterestingOptions(it: QueueTask): Int = it.options("Did he really?", "Yeah, that's what I thought.")
suspend fun didHeReally(it: QueueTask) {
    it.chatPlayer("Did he really?")
    it.chatNpc("Woo.")
    when (didHeReallyOptions(it)) {
        1 -> sameProblem(it)
        2 -> thanksForChat(it)
    }
}
suspend fun didHeReallyOptions(it: QueueTask): Int = it.options("My brother had EXACTLY the same problem.", "Goodbye. Thanks for the chat.")

suspend fun sameProblem(it: QueueTask) {
    it.chatPlayer("My brother had EXACTLY the same problem.")
    it.chatNpc("Woo Wooooo!")
    it.chatNpc("Wooooo Woo woo woo!")
    when (sameProblemOptions(it)) {
        1 -> thanksForChat(it)
        2 -> recipeSomeTime(it)
    }
}
suspend fun sameProblemOptions(it: QueueTask): Int = it.options("Goodbye. Thanks for the chat.", "You'll have to give me the recipe some time...")

suspend fun recipeSomeTime(it: QueueTask) {
    it.chatPlayer("You'll have to give me the recipe some time...")
    it.chatNpc("Wooooooo woo woooooooo.")
    when (whatIThoughtOptions(it)) {
        1 -> thanksForChat(it)
        2 -> notSureAboutThat(it)
    }
}

suspend fun thanksForChat(it: QueueTask) {
    it.chatPlayer("Goodbye. Thanks for the chat.")
    it.chatNpc("Wooo wooo?")
}

suspend fun whatIThought(it: QueueTask) {
    it.chatPlayer("Yeah, that's what I thought.")
    it.chatNpc("Wooo woooooooooooooo...")
    when (whatIThoughtOptions(it)) {
        1 -> thanksForChat(it)
        2 -> notSureAboutThat(it)
    }
}
suspend fun whatIThoughtOptions(it: QueueTask): Int = it.options("Goodbye. Thanks for the chat.", "Hmm I'm not sure about that.")

suspend fun notSureAboutThat(it: QueueTask) {
    it.chatPlayer("Hmm... I'm not so sure about that.")
    it.chatNpc("Wooo woo?")
    it.chatPlayer("Well, if you INSIST.")
    it.chatNpc("Wooooooooo!")
    it.chatPlayer("Ah well, better be off now...")
    it.chatNpc("Woo.")
    it.chatPlayer("Bye.")
}

suspend fun anyHintsTreasure(it: QueueTask) {
    it.chatPlayer("Any hints where I can find some treasure?")
    it.chatNpc("Wooooooo woo! Wooooo woo wooooo woowoowoo woo Woo wooo. Wooooo woo woo? Woooooooooooooooooo!")
    when (anyHintsOptions(it)) {
        1 -> sorryDontSpeakGhost(it)
        2 -> beenHelpful(it)
    }
}
suspend fun anyHintsOptions(it: QueueTask): Int = it.options("Sorry, I don't speak ghost.", "Thank you. You've been very helpful.")

suspend fun beenHelpful(it: QueueTask) {
    it.chatPlayer("Thank you. You've been very helpful.")
    it.chatNpc("Wooooooo.")
}

//FATHER URHNEY DIALOG
suspend fun urhneyDialog(it: QueueTask) {
    it.chatNpc("Go away! I'm meditating!")
    when (it.player.getVarp(RG_VARP)) {
        0 -> {
            when (urhneyNotStartedOptions(it)) {
                1 -> thatsFriendly(it)
                2 -> repossessHouse(it)
            }
        }
        1,2 -> {
            when (urhneyStartedOptions(it)) {
                1 -> thatsFriendly(it)
                2 -> fatherAereckSentMe(it)
                3 -> repossessHouse(it)
            }
        }
        3,4,5 -> {
            when (urhneyStartedOptionsTwo(it)) {
                1 -> thatsFriendly(it)
                2 -> lostAmulet(it)
                3 -> repossessHouse(it)
            }
        }
    }

}
suspend fun urhneyNotStartedOptions(it: QueueTask): Int = it.options("Well, that's friendly.", "I've come to repossess your house.")

suspend fun thatsFriendly(it: QueueTask) {
    it.chatPlayer("Well, that's friendly.")
    it.chatNpc("I SAID go AWAY.")
    it.chatPlayer("Okay, okay... sheesh, what a grouch.")
}
suspend fun repossessHouse(it: QueueTask) {
    it.chatPlayer("I've come to repossess your house.")
    it.chatNpc("Under what grounds???")
    when (repossessHouseOptions(it)) {
        1 -> mortgageRepayments(it)
        2 -> justWantThisHouse(it)
    }
}
suspend fun repossessHouseOptions(it: QueueTask): Int = it.options("Repeated failure on mortgage repayments.", "I don't know, I just wanted ths house.")

suspend fun mortgageRepayments(it: QueueTask) {
    it.chatPlayer("Repeated failure on mortgage repayments.")
    it.chatNpc("What?")
    it.chatNpc("But... I don't have a mortgage! I built this house myself!")
    it.chatPlayer("Sorry. I must have got the wrong address. All the houses look the same around here.")
    it.chatNpc("What? What houses? What ARE you talking about???")
    it.chatPlayer("Never mind.")
}
suspend fun justWantThisHouse(it: QueueTask) {
    it.chatPlayer("I don't know. I just wanted this house...")
    it.chatNpc("Oh... go away and stop wasting my time!")
}

suspend fun fatherAereckSentMe(it: QueueTask) {
    it.chatPlayer("Father Aereck sent me to talk to you.")
    it.chatNpc("I suppose I'd better talk to you then. What problems has he got himself into this time?")

    when (fatherAereckSentMeOptions(it)) {
        1 -> ghostHaunting(it)
        2 -> lotsOfProblems(it)
    }
}
suspend fun fatherAereckSentMeOptions(it: QueueTask): Int = it.options("He's got a ghost haunting his graveyard.", "You mean he gets himself into lots of problems?")

suspend fun ghostHaunting(it: QueueTask) {
    it.chatPlayer("He's got a ghost haunting his graveyard.")
    it.chatNpc("Oh, the silly fool.")
    it.chatNpc("I leave town for just five months, and ALREADY he can't manage.")
    it.chatNpc("(sigh)")
    it.chatNpc("Well, I can't go back and exorcise it. I vowed not to leave this place. Until I had done a full two years of prayer and meditation.")
    it.chatNpc("Tell you what I can do though; take this amulet.")
    it.player.setVarp(RG_VARP, 2)
    if (it.player.inventory.hasSpace) {
        it.itemMessageBox("Father Urhney hands you an amulet.", Items.GHOSTSPEAK_AMULET, 400)
        it.player.inventory.add(Items.GHOSTSPEAK_AMULET, 1)
    } else {
        world.spawn(GroundItem(item = Items.GHOSTSPEAK_AMULET, amount = 1, tile = Tile(it.player.tile), owner = it.player))
        it.itemMessageBox("Father Urhney places the amulet on the ground.", Items.GHOSTSPEAK_AMULET, 400)
    }
    it.chatNpc("It is an Amulet of Ghostspeak.")
    it.chatNpc("So called, because when you wear it you can speak to ghosts. A lot of ghosts are doomed to be ghosts because they have left some important task uncompleted.")
    it.chatNpc("Maybe if you know what this task is, you can get rid of the ghost. I'm not making any guarantees mind you, but it is the best I can do right now.")
    it.chatPlayer("Thank you. I'll give it a try!")
}
suspend fun lotsOfProblems(it: QueueTask) {
    it.chatPlayer("You mean he gets himself into lots of problems?")
    it.chatNpc("Yeah. For example, when we were trainee priests he kept on getting stuck up bell ropes.")
    it.chatNpc("Anyway. I don't have time for chitchat. What's his problem THIS time?")
    ghostHaunting(it)
}

suspend fun lostAmulet(it: QueueTask) {
    it.chatPlayer("I've lost the Amulet of Ghostspeak.")
    it.messageBox("Father Urhney sighs.")
    if (it.player.inventory.contains(Items.GHOSTSPEAK_AMULET) || it.player.hasEquipped(EquipmentType.AMULET, Items.GHOSTSPEAK_AMULET)) {
        it.chatNpc("What are you talking about? I can see you've got it with you!")
    } else {
        it.chatNpc("How careless can you get? Those things aren't easy to come by you know! It's a good job I've got a spare.")
        if (it.player.inventory.hasSpace) {
            it.itemMessageBox("Father Urhney hands you an amulet.", Items.GHOSTSPEAK_AMULET, 400)
        } else {
            world.spawn(GroundItem(item = Items.GHOSTSPEAK_AMULET, amount = 1, tile = Tile(it.player.tile), owner = it.player))
            it.itemMessageBox("Father Urhney places the amulet on the ground.", Items.GHOSTSPEAK_AMULET, 400)
        }
        it.chatNpc("Be more careful this time.")
        it.chatPlayer("Okay, I'll try to be.")
    }
}

suspend fun urhneyStartedOptions(it: QueueTask): Int = it.options("Well, that's friendly.", "Father Aereck sent me to talk to you.", "I've come to repossess your house.")
suspend fun urhneyStartedOptionsTwo(it: QueueTask): Int = it.options("Well, that's friendly.", "I've lost the Amulet of Ghostspeak.", "I've come to repossess your house.")

fun String.blue() = "<col=0845c9>$this</col>"