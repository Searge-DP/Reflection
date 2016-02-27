/*
 * This file ("TileReflector3.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier3;

import de.ellpeck.reflection.mod.tile.TileLightComponentBase;

public class TileReflector3 extends TileLightComponentBase{

    @Override
    public int getMaxConnections(){
        return 4;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 12;
    }
}
