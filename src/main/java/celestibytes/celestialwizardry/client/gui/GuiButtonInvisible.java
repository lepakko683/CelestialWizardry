package celestibytes.celestialwizardry.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonInvisible extends GuiButton
{
    public GuiButtonInvisible(int id, int x, int y, int width, int height, String name)
    {
        super(id, x, y, width, height, name);
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {
        field_146123_n = x >= xPosition && y >= yPosition && x < xPosition + width
                && y < yPosition + height;
        int k = getHoverState(field_146123_n);

        boolean unicode = mc.fontRenderer.getUnicodeFlag();

        mc.fontRenderer.setUnicodeFlag(true);
        mc.fontRenderer.drawString(displayString, xPosition + (k == 2 ? 5 : 0), yPosition + (height - 8) / 2, 0);
        mc.fontRenderer.setUnicodeFlag(unicode);
    }
}
