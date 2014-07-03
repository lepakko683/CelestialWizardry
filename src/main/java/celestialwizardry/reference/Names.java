package celestialwizardry.reference;

public class Names
{
    public static class Blocks
    {
        public static final String WRITING_TABLE = "writingTable";
        public static final String LIVING_ORE = "livingOre";
        public static final String BELL = "bell";
        public static final String MAGICAL_STONE = "magicalStone";
        public static final String CONTAINED_CRYSTAL = "containedCrystal";
    }

    public static class Items
    {
        public static final String MATERIAL = "material";
        public static final String[] MATERIALS = {
                "magicalIntelligenceCore", "magicalPebble", "netherPearl", "mysteriousMatter"
        };

        public static final String MAGICAL_INK = "magicalInk";
        public static final String STAFF = "staff";
        public static final String SCROLL = "scroll";
        public static final String SPELL_SCROLL = "spellScroll";
        public static final String SPELL_BOOK = "spellBook";
        public static final String CONCENTRATION_RING = "concentrationRing";
        public static final String[] CONCENTRATION_RING_SUBTYPES = {"normal", "lunar", "solar"};
        public static final String SEASON_RING = "seasonRing";
        public static final String PAGE = "page";
        public static final String MAGICAL_PEN = "magicalPen";
        public static final String SOARYN_CHEST_PLCR = "soarynChestPlcr";
    }

    public static class NBT
    {
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
        public static final String SPELL_BOOK_GUI_OPEN = "spellBookGuiOpen";
        public static final String SPELL_BOOK_INVENTORY_OPEN = "spellBookInventoryOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
        public static final String IS_CUSTOM = "isCustom";
        public static final String DISPLAY = "display";
        public static final String NAME = "Name";
        public static final String LORE = "Lore";
        public static final String BACKUP_NAME = "backupName";
        public static final String OPENERS = "openers";
        public static final String OPEN = "open";
    }

    public static class Containers
    {
        public static final String VANILLA_INVENTORY = "container.inventory";

        public static final String WRITING_TABLE = "container." + Resources.RESOURCE_PREFIX + Blocks.WRITING_TABLE;
        public static final String SPELL_BOOK_INVENTORY = "container." + Resources.RESOURCE_PREFIX + Items.SPELL_BOOK
                + "Inventory";
    }
}
