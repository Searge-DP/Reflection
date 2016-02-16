/*
 * This file ("InitItems.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.items.light.ItemLightBattery;
import net.minecraft.item.Item;

public class InitItems{

    public static Item itemLightConnector;
    public static Item itemLightBatteryBase;

    public static void preInit(){
        itemLightConnector = new ItemLightConnector();
        itemLightBatteryBase = new ItemLightBattery();
    }

}
