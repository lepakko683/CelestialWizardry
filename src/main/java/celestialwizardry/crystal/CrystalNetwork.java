package celestialwizardry.crystal;

import celestialwizardry.crystal.api.crystal.ICrystal;
import celestialwizardry.crystal.reference.CrystalNames;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public final class CrystalNetwork
{
    public final World world;

    public List<ICrystal> crystals = new ArrayList<ICrystal>();

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
        NBTTagCompound cnetwork = new NBTTagCompound();

        int i = 0;

        for (ICrystal crystal : crystals)
        {
            NBTTagCompound tagC = new NBTTagCompound();

            tagC.setInteger(CrystalNames.NBT.X, crystal.getXPos());
            tagC.setInteger(CrystalNames.NBT.Y, crystal.getYPos());
            tagC.setInteger(CrystalNames.NBT.Z, crystal.getZPos());

            cnetwork.setTag(CrystalNames.NBT.CRYSTAL_PREFIX + 1, tagC);

            i++;
        }

        cnetwork.setInteger(CrystalNames.NBT.COUNT, i);

        tagCompound.setTag(CrystalNames.NBT.CNETWORK, cnetwork);
    }

    public void read()
    {
        readFromWorld(world);
    }

    public void readFromWorld(World world)
    {
        readFromNBT(world.getWorldInfo().getNBTTagCompound());
    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {
        // TODO Remember to check in for loop if the tag is the count tag
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

    public static CrystalNetwork getFromWorld(World world)
    {
        return null;
    }

    public static boolean hasNetwork(World world)
    {
        return world.getWorldInfo().getNBTTagCompound().hasKey(CrystalNames.NBT.HAS_CNETWORK) && world.getWorldInfo().getNBTTagCompound().getBoolean(CrystalNames.NBT.HAS_CNETWORK) && world.getWorldInfo().getNBTTagCompound().hasKey(CrystalNames.NBT.CNETWORK);
    }
}
