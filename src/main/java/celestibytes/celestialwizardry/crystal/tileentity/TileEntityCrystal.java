package celestibytes.celestialwizardry.crystal.tileentity;

import celestibytes.celestialwizardry.api.energy.EnergyRegistry;
import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystal;
import celestibytes.celestialwizardry.crystal.util.PacketBuilder;
import celestibytes.celestialwizardry.tileentity.TileEntityCW;
import celestibytes.celestialwizardry.util.LogHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCrystal extends TileEntityCW implements ICrystal
{
    /**
     * Get the {@link celestibytes.celestialwizardry.api.energy.EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link ICrystal} is
     *
     * @return A list of {@link celestibytes.celestialwizardry.api.energy.EnergyType} this {@link ICrystal} can handle
     */
    @Override
    public List<EnergyType> acceptableEnergies(World world)
    {
        List<EnergyType> list = new ArrayList<EnergyType>();

        for (EnergyType energyType : EnergyRegistry.energyMap.values())
        {
            list.add(energyType); // aka every energy type
        }

        return list;
    }

    @Override
    public float getEnergyYieldMultiplier()
    {
        return 1f;
    }

    /**
     * The x position of the crystal tile.
     *
     * @return x position
     */
    @Override
    public int getXPos()
    {
        return xCoord;
    }

    /**
     * The y position of the crystal tile.
     *
     * @return y position
     */
    @Override
    public int getYPos()
    {
        return yCoord;
    }

    /**
     * The z position of the crystal tile.
     *
     * @return z position
     */
    @Override
    public int getZPos()
    {
        return zCoord;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        LogHelper.info("Reading " + toString() + " from NBT");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        LogHelper.info("Writing " + toString() + " to NBT");
    }

    public abstract PacketBuilder getBuilder();

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + ".x:" + getXPos() + ".y:" + getYPos() + ".z:" + getZPos();
    }
}
