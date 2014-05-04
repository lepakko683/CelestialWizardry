package celestialwizardry.init;

import celestialwizardry.item.*;
import celestialwizardry.reference.Names;
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

    public static void init()
    {
        GameRegistry.registerItem(magicalInk, "item." + Names.Items.MAGICAL_INK);
        GameRegistry.registerItem(staff, "item." + Names.Items.STAFF);
        GameRegistry.registerItem(scroll, "item." + Names.Items.SCROLL);
        GameRegistry.registerItem(spellScroll, "item." + Names.Items.SPELL_SCROLL);
        GameRegistry.registerItem(spellBook, "item." + Names.Items.SPELL_BOOK);
        GameRegistry.registerItem(concentrationRing, "item." + Names.Items.CONCENTRATION_RING);
        GameRegistry.registerItem(seasonRing, "item." + Names.Items.SEASON_RING);
        GameRegistry.registerItem(page, "item." + Names.Items.PAGE);
        GameRegistry.registerItem(magicalPen, "item." + Names.Items.MAGICAL_PEN);
    }
}
