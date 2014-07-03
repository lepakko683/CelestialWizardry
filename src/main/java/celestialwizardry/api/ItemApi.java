package celestialwizardry.api;

import celestialwizardry.api.util.ApiLogHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Allows you to access mod items
 */
public class ItemApi
{
    private static final String PACKAGE = CWApi.CW_PACKAGE + "init.";

    /**
     * Allows you to get item from string.
     *
     * @param field the item field name
     * @param meta  the metadata
     *
     * @return the item
     */
    public static ItemStack getItem(String field, int meta)
    {
        ItemStack stack = null;

        try
        {
            String clazz = PACKAGE + "ModItems";
            Object obj = Class.forName(clazz).getField(field).get(null);

            if (obj instanceof Item)
            {
                stack = new ItemStack((Item) obj, 1, meta);
            }
            else if (obj instanceof ItemStack)
            {
                stack = (ItemStack) obj;
            }
        }
        catch (Exception ex)
        {
            ApiLogHelper.warn("Could not retrieve item identified by " + field);
        }

        return stack;
    }

    /**
     * Allows you to get block from string.
     *
     * @param field the block field name
     * @param meta  the metadata
     *
     * @return the block
     */
    public static ItemStack getBlock(String field, int meta)
    {
        ItemStack stack = null;

        try
        {
            String clazz = PACKAGE + "ModBlocks";
            Object obj = Class.forName(clazz).getField(field).get(null);

            if (obj instanceof Block)
            {
                stack = new ItemStack((Block) obj, 1, meta);
            }
            else if (obj instanceof ItemStack)
            {
                stack = (ItemStack) obj;
            }
        }
        catch (Exception ex)
        {
            ApiLogHelper.warn("Could not retrieve block identified by " + field);
        }

        return stack;
    }
}
