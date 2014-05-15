package celestialwizardry.reference;

import net.minecraftforge.common.config.Configuration;

/**
 * Class containing all config options
 */
public class Settings
{
    /**
     * General
     */
    public static boolean enableSeasonal;
    public static boolean debugMode;

    /**
     * Spells
     */
    public static float spellCostMultiplier;

    /**
     * Client
     */
    public static boolean spellBook3dModel;

    public static class Categories
    {
        public static final String SEPARATOR = Configuration.CATEGORY_SPLITTER;

        public static final String GENERAL = Configuration.CATEGORY_GENERAL;
        public static final String SPELLS = "spells";
        public static final String CLIENT = "client";
    }
}
