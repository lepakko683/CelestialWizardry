package celestibytes.celestialwizardry.config;

import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.reference.Settings;

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

        // enableSeasonal
        Settings.enableSeasonal = CelestialWizardry.config
                .get(category, Settings.ConfigNames.ENABLE_SEASONAL, Settings.DefaultValues.ENABLE_SEASONAL,
                     Settings.Comments.ENABLE_SEASONAL);

        // enableVersionCheck
        Settings.enableVersionCheck = CelestialWizardry.config.get(category, Settings.ConfigNames.ENABLE_VERSION_CHECK,
                                                                   Settings.DefaultValues.ENABLE_VERSION_CHECK,
                                                                   Settings.Comments.ENABLE_VERSION_CHECK);

        // enableVersionNotification
        Settings.enableVersionNotification = CelestialWizardry.config
                .get(category, Settings.ConfigNames.ENABLE_VERSION_NOTIFICATION,
                     Settings.DefaultValues.ENABLE_VERSION_NOTIFICATION, Settings.Comments.ENABLE_VERSION_NOTIFICATION);

        // channel
        Settings.checkForLatest = CelestialWizardry.config.get(category, Settings.ConfigNames.CHECK_FOR_LATEST, Settings.DefaultValues.CHECK_FOR_LATEST, Settings.Comments.CHECK_FOR_LATEST);

        // debugMode
        Settings.debugMode = CelestialWizardry.config
                .get(category, Settings.ConfigNames.DEBUG_MODE, Settings.DefaultValues.DEBUG_MODE,
                     Settings.Comments.DEBUG_MODE);

        // shiftForDetails
        Settings.shiftForDetails = CelestialWizardry.config.get(category, Settings.ConfigNames.SHIFT_FOR_DETAILS,
                                                                Settings.DefaultValues.SHIFT_FOR_DETAILS,
                                                                Settings.Comments.SHIFT_FOR_DETAILS);

        /**
         * Spells
         */
        category = Settings.Categories.SPELLS;

        // spellCostMultiplier
        Settings.spellCostMultiplier = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPELL_COST_MULTIPLIER, Settings.DefaultValues.SPELL_COST_MULTIPLIER,
                     Settings.Comments.SPELL_COST_MULTIPLIER);

        /**
         * Client
         */
        category = Settings.Categories.CLIENT;

        // spellBook3dModel
        Settings.spellBook3dModel = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPELL_BOOK_3D_MODEL, Settings.DefaultValues.SPELL_BOOK_3D_MODEL,
                     Settings.Comments.SPELL_BOOK_3D_MODEL);

        /**
         * Tweaks
         */
        category = Settings.Categories.TWEAKS;

        // Difficulty
        category = Settings.Categories.DIFFICULTY;

        // spawnSpellBook
        Settings.spawnSpellBook = CelestialWizardry.config
                .get(category, Settings.ConfigNames.SPAWN_SPELL_BOOK, Settings.DefaultValues.SPAWN_SPELL_BOOK,
                     Settings.Comments.SPAWN_SPELL_BOOK);

        // startingIntelligence
        Settings.startingIntelligence = CelestialWizardry.config
                .get(category, Settings.ConfigNames.STARTING_INTELLIGENCE, Settings.DefaultValues.STARTING_INTELLIGENCE,
                     Settings.Comments.STARTING_INTELLIGENCE);

        // hardcoreRecipes
        Settings.hardcoreRecipes = CelestialWizardry.config
                .get(category, Settings.ConfigNames.HARDCORE_RECIPES, Settings.DefaultValues.HARDCORE_RECIPES,
                     Settings.Comments.HARDCORE_RECIPES);

        if (CelestialWizardry.config.hasChanged())
        {
            CelestialWizardry.config.save();
        }
    }
}
