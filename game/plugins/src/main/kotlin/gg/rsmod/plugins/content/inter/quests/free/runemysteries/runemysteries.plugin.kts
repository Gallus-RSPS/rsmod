package gg.rsmod.plugins.content.inter.quests.free.runemysteries

import gg.rsmod.plugins.content.inter.quests.QuestTab

val RUNE_VARP = 63
val DUKE = 815
val SEDRIDOR = 5034
val AUBURY = 2886

on_npc_option(npc = DUKE, option = "Talk-to"){
    player.queue {
        when(player.getVarp(RUNE_VARP)) {
            0 -> dukeNotStartedDialog(this)
            1,3,4,5, 7 -> dukeInProgressDialog(this)
            6 -> completedDialog(this)
        }
    }
}

on_npc_option(SEDRIDOR, "Teleport") {
    player.queue {
        when(player.getVarp(RUNE_VARP)) {
            0, 1, 2, 3, 4, 5, 7 ->
                player.filterableMessage("You need to have completed the Rune Mysteries Quest to use this feature.")
            6 -> {
                val npc = player.getInteractingNpc()
                player.lock = LockState.FULL
                npc . forceChat ("Senventior Disthine Molenko")
                npc . graphic (100, 10)
                wait(3)
                player.graphic(110, 125)
                wait (2)
                player . moveTo (2912, 4833, 0)
                player.lock = LockState.NONE
            }
        }
    }
}

arrayOf(Npcs.AUBURY).forEach { aubury ->
    on_npc_option(npc = aubury, option = "trade", lineOfSightDistance = 1) {
        player.openShop("Aubury's Rune Shop.")
    }
    on_npc_option(npc = aubury, option = "talk-to", lineOfSightDistance = 1) {
        player.queue {
            when (player.getVarp(RUNE_VARP)) {
                0,1,2,5 -> auburyDuringDialog(this)
                4 -> gavePackage(this)
                3 -> auburyDialog(this)
                6 -> auburyPostDialog(this)
            }
        }
    }
    on_npc_option(npc = aubury, option = "teleport", lineOfSightDistance = 1) {
        if (player.getVarp(RUNE_VARP) != 6) {
            player.filterableMessage("You need to have completed the Rune Mysteries Quest to use this feature.")
        } else {
            player.queue {
                val npc = player.getInteractingNpc()
                player.lock = LockState.FULL
                npc.forceChat("Senventior Disthine Molenko")
                npc.graphic(100, 10)
                wait(3)
                player.graphic(110, 125)
                wait(2)
                player.moveTo(2912, 4833, 0)
                player.lock = LockState.NONE
            }
        }
    }
}

on_npc_option(npc = SEDRIDOR, option = "Talk-to") {
    player.queue {
        this.chatNpc("Welcome adventurer, to the world renowned Wizards' Tower. How may I help you?")
        when(player.getVarp(RUNE_VARP)) {
            0 -> {
                this.messageBox("Dialog Missing")
            }
            1 -> sedridorStart(this)
            2 -> gaveTalismanStart(this)
            5 -> returnNotes(this)
        }
    }
}


