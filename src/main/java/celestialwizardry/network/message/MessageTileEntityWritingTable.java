package celestialwizardry.network.message;

import celestialwizardry.tileentity.TileEntityCW;
import celestialwizardry.tileentity.TileEntityWritingTable;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityWritingTable implements IMessage, IMessageHandler<MessageTileEntityWritingTable, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntityWritingTable()
    {
    }

    public MessageTileEntityWritingTable(TileEntityWritingTable tileEntityWritingTable)
    {
        this.x = tileEntityWritingTable.xCoord;
        this.y = tileEntityWritingTable.yCoord;
        this.z = tileEntityWritingTable.zCoord;
        this.orientation = (byte) tileEntityWritingTable.getOrientation().ordinal();
        this.state = (byte) tileEntityWritingTable.getState();
        this.customName = tileEntityWritingTable.getCustomName();
        this.owner = tileEntityWritingTable.getOwner();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        int ownerLength = buf.readInt();
        this.owner = new String(buf.readBytes(ownerLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeInt(owner.length());
        buf.writeBytes(owner.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntityWritingTable message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld
                .getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityWritingTable)
        {
            ((TileEntityCW) tileEntity).setOrientation(message.orientation);
            ((TileEntityCW) tileEntity).setState(message.state);
            ((TileEntityCW) tileEntity).setCustomName(message.customName);
            ((TileEntityCW) tileEntity).setOwner(message.owner);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format(
                "MessageTileEntityWritingTable - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s",
                x, y, z, orientation, state, customName, owner);
    }
}
