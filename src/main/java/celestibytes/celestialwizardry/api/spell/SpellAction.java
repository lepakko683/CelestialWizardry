package celestibytes.celestialwizardry.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class SpellAction {
	
	public abstract void execute(World world, Entity caster, Object[] data);
	
}
