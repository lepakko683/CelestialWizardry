package celestibytes.celestialwizardry.crystal.block;

import celestibytes.celestialwizardry.block.BlockCW;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetwork;
import celestibytes.celestialwizardry.crystal.client.render.RenderCrystalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockCrystal extends BlockCW implements ITileEntityProvider
{
    public static final List<BlockCrystal> crystalList = new ArrayList<BlockCrystal>();

    public BlockCrystal()
    {
        super(Material.glass);
        this.setHardness(6.0F);

        crystalList.add(this);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (!world.isRemote)
        {
            if (world.getTileEntity(x, y, z) instanceof ICrystalNetwork)
            {
                ((ICrystalNetwork) world.getTileEntity(x, y, z)).onAdded();
            }
        }

        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (!world.isRemote)
        {
            if (world.getTileEntity(x, y, z) instanceof ICrystalNetwork)
            {
                ((ICrystalNetwork) world.getTileEntity(x, y, z)).onRemoved();
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
                                    float par8, float par9)
    {
        if (!world.isRemote)
        {
            // NO-OP (yet)
        }

        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderCrystalBlock.ID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     * @param var2
     */
    @Override
    public abstract TileEntity createNewTileEntity(World world, int var2);
}
