package celestialwizardry.config.spell;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spell.Spell;
import celestialwizardry.config.Config;
import celestialwizardry.registry.SpellRegistry;

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
