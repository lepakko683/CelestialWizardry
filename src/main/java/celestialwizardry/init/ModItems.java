package celestialwizardry.init;

import celestialwizardry.item.*;
import celestialwizardry.reference.Names;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

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
    public static final ItemCW matrix = new ItemMatrix();
    public static final ItemCW dummyItem = new ItemDummy();

    public static void init()
    {
        register(magicalInk, Names.Items.MAGICAL_INK);
        register(staff, Names.Items.STAFF);
        register(scroll, Names.Items.SCROLL);
        register(spellScroll, Names.Items.SPELL_SCROLL);
        register(spellBook, Names.Items.SPELL_BOOK);
        register(concentrationRing, Names.Items.CONCENTRATION_RING);
        register(seasonRing, Names.Items.SEASON_RING);
        register(page, Names.Items.PAGE);
        register(magicalPen, Names.Items.MAGICAL_PEN);
        register(matrix, Names.Items.MATRIX);
        register(dummyItem, Names.Items.DUMMY_ITEM);
    }

    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, "item." + name);
    }
}
