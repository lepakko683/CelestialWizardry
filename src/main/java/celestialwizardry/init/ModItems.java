package celestialwizardry.init;

import celestialwizardry.item.*;
import celestialwizardry.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Just a class containing all mod items
 */
public class ModItems
{
    public static final ItemCW magicalInk = new ItemMagicalInk();
    public static final ItemCW staff = new ItemStaff();
    public static final ItemCW scroll = new ItemScroll();
    public static final ItemCW spellScroll = new ItemSpellScroll();
    public static final ItemCW spellBook = new ItemSpellBook();
    public static final ItemCW concentrationRing = new ItemConcentrationRing();
    public static final ItemCW seasonRing = new ItemSeasonRing();
    public static final ItemCW page = new ItemPage();
    public static final ItemCW magicalPen = new ItemMagicalPen();

    public static void init()
    {
        register(magicalInk, "item." + Names.Items.MAGICAL_INK);
        register(staff, "item." + Names.Items.STAFF);
        register(scroll, "item." + Names.Items.SCROLL);
        register(spellScroll, "item." + Names.Items.SPELL_SCROLL);
        register(spellBook, "item." + Names.Items.SPELL_BOOK);
        register(concentrationRing, "item." + Names.Items.CONCENTRATION_RING);
        register(seasonRing, "item." + Names.Items.SEASON_RING);
        register(page, "item." + Names.Items.PAGE);
        register(magicalPen, "item." + Names.Items.MAGICAL_PEN);
    }

    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, "item." + name);
    }
}
