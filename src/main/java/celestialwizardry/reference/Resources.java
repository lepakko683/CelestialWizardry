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
        public static final ResourceLocation GUI_SPELL_BOOK = ResourceLocationHelper
                .getResourceLocation(GUI_SHEET_LOCATION + "spellBook.png");
        public static final ResourceLocation GUI_SPELL_BOOK_PAGES = ResourceLocationHelper
                .getResourceLocation(GUI_SHEET_LOCATION + "spellBookPages.png");

        @Deprecated
        public static final ResourceLocation GUI_SPELL_BOOK_OLD = ResourceLocationHelper
                .getResourceLocation(GUI_SHEET_LOCATION + "spellBookOld.png");
        
        
    }

    public static class Models
    {
        // Model textures
        public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
        public static final String MODEL_LOCATION = "models/";

        public static final ResourceLocation TEXTURE_SPELL_BOOK = ResourceLocationHelper
                .getResourceLocation(MODEL_TEXTURE_LOCATION + "spellBook.png");
        public static final ResourceLocation TEXTURE_WRITING_TABLE = ResourceLocationHelper
                .getResourceLocation(MODEL_TEXTURE_LOCATION + "writingTable.png");
        
        public static final ResourceLocation TEXTURE_BELL = new ResourceLocation("textures/blocks/gold_block.png");
        public static final ResourceLocation TEXTURE_CONTAINED_CRYSTAL = new ResourceLocation("textures/blocks/stone.png");

        
        
        // Model files (.OBJs)
        public static final ResourceLocation MODEL_WRITING_TABLE = ResourceLocationHelper
                .getResourceLocation(MODEL_LOCATION + "WritingTable.obj");
        
        public static final ResourceLocation MODEL_BELL = ResourceLocationHelper
                .getResourceLocation(MODEL_LOCATION + "RandomBell2.obj");
        
        public static final ResourceLocation MODEL_CRYS_ITEM_HOLDER = ResourceLocationHelper
        		.getResourceLocation(MODEL_LOCATION + "CrystalItemHolder.obj");
        
        public static final ResourceLocation MODEL_CRYSTAL_CONTAINED = ResourceLocationHelper
        		.getResourceLocation(MODEL_LOCATION + "CrystalContained.obj");
        
		public static final ResourceLocation MODEL_CRYSTAL_CONTAINED_QUADS = ResourceLocationHelper
        		.getResourceLocation(MODEL_LOCATION + "CrystalContainedQuads.obj");
		
		public static final ResourceLocation MODEL_CRYSTAL_SIMPLE_QUADS = ResourceLocationHelper
        		.getResourceLocation(MODEL_LOCATION + "CrystalSimpleQuads.obj");
    }
    
    public static class Misc
    {
    	public static final String BLOCK_TEXTURE_LOCATION = "textures/blocks/";
    	public static final ResourceLocation TEXTURE_MAGICAL_STONE_INNER = ResourceLocationHelper.getResourceLocation(BLOCK_TEXTURE_LOCATION + "magicalStoneOLD.png");
    }
}
