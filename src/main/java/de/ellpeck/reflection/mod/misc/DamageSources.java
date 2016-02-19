/*
 * This file ("DamageSources.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.misc;

import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSources extends DamageSource{

    public static final DamageSource DAMAGE_GLASS_SHARDS = new DamageSources("glassShards", 3);

    private int amount;

    public DamageSources(String name, int amount){
        super(name);
        this.amount = amount;
    }

    @Override
    public IChatComponent getDeathMessage(EntityLivingBase entity){
        int message = LibMod.RANDOM.nextInt(this.amount)+1;
        String strg = "death."+LibNames.BASE_TRANSLATOR+this.damageType+"."+message;
        return new ChatComponentTranslation(strg, entity.getDisplayName());
    }

}
