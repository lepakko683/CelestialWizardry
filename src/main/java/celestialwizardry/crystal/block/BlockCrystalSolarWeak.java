package celestialwizardry.crystal.block;

import celestialwizardry.crystal.api.crystal.ICrystalNetworkBuffer;
import celestialwizardry.crystal.api.crystal.ICrystalNetworkPool;
import celestialwizardry.crystal.reference.CrystalNames;
import celestialwizardry.crystal.tileentity.TileEntityCrystalNetworkSolarWeak;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrystalSolarWeak extends BlockCrystal
{
    public BlockCrystalSolarWeak()
    {
        super();
        this.setBlockName(CrystalNames.Blocks.CRYSTAL_SOLAR_WEAK);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        if (world instanceof World && !(((World) world).isRemote))
        {
            super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);

            if (world.getTileEntity(x, y, z) instanceof ICrystalNetworkPool)
            {
                if (world.getTileEntity(tileX, tileY, tileZ) instanceof ICrystalNetworkBuffer)
                {
                    ((ICrystalNetworkPool) world.getTileEntity(x, y, z)).setDest(tileX, tileY, tileZ);
                }
                else
                {
                    ((ICrystalNetworkPool) world.getTileEntity(x, y, z)).setDest(tileX, tileY, tileZ); // aka null
                }
            }
        }
    }

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
}
