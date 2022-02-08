package gg.rsmod.plugins.content.inter.quests.free.sheepshearer

import gg.rsmod.game.model.attr.THE_THING
import gg.rsmod.plugins.content.inter.quests.QuestTab

/**
 * Varp 179
 * 0 - Not Started
 * 1 - Started 20b of wool left
 * 2-20 - Turning in balls of wool
 * 21 - Finished
 */
val SS_VARP = 179

val FARMER_FRED = Npcs.FRED_THE_FARMER

spawn_npc(FARMER_FRED, 3189, 3273, 0, 5)

on_npc_option(FARMER_FRED, "talk-to") {
    player.queue {
        when(player.getVarp(SS_VARP)) {
            0 -> notStarted(this)
            in 1..20 -> started(this)
            21 -> finished(this)
        }
    }
}

suspend fun missingDialog(it: QueueTask) {
    it.messageBox("This dialog is missing.")
    return
}


//NOT STARTED
suspend fun notStarted(it: QueueTask) {
    it.chatNpc("What are you doing on my land? You're not the one who keeps leaving all my gates opened and letting out all my skeep, are you?")

    when (notStartedOptions(it)) {
        1 -> lfQuest(it)
        2 -> stKill(it)
        3 -> imLost(it)
    }
}
suspend fun notStartedOptions(it: QueueTask): Int = it.options("I'm looking for a quest.", "I'm looking for something to kill.", "I'm lost.")

suspend fun lfQuest(it: QueueTask) {
    it.chatPlayer("I'm looking for a quest.")
    it.chatNpc("You're after a quest, you say? Actually, I could do with a bit of help.")
    it.chatNpc("My sheep are getting mighty wooly. I'd be much obliged if you could shear them. And while you're at it, spin the wool for me too.")
    it.chatNpc("Yes, that's it. Bring me 20 balls of wool. And I'm sure I could sort out some sort of payment. Of course, there's the small matter of The Thing.")
    if (it.player.inventory.contains(Items.BALL_OF_WOOL)) {
        if (it.player.inventory.getItemCount(Items.BALL_OF_WOOL) >= 20) {
            actuallyHaveSome(it)
        } else {
            lfQuest2(it)
        }
    } else {
        lfQuest2(it)
    }
}

suspend fun lfQuest2(it: QueueTask) {
    it.chatPlayer("What do you mean, The Thing?")
    it.chatNpc("Well now, no one has ever seen The Thing. That's why we call it The Thing, 'cos we don't know what it is.")
    it.chatNpc("Some say it's a black hearted shapeshifter, hungering for the souls of hard working decent fold like me. Others say it's just a sheep.")
    it.chatNpc("Well I don't have all day to stand around and gossip. Are you going to shear my sheep or what!")
    when(startQuestOptions(it)) {
        1 -> startQuest(it)
        2 -> {
            it.chatPlayer("No, I'll give it a miss.")
            it.chatNpc("Suit yourself.")
        }
    }
}
suspend fun startQuestOptions(it: QueueTask): Int = it.options("Yes.", "No.", title = "Start the Sheep Shearer quest?")

suspend fun startQuest(it: QueueTask) {
    var stage = it.player.getVarp(SS_VARP)
    val p = it.player
    it.chatPlayer("Yes, okay. I can do that.")
    p.setVarp(SS_VARP, 1)
    it.chatNpc("Good! Now one more thing, do you actually know how to shear a sheep?")
    it.chatPlayer("Err. No, I don't know actually.")
    if (p.inventory.contains(Items.SHEARS)) {
        haveShears(it)
    } else {
        noShears(it)
    }
}

suspend fun haveShears(it: QueueTask) {
    val p = it.player
    it.chatNpc("Well, you're half way there already! You have a set of shears in your inventory. Just use those on a Sheep to shear it.")
    it.chatPlayer("That's all I have to do?")
    it.chatNpc("Well once you've collected some wool you'll need to spin it into balls.")
    it.chatNpc("Do you know how to spin wool?")
    it.chatPlayer("I don't know how to spin wool, sorry.")
    it.chatNpc("Don't worry, it's quite simple.")
    it.chatNpc("The nearest Spinning Wheel can be found on the first floor of Lumbridge Castle.")
    it.chatNpc("To get to Lumbridge Castle just follow the road east.")
    it.itemMessageBox("This icon denotes a Spinning Wheel on the world map.", item = 7670, amountOrZoom = 400)
    it.chatPlayer("Thank you")
    p.message("You've started a new quest: " + "Sheep Shearer".blue())
}
suspend fun noShears(it: QueueTask) {
    val p = it.player
    it.chatNpc("Well, first things first, you need a pair of shears. I've got some here you can use.")
    p.inventory.add(Items.SHEARS, 1)
    it.chatNpc("You just need to use them on the sheep out in my field.")
    it.chatPlayer("Sounds easy!")
    it.chatNpc("That's what they all say!")
    it.chatNpc("Some of the sheep don't like it too much... Persistence is the key.")
    it.chatNpc("Once you've collected some wool you can spin it into balls.")
    it.chatNpc("Do you know how to spin wool?")
    it.chatPlayer("I don't know how to spin wool, sorry.")
    it.chatNpc("Don't worry, it's quite simple.")
    it.chatNpc("The nearest Spinning Wheel can be found on the first floor of Lumbridge Castle.")
    it.chatNpc("To get to Lumbridge Castle just follow the road east.")
    it.itemMessageBox("This icon denotes a Spinning Wheel on the world map.", item = 7670, amountOrZoom = 400)
    it.chatPlayer("Thank you")
    p.message("You've started a new quest: " + "Sheep Shearer".blue())
}

