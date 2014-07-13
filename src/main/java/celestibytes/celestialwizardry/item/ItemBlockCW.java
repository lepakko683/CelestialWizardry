package celestibytes.celestialwizardry.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockCW extends ItemBlock
{
    public ItemBlockCW(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }
}
