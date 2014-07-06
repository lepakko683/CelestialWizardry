package celestialwizardry.client.gui;

import celestialwizardry.util.Colour;
import celestialwizardry.util.ResourceLocationHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiSpellSwitcher extends GuiScreen {
	private int borderMinX;
	private int borderMinY;
	private int borderMaxX;
	private int borderMaxY;
	
	private final int width = 124;
	private final int height = 124;
	
	private final ResourceLocation texture;

	private static Colour SOLAR;
	private static Colour LUNAR;
	private static Colour CELESTIAL;
	
	public GuiSpellSwitcher() {
		this.texture = ResourceLocationHelper.getResourceLocation("textures/gui/spellSwitcher.png");
		SOLAR = new Colour(1f, 1f, .3f);
		LUNAR = new Colour(.4f, .4f, 1f);
		CELESTIAL = new Colour(.67f, .98f, 1f);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partTick) {
		super.drawScreen(mouseX, mouseY, partTick);
		bindTex(texture);
		
		renderBackground();
		renderPoint(60, 60, 16, Colour.WHITE);
		Colour.resetGLColor();
	}
	
	private void renderCloud(int x, int y) {
		
	}
	
	private void renderBackground() {
		CELESTIAL.setGLColor();
		drawTexturedModalRect(3, 3, 134, 1, width-2, height-2);
		Colour.resetGLColor();
		drawTexturedModalRect(0, 0, 0, 0, width+4, height+4);
	}
	
	private void renderPoint(int x, int y, int size, Colour color) {
		color.setGLColor();
		if(size < 1 || size > 16) {
			drawTexturedModalRect(x, y, 0, 128, 1, 1);
		} else {
			drawTexturedModalRect(x-(int)Math.floor(size/2), y-(int)Math.floor(size/2), (size - 1) * 16, 128, size, size);
		}
	}
	
	private void bindTex(ResourceLocation rc) {
		this.mc.getTextureManager().bindTexture(rc);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
