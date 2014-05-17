package celestialwizardry.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonInvisible extends GuiButton
{
    public GuiButtonInvisible(int par1, int par2, int par3, int par4, int par5, String par6Str)
    {
        super(par1, par2, par3, par4, par5, par6Str);
    }

    @Override
    public void drawButton(Minecraft mc, int par2, int par3)
    {
        field_146123_n = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width
                && par3 < yPosition + height;
        int k = getHoverState(field_146123_n);

        boolean unicode = mc.fontRenderer.getUnicodeFlag();

        mc.fontRenderer.setUnicodeFlag(true);
        mc.fontRenderer.drawString(displayString, xPosition + (k == 2 ? 5 : 0), yPosition + (height - 8) / 2, 0);
        mc.fontRenderer.setUnicodeFlag(unicode);
    }
}
