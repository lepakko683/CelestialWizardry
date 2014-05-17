package celestialwizardry.api.spell;

import net.minecraft.item.ItemStack;

/**
 * Interface implemented in items that contain spells. Used to check if item is valid for various inventories
 */
public interface ISpellContainer
{
    public static final String SPELL_NBT_TAG = "cwSpell";

    /**
     * Returns the spell that is in the ItemStack
     *
     * @param stack the ItemStack containing the Spell
     *
     * @return the Spell that is in the stack
     */
    public Spell getSpell(ItemStack stack);

    /**
     * Sets spell to given ItemStack
     *
     * @param stack the ItemStack to receive the Spell
     * @param spell the Spell to set
     */
    public void setSpell(ItemStack stack, Spell spell);
}
