package celestialwizardry.client.gui;

import celestialwizardry.reference.Resources;
import celestialwizardry.util.RenderHelper;
import celestialwizardry.util.StringHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiButtonBack extends GuiButton
{
    public GuiButtonBack(int par1, int par2, int par3)
    {
        super(par1, par2, par3, 18, 9, "");
    }

    @Override
    public void drawButton(Minecraft mc, int par2, int par3)
    {
        field_146123_n = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width
                && par3 < yPosition + height;
        int k = getHoverState(field_146123_n);

        mc.renderEngine.bindTexture(Resources.Textures.GUI_SPELL_BOOK);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        drawTexturedModalRect(xPosition, yPosition, 36, k == 2 ? 180 : 189, 18, 9);

        List<String> tooltip = getTooltip();
        int tooltipY = (tooltip.size() - 1) * 10;

        if (k == 2)
        {
            RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
        }
    }

    public List<String> getTooltip()
    {
        return Arrays.asList(StringHelper.localize("misc." + Resources.RESOURCE_PREFIX + "back"));
    }
}
