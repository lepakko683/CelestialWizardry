package celestibytes.celestialwizardry.client.util;

import java.util.ArrayList;
import java.util.List;

import celestibytes.celestialwizardry.client.gui.spellbook.PageRenderer;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class WordWrapper {
	
	public static String[] autoWrap(String[] text, int[] runes, PageRenderer pr, int width, int height) {
		List<String> lines = new ArrayList<String>();
		
		boolean needToRead = true;
		int i=0;
		StringBuilder sb = new StringBuilder();
		
		while(needToRead) {
			
		}
		return null;
	}
}
