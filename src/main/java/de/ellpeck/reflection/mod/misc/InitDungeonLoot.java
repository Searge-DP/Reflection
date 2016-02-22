/*
 * This file ("InitDungeonLoot.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.misc;

import de.ellpeck.reflection.mod.items.InitItems;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class InitDungeonLoot{

    public static void init(){

        ChestGenHooks bonus = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
        ChestGenHooks[] stronghold = new ChestGenHooks[]{ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR), ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING), ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY)};
        ChestGenHooks mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);

        WeightedRandomChestContent[] mochis = new WeightedRandomChestContent[]{
                new WeightedRandomChestContent(InitItems.itemMochiWhite, 0, 1, 2, 2),
                new WeightedRandomChestContent(InitItems.itemMochiGreen, 0, 1, 2, 2),
                new WeightedRandomChestContent(InitItems.itemMochiRed, 0, 1, 2, 2)
        };

        for(WeightedRandomChestContent mochi : mochis){
            bonus.addItem(mochi);
            mineshaft.addItem(mochi);
            for(ChestGenHooks hook : stronghold){
                hook.addItem(mochi);
            }
        }

    }

}
