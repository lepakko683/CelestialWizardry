package celestibytes.core.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }
}
