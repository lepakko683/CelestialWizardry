package celestialwizardry.network.message;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageRuneConfig implements IMessage, IMessageHandler<MessageRuneConfig, IMessage> {
	
	public int entryCount;
	public short msgId;
	public short msgCount;
	
	
	@Override
	public IMessage onMessage(MessageRuneConfig message, MessageContext ctx) {
//		return message.toBytes(buf);
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

}
