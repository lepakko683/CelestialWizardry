package celestialwizardry.reference;

public class Names
{
    public static class Blocks
    {
        public static final String WRITING_TABLE = "writingTable";
    }

    public static class Items
    {
        public static final String MAGICAL_INK = "magicalInk";
        public static final String STAFF = "staff";
        public static final String SCROLL = "scroll";
        public static final String SPELL_SCROLL = "spellScroll";
        public static final String SPELL_BOOK = "spellBook";
        public static final String CONCENTRATION_RING = "concentrationRing";
        public static final String[] CONCENTRATION_RING_SUBTYPES = {"normal", "lunar", "solar"};
        public static final String SEASON_RING = "seasonRing";
        public static final String PAGE = "page";
    }

    public static class NBT
    {
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
        public static final String SPELL_BOOK_GUI_OPEN = "spellBookGuiOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
    }

    public static class Containers
    {
        public static final String VANILLA_INVENTORY = "container.inventory";

        public static final String WRITING_TABLE = "container." + Resources.RESOURCE_PREFIX + Blocks.WRITING_TABLE;
    }
}
