package celestialwizardry.crystal.block;

import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkSolarWeak;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrystalSolarWeak extends BlockCrystal
{
    public BlockCrystalSolarWeak()
    {
        super();
        this.setBlockName(CrystalNames.Blocks.CRYSTAL_SOLAR_WEAK);
    }

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
        return new TileEntityCrystalNetworkSolarWeak();
    }

    /* ======================================== ITileEntityProvider END ===================================== */
}
