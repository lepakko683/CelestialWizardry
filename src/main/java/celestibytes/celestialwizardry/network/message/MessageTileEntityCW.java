package celestibytes.celestialwizardry.network.message;

import celestibytes.celestialwizardry.tileentity.TileEntityCW;

import celestibytes.core.network.message.MessageTileEntity;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public class MessageTileEntityCW extends MessageTileEntity
{
    public MessageTileEntityCW()
    {
        super();
    }

    public MessageTileEntityCW(TileEntityCW tileEntityCW)
    {
        super(tileEntityCW);
    }

    @Override
    public IMessage onMessage(MessageTileEntity message, MessageContext ctx)
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
