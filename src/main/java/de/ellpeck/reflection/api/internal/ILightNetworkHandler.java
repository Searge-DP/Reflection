package de.ellpeck.reflection.api.internal;

import de.ellpeck.reflection.api.light.TileLightComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public interface ILightNetworkHandler{

    void writeConnectionInfoNBT(TileLightComponent tile, NBTTagCompound compound);

    void readConnectionInfoNBT(TileLightComponent tile, NBTTagCompound compound);

    void removeConnections(BlockPos component, World world);

    ILightNetwork getNetworkForComponent(BlockPos component, int dimension);

    Set<IConnectionPair> getConnectionsForComponent(BlockPos component, int dimension);

    boolean addConnection(BlockPos first, BlockPos second, World world, boolean validate);

    Map<Integer, Set<ILightNetwork>> getAllNetworks();
}
