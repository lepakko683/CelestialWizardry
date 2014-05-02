package celestialwizardry.init;

import celestialwizardry.item.ItemCW;
import celestialwizardry.item.ItemMagicalInk;
import celestialwizardry.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Just a class containing all mod items
 */
public class ModItems
{
    public static final ItemCW magicalInk = new ItemMagicalInk();

    public static void init()
    {
        GameRegistry.registerItem(magicalInk, "item." + Names.Items.MAGICAL_INK);
    }
}
