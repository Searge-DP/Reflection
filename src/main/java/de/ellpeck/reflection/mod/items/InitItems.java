package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.item.Item;

public class InitItems{

    public static Item itemLightConnector;

    public static void preInit(){
        itemLightConnector = new ItemLightConnector(LibNames.ITEM_LIGHT_CONNECTOR, true);
    }

}
