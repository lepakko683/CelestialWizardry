package celestibytes.celestialwizardry.api.spell;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SpellActionSetBlock extends SpellAction {

	@Override
	public void execute(World world, Entity caster, Object[] data) {
		if(data != null && data.length == 5 && data[0] instanceof Block && data[1] instanceof Integer && data[2] instanceof Integer && data[3] instanceof Integer && data[4] instanceof Integer) {
			Block block = (Block) data[0];
			int meta = (Integer) data[1];
			int x = (Integer) data[2];
			int y = (Integer) data[3];
			int z = (Integer) data[4];
			world.setBlock(x, y, z, block, meta, 1+2);
		}
	}
	
}
