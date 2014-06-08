package celestialwizardry.client.gui;

import celestialwizardry.reference.Resources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiButtonBookLeft extends GuiButton
{
    public GuiButtonBookLeft(int id, int x, int y, String name)
    {
        super(id, x, y, name);
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {
        field_146123_n = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;

        mc.renderEngine.bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(xPosition, yPosition, 198, 188, 216 - 248, 255 - 210);
    }
}
