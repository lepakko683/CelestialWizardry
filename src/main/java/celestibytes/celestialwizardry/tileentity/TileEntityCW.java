package celestibytes.celestialwizardry.tileentity;

import celestibytes.celestialwizardry.network.PacketHandler;
import celestibytes.celestialwizardry.network.message.MessageTileEntityCW;
import celestibytes.celestialwizardry.reference.Names;

import celestibytes.core.tileentity.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCW extends TileEntityBase
{
    public TileEntityCW()
    {
        super();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCW(this));
    }
}
