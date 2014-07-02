package celestialwizardry.crystal.block;

import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalConductive;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCrystalConductive extends BlockCrystal
{
    public BlockCrystalConductive()
    {
        super();
        this.setBlockName(CrystalNames.Blocks.CRYSTAL_CONDUCTIVE);
    }

    /* ======================================== Block START ===================================== */

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        super.randomDisplayTick(world, x, y, z, random);

        if (world.getTileEntity(x, y, z) instanceof TileEntityCrystalConductive)
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
        return new TileEntityCrystalConductive(this);
    }

    /* ======================================== ITileEntityProvider END ===================================== */
}
