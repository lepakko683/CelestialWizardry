package celestialwizardry.util;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable
{
    void readFromNBT(NBTTagCompound tagCompound);

    void writeToNBT(NBTTagCompound tagCompound);
}
