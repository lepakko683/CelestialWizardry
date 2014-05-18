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

    public static class DefaultValues
    {
        /**
         * General
         */
        public static final boolean ENABLE_SEASONAL = true;
        public static final boolean DEBUG_MODE = false;

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
    }

    public static class ConfigNames
    {
        /**
         * General
         */
        public static final String ENABLE_SEASONAL = "enableSeasonal";
        public static final String DEBUG_MODE = "debugMode";

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
    }

    public static class Comments
    {
        /**
         * General
         */
        public static final String ENABLE_SEASONAL = "If true, mod will have some seasonal fun.";
        public static final String DEBUG_MODE = "You should not touch this.";

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
    }

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
