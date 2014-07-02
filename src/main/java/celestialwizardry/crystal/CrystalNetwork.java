package celestialwizardry.crystal;

import celestialwizardry.crystal.reference.CrystalNames;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class CrystalNetwork
{
    public final World world;

    public CrystalNetwork(World world)
    {
        this.world = world;
    }

    public void write()
    {
        writeToWorld(world);
    }

    public void writeToWorld(World world)
    {
        writeToNBT(world.getWorldInfo().getNBTTagCompound());
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {
        // TODO
    }

    public void clear()
    {
        clearFromWorld(world);
    }

    public void clearFromWorld(World world)
    {
        clearFromNBT(world.getWorldInfo().getNBTTagCompound());
    }

    public void clearFromNBT(NBTTagCompound tagCompound)
    {
        tagCompound.removeTag(CrystalNames.NBT.HAS_CNETWORK);
        tagCompound.removeTag(CrystalNames.NBT.CNETWORK);
    }

    public static CrystalNetwork create(World world)
    {
        CrystalNetwork network = new CrystalNetwork(world);

        if (hasNetwork(world))
        {
            network.clear();
        }

        network.write();

        return network;
    }

    public static boolean hasNetwork(World world)
    {
        return world.getWorldInfo().getNBTTagCompound().hasKey(CrystalNames.NBT.HAS_CNETWORK) && world.getWorldInfo().getNBTTagCompound().getBoolean(CrystalNames.NBT.HAS_CNETWORK);
    }
}
