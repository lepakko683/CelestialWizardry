package celestialwizardry.block;

import java.util.List;
import java.util.Random;

import celestialwizardry.entity.EntityLivingOre;
import celestialwizardry.reference.Names;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLivingOre extends BlockCW {

	public BlockLivingOre() {
		super(Material.rock);
		setTickRandomly(true);
		this.setBlockName(Names.Blocks.LIVING_ORE);
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand) {
		List entities = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x-8, y-4, z-8, x+8, y+4, z+8));
		if(entities.size()>0 && entities.get(0) != null) {
			for(int i=0;i<entities.size();i++) {
				if(entities.get(i) != null || !((EntityPlayer)entities.get(i)).capabilities.isCreativeMode) {
					System.out.println(((EntityPlayer)(entities.get(0))).getDisplayName());
					//TODO: spawn 1x1x1 slow-moving ore golem
					break;
				}
			}
			
			
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer plr, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(!world.isRemote) {
			EntityLivingOre ent = new EntityLivingOre(world, 1);
			ent.setPosition(x, y+2, z);
			System.out.println("spawning... " + world.spawnEntityInWorld(ent));
			System.out.println("activated!");
		}
		return true;
	};
}
