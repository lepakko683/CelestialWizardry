package celestialwizardry.config;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.reference.Settings;

/**
 * Class used to initialize all mod configuration settings
 */
public class SettingHandler
{
    public static void init()
    {
        Settings.enableSeasonal = CelestialWizardry.config.get(Settings.Categories.GENERAL, "enableSeasonal", true);
    }
}
