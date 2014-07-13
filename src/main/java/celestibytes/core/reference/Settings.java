package celestibytes.core.reference;

import net.minecraftforge.common.config.Configuration;

public class Settings
{
    /**
     * General
     */
    public static boolean enableVersionNotification;
    public static boolean debugMode;
    public static boolean shiftForDetails;

    public static class DefaultValues
    {
        /**
         * General
         */
        public static final boolean ENABLE_VERSION_NOTIFICATION = true;
        public static final boolean DEBUG_MODE = false;
        public static final boolean SHIFT_FOR_DETAILS = true;
    }

    public static class ConfigNames
    {
        /**
         * General
         */
        public static final String ENABLE_VERSION_NOTIFICATION = "enableVersionNotification";
        public static final String DEBUG_MODE = "debugMode";
        public static final String SHIFT_FOR_DETAILS = "shiftForDetails";
    }

    public static class Comments
    {
        /**
         * General
         */
        public static final String ENABLE_VERSION_NOTIFICATION = "If true, " + Reference.MOD_NAME + " will display notifications about new versions.";
        public static final String DEBUG_MODE = "You should not touch this.";
        public static final String SHIFT_FOR_DETAILS = "If true, you can see extra tooltips by holding shift.";
    }

    public static class Categories
    {
        public static final String SEPARATOR = Configuration.CATEGORY_SPLITTER;

        public static final String[] SUPER_CATEGORIES = {Configuration.CATEGORY_GENERAL};

        public static final String GENERAL = SUPER_CATEGORIES[0];
    }
}
