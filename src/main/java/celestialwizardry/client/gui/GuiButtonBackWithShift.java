package celestialwizardry.client.gui;

import celestialwizardry.reference.Resources;
import celestialwizardry.util.StringHelper;

import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiButtonBackWithShift extends GuiButtonBack
{
    public GuiButtonBackWithShift(int par1, int par2, int par3)
    {
        super(par1, par2, par3);
    }

    @Override
    public List<String> getTooltip()
    {
        return Arrays.asList(StringHelper.localize("misc." + Resources.RESOURCE_PREFIX + "back"),
                             EnumChatFormatting.GRAY + StringHelper
                                     .localize("misc." + Resources.RESOURCE_PREFIX + "clickToIndex")
                            );
    }
}
