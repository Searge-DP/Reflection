/*
 * This file ("BlockContainerBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.Reflection;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.util.ItemUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockContainerBase extends BlockContainer{

    private Class<? extends TileEntity> tileClass;

    public BlockContainerBase(Material material, String name, boolean addTab, Class<? extends TileEntity> tileClass, String tileName){
        super(material);
        this.tileClass = tileClass;

        ItemUtil.registerBlock(this, name, addTab);
        GameRegistry.registerTileEntity(tileClass, LibNames.BASE_REGISTRY+tileName);
        this.registerRendering(name);
    }

    public void registerRendering(String name){
        Reflection.proxy.addRenderRegister(new ItemStack(this), new ResourceLocation(LibMod.MOD_ID, name));
    }

    @Override
    public int getRenderType(){
        return 3;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        try{
            return this.tileClass.newInstance();
        }
        catch(Exception e){
            LibMod.LOGGER.fatal("Creating a new TileEntity failed!", e);
            return null;
        }
    }
}
