package celestialwizardry.item;

import celestialwizardry.api.spell.ISpellContainer;
import celestialwizardry.api.spell.Spell;
import celestialwizardry.reference.Names;

public class ItemSpellScroll extends ItemScroll implements ISpellContainer
{
    public ItemSpellScroll()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_SCROLL);
    }

    @Override
    public Spell getSpell()
    {
        return null;
    }
}
