package gg.rsmod.plugins.content.skills.cooking.data

import gg.rsmod.plugins.api.cfg.Items

enum class CookingIngredient(val item1: Int, val item2: Int, val usedItem1: Int, val usedItem2: Int, val result: Int, val minLevel: Int, val xp: Double) {

    BREAD_DOUGH(Items.POT_OF_FLOUR, Items.BUCKET_OF_WATER, Items.POT, Items.EMPTY_BUCKET, Items.BREAD_DOUGH, 1,0.0),
    PASTRY_DOUGH(Items.POT_OF_FLOUR, Items.BUCKET_OF_WATER, Items.POT, Items.EMPTY_BUCKET, Items.PASTRY_DOUGH, 1, 0.0),
    PIZZA_BASE(Items.POT_OF_FLOUR, Items.BUCKET_OF_WATER, Items.POT, Items.EMPTY_BUCKET, Items.PIZZA_BASE, 35, 0.0),
    INCOMPLETE_PIZZA(Items.PIZZA_BASE, Items.TOMATO, -1, -1, Items.INCOMPLETE_PIZZA, 35, 0.0),
    UNCOOKED_PIZZA(Items.INCOMPLETE_PIZZA, Items.CHEESE, -1, -1, Items.UNCOOKED_PIZZA, 35, 0.0),
    MEAT_PIZZA(Items.PLAIN_PIZZA, Items.COOKED_MEAT, -1, -1, Items.MEAT_PIZZA, 45, 26.00),
    ANCHOVY_PIZZA(Items.PLAIN_PIZZA, Items.ANCHOVIES, -1, -1, Items.ANCHOVY_PIZZA, 55, 39.00),
    CHOCOLATE_CAKE(Items.CAKE, Items.CHOCOLATE_BAR, -1, -1, Items.CHOCOLATE_CAKE, 50, 30.00),
    CHOCOLATE_CAKE_ALT(Items.CAKE, Items.CHOCOLATE_DUST, -1, -1, Items.CHOCOLATE_CAKE, 50, 30.00),
    INCOMPLETE_STEW(Items.BOWL_OF_WATER, Items.POTATO, -1, -1, Items.INCOMPLETE_STEW, 25, 0.0),
    UNCOOKED_STEW(Items.INCOMPLETE_STEW, Items.COOKED_MEAT, -1, -1, Items.UNCOOKED_STEW, 25, 0.0),
    UNCOOKED_STEW_ALT(Items.INCOMPLETE_STEW, Items.COOKED_MEAT, -1, -1, Items.UNCOOKED_STEW, 25, 0.0),
    PIE_SHELL(Items.PASTRY_DOUGH, Items.PIE_DISH, -1, -1, Items.PIE_SHELL, 20, 0.0),
    UNCOOKED_REDBERRY_PIE(Items.PIE_SHELL, Items.REDBERRIES, -1, -1, Items.UNCOOKED_BERRY_PIE, 10, 0.0),
    UNCOOKED_MEAT_PIE(Items.PIE_SHELL, Items.COOKED_MEAT, -1, -1, Items.UNCOOKED_MEAT_PIE, 20, 0.0),
    UNCOOKED_APPLE_PIE(Items.PIE_SHELL, Items.COOKING_APPLE, -1, -1, Items.UNCOOKED_APPLE_PIE, 30, 0.0),
    POTATO_BUTTER(Items.BAKED_POTATO, Items.PAT_OF_BUTTER, -1, -1, Items.POTATO_WITH_BUTTER, 39, 40.0);

    companion object {
        val values = enumValues<CookingIngredient>()
    }
}