package celestialwizardry.init;

import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.api.spellgrammar.RuneAction;
import celestialwizardry.registry.RuneRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InitRunes {
	
	public static final Rune runeAction = new RuneAction();
	public static final Rune runeActionTeleport = new RuneAction();
	
	@SideOnly(Side.SERVER)
	public void serverInitRunes() {
//		RuneRegistry.
	}
	
	@SideOnly(Side.CLIENT)
	public void clientInitRunes(/*RuneConfig rc*/) {
		
	}
}
