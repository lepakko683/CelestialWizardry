package celestialwizardry.util.helper;

import celestialwizardry.util.LogH;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpellBookHelper {
	
	public static final String TAG_PAGECOUNT = "cwSbPageCount";
	public static final String TAG_PAGE_ID_ARRAY= "cwSbPageArray";
	
	/**@return an int array containing the contained*/
	public static int[] getContainedPages(ItemStack spellbook) {
		NBTTagCompound data = spellbook.getTagCompound();
		if(data != null && !data.hasNoTags()) {
			if(data.getInteger(TAG_PAGECOUNT) > 0) {
				return data.getIntArray(TAG_PAGE_ID_ARRAY);
			}
			
		} else {
			LogH.err("Couldn't get NBTTagCompound from spellbook itemstack.");
		}
		
		
		return new int[0];
	}
}
