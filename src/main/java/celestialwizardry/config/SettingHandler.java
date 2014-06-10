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
        /**
         * General
         */
        String category = Settings.Categories.GENERAL;
        Settings.enableSeasonal = CelestialWizardry.config
                .get(category, Settings.ConfigNames.ENABLE_SEASONAL, Settings.DefaultValues.ENABLE_SEASONAL,
                     Settings.Comments.ENABLE_SEASONAL);
        Settings.debugMode = CelestialWizardry.config
                .get(category, Settings.ConfigNames.DEBUG_MODE, Settings.DefaultValues.DEBUG_MODE,
                     Settings.Comments.DEBUG_MODE);
        Settings.shiftForDetails = CelestialWizardry.config.get(category, Settings.ConfigNames.SHIFT_FOR_DETAILS,
                                                                Settings.DefaultValues.SHIFT_FOR_DETAILS,
                                                                Settings.Comments.SHIFT_FOR_DETAILS);

        /**
         * Spells
         */
        category = Settings.Categories.SPELLS;
        Settings.spellCostMultiplier = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPELL_COST_MULTIPLIER, Settings.DefaultValues.SPELL_COST_MULTIPLIER,
                     Settings.Comments.SPELL_COST_MULTIPLIER);

        /**
         * Client
         */
        category = Settings.Categories.CLIENT;
        Settings.spellBook3dModel = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPELL_BOOK_3D_MODEL, Settings.DefaultValues.SPELL_BOOK_3D_MODEL,
                     Settings.Comments.SPELL_BOOK_3D_MODEL);

        /**
         * Tweaks
         */
        category = Settings.Categories.TWEAKS;
        // Difficulty
        category = Settings.Categories.DIFFICULTY;
        Settings.spawnSpellBook = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPAWN_SPELL_BOOK, Settings.DefaultValues.SPAWN_SPELL_BOOK,
                     Settings.Comments.SPAWN_SPELL_BOOK);
        Settings.startingIntelligence = CelestialWizardry.config
                .get(category, Settings.ConfigNames.STARTING_INTELLIGENCE, Settings.DefaultValues.STARTING_INTELLIGENCE,
                     Settings.Comments.STARTING_INTELLIGENCE);
    }
}
