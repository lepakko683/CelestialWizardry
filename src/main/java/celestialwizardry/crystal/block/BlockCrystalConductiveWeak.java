package celestialwizardry.crystal.block;

import celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkConductiveWeak;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCrystalConductiveWeak extends BlockCrystal
{
    public BlockCrystalConductiveWeak()
    {
        super();
        this.setBlockName(CrystalNames.Blocks.CRYSTAL_CONDUCTIVE_WEAK);
    }

    /* ======================================== Block START ===================================== */

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        if (world instanceof World && !(((World) world).isRemote))
        {
            super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);

            if (world.getTileEntity(x, y, z) instanceof ICrystalNetworkBuffer)
            {
                if (world.getTileEntity(tileX, tileY, tileZ) instanceof ICrystalNetworkPool)
                {
                    ((ICrystalNetworkPool) world.getTileEntity(tileX, tileY, tileZ)).setDest(x, y, z);
                }
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        super.breakBlock(world, x, y, z, block, meta);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        super.randomDisplayTick(world, x, y, z, random);

        if (world.getTileEntity(x, y, z) instanceof TileEntityCrystalNetworkConductiveWeak)
        {
            /* if (((TileEntityCrystalConductive) world.getTileEntity(x, y, z)).open)
            {
                world.spawnParticle(Particles.LARGE_SMOKE, (double) x + random.nextDouble(), (double) y + 1,
                (double) z + random.nextDouble(), 0D, 0D, 0D);
            } */
        }
    }

    /* ======================================== Block END ===================================== */

    /* ======================================== ITileEntityProvider START ===================================== */

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     * @param var2
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2)
    {
        return new TileEntityCrystalNetworkConductiveWeak();
    }

    /* ======================================== ITileEntityProvider END ===================================== */
}
