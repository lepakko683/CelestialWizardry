package celestialwizardry.item;

import celestialwizardry.entity.EntityBell;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockBell extends ItemBlockCW {

	public ItemBlockBell(Block block) {
		super(block);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		World world = entity.worldObj;
		if(!world.isRemote) {
			System.out.println("Hello!");
			world.spawnEntityInWorld(new EntityBell(world, "lepakko683"));
		}
		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if(!world.isRemote) {
			System.out.println("hellope!");
			EntityBell bell = new EntityBell(world, "lepakko683");
			bell.setPosition(player.posX, player.posY+2d, player.posZ);
			
			world.spawnEntityInWorld(bell);
		}
		return item;
	}
}
