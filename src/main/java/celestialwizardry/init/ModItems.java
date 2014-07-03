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
    // Materials
    public static final ItemMaterial material = new ItemMaterial();

    public static final ItemStack magicalIntelligenceCore = material.addItem(0, Names.Items.MATERIALS[0]);
    public static final ItemStack magicalPebble = material.addItem(1, Names.Items.MATERIALS[1]);
    public static final ItemStack netherPearl = material.addItem(2, Names.Items.MATERIALS[2]);
    public static final ItemStack mysteriousMatter = material.addItem(3, Names.Items.MATERIALS[3]);

    // Single items
    public static final ItemMagicalInk magicalInk = new ItemMagicalInk();
    public static final ItemStaff staff = new ItemStaff();
    public static final ItemScroll scroll = new ItemScroll();
    public static final ItemSpellScroll spellScroll = new ItemSpellScroll();
    public static final ItemSpellBook spellBook = new ItemSpellBook();
    public static final ItemConcentrationRing concentrationRing = new ItemConcentrationRing();
    public static final ItemSeasonRing seasonRing = new ItemSeasonRing();
    public static final ItemPage page = new ItemPage();
    public static final ItemMagicalPen magicalPen = new ItemMagicalPen();
    public static final ItemSoarynChestPlcr soarynChestPlcr = new ItemSoarynChestPlcr();

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
        register(soarynChestPlcr, Names.Items.SOARYN_CHEST_PLCR);
    }

    public static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, "item." + name);
    }
}
