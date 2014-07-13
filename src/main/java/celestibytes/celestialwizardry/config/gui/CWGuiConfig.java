package celestibytes.celestialwizardry.config.gui;

import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.reference.Reference;
import celestibytes.celestialwizardry.reference.Settings;
import celestibytes.celestialwizardry.util.CWStringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class CWGuiConfig extends GuiConfig
{
    public CWGuiConfig(GuiScreen parentScreen)
    {
        super(parentScreen, getConfigElements(), Reference.MOD_ID, false, false,
              CWStringHelper.getConfig("configTitle") /* GuiConfig.getAbridgedConfigPath(CelestialWizardry.config
              .toString() */);
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        for (String s : Settings.Categories.SUPER_CATEGORIES)
        {
            list.add(new ConfigElement(CelestialWizardry.config.getCategory(s)));
        }

        return list;
    }
}
