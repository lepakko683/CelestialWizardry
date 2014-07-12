package celestibytes.core.config.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.IConfigElement;

import celestibytes.core.CelestiCore;
import celestibytes.core.reference.Reference;
import celestibytes.core.reference.Settings;
import celestibytes.core.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig
{
    public GuiConfig(GuiScreen parentScreen)
    {
        super(parentScreen, getConfigElements(), Reference.MOD_ID, false, false,
              StringHelper.getConfig("configTitle") /* GuiConfig.getAbridgedConfigPath(CelestialWizardry.config
              .toString() */);
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        for (String s : Settings.Categories.SUPER_CATEGORIES)
        {
            list.add(new ConfigElement(CelestiCore.config.getCategory(s)));
        }

        return list;
    }
}
