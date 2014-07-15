package celestibytes.core.reference;

import net.minecraftforge.common.config.Configuration;

public class Settings
{
    /**
     * General
     */
    public static boolean enableVersionCheck;
    public static boolean enableVersionNotification;
    public static String channel;
    public static boolean debugMode;

    public static class DefaultValues
    {
        /**
         * General
         */
        public static final boolean ENABLE_VERSION_CHECK = true;
        public static final boolean ENABLE_VERSION_NOTIFICATION = true;
        public static final String CHANNEL = "stable";
        public static final boolean DEBUG_MODE = false;
    }

    public static class ConfigNames
    {
        /**
         * General
         */
        public static final String ENABLE_VERSION_CHECK = "enableVersionCheck";
        public static final String ENABLE_VERSION_NOTIFICATION = "enableVersionNotification";
        public static final String CHANNEL = "channel";
        public static final String DEBUG_MODE = "debugMode";
    }

    public static class Comments
    {
        /**
         * General
         */
        public static final String ENABLE_VERSION_CHECK = "If true, mod will automatically check for new version.";
        public static final String ENABLE_VERSION_NOTIFICATION =
                "If true, mod will display notification to player if new version is found. Doesn't do anything if "
                        + ConfigNames.ENABLE_VERSION_CHECK + " is false.";
        public static final String CHANNEL = "The channel this mod looks for update. Valid values: stable, rc, beta, alpha.";
        public static final String DEBUG_MODE = "You should not touch this.";
    }

    public static class Categories
    {
        public static final String SEPARATOR = Configuration.CATEGORY_SPLITTER;

        public static final String[] SUPER_CATEGORIES = {Configuration.CATEGORY_GENERAL};

        public static final String GENERAL = SUPER_CATEGORIES[0];
    }
}
