package celestialwizardry.client.gui.spellbook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Page {
	
	@SideOnly(Side.CLIENT)
	protected PageData[] pageData;
	
	/**The modid of the mod that has added this page.*/
	private String owner;
	
	private PageType type;
	
	/**Whether this page shouldn't be able to be removed from the spellbook.*/
	boolean essential = false;
	
	/**Whether this page should be automatically added to the spellbook.*/
	boolean autoAdd = false;
	
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
	
	public Page(boolean essential) {
		this.essential = essential;
	}
	
	public boolean autoAdd() {
		return this.autoAdd;
	}
	
	public boolean isEssential() {
		return this.essential;
	}
	
	public void setEssential(boolean value) {
		this.essential = value;
	}
	
	public PageType getType() {
		return this.type;
	}
	
	public static enum PageType {
		GUIDE,
		NOTE,
		SPELL,
		OTHER;
	}
}
