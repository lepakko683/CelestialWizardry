package celestibytes.core.config;

import celestibytes.celestialwizardry.CelestialWizardry;

import celestibytes.core.CelestiCore;
import celestibytes.core.reference.Settings;

/**
 * Class used to initialize all mod configuration settings
 */
public class SettingHandler
{
    public static void sync()
    {
        /**
         * General
         */
        String category = Settings.Categories.GENERAL;

        // enableVersionCheck
        Settings.enableVersionCheck = CelestiCore.config
                .get(category, Settings.ConfigNames.ENABLE_VERSION_CHECK, Settings.DefaultValues.ENABLE_VERSION_CHECK,
                     Settings.Comments.ENABLE_VERSION_CHECK);

        // enableVersionNotification
        Settings.enableVersionNotification = CelestiCore.config.get(category,
                                                                    Settings.ConfigNames.ENABLE_VERSION_NOTIFICATION,
                                                                    Settings.DefaultValues.ENABLE_VERSION_NOTIFICATION,
                                                                    Settings.Comments.ENABLE_VERSION_NOTIFICATION);

        // channel
        Settings.channel = CelestiCore.config.get(category, Settings.ConfigNames.CHANNEL, Settings.DefaultValues.CHANNEL, Settings.Comments.CHANNEL);

        // debugMode
        Settings.debugMode = CelestiCore.config.get(category, Settings.ConfigNames.DEBUG_MODE,
                                                    Settings.DefaultValues.DEBUG_MODE, Settings.Comments.DEBUG_MODE);

        if (CelestiCore.config.hasChanged())
        {
            CelestiCore.config.save();
        }
    }
}
