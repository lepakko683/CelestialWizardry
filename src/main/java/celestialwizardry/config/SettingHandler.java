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
        Settings.enableSeasonal = CelestialWizardry.config.get(category, "enableSeasonal", true);
        Settings.debugMode = CelestialWizardry.config.get(category, "debugMode", false);

        /**
         * Spells
         */
        category = Settings.Categories.SPELLS;
        Settings.spellCostMultiplier = (float) CelestialWizardry.config.get(category, "spellCostMultiplier", 1.0f);

        /**
         * Client
         */
        category = Settings.Categories.CLIENT;
        Settings.spellBook3dModel = CelestialWizardry.config.get(category, "enableSpellBook3dModel", true);

        /**
         * Tweaks
         */
        category = Settings.Categories.TWEAKS;
        // Difficulty
        category = Settings.Categories.DIFFICULTY;
        Settings.spawnBook = CelestialWizardry.config.get(category, "spawnSpellBook", true);
    }
}
