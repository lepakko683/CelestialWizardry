package celestialwizardry.init;

import celestialwizardry.item.*;
import celestialwizardry.reference.Names;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Just a class containing all mod items
 */
public class ModItems
{
    public static final ItemMaterial material = new ItemMaterial();

    // Materials
    public static final ItemStack magicalIntelligenceCore = material.addItem(0, Names.Items.MATERIALS[0]);
    public static final ItemStack magicalPebble = material.addItem(1, Names.Items.MATERIALS[1]);

    // Single items
    public static final ItemSingle magicalInk = new ItemMagicalInk();
    public static final ItemSingle staff = new ItemStaff();
    public static final ItemSingle scroll = new ItemScroll();
    public static final ItemSingle spellScroll = new ItemSpellScroll();
    public static final ItemSingle spellBook = new ItemSpellBook();
    public static final ItemSingle concentrationRing = new ItemConcentrationRing();
    public static final ItemSingle seasonRing = new ItemSeasonRing();
    public static final ItemSingle page = new ItemPage();
    public static final ItemSingle magicalPen = new ItemMagicalPen();
    public static final ItemSingle matrix = new ItemMatrix();

    public static void init()
    {
        register(material, Names.Items.MATERIAL);

        // Single items
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
    }

    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, "item." + name);
    }
}
