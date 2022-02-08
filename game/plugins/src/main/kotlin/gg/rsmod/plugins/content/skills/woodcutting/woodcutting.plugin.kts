package gg.rsmod.plugins.content.skills.woodcutting

import gg.rsmod.plugins.content.skills.woodcutting.Woodcutting.Tree

private val TREES = setOf(
        Tree(TreeType.TREE, obj = 1278, trunk = 1342), // Tree
        Tree(TreeType.TREE, obj = 1276, trunk = 1342), // Tree
        Tree(TreeType.TREE, obj = 2409, trunk = 1342), // Tree (check trunk)
        Tree(TreeType.TREE, obj = 10041, trunk = 1342), // Tree (check trunk)
        Tree(TreeType.TREE, obj = 1365, trunk = 1351), // Dead tree (check trunk)
        Tree(TreeType.TREE, obj = 1286, trunk = 1351), // Dead tree
        Tree(TreeType.TREE, obj = 1282, trunk = 1347), // Dead tree
        Tree(TreeType.TREE, obj = 1383, trunk = 1358), // Dead tree
        Tree(TreeType.TREE, obj = 1289, trunk = 1353), // Dead tree
        Tree(TreeType.TREE, obj = 2091, trunk = 1342), // Evergreen

        Tree(TreeType.OAK, obj = 10820, trunk = 8468), // Oak
        Tree(TreeType.OAK, obj = 10820, trunk = 8468),

        Tree(TreeType.WILLOW, obj = 10831, trunk = 9471), // Willow
        Tree(TreeType.WILLOW, obj = 10829, trunk = 9471),
        Tree(TreeType.WILLOW, obj = 10819, trunk = 9711),
        Tree(TreeType.WILLOW, obj = 10833, trunk = 9471),

        Tree(TreeType.MAPLE, obj = 10832, trunk = 9712), // Maple

        Tree(TreeType.YEW, obj = 10822, trunk = 9714), // YEW
        Tree(TreeType.YEW, obj = 1753, trunk = 9714),
        Tree(TreeType.YEW, obj = 1754, trunk = 9714),

        Tree(TreeType.REDWOOD, obj = 29668, trunk = 29669), // Redwood
        Tree(TreeType.REDWOOD, obj = 29670, trunk = 29671)
)

TREES.forEach { tree ->
    on_obj_option(obj = tree.obj, option = 1) {
        val obj = player.getInteractingGameObj()
        player.queue {
            Woodcutting.chopDownTree(this, obj, tree.type, tree.trunk)
        }
    }
}