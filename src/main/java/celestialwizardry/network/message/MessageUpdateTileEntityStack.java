package celestialwizardry.network.message;

import net.minecraft.inventory.IInventory;
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
			this.stackSize = stack.stackSize;
			this.damage = stack.getItemDamage();
		} else {
			this.stackSize = 1;
			this.damage = 0;
		}
		this.isItem = true;
		this.itemName = stack.getItem().getUnlocalizedName();
		this.stack = null;
	}
	
	@Override
	public MessageUpdateTileEntityStack onMessage(MessageUpdateTileEntityStack message, MessageContext ctx) {
		if(message.stack != null) {
			Chunk chunk = FMLClientHandler.instance().getClient().theWorld.getChunkProvider().provideChunk(message.x >> 4, message.z >> 4);
			if(chunk == null || chunk.isEmpty()) {
				return null;
			}
			TileEntity te = FMLClientHandler.instance().getClient().theWorld.getTileEntity(x, y, z);
			if(te != null && te instanceof IInventory) {
				if(((IInventory)te).getSizeInventory()<message.slotId) {
					((IInventory)te).setInventorySlotContents(slotId, stack);
				}
			}
		}
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
		
		while(buf.readableBytes()>0) {
			sb.append((char)chr);
		}
		this.itemName = sb.toString();
		if(itemName != null) {
			String[] split = this.itemName.split(":");
			if(split.length == 2) {
				this.stack = this.isItem ? new ItemStack(GameRegistry.findItem(split[0], split[1])) : new ItemStack(GameRegistry.findBlock(split[0], split[1]));
			}
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
		}
	}

}
