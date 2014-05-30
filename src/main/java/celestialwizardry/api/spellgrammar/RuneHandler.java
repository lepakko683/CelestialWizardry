package celestialwizardry.api.spellgrammar;

import net.minecraft.world.World;

public abstract class RuneHandler {
	
	public abstract void handleRune(World world, int x, int y, int z, Rune r);
	
	

}
