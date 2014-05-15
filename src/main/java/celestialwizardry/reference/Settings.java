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

    /**
     * Tweaks
     */
    // Difficulty
    public static boolean spawnBook;

    public static class Categories
    {
        public static final String SEPARATOR = Configuration.CATEGORY_SPLITTER;

        public static final String GENERAL = Configuration.CATEGORY_GENERAL;
        public static final String SPELLS = "spells";
        public static final String CLIENT = "client";
        public static final String TWEAKS = "tweaks";
        public static final String DIFFICULTY = TWEAKS + SEPARATOR + "difficulty";
    }
}
