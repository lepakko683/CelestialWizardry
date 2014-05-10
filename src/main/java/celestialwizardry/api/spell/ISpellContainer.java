package celestialwizardry.api.spell;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Interface implemented in items that contain spells. Used to check if item is valid for various inventories
 */
public interface ISpellContainer
{
    /**
     * Returns the spell that is in the {@link net.minecraft.item.ItemStack}
     *
     * @param stack the {@link net.minecraft.item.ItemStack} containing the {@link celestialwizardry.api.spell.Spell}
     * @return the {@link celestialwizardry.api.spell.Spell} that is in the {@link net.minecraft.item.ItemStack}
     */
    public Spell getSpell(ItemStack stack);
}
