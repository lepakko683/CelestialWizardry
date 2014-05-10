package celestialwizardry.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Allows you to access mod items
 */
public class ItemApi
{
    private static final String PACKAGE = "celestialwizardry.init.";

    /**
     * Allows you to get item from string.
     *
     * @param field the item field name
     * @param meta  the metadata
     * @return the item
     */
    public static ItemStack getItemStack(String field, int meta)
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
            CWApi.apiLog.warn("Could not retrieve item identified by " + field);
        }

        return stack;
    }

    /**
     * Allows you to get item from string. You should NOT use this, use the above method instead
     *
     * @param field the item field name
     * @return the item
     */
    public static Item getItem(String field)
    {
        Item item = null;

        try
        {
            String clazz = PACKAGE + "ModItems";
            Object obj = Class.forName(clazz).getField(field).get(null);
            item = (Item) obj;
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Could not retrieve item identified by " + field);
        }

        return item;
    }

    /**
     * Allows you to get block from string.
     *
     * @param field the block field name
     * @param meta  the metadata
     * @return the block
     */
    public static ItemStack getBlockStack(String field, int meta)
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
            CWApi.apiLog.warn("Could not retrieve block identified by " + field);
        }

        return stack;
    }

    /**
     * Allows you to get block from string. You should NOT use this, use the above method instead
     *
     * @param field the block field name
     * @return the block
     */
    public static Block getBlock(String field)
    {
        Block block = null;

        try
        {
            String clazz = PACKAGE + "ModBlocks";
            Object obj = Class.forName(clazz).getField(field).get(null);
            block = (Block) obj;
        }
        catch (Exception e)
        {
            CWApi.apiLog.warn("Could not retrieve block identified by " + field);
        }

        return block;
    }
}
