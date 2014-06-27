package celestialwizardry.client.render;

import celestialwizardry.util.Colour;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RuneRenderer implements IResourceManagerReloadListener {
	
	private final ResourceLocation currentRuneTexture = null;
	
	private String[] modidRuneTexLocs = null;
	
	private Colour currColor;
	
	public RuneRenderer() {
		
	}
	
	public int getRuneWidth(int runeId) {
		return -1;
	}
	
	public int getWidthOfRunes(int runes[]) {
		return -1;
	}
	
	/**Draw a string of runes at the specified coordinates with scale and color.*/
	public void renderRunes(int runeids[], int posX, int posY, float scale, Colour color) {
		
	}
	
	/**Draw a string of runes at the specified coordinates with scale(relative to 1f) using black color.*/
	public void renderRunes(int runeids[], int posX, int posY, float scale) {
		
	}
	
	/**Used whenever attempting to render a rune before the configuration is initialized or the runeId was invalid. The correct rune will be rendered automatically when possible.*/
	private void renderRuneUnknown() {
		
	}
	
	@Override //TODO: reload rune texture(s).
	public void onResourceManagerReload(IResourceManager var1) {
		
	}

}
