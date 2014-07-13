package celestibytes.celestialwizardry.client.util;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiRenderHelper {
	public static RenderItem itemRender = new RenderItem();
	
	public static void renderItemStack(float zLevel, int x, int y, ItemStack stack, String string, FontRenderer fontRe, boolean renderOverlay) {
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if(renderOverlay) {
	        if (stack != null) {
	        	font = stack.getItem().getFontRenderer(stack);
	        }
	        if (font == null) {
	        	font = fontRe;
	        }
        }
        itemRender.renderItemAndEffectIntoGUI(font, FMLClientHandler.instance().getClient().getTextureManager(), stack, x, y);
        if(renderOverlay){
//      	itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_ - (this.draggedStack == null ? 0 : 8), string);        	
        }

        itemRender.zLevel = 0.0F;
	}
}
