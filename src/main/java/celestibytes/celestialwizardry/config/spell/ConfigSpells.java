package celestibytes.celestialwizardry.config.spell;

import celestibytes.celestialwizardry.CelestialWizardry;
import celestibytes.celestialwizardry.api.spell.Spell;
import celestibytes.celestialwizardry.config.Config;
import celestibytes.celestialwizardry.registry.SpellRegistry;

public class ConfigSpells extends Config
{
    public ConfigSpells(String version)
    {
        super(version);
    }

    public static void init()
    {
        for (String s : SpellRegistry.spellMap.keySet())
        {
            Spell spell = SpellRegistry.getSpell(s);

            if (spell == null)
            {
                continue;
            }

            boolean isAllowed = CelestialWizardry.configSpells.get("whitelist", s, true);

            if (!isAllowed)
            {
                SpellRegistry.spellMap.remove(s);
            }
        }
    }

    public static double handleSpellCost(Spell spell, double defaultCost)
    {
        return CelestialWizardry.configSpells.get("costs", spell.getName(), defaultCost);
    }
}
