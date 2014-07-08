package celestialwizardry.client.gui.spellbook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Page {
	
	protected int beginX, beginY;
	@SideOnly(Side.CLIENT)
	protected PageData pageData;
	
	public static class Guides {
		public static final String[] pages = {
			"runes"
		};
	}
	
//	public static class Base {
//		public static final String[] pages = {
//			
//		};
//	}
	
	public Page() {
		
	}
	
	public void renderPage(PageRenderer renderer) {
		
	}
}