suspend fun shearSheep(it: QueueTask) {
    val p = it.player
    if(p.inventory.contains(Items.SHEARS)) {
        it.chatNpc("Well, you're half way there already! You have a set of shears in your inventory. Just use those on a Sheep to shear it.")
        it.chatPlayer("That's all I have to do?")
        it.chatNpc("Well once you've collected some wool you'll need to spin it into balls.")
        it.chatNpc("Do you know how to spin wool?")
    } else {
        it.chatNpc("Well, first things first, you need a pair of shears. I've got some here you can use.")
        p.inventory.add(Items.SHEARS, 1)
        it.chatNpc("You just need to use them on the sheep out in my field.")
        it.chatPlayer("Sounds easy!")
        it.chatNpc("That's what they all say!")
        it.chatNpc("Some of the sheep don't like it too much... Persistence is the key.")
        it.chatNpc("Once you've collected some wool you can spin it into balls.")
        it.chatNpc("Do you know how to spin wool?")
    }
    when (shearOptions(it)) {
        1 -> {
            it.chatPlayer("Yes, I know how to spin wool.")
            it.chatNpc("Great!")
        }
        2 -> {
            it.chatPlayer("I don't know how to spin wool, sorry.")
            spinWool(it)
        }
    }
}
suspend fun shearOptions(it: QueueTask): Int = it.options("Yes, I know how to spin wool", "I don't know how to spin wool, sorry.")

suspend fun spinWool(it: QueueTask) {
    it.chatNpc("The nearest Spinning Wheel can be found on the first floor of Lumbridge Castle.")
    it.chatNpc("To get to Lumbridge Castle just follow the road east.")
    it.itemMessageBox("This icon denotes a Spinning Wheel on the world map.", item = 7670, amountOrZoom = 400)
    it.chatPlayer("Thank you")
}

suspend fun stKill(it: QueueTask) {
    it.chatPlayer("I'm looking for something to kill.")
    it.chatNpc("What, on my land? Leave my livestock alone you scoundrel!")
}

suspend fun imLost(it: QueueTask) {
    it.chatPlayer("I'm lost.")
    it.chatNpc("How can you be lost? Just follow the road east and south. You'll end up in Lumbridge fairly quickly.")
}

//STARTED
suspend fun started(it: QueueTask) {
    val p = it.player
    it.chatNpc("What are you doing on my land?")

    if(p.attr[THE_THING] == true) {
        when(startedOptions(it)) {
            1 -> shearingSheep(it)
            2 -> theThing(it)
        }
    } else {
        shearingSheep(it)
    }
}
suspend fun startedOptions(it: QueueTask): Int = it.options("I need to talk to you about shearing these sheep!", "Fred! Fred! I've seen The Thing!")

suspend fun shearingSheep(it: QueueTask) {
    it.chatPlayer("I need to talk to you about shearing these sheep!")
    it.chatNpc("Oh. How are you doing getting those balls of wool?")
    if (it.player.inventory.contains(Items.BALL_OF_WOOL)) {
        haveBalls(it)
    } else {
        it.chatPlayer("How many more do I need to give you?")
        amtToCollect(it)
    }
}

suspend fun haveBalls(it: QueueTask) {
    val p = it.player
    var gaveAmount = 0
    it.chatPlayer("I have some.")
    it.chatNpc("Give 'em here then.")
    for (i in 1..p.inventory.getItemCount(Items.BALL_OF_WOOL)) {
        var stage = p.getVarp(SS_VARP)
        if(p.getVarp(SS_VARP) == 20) {
            break
        } else {
            p.inventory.remove(Items.BALL_OF_WOOL, 1)
            gaveAmount++
            stage++
            p.setVarp(SS_VARP, stage)
        }
    }
    if(p.getVarp(SS_VARP) < 20) {
        it.messageBox("You give Fred ${gaveAmount} balls of wool.")
        allIveGot(it)
    } else {
        lastOfThem(it)
    }
}

suspend fun allIveGot(it: QueueTask) {
    it.chatPlayer("That's all I've got so far.")
    it.chatNpc("You need ${ballCount(it.player)} more before I can pay you.")
    it.chatPlayer("Ok I'll work on it.")
}

suspend fun lastOfThem(it: QueueTask) {
    it.chatPlayer("That's the last of them.")
    it.chatNpc("I guess I'd better pay you then.")
    it.player.inventory.remove(Items.BALL_OF_WOOL, 1)
    finishQuest(it)
}

