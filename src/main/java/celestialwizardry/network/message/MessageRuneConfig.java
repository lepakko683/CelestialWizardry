package celestialwizardry.network.message;

import java.util.ArrayList;
import java.util.List;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.handler.ClientRuneConfigurationHandler;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageRuneConfig implements IMessage, IMessageHandler<MessageRuneConfig, IMessage> {
	
	private static final char entryEndChar = ';';
	private static final char escChar = '/';
	private static final char endOfEntries = '#';
	
	public static final int entryHardLimit = 5; 
	
	/**the id of this message*/
	public int msgId;
	/**Count of messges that are sent to the client*/
	public int msgCount;
	/**Count of RuneConfig entries in this message*/
	public int entryCount;
	/**Count of entries that are sent from the server*/
	public int fullEntryCount;
	public String[] entryLines;
	
	
	@Override
	public IMessage onMessage(MessageRuneConfig message, MessageContext ctx) {
		if(ctx.side == Side.CLIENT) {
			ClientRuneConfigurationHandler.addEntriesToBuffer(message);
		}
		return null;
	}

	@Override
	// This method is very likely to contain bugs
	public void fromBytes(ByteBuf buf) {
		this.msgId = buf.readInt();
		this.msgCount = buf.readInt();
		this.entryCount = buf.readInt();
		this.fullEntryCount = buf.readInt();
		this.entryLines = new String[entryCount];
		
		char cc = endOfEntries;
		int cycles = 0;
		StringBuilder sb = null;
		List<String> lines = new ArrayList<String>();
		while((cc = buf.readChar()) != endOfEntries && cycles < entryCount && cycles < entryHardLimit) {
			if(cc == entryEndChar) {
				lines.add(sb.toString());
				sb = new StringBuilder();
			} else {
				if(cc == escChar) {
					sb.append(buf.readChar()); // Get the next character after the escChar
				} else {
					sb.append(cc);
				}
			}
			cycles++;
		}
		this.entryLines = (String[]) lines.toArray();
	}

	@Override
	// This method is very likely to contain bugs
	public void toBytes(ByteBuf buf) {
		if(entryCount != entryLines.length) {
			CelestialWizardry.log.error("Error at building a message: entryCount doesn't match the length of the entryLines array! Continuing anyway, THIS WILL CAUSE PROBLEMS!!!");
		}
		buf.writeInt(msgId);
		buf.writeInt(msgCount);
		buf.writeInt(entryCount);
		buf.writeInt(fullEntryCount);
		
		StringBuilder sb = null;
		for(int i=0; i<Math.min(entryLines.length, entryCount); i++) { // Prevents array index out of bounds exception, but will cause further problems with invalid amount of runeconfig entries
			sb = new StringBuilder();
			for(int q=0;q<entryLines[i].length();q++) {
				char cc = entryLines[i].charAt(q);
				if(needsEscape(cc)) {
					sb.append(escapeChar(cc));
				} else {
					sb.append(cc);
				}
			}
			sb.append(entryEndChar);
			buf.writeBytes(sb.toString().getBytes());
			sb = null;
		}
		buf.writeChar((int)endOfEntries);
	}
	
	private boolean needsEscape(char c) {
		return ( c == escChar ? true : ( c == entryEndChar ? true : ( c == endOfEntries ? true : false ) ) );
	}
	
	private String escapeChar(char c) {
		return Character.toString(escChar) + Character.toString(c);
	}
}
