/*
 * This file ("BlockLightComponentBas.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.api.light.ILightTierDisplay;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import net.minecraft.block.material.Material;

public class BlockLightComponentBase extends BlockContainerBase implements ILightTierDisplay{

    private LightNetworkTier theTier;

    public BlockLightComponentBase(Material material, String name, LightNetworkTier tier, boolean addTab, Class<? extends TileLightComponentBase> tileClass, String tileName){
        super(material, name, addTab, tileClass, tileName);
        this.theTier = tier;
        this.setLightOpacity(1);
    }

    @Override
    public LightNetworkTier getTier(){
        return this.theTier;
    }
}
