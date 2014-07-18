package celestibytes.core.network.message;

import celestibytes.celestialwizardry.tileentity.TileEntityCW;

import celestibytes.core.tileentity.TileEntityBase;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public class MessageTileEntity implements IMessage, IMessageHandler<MessageTileEntity, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntity()
    {
    }

    public MessageTileEntity(TileEntityBase tileEntityBase)
    {
        this.x = tileEntityBase.xCoord;
        this.y = tileEntityBase.yCoord;
        this.z = tileEntityBase.zCoord;
        this.orientation = (byte) tileEntityBase.getOrientation().ordinal();
        this.state = (byte) tileEntityBase.getState();
        this.customName = tileEntityBase.getCustomName();
        this.owner = tileEntityBase.getOwner();
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
    public IMessage onMessage(MessageTileEntity message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld
                .getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityBase)
        {
            ((TileEntityBase) tileEntity).setOrientation(message.orientation);
            ((TileEntityBase) tileEntity).setState(message.state);
            ((TileEntityBase) tileEntity).setCustomName(message.customName);
            ((TileEntityBase) tileEntity).setOwner(message.owner);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String
                .format("MessageTileEntity - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x,
                        y, z, orientation, state, customName, owner);
    }
}
