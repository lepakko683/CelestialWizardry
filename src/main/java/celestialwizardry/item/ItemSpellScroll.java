package celestialwizardry.item;

import celestialwizardry.api.spell.ISpellContainer;
import celestialwizardry.api.spell.Spell;
import celestialwizardry.reference.Names;
import net.minecraft.item.ItemStack;

public class ItemSpellScroll extends ItemScroll implements ISpellContainer
{
    public ItemSpellScroll()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_SCROLL);
    }

    /**
     * Returns the spell that is in the {@link net.minecraft.item.ItemStack}
     *
     * @param stack the {@link net.minecraft.item.ItemStack} containing the {@link celestialwizardry.api.spell.Spell}
     * @return the {@link celestialwizardry.api.spell.Spell} that is in the {@link net.minecraft.item.ItemStack}
     */
    @Override
    public Spell getSpell(ItemStack stack)
    {
        return null;
    }
}
