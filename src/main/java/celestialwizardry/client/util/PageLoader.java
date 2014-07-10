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

import celestialwizardry.client.gui.spellbook.PageData;
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
	
	public static PageData loadPage(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LogHelper.err("Failed to open file! - PageLoader.loadPage()");
			return null;
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int[] readRunes(BufferedReader br) throws IOException {
//		br.r
		
		return null;
	}
	
	private static final String recipeCrafting2X2 = "crafting2x2";
	private static final String recipeCrafting3X3 = "crafting3x3";
	private static final String recipeSmelting = "smelting";
	private static final String recipeBrewing = "brewing";
	
	private static void readRecipes() {}
	
	private static void readImages() {}
	
}
