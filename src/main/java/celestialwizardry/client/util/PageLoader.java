package celestialwizardry.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import celestialwizardry.client.gui.spellbook.Page;
import celestialwizardry.client.gui.spellbook.PageData;
import celestialwizardry.reference.Characters;
import celestialwizardry.util.LogH;
import celestialwizardry.util.LogHelper;

public class PageLoader {
	
	private static final byte CR = 0x0D;      // 13
	private static final byte LF = 0x0A;      // 10
	private static final byte SPECIAL = 0x26; // 38 '&'
	
	private static final byte RUNE = 'R';
	private static final byte IMAGE = 'I';
	private static final byte RECIPE = 'C';
	
	private static final byte NEWLINE = 'N';
	private static final byte HORIZ_LINE = 'H';
	
	private static final String[] pageDataObjects = new String[] {"title", "essential", "text", "runes", "recipes", "images"};
	private static final String FALSE = "false";
	private static final String TRUE = "true";
	
	// Don't mind my messy code :P -OkkapeL
	
	public static Page loadPage(ResourceLocation rl) {
		// TODO: finish!
		return null;
	}
	
	public static Page loadPage(InputStream is) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.err("Something unexpected went wrong in loadPage(...)");
			return null;
		}
		try {
			Page pge = null;
			List<PageData> ret = new ArrayList<PageData>();
			char cc = (char) -1;
			boolean readingInnerPageData = false;
			StringBuilder linebuffer = new StringBuilder();
			String pageName = null;
			String[] pageNameParts = null;
			
			while((cc = (char)br.read()) != (char) -1) {
				if(cc == Characters.COMMENT) {
					char cc2 = (char)-1;
					do {
						cc2 = (char)br.read();
						if(cc2 == Characters.CR) {
							cc2 = (char)br.read();
							if(cc2 == Characters.LF) {
								break;
							}else{
								LogH.warn("Missing an LF after CR in a page file. Handling it as a newline... This might cause some buggy behaviour. It's recommended to use LFs over CRLFs.");
								cc = cc2;
								break;
							}
						} else if(cc2 == Characters.LF) {
							break;
						}
					} while(cc2 != -1);
				}
				if(!readingInnerPageData && cc != Characters.CR && cc != Characters.LF && cc != Characters.WHITESPACE) {
					linebuffer.append(cc);
				}
				if(cc == '{') {
					if(!readingInnerPageData) {
						readingInnerPageData = true;
						
						pageName = linebuffer.toString().trim();
						LogH.debug("Loading page \"" + pageName + "\"...");
						pageNameParts = pageName.split(".");
						
						
						
					}
					
				}
			}
		} catch(Exception e) {
			LogH.err("Something went wrong with reading a page file");
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int[] parseRunes(BufferedReader br) throws IOException {
//		br.r
		
		return null;
	}
	
	private static final String recipeCrafting2X2 = "crafting2x2";
	private static final String recipeCrafting3X3 = "crafting3x3";
	private static final String recipeSmelting = "smelting";
	private static final String recipeBrewing = "brewing";
	
	private static void parseRecipes() {}
	
	private static void parseImages() {}
	
}
