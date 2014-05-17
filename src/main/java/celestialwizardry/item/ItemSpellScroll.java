package celestialwizardry.item;

import celestialwizardry.api.spell.ISpellContainer;
import celestialwizardry.api.spell.Spell;
import celestialwizardry.reference.Names;
import celestialwizardry.registry.SpellRegistry;
import celestialwizardry.util.NBTHelper;

import net.minecraft.item.ItemStack;

public class ItemSpellScroll extends ItemScroll implements ISpellContainer
{
    public ItemSpellScroll()
    {
        super();
        this.setUnlocalizedName(Names.Items.SPELL_SCROLL);
    }

    /**
     * Returns the spell that is in the ItemStack
     *
     * @param stack the ItemStack containing the Spell
     *
     * @return the Spell that is in the stack
     */
    @Override
    public Spell getSpell(ItemStack stack)
    {
        if (stack != null)
        {
            return SpellRegistry.getSpellFromString(NBTHelper.getString(stack, SPELL_NBT_TAG));
        }

        return null;
    }

    /**
     * Sets spell to given ItemStack
     *
     * @param stack the ItemStack to receive the Spell
     * @param spell the Spell to set
     */
    @Override
    public void setSpell(ItemStack stack, Spell spell)
    {
        if (spell != null && stack != null)
        {
            NBTHelper.setString(stack, SPELL_NBT_TAG, spell.getName());
        }
    }
}
