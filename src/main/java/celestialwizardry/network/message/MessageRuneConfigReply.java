package celestialwizardry.network.message;

import celestialwizardry.util.LogHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public class MessageRuneConfigReply implements IMessage, IMessageHandler<MessageRuneConfigReply, IMessage>
{

    public static final int CODE_OK = 1;
    public static final int CODE_RESEND = 2;

    public int code;

    /**
     * Example: the part the server needs to resend, unused with CODE_OK
     */
    public int dataA = -1;

    public MessageRuneConfigReply()
    {
    }

    public MessageRuneConfigReply(int code)
    {
        this.code = code;
    }

    public MessageRuneConfigReply(int code, int dataA)
    {
        this.code = code;
        this.dataA = dataA;
    }

    @Override
    public IMessage onMessage(MessageRuneConfigReply message, MessageContext ctx)
    {
        if (message.code == CODE_RESEND)
        {
            LogHelper.info("Config part send failed, resending...");
            return null; //new MessageRuneConfig(); // TODO!
        }
        if (message.code == CODE_OK)
        {
            LogHelper.info("Config part sent successfully!");
            return null;
        }
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.code = buf.readInt();
        this.dataA = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(code);
        buf.writeInt(dataA);
    }

}
