package celestialwizardry.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.config.RuneConfig;
import celestialwizardry.registry.RuneRegistry;


@SideOnly(Side.CLIENT)
public class ClientRuneConfigurationHandler {
	
	public static void setupRuneConfig(RuneConfig rc) {
		RuneRegistry.setupNumIds(rc);
	}
	
}
