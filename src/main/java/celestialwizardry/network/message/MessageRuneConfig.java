package celestialwizardry.network.message;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.handler.ClientRuneConfigurationHandler;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageRuneConfig implements IMessage, IMessageHandler<MessageRuneConfig, IMessage> {
	
	public int msgId; // the id of this message
	public int msgCount; // Count of messges that are sent to the client
	public int entryCount; // Count of RuneConfig entries in this message
	public String[] entryLines;
	
	
	@Override
	public IMessage onMessage(MessageRuneConfig message, MessageContext ctx) {
		if(ctx.side == Side.CLIENT) {
			ClientRuneConfigurationHandler.addEntriesToBuffer(message);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.msgId = buf.readInt();
		this.msgCount = buf.readInt();
		this.entryCount = buf.readInt();
		this.entryLines = new String[entryCount];
		for(int i=0; i<this.entryCount; i++) {
//			this.entryLines[i] = new String(buf.readB) TODO
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if(entryCount != entryLines.length) {
			CelestialWizardry.log.error("Error at building a message: entryCount doesn't match the length of the entryLines array! Continuing anyway, THIS WILL CAUSE PROBLEMS!!!");
		}
		buf.writeInt(msgId);
		buf.writeInt(msgCount);
		buf.writeInt(entryCount);
		for(int i=0; i<Math.min(entryLines.length, entryCount); i++) { // Prevents array index out of bounds exception, but will cause further problems with invalid amount of runeconfig entries
			buf.writeBytes(entryLines[i].getBytes());
		}
		
	}

}
