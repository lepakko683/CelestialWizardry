package celestibytes.celestialwizardry.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import celestibytes.celestialwizardry.client.gui.spellbook.Page;
import celestibytes.celestialwizardry.client.gui.spellbook.PageData;
import celestibytes.celestialwizardry.client.gui.spellbook.object.PageObject.PageObjectType;
import celestibytes.celestialwizardry.client.gui.spellbook.object.PageStyle;
import celestibytes.celestialwizardry.reference.Characters;
import celestibytes.celestialwizardry.util.LogH;
import celestibytes.celestialwizardry.util.LogHelper;

public class PageLoader {
	
	// Don't mind my messy code :P -OkkapeL
	
	public static Page loadPage(ResourceLocation rl) {
		// TODO: finish!
		return null;
	}
	
	public static Page loadPage(InputStream is) {
		if(is == null) {
			return null;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.err("Something unexpected went wrong in loadPage(...)");
			System.err.println("Something unexpected went wrong in loadPage(...)");
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
			boolean readingInnerObjectData = false;
			
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
								System.out.println("WARN: " + "Missing an LF after CR in a page file. Handling it as a newline... This might cause some buggy behaviour. It's recommended to use LFs over CRLFs.");
								cc = cc2;
								break;
							}
						} else if(cc2 == Characters.LF) {
							break;
						}
					} while(cc2 != -1);
				}
				if(/*!readingInnerPageData && */cc != Characters.CR && cc != Characters.LF && cc != Characters.WHITESPACE && cc != '{' && cc != '[' && cc != '"' && cc != ']' && cc != '=' && cc != '}') {
					linebuffer.append(cc);
				}
				
				if(readingInnerPageData && readingInnerObjectData) {
					if(cc == '"') {
						System.out.println("Value: " + linebuffer.toString());
						readingInnerObjectData = false;
						linebuffer = new StringBuilder();
						cc = (char)br.read();
					} else if(cc == ']') {
						System.out.println("Value: " /*+ printArray(parseRunes(linebuffer.toString()))*/);
						printArray(parseRunes(linebuffer.toString()));
						readingInnerObjectData = false;
						linebuffer = new StringBuilder();
						cc = (char)br.read();
					}
				}
				if(readingInnerPageData && !readingInnerObjectData) {
					String key = null;
					if(cc == '"') {
//						System.out.println("-String-");
						System.out.println("Key: " + linebuffer.toString());
						key = linebuffer.toString();
						readingInnerObjectData = true;
						linebuffer = new StringBuilder();
					} else if(cc == '[') {
//						System.out.println("-Array-");
						System.out.println("Key: " + linebuffer.toString());
						key = linebuffer.toString();
						readingInnerObjectData = true;
						linebuffer = new StringBuilder();
					}
					
					if(key != null){
						if(key.equalsIgnoreCase("title")) {
							System.out.println("TITLE!");
						} else if(key.equalsIgnoreCase("text")) {
							System.out.println("TEXT!");
						} else if(key.equalsIgnoreCase("runes")) {
							System.out.println("RUNES!");
						} else if(key.equalsIgnoreCase("images")) {
							System.out.println("IMAGES!");
						} else if(key.equalsIgnoreCase("recipes")) {
							System.out.println("RECIPES!");
						} else if(key.equalsIgnoreCase("essential")) {
							System.out.println("ESSENTIAL?");
						}
					}
				}
				if(cc == '{') {
					if(!readingInnerPageData) {
						readingInnerPageData = true;
						
						pageName = linebuffer.toString().trim();
//						LogH.debug("Loading page \"" + pageName + "\"...");
						System.out.println("Loading page \"" + pageName + "\"...");
						pageNameParts = pageName.split(".");
						linebuffer = new StringBuilder();
					}
					
				}
				if(cc == '}') {
					if(readingInnerPageData) {
						readingInnerPageData = false;
						
						System.out.println("Finished loading page \"" + pageName + "\".");
					}
				}
			}
		} catch(Exception e) {
//			LogH.err("Something went wrong with reading a page file");
			System.err.println("Something went wrong with reading a page file");
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int[] parseRunes(String lst) {
		String[] prts = lst.split(",");
		if(prts != null && prts.length > 0) {
			int[] ret = new int[prts.length];
			for(int i=0;i<ret.length;i++) {
				try {
					ret[i] = Integer.parseInt(prts[i]);
				} catch(NumberFormatException e) {
					System.err.println("Not a number!: " + prts[i]);
					e.printStackTrace();
				}
			}
			return ret;
		}
		
		return null;
	}
	
	private static final String recipeCrafting2X2 = "crafting2x2";
	private static final String recipeCrafting3X3 = "crafting3x3";
	private static final String recipeSmelting = "smelting";
	private static final String recipeBrewing = "brewing";
	
	private static void parseRecipes(String lst) {}
	
	private static void parseImages(String lst) {}
	
	private static void printArray(int[] arr) {
		if(arr == null) {
			return;
		}
		for(int i : arr) {
			System.out.print(i + ", ");
		}
		System.out.print("\n");
	}
	
	private static PageStyle parsePropertiesFor(PageObjectType type, String str) {
		
		
		
		return null;
	}
	
}
