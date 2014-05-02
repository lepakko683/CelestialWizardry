package celestialwizardry.tileentity;

import celestialwizardry.network.PacketHandler;
import celestialwizardry.network.message.MessageTileEntityWritingTable;
import net.minecraft.network.Packet;

public class TileEntityWritingTable extends TileEntityCW
{
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityWritingTable(this));
    }
}
