package celestialwizardry.block;

import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Names;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockMagicalStone extends BlockCW
{
    public BlockMagicalStone()
    {
        super(Material.rock);
        this.setBlockName(Names.Blocks.MAGICAL_STONE);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ModItems.material;
    }

    @Override
    public int damageDropped(int meta)
    {
        return ModItems.magicalPebble.getItemDamage();
    }
}
