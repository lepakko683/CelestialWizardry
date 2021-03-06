package celestibytes.celestialwizardry.reference;

import net.minecraftforge.common.config.Configuration;

import javax.print.DocFlavor;

/**
 * Class containing all config options
 */
public class Settings
{
    /**
     * General
     */
    public static boolean enableSeasonal;
    public static boolean enableVersionCheck;
    public static boolean enableVersionNotification;
    public static boolean checkForLatest;
    public static boolean debugMode;
    public static boolean shiftForDetails;

    /**
     * Spells
     */
    public static double spellCostMultiplier;

    /**
     * Client
     */
    public static boolean spellBook3dModel;

    /**
     * Tweaks
     */
    // Difficulty
    public static boolean spawnSpellBook;
    public static int startingIntelligence;
    public static boolean hardcoreRecipes;

    public static class DefaultValues
    {
        /**
         * General
         */
        public static final boolean ENABLE_SEASONAL = true;
        public static final boolean ENABLE_VERSION_CHECK = true;
        public static final boolean ENABLE_VERSION_NOTIFICATION = true;
        public static final boolean CHECK_FOR_LATEST = false;
        public static final boolean DEBUG_MODE = false;
        public static final boolean SHIFT_FOR_DETAILS = true;

        /**
         * Spells
         */
        public static final double SPELL_COST_MULTIPLIER = 1.0d;

        /**
         * Client
         */
        public static final boolean SPELL_BOOK_3D_MODEL = true;

        /**
         * Tweaks
         */
        // Difficulty
        public static final boolean SPAWN_SPELL_BOOK = true;
        public static final int STARTING_INTELLIGENCE = 0;
        public static final boolean HARDCORE_RECIPES = false;
    }

    public static class ConfigNames
    {
        /**
         * General
         */
        public static final String ENABLE_SEASONAL = "enableSeasonal";
        public static final String ENABLE_VERSION_CHECK = "enableVersionCheck";
        public static final String ENABLE_VERSION_NOTIFICATION = "enableVersionNotification";
        public static final String CHECK_FOR_LATEST = "checkForLatest";
        public static final String DEBUG_MODE = "debugMode";
        public static final String SHIFT_FOR_DETAILS = "shiftForDetails";

        /**
         * Spells
         */
        public static final String SPELL_COST_MULTIPLIER = "spellCostMultiplier";

        /**
         * Client
         */
        public static final String SPELL_BOOK_3D_MODEL = "spellBook3dModel";

        /**
         * Tweaks
         */
        // Difficulty
        public static final String SPAWN_SPELL_BOOK = "spawnSpellBook";
        public static final String STARTING_INTELLIGENCE = "startingIntelligence";
        public static final String HARDCORE_RECIPES = "hardcoreRecipes";
    }

    public static class Comments
    {
        /**
         * General
         */
        public static final String ENABLE_SEASONAL = "If true, mod will have some seasonal fun.";
        public static final String ENABLE_VERSION_CHECK = "If true, mod will automatically check for new version.";
        public static final String ENABLE_VERSION_NOTIFICATION =
                "If true, mod will display notification to player if new version is found. Doesn't do anything if "
                        + ConfigNames.ENABLE_VERSION_CHECK + " is false.";
        public static final String CHECK_FOR_LATEST = "If true, mod checks also for unstable latest releases on version check.";
        public static final String DEBUG_MODE = "You should not touch this.";
        public static final String SHIFT_FOR_DETAILS = "If true, you can see extra tooltips by holding shift.";

        /**
         * Spells
         */
        public static final String SPELL_COST_MULTIPLIER = "The factor for spell cost.";

        /**
         * Client
         */
        public static final String SPELL_BOOK_3D_MODEL = "If true, spell book has cool 3D model.";

        /**
         * Tweaks
         */
        // Difficulty
        public static final String SPAWN_SPELL_BOOK
                = "If true, players get spell book when they enter the world first time.";
        public static final String STARTING_INTELLIGENCE = "The intelligence new players will have.";
        public static final String HARDCORE_RECIPES = "If true, the mod recipes will be much harder.";
    }

    public static class Categories
    {
        public static final String SEPARATOR = Configuration.CATEGORY_SPLITTER;

        public static final String[] SUPER_CATEGORIES = {Configuration.CATEGORY_GENERAL, "spells", "client", "tweaks"};

        public static final String GENERAL = SUPER_CATEGORIES[0];

        public static final String SPELLS = SUPER_CATEGORIES[1];

        public static final String CLIENT = SUPER_CATEGORIES[2];

        public static final String TWEAKS = SUPER_CATEGORIES[3];
        public static final String DIFFICULTY = TWEAKS + SEPARATOR + "difficulty";
    }
}
