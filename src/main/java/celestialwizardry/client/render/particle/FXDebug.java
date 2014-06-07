package celestialwizardry.client.render.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXDebug extends EntityFX {

	public FXDebug(World world, double ltx, double lty, double ltz, double dx, double dy, double dz) {
		super(world, ltx, lty, ltz, dx, dy, dz);
	}
	
	
	
	
}
