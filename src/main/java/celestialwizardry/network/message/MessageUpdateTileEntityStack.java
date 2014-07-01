package celestialwizardry.network.message;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;

public class MessageUpdateTileEntityStack implements IMessage, IMessageHandler<MessageUpdateTileEntityStack, MessageUpdateTileEntityStack> {

	public int x, y, z;
	public int slotId;
	public int stackSize;
	public int damage;
	public boolean isItem;
	public String itemName;
	public ItemStack stack;
	
	public MessageUpdateTileEntityStack() {}
	
	public MessageUpdateTileEntityStack(int x, int y, int z, int slotId, ItemStack stack) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.slotId = slotId;
		if(stack != null) {
			this.itemName = stack.getItem().getUnlocalizedName();
			this.stackSize = stack.stackSize;
			this.damage = stack.getItemDamage();
		} else {
			this.stackSize = 1;
			this.damage = 0;
			this.itemName = "null";
		}
		this.isItem = true;
		
		this.stack = null;
	}
	
	@Override // TODO: handle null stack!
	public MessageUpdateTileEntityStack onMessage(MessageUpdateTileEntityStack message, MessageContext ctx) {
		System.out.println("In message: " + message.x + " " + message.y + " " + message.z);
//		if(message.stack != null) {
			System.out.println("Side: " + (ctx.side.name()));
			Chunk chunk = FMLClientHandler.instance().getClient().theWorld.getChunkProvider().provideChunk(message.x >> 4, message.z >> 4);
			if(chunk == null || chunk.isEmpty()) {
				return null;
			}
			TileEntity te = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
			if(te != null && te instanceof IInventory) {
				if(((IInventory)te).getSizeInventory()>message.slotId) {
					((IInventory)te).setInventorySlotContents(message.slotId, message.stack);
				}
			}
			
			System.out.println("TE instanceof IInventory: " + (te instanceof IInventory));
//		} else {
//			System.out.println("Stack is null!");
//		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.slotId = buf.readInt();
		this.stackSize = buf.readInt();
		this.damage = buf.readInt();
		this.isItem = buf.readBoolean();
		
		byte chr = (byte)0;
		StringBuilder sb = new StringBuilder();
		int breaker = 200;
		
		while((chr = buf.readByte()) != (byte)';' && buf.readableBytes()>0 && breaker > 1) {
			sb.append((char)chr);
			breaker--;
		}
		this.itemName = sb.toString();
		System.out.println("itemaname: " + this.itemName);
		if(this.itemName.equals("null")){
			this.stack = null;
		}
		if(itemName != null && itemName.length() > 0) {
			String owner;
			String itemPrefix = "";
			int indx = itemName.indexOf(":"); 
			
			if(indx == -1) {
				owner = "minecraft";
			} else {
				owner = itemName.substring(itemName.indexOf(".")+1, indx);
				itemPrefix = "item.";
			}
			
			String item = indx == -1 ? itemName.substring(itemName.indexOf(".")+1) : itemPrefix + itemName.substring(indx+1);
			System.out.println("at end owner=" + owner);
			System.out.println("at end item=" + item);
			Item itm = GameRegistry.findItem(owner, item);
			if(itm != null) {
				this.stack = new ItemStack(itm, stackSize, damage);
			}
			System.out.println(this.stack + " @ clienta");
		}
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(this.slotId);
		buf.writeInt(this.stackSize);
		buf.writeInt(this.damage);
		buf.writeBoolean(this.isItem);
		if(stack != null) {
			buf.writeBytes(this.stack.getItem().getUnlocalizedName().getBytes());
		} else if(itemName != null){
			buf.writeBytes(this.itemName.getBytes());
			System.out.println("IteName: " + this.itemName);
		}
		buf.writeByte((int)';');
	}

}
