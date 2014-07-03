package celestialwizardry.block;

import celestialwizardry.client.render.RenderMagicalStone;
import celestialwizardry.init.ModItems;
import celestialwizardry.reference.Names;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

public class BlockMagicalStone extends BlockCW
{
    private IIcon innerTexture;

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

    public int getRenderBlockPass()
    {
        return 0;
    }

    @Override
    public int getRenderType()
    {
        return RenderMagicalStone.ID;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        innerTexture = iconRegister.registerIcon("celestialwizardry:magicalStoneInner");
        super.registerBlockIcons(iconRegister);
    }

    public IIcon getInnerIcon()
    {
        return this.innerTexture;
    }
}