//Duke Dialogue
suspend fun dukeNotStartedDialog(it: QueueTask) {
    it.chatNpc("Greetings. Welcome to my castle.")
    when (notStartedOptions(it)) {
        1 -> anyQuests(it)
        2 -> findMoney(it)
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("Have you any quests for me?", "Where can I find money?")

suspend fun anyQuests(it: QueueTask) {
    it.chatPlayer("Have you any quests for me?")
    it.chatNpc("Well, it's not really a quest but I recently discovered this strange talisman.")
    it.chatNpc("It seems to be mystical and I have never seen anything like it before. Would you take it to the head wizard at")
    it.chatNpc("the Wizards' Tower for me? It's just south-west of here and should not take you very long at all. I would be awfully grateful.")
    when(dukeStartOptions(it)) {
        1 -> {
            it.chatPlayer("Sure, no problem.")
            it.chatNpc("Thank you very much, stranger. I am sure the head wizard will reward you for such an interesting find.")
            if(it.player.inventory.isFull) {
                it.messageBox("Your inventory is full. Please have at least one free space.")
                return
            } else if (it.player.inventory.hasSpace) {
                it.player.inventory.add(Items.AIR_TALISMAN, 1)
                it.messageBox("The Duke hands you an " + "air talisman.".blue())
                it.player.setVarp(RUNE_VARP, 1)
            }
        }
        2 -> {
            it.chatPlayer("Not right now.")
            it.chatNpc("As you wish, stranger, although I have this strange feeling that it is important. Unfortunately, I cannot leave my castle unattended.")
            return
        }
    }
}
suspend fun dukeStartOptions(it: QueueTask): Int = it.options("Sure, no problem.", "Not right now.")

suspend fun findMoney(it: QueueTask) {
    it.chatPlayer("Where can I find money?")
    it.chatNpc("I've heard that the blacksmiths are prosperous amongst the peasantry. Maybe you could try your hand at that?")
}

suspend fun dukeInProgressDialog(it: QueueTask) {
    it.messageBox("Dialog Missing")
}


//Sedridor Dialog
suspend fun sedridorStart(it: QueueTask) {
    when(sedridorStartOptions(it)) {
        1 -> sedNothingThanks(it)
        2 -> sedWhatAreYou(it)
        3 -> sedImLooking(it)
    }
}
suspend fun sedridorStartOptions(it: QueueTask): Int = it.options("Nothing thanks, I'm just looking around.", "What are you doing down here?", "I'm looking for the head wizard.")

suspend fun sedNothingThanks(it: QueueTask) {
    it.chatPlayer("Nothing thanks, I'm just looking around.")
    it.chatNpc("Well, take care adventurer. You stand on the ruins of the old destroyed Wizards' Tower. Strange and powerful magicks lurk here.")
}
suspend fun sedWhatAreYou(it: QueueTask) {
    it.chatPlayer("What are you doing down here?")
    it.chatNpc("That is indeed a good question. Here in the cellar of the Wizards' Tower you find the remains of the old Wizards' Tower, destroyed by fire")
    it.chatNpc("many years past by the treachery of the Zamorakians. Many mysteries were lost, which we try to find once more. By building this Tower on the remains of the old,")
    it.chatNpc("we sought to show the world of our dedication to learning the mysteries of Magic. I am here searching through these fragments for knowledge from the artefacts from our past.")
    it.chatPlayer("And have you found anything useful?")
    it.chatNpc("Aaaah... that would be telling adventurer. Anything I have found I cannot speak freely of, for fear the treachery of the past might be repeated.")
    when(sedSecondOptions(it)) {
        1 -> illLeaveYou(it)
        2 -> whatDoYouMean(it)
    }
}
suspend fun sedSecondOptions(it: QueueTask): Int = it.options("Ok, well I'll leave you to it.", "What do you mean treachery?")

suspend fun illLeaveYou(it: QueueTask) {
    it.chatPlayer("Ok, well I'll leave you to it.")
    it.chatNpc("Perhaps I will see you later " + it.player.username + ".")
    it.chatPlayer("How did you konw my name???")
    it.chatNpc("Well, I AM the head wizard here...")
}
suspend fun whatDoYouMean(it: QueueTask) {
    it.chatPlayer("What do you mean treachery?")
    it.chatNpc("Well, it is a long story from the past... Many years ago, this Wizards' Tower was a focus of great learning, as we mages studied together to try and learn the secrets behind")
    it.chatNpc("the Rune Stones that allow us to use Magic. Who makes them? Where do they come from? How many types are there? What spells can they produce? All these questions and more are still unknown to us,")
    it.chatNpc("but were once known to our ancestors. Legends tell us that in the past the mages who lived here could fashion Rune Stones almost at will, and as many as they desired.")
    it.chatPlayer("But they cannot anymore?")
    it.chatNpc("No, unfortunately not. Many years past, the Wizards who follow Zamorak, the god of chaos, burned this Tower to the ground, and all who were inside.")
    it.chatNpc("To this day we do not fully know why they did this terrible act, but all our reashearch, all of our greatest magical minds were destroyed in one fell swoop.")
    it.chatNpc("This is why I spend my time searching through these few remains we have left from the glorious old Tower. I hope someday to find something that will tell us once more of the mysteries of")
    it.chatNpc("the runes that we use daily, which dwindle in supply with each use. Someday I hope we may once more create our own runes, and the Wizards' Tower will once more be a place of glory!")
    illLeaveYou(it)
}

suspend fun sedImLooking(it: QueueTask) {
    it.chatPlayer("I'm looking for the head wizard.")
    it.chatNpc("Oh, you are, are you?<br>And just why would you be doing that?")
    it.chatPlayer("The Duke of Lumbridge sent me to find him. I have this weird talisman he found. He said the head wizard would be very interested in it.")
    it.chatNpc("Did he now? HmmmMMMMMmmmmm. Well that IS interesting. Hand it over then adventurer, let me see what all the hubbub about it is. Just some amulet I'll wager.")
    when(lookingOptions(it)) {
        1 -> hereYouAre(it)
        2 -> noOnlyHeadWizard(it)
    }
}
suspend fun lookingOptions(it: QueueTask): Int = it.options("Okay, here you are.", "No, I'll only give it to the head wizard.")

suspend fun hereYouAre(it: QueueTask) {
    if(!it.player.inventory.contains(Items.AIR_TALISMAN)) {
        it.messageBox("You need the Air Talisman.")
        return
    } else {
        it.chatPlayer("Ok, here you are.")
        it.messageBox("You hand the Talisman to the wizard.")
        it.player.inventory.remove(Items.AIR_TALISMAN, 1)
        it.player.setVarp(RUNE_VARP, 2)
        gaveTalismanStart(it)
    }
}

suspend fun noOnlyHeadWizard(it: QueueTask) {
    it.chatPlayer("No, I'll only give it to the head wizard.")
    it.chatNpc("HA HA HA HA HA! I can tell you are new to this land, for I AM the head wizard! Hand it over and let me have a proper look at it, hmmm?")
    when(lookingOptionsTwo(it)){
        1 -> hereYouAre(it)
        2 -> iDontThink(it)
    }
}
suspend fun lookingOptionsTwo(it: QueueTask): Int = it.options("Okay, here you are.", "No, I don't think you are the head wizard.")

suspend fun iDontThink(it: QueueTask) {
    it.chatPlayer("No, I don't think ou are the head wizard.")
    it.chatNpc("Hmmm. Well, I admire your caution adventurer, perhaps I can prove myself? I will use my mental power to discover...")
    it.chatNpc("Your name is... " + it.player.username +"!")
    it.chatPlayer("You're right!")
    it.chatNpc("Well I am head wizard you know! You don't get to my position without learning a few tricks along the way!")
    it.chatNpc("So now I have proved myself to you why don't you hand over that talisman, hmm?")
    hereYouAre(it)
}

suspend fun gaveTalismanStart(it: QueueTask) {
    it.chatNpc("Wow! This is... incredible!")
    it.chatNpc("Th-this talisman you brought me...! It is the last piece of the puzzle, I think! Finally! The legacy of our ancestors... it will return to us once more!")
    it.chatNpc("I need time to study this, " + it.player.username + ". Can you please do me this task while I study this talisman you have brought me? In the mighty town of Varrock, which")
    it.chatNpc("is located North East of here, there is a certain shop that sells magical runes. I have in this package all of the research I have done relating to the Rune Stones, and")
    it.chatNpc("require somebody to take them to the shopkeeper so that he may share my research and offer me his insights. Do this for me, and bring back what he gives you,")
    it.chatNpc("and if my suspicions are correct, I will let you into the kowledge of one of the greatest secrets this world has ever known! A secret so powerful that it destroyed the")
    it.chatNpc("original Wizards' Tower all those centeries ago! My research, combined with this mysterious talisman... I cannot believe the answer to the mysteries is so close now!")
    it.chatNpc("Do this thing for me " + it.player.username + ". Be rewarded in a way you can never imagine.")
    when(postGiveOptions(it)) {
        1 -> yesCertainly(it)
        2 -> noImBusy(it)
    }
}
suspend fun postGiveOptions(it: QueueTask): Int = it.options("Yes, certainly.", "No, I'm busy.")

suspend fun yesCertainly(it: QueueTask) {
    it.chatPlayer("Yes, certainly.")
    it.chatNpc("Take this package, and head directly North from here, through Draynor village, until you reach the Barbarian Village. Then head East from there until you reach Varrock.")
    it.chatNpc("Once in Varrock, take this package to the owner of the rune shop. His name is Aubury. You may find it helpful to ask one of Varrock's citizens for directions,")
    it.chatNpc("as Varrock can be a confusing place for the first time visitor. He will give you a special item - bring it back to me, and I shall show you the mystery of the runes...")
    if (!it.player.inventory.hasSpace){
        it.messageBox("You must have at least one free inventory space.")
    } else {
        it.player.inventory.add(Items.RESEARCH_PACKAGE, 1)
        it.player.setVarp(RUNE_VARP, 3)
        it.messageBox("The head wizard gives you a package.")
        it.chatNpc("Best of luck with your quest, " + it.player.username + ".")
    }

}
suspend fun noImBusy(it: QueueTask) {
    it.chatPlayer("No, I'm busy.")
    it.chatNpc("As you wish adventurer. I will continue to study this talisman you have brought me. Return here if you find yourself with some spare time to help me.")
}

suspend fun returnNotes(it: QueueTask) {
    it.chatPlayer("Yes, I have. He gave me some research notes to pass on to you.")
    it.chatNpc("May I have the notes then?")
    if (it.player.inventory.contains(Items.NOTES)){
        it.chatPlayer("Sure. I have them here.")
        it.chatNpc("Well, before you hand them over to me, as you have been nothing but truthful with me to this point, and I admire that in an adventurer, I will let you into the secret of our research.")
        intoTheSecret(it)
    } else {
        it.chatPlayer("I don't have them on me.")
        it.chatNpc("Come back when you have the notes.")
    }
}

suspend fun intoTheSecret(it: QueueTask) {
    it.chatNpc("Now as you may or may not know, many centuries ago, the wizards at this Tower learnt the secret of creating Rune Stones, which allowed us to cast Magic very easily.")
    it.chatNpc("When this Tower was burnt down the secret of creating runes was lost to us for al time... except it wasn't. Some months ago, while searching the ruins for information from the old days,")
    it.chatNpc("I came upon a scroll, almost destroyed, that detailed a magical rock deep in the icefields of the North, closed off from access by anything other than magical means.")
    it.chatNpc("This rock was called the 'Rune Essence' by the magicians who studied its powers. Apparently, by simply breaking a chunk from it, a Rune Stone could be fashioned very quickly and easily at certain")
    it.chatNpc("elemental altars that were scattered across the land back then. Now, this is an interesting little piece of history, but not much use to us as modern wizards without access to the Rune Essence,")
    it.chatNpc("or these elemental altars. This is where you and Aubury come into this story. A few weeks back, Aubury discovered in a standard delivery of runes to his store, a parchment detailing a")
    it.chatNpc("teleportation spell that he had never come across before. To his shock, when cast it took him to a strange rock he had never encountered before... yet that felt strangely familiar...")
    it.chatNpc("As I'm sure you have not guessed, he had discovered a portal leading to the mythical Rune Essence. As soon as he told me of this spell, I saw the importance of his find,")
    it.chatNpc("for if we could but find the elemental altars spoken of in the ancient texts, we would once more be able to create runes as our ancestors had done! It would be the saviour of the wizards' art!")
    it.chatPlayer("I'm still not sure how I fit into this little story of yours...")
    it.chatNpc("You haven't guessed? This talisman you brought me... it is the key to the elemental altar of air! When you hold it next, it will direct you towards")
    it.chatNpc("the entrance to the long forgotten Air Altar! By bringing pieces of the Rune Essence to the Air Temple, you will be able to fashion your own Air Runes!")
    it.chatNpc("And this is not all! By finding other talismans similar to this one, you will eventually be able to craft every rune that is available on this world! Just")
    it.chatNpc("as our ancestors did! I cannot stress enough what a find this is! Now, due to the risks involved of letting this mighty power fall into the wrong hands")
    it.chatNpc("I will keep the teleport skill to the Rune Essence a closely guarded secret, shared only by myself and those Magic users around the world whom I trust enough to keep it.")
    it.chatNpc("This means that if any evil power should discover the talismans required to enter the elemental temples, we will be able to prevent their access to the Rune Essence and prevent")
    it.chatNpc("tragedy befalling this world. I know not where the temples are located, nor do I know where the talismans have been scattered to in this land, but I now")
    it.chatNpc("return your Air Talisman to you. Find the Air Temple, and you will be able to charge your Rune Essence to become Air Runes at will. Any time")
    it.chatNpc("you wish to visit the Rune Essence, speak to me or Aubury and we will open a portal to that mystical place for you to visit.")
    it.chatPlayer("So only you and Aubury know the teleport spell to the Rune Essence?")
    it.chatNpc("No... there are others... whom I will tell of your authorisation to visit that place. When you speak to them, they will know you, and grand you access to that place when asked.")
    it.chatNpc("Use the Air Talisman to locate the air temple, and use any further talismans you find to locate the other missing elemental temples. Now... my research notes please?")
    giveNotes(it)
}

suspend fun giveNotes(it: QueueTask) {
    it.messageBox("You hand the head wizard the research notes. He hands you back the Air Talisman.")
    it.player.inventory.remove(Items.NOTES)
    it.player.inventory.add(Items.AIR_TALISMAN, 1)
    questComplete(it)
}

//Aubury Dialog

suspend fun auburyPostDialog(it: QueueTask) {
    it.chatNpc("Do you want to buy some runes?")
    when (auburyOptions(it)) {
        1 -> about_your_cape(it)
        2 -> open_shop(it.player)
        3 -> no_thank_you(it)
        4 -> teleport_me(it)
    }
}

suspend fun auburyOptions(it: QueueTask): Int = it.options("Can you tell me about your cape?", "Yes please!", "Oh, it's a rune shop. No thank you then.", "Can you teleport me to the Rune Essence?")

suspend fun auburyDuringDialog(it: QueueTask) {
    it.chatNpc("Do you want to buy some runes?")
    when (auburyDuringOptions(it)) {
        1 -> about_your_cape(it)
        2 -> open_shop(it.player)
        3 -> no_thank_you(it)
    }
}
suspend fun auburyDuringOptions(it: QueueTask): Int = it.options("Can you tell me about your cape?", "Yes please!", "Oh, it's a rune shop. No thank you then.")

suspend fun auburyDialog(it: QueueTask) {
    it.chatNpc("Do you want to buy some runes?")
    when (auburyPreOptions(it)) {
        1 -> about_your_cape(it)
        2 -> open_shop(it.player)
        3 -> no_thank_you(it)
        4 -> sendPackage(it)
    }
}
suspend fun auburyPreOptions(it: QueueTask): Int = it.options("Can you tell me about your cape?", "Yes please!", "Oh, it's a rune shop. No thank you.", "I have been sent here with a package for you.")

suspend fun no_thank_you(it: QueueTask) {
    it.chatPlayer("Oh, it's a rune shop. No thank you, then.", animation = 568)
    it.chatNpc("Well, if you find someone who does want runes, please send them my way.", animation = 554)
}

suspend fun about_your_cape(it: QueueTask) {
    it.chatNpc("Certainly! Skillcapes are a symbol of achievement. Only people who have mastered a skill and reached level 99 can get their hands on them and gain the benefits they carry.", animation = 568)
    it.chatNpc("The Cape of Runescape has been upgraded with each talisman, allowing you to access all Runecrafting altars. Is there anything else I can help you with?", animation = 554)
}

fun open_shop(p: Player) {
    p.openShop("Aubury's Rune Shop.")
}

suspend fun teleport_me(it: QueueTask) {
    it.chatPlayer("Can you teleport me to the Rune Essence?", animation = 568)
}

suspend fun sendPackage(it: QueueTask) {
    it.chatPlayer("I have been sent here with a package for you. It's from the head wizard at the Wizards' Tower.")
    it.chatNpc("Really? But... surely he can't have..? Please, let me have it, it must be extremely important for him to have sent a stranger.")
    if (!it.player.inventory.contains(Items.RESEARCH_PACKAGE)) {
        it.messageBox("You don't have the research package.")
    }else {
        it.messageBox("You hand Aubury the research package.")
        it.player.inventory.remove(Items.RESEARCH_PACKAGE, 1)
        it.player.setVarp(RUNE_VARP, 4)
    }
}

suspend fun gavePackage(it: QueueTask) {
    it.chatNpc("My gratitude to you adventurer for bringing me these research notes. I notice that you brought the head wizard a special talisman that was the key to our finally unlocking the pizzle.")
    it.chatNpc("Combined with the information I had already collated regarding the Rune Essence, I think we have finally unlocked the power to")
    it.chatNpc("...no. I am getting ahead of myself. Please take this summary of my research back to the head wizard at the Wizards' Tower. I trust his judgement on whether to let you in on our little secret or not.")
    it.player.inventory.add(Items.NOTES, 1)
    it.player.setVarp(RUNE_VARP, 5)
    it.messageBox("Aubury gives you his research notes.")
}

suspend fun questComplete(it: QueueTask) {
    val player = it.player
    var qp = player.getVarp(QuestTab.QP_VARP)
    player.setVarp(QuestTab.QP_VARP, (qp + 1))
    player.message("Congratulations! Quest complete!")
    player.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = Items.AIR_TALISMAN, amountOrZoom = 250)
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed the Rune Mysteries Quest!")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${player.getVarp(QuestTab.QP_VARP)}")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "1 Quest Point")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "Runecrafting skill")
    player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 10, text = "Air talisman")
    for (x in 11..14) {
        player.setComponentText(interfaceId = QuestTab.REWARD_ID, component = x, text = "")
    }
    player.openInterface(QuestTab.REWARD_ID, InterfaceDestination.MAIN_SCREEN)
}

suspend fun completedDialog(it: QueueTask) {
}

fun String.blue() = "<col=0845c9>$this</col>"