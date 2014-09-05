package celestibytes.celestialwizardry.client.gui;

import org.lwjgl.opengl.GL11;

import celestibytes.core.util.Colour;
import celestibytes.celestialwizardry.util.ResourceLocationHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GuiSpellSwitcher extends GuiScreen {
	private int borderMinX;
	private int borderMinY;
	private int borderMaxX;
	private int borderMaxY;
	
	private final int l_width = 124;
	private final int l_height = 124;
	
	private final ResourceLocation texture;
	
	private static final boolean renderClouds = OpenGlHelper.func_153193_b();

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
		renderCloud(60, 30, 9, 2f);
		renderCloud(60, 62, 19, 2f);
		renderCloud(30, 62, 15, 1.5f);
		renderCloud(90, 90, 16, 3f);
		
		
		renderPoint(40, 60, 16, LUNAR);
		Colour.resetGLColor();
	}
	
	private void renderCloud(int x, int y, int cloud, float size) {
		if(cloud >= 5*16 || cloud < 0) {
			return;
		}
		float offsX = (width/2)-((l_width+4)/2);
		float offsY = (height/2)-((l_height+4)/2);
		float cloudTexOffsX = (float)(cloud % 16) / 16f;
		float cloudTexOffsY = ( (float)(Math.floor(cloud/16f))*16f + 11f*16f ) / 256f;
		float l = 16f/256f;
		Tessellator tes = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // These values are so much fun to play with :D
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		tes.startDrawingQuads();
		tes.setColorRGBA_F(.5f, 0f, .7f, .5f);
		tes.addVertexWithUV((double)(x-8*size)+offsX, (double)(y-8*size)+offsY, (double)zLevel, cloudTexOffsX    , cloudTexOffsY);
		tes.addVertexWithUV((double)(x+8*size)+offsX, (double)(y-8*size)+offsY, (double)zLevel, cloudTexOffsX + l, cloudTexOffsY);
		tes.addVertexWithUV((double)(x+8*size)+offsX, (double)(y+8*size)+offsY, (double)zLevel, cloudTexOffsX + l, cloudTexOffsY + l);
		tes.addVertexWithUV((double)(x-8*size)+offsX, (double)(y+8*size)+offsY, (double)zLevel, cloudTexOffsX    , cloudTexOffsY + l);
		tes.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void renderBackground() {
		SOLAR.setGLColor();
		// Inner
		Tessellator tes = Tessellator.instance;
		
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
		GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
		GL11.glStencilMask(0xFF);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		tes.startDrawing(GL11.GL_TRIANGLES);
		
		// TODO: set up verticies
//		tes.addVertexWithUV(, p_78374_3_, p_78374_5_, p_78374_7_, p_78374_9_);
		tes.draw();
		GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
		GL11.glStencilMask(0x00);
		
		// Draw clouds
		
		GL11.glDisable(GL11.GL_STENCIL_TEST);
//		drawTexturedModalRect((width/2)-((l_width-2)/2), (height/2)-((l_height-2)/2), 134, 1, l_width-2, l_height-2);
		Colour.resetGLColor();
		// Border
		drawTexturedModalRect((width/2)-((l_width+4)/2), (height/2)-((l_height+4)/2), 0, 0, l_width+4, l_height+4);
	}
	
	private void renderPoint(int x, int y, int size, Colour color) {
		float offsX = (width/2)-((l_width+4)/2);
		float offsY = (height/2)-((l_height+4)/2);
//		color.setGLColor();
		GL11.glColor4f(1f, 1f, 1f, .2f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(size < 1 || size > 16) {
			drawTexturedModalRect(x+(int)offsX, y+(int)offsY, 0, 128, 1, 1);
		} else {
			drawTexturedModalRect(x-(int)Math.floor(size/2)+(int)offsX, y-(int)Math.floor(size/2)+(int)offsY, (size - 1) * 16, 128, size, size);
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void bindTex(ResourceLocation rc) {
		this.mc.getTextureManager().bindTexture(rc);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
