package gg.rsmod.plugins.content.skills.prayer

val bones = Bone.values

private val altars = setOf(
        Objs.ALTAR_14860,
        Objs.ALTAR_2640,
        Objs.CHAOS_ALTAR_411
)

bones.forEach { bone ->
    on_item_option(item = bone.item, option = "bury") {

        if (bone.item.equals(Items.SUPERIOR_DRAGON_BONES) && player.getSkills().getCurrentLevel(Skills.PRAYER) < 70) {
            player.message("You need a Prayer level of 70 to bury these bones.")

        } else {
            player.queue {
                BoneBurying.bury(this, bone)
            }
        }
    }
    on_item_on_obj(obj = Objs.ALTAR_409, item = bone.item) {
        player.message("You fear the wrath of the gods!")
    }
}

altars.forEach { altar ->
    on_obj_option(obj = altar, option = "pray-at") {
        player.queue {
            player.lock()
            player.animate(645)
            wait(5)
            player.getSkills().restore(Skills.PRAYER)
            player.filterableMessage("Your Prayer Points Are Restored")
            player.unlock()
        }
    }
}

altars.forEach { altar ->
    bones.forEach { bone ->
        on_item_on_obj(obj = altar, item = bone.item) {
            if (bone.item.equals(Items.SUPERIOR_DRAGON_BONES) && player.getSkills().getCurrentLevel(Skills.PRAYER) < 70) {
                player.message("You need a Prayer level of 70 to burn these bones.")

            } else {
                player.queue {
                    var obj = player.getInteractingGameObj()
                    BoneBurying.bonesOnAltar(this, obj, bone)
                }
            }
        }
    }
}