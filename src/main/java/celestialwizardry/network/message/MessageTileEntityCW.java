package celestialwizardry.network.message;

import celestialwizardry.tileentity.TileEntityCW;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityCW implements IMessage, IMessageHandler<MessageTileEntityCW, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntityCW()
    {
    }

    public MessageTileEntityCW(TileEntityCW tileEntityCW)
    {
        this.x = tileEntityCW.xCoord;
        this.y = tileEntityCW.yCoord;
        this.z = tileEntityCW.zCoord;
        this.orientation = (byte) tileEntityCW.getOrientation().ordinal();
        this.state = (byte) tileEntityCW.getState();
        this.customName = tileEntityCW.getCustomName();
        this.owner = tileEntityCW.getOwner();
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
    public IMessage onMessage(MessageTileEntityCW message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld
                .getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityCW)
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
        return String
                .format("MessageTileEntityCW - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x,
                        y, z, orientation, state, customName, owner);
    }
}
