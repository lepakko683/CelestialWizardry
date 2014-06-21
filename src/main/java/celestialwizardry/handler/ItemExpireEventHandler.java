package celestialwizardry.handler;

import celestialwizardry.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemExpireEventHandler {
	
	@SubscribeEvent
	public void onEvent(ItemExpireEvent event) {
		if(!event.entityItem.worldObj.isRemote) {
			if(event.entityItem.getEntityItem().getItem().equals(Items.diamond)) {
				event.entityItem.worldObj.spawnEntityInWorld(new EntityItem(event.entityItem.worldObj, event.entityItem.posX, event.entityItem.posY, event.entityItem.posZ, new ItemStack(ModItems.material, 1, 3)));
			}
		}
	}
}
