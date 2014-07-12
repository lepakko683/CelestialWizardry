package celestibytes.core.config;

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
        Settings.enableVersionCheck = CelestiCore.config.get(category, Settings.ConfigNames.ENABLE_VERSION_CHECK,
                                                             Settings.DefaultValues.ENABLE_VERSION_CHECK,
                                                             Settings.Comments.ENABLE_VERSION_CHECK);
        Settings.debugMode = CelestiCore.config.get(category, Settings.ConfigNames.DEBUG_MODE,
                                                    Settings.DefaultValues.DEBUG_MODE,
                                                    Settings.Comments.DEBUG_MODE);
        Settings.shiftForDetails = CelestiCore.config.get(category, Settings.ConfigNames.SHIFT_FOR_DETAILS,
                                                          Settings.DefaultValues.SHIFT_FOR_DETAILS,
                                                          Settings.Comments.SHIFT_FOR_DETAILS);

        if (CelestiCore.config.hasChanged())
        {
            CelestiCore.config.save();
        }
    }
}
