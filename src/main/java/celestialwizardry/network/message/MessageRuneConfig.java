package celestialwizardry.network.message;

import java.util.ArrayList;
import java.util.List;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.config.RuneConfigPart;
import celestialwizardry.handler.ClientRuneConfigurationHandler;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageRuneConfig implements IMessage, IMessageHandler<MessageRuneConfig, IMessage> {
	
	public static final int entryHardLimit = 5;
	
	private static final char entryEndChar = ';';
	private static final char escChar = '/';
	private static final char endOfEntries = '#';
	
	/**the id of this message*/
	public int msgId;
	/**Count of messges that are sent to the client*/
	public int msgCount;
	/**Count of RuneConfig entries in this message*/
	public int entryCount;
	/**Count of entries that are sent from the server*/
	public int fullEntryCount;
	public String[] entryLines;
	
	public MessageRuneConfig() {}
	
	public MessageRuneConfig(int msgId, int msgCount, int entryCount, int fullEntryCount, String[] entryLines) {
		this.msgId = msgId;
		this.msgCount = msgCount;
		this.entryCount = entryCount;
		this.fullEntryCount = fullEntryCount;
		this.entryLines = entryLines;
	}
	
	public MessageRuneConfig(int msgId, int msgCount, int fullEntryCount, RuneConfigPart entries) {
		this.msgId = msgId;
		this.msgCount = msgCount;
		this.entryCount = entries.getLineCount();
		this.fullEntryCount = fullEntryCount;
		String[] lines = new String[this.entryCount];
		for(int i=0;i<this.entryCount;i++) {
			lines[i] = entries.getLine(i);
		}
		this.entryLines = lines;
	}
	
	@Override
	public IMessage onMessage(MessageRuneConfig message, MessageContext ctx) {
		ClientRuneConfigurationHandler.addEntriesToBuffer(message);
		for(int i=0;i<message.entryLines.length;i++) {
			System.out.println("Received: " + message.entryLines[i]);
		}
		return new MessageRuneConfigReply(MessageRuneConfigReply.CODE_OK);
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
		int cysLeft = 1000;
		int cycles = 0;
		StringBuilder sb = new StringBuilder();
		List<String> lines = new ArrayList<String>();
		while((cc = (char)buf.readByte()) != endOfEntries && cycles < entryCount && cycles < entryHardLimit && cysLeft>1) {
			if(cc == entryEndChar) {
				lines.add(sb.toString());
				sb = new StringBuilder();
				cycles++;
			} else {
				if(cc == escChar) {
					sb.append((char)buf.readByte()); // Get the next character after the escChar
				} else {
					sb.append(cc);
				}
			}
			cysLeft--;
		}
		
		Object[] lines2 = lines.toArray();
		System.out.println("Reading " + lines2.length + " objects...");
		this.entryLines = new String[lines2.length];
		for(int i=0;i<this.entryLines.length;i++) {
			this.entryLines[i] = lines2[i].toString();
		}
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
			try{
				buf.writeBytes(sb.toString().getBytes("UTF-8"));
			}catch(Exception e) {}
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