suspend fun actuallyHaveSome(it: QueueTask) {
    it.chatPlayer("In fact Fred, funnily enough, I actually have 20 balls of wool already on me.")
    it.chatNpc("Have you been shearing my sheep without permission!?")
    it.chatPlayer("No! Well, maybe... They just looked a little wooly! Surely you like a shave once in a while, too?")
    it.chatNpc("It's rude to shave another person without permission - don't be coming at me with them shears!")
    it.chatPlayer("I'm sorry, I'll ask permission next time.")
    it.chatNpc("I guess no real 'arm was done. Hand the balls over and we can put this whole thing behind us.")
    it.player.inventory.remove(Items.BALL_OF_WOOL, 20)
    finishQuest(it)
}

fun ballCount(p: Player): Int {
    var ballCount = 20
    when (p.getVarp(SS_VARP)) {
        1 -> ballCount = ballCount
        2 -> ballCount -= 1
        3 -> ballCount -= 2
        4 -> ballCount -= 3
        5 -> ballCount -= 4
        6 -> ballCount -= 5
        7 -> ballCount -= 6
        8 -> ballCount -= 7
        9 -> ballCount -= 8
        10 -> ballCount -= 9
        11-> ballCount -= 10
        12 -> ballCount -= 11
        13 -> ballCount -= 12
        14 -> ballCount -= 13
        15 -> ballCount -= 14
        16 -> ballCount -= 15
        17 -> ballCount -= 16
        18 -> ballCount -= 17
        19 -> ballCount -= 18
        20 -> ballCount -= 19
    }
    return ballCount
}

suspend fun amtToCollect(it: QueueTask) {
    val p = it.player
    it.chatNpc("You need to collect ${ballCount(p)} more balls of wool.")
    it.chatPlayer("I haven't got any at the moment.")
    it.chatNpc("Ah well at least you haven't been eaten. You know what you're doing right?")
    when(duringOptions(it)) {
        1 -> {
            it.chatPlayer("How do I shear sheep, again?")
            shearSheep(it)
        }
        2 -> spinWool(it)
        3 -> {
            it.chatPlayer("Yeah, I think so.")
            it.chatNpc("You can get to it, then!")
        }
    }
}
suspend fun duringOptions(it: QueueTask): Int = it.options("How do I shear sheep, again?", "Remind me how to spin wool.", "Yeah, I think so.")

suspend fun theThing(it: QueueTask) {
    val gender = when(it.player.appearance.gender) {
        Gender.MALE -> "boy"
        Gender.FEMALE -> "girl"
    }
    it.chatPlayer("Fred! Fred! I've seen The Thing!")
    it.chatNpc("You ... you actually saw it?")
    it.chatNpc("Run for the hills! ${it.player.username} grab as many chickens as you can! We have to ...")
    it.chatPlayer("Fred!")
    it.chatNpc("... flee! Oh, woe is me! The shapeshifter is coming! We're all ...")
    it.chatPlayer("FRED!")
    it.chatNpc("... doomed. What!")
    it.chatPlayer("It's not a shapeshifter or any other kind of monster!")
    it.chatNpc("Well then what is it ${gender}?")
    it.chatPlayer("Well ... it's just two Penguins; Penguins disguised as a sheep.")
    it.chatNpc("...")
    it.chatNpc("Have you been out in the sun too long?")
}

//FINISHED
suspend fun finished(it: QueueTask) {
    it.chatNpc("What are you doing on my land?")

    when (finishedOptions(it)) {
        1 -> stKill(it)
        2 -> imLost(it)
        3 -> theThing(it)
    }
}
suspend fun finishedOptions(it: QueueTask): Int = it.options("I'm looking for something to kill.", "I'm Lost", "Fred! Fred! I've seen The Thing!")

suspend fun finishQuest(it: QueueTask) {
    val p = it.player
    var qp = p.getVarp(QuestTab.QP_VARP)
    p.inventory.add(Items.COINS_995, 60)
    p.addXp(Skills.CRAFTING, 150.00)
    p.setVarp(QuestTab.QP_VARP, (qp + 1))
    p.setVarp(SS_VARP, 21)
    p.message("Congratulations, you've completed the quest: " + "Sheep Shearer".blue())
    p.setComponentItem(interfaceId = QuestTab.REWARD_ID, component = 3, item = Items.SHEARS, amountOrZoom = 250)
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 1, text = "Congratulations!")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 2, text = "You have completed Sheep Shearer!")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 4, text = "Quest Points:")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 5, text = "${p.getVarp(QuestTab.QP_VARP)}")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 7, text = "You are awarded:")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 8, text = "1 Quest Point")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 9, text = "150 Crafting XP")
    p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = 10, text = "60 Coins")
    for (x in 11..14) {
        p.setComponentText(interfaceId = QuestTab.REWARD_ID, component = x, text = "")
    }
    p.openInterface(QuestTab.REWARD_ID, InterfaceDestination.MAIN_SCREEN)
}

fun String.blue() = "<col=0845c9>$this</col>"