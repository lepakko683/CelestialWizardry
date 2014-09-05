package celestibytes.celestialwizardry.client.util;

import java.util.ArrayList;
import java.util.List;

import celestibytes.celestialwizardry.client.gui.spellbook.PageRenderer;
import celestibytes.celestialwizardry.client.gui.spellbook.object.PageObject;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class PageWrapper {
	
	public static PageObject[] autoWrap(String[] text, int[] runes, PageRenderer pr, int width, int height) {
		List<PageObject> objs = new ArrayList<PageObject>();
		
		boolean needToRead = true;
		int i=0;
		StringBuilder sb = new StringBuilder();
		
		while(needToRead) {
			
		}
		return null;
	}
}
