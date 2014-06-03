package celestialwizardry.network.message;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageRuneConfig implements IMessage, IMessageHandler<MessageRuneConfig, IMessage> {
	
	@Override
	public IMessage onMessage(MessageRuneConfig message, MessageContext ctx) {
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

}
