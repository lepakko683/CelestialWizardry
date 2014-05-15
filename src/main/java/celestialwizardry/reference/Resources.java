package celestialwizardry.reference;

import celestialwizardry.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Resources
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static class Textures
    {
        // GUI textures
        public static final String GUI_SHEET_LOCATION = "textures/gui/";

        public static final ResourceLocation GUI_WRITING_TABLE = ResourceLocationHelper.getResourceLocation(
                GUI_SHEET_LOCATION + "writingTable.png");
        public static final ResourceLocation GUI_SPELL_BOOK_INVENTORY = ResourceLocationHelper
                .getResourceLocation(GUI_SHEET_LOCATION + "spellBookInventory.png");
        public static final ResourceLocation GUI_SPELL_BOOK = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "spellBook.png");
    }

    public static class Models
    {
        // Model textures
        public static final String MODEL_LOCATION = "textures/models/";

        public static final ResourceLocation MODEL_SPELL_BOOK = ResourceLocationHelper.getResourceLocation(MODEL_LOCATION + "spellBook.png");
    }
}
