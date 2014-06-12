package celestialwizardry.block;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.EnergyMagic;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.reference.Names;
import celestialwizardry.registry.EnergyRegistry;
import celestialwizardry.tileentity.TileEntityCrystal;
import celestialwizardry.tileentity.TileEntityCrystalConductive;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO I AM JUST A SMALL LITTLE PURPLE CRYSTAL WHO NEEDS MODEL ;) PROBABLY SOMETHING LIKE THAUMCRAFT CRYSTALS
 */
public class BlockCrystalConductive extends BlockCrystal
{
    public BlockCrystalConductive()
    {
        super();
        this.setBlockName(Names.Blocks.CRYSTAL_CONDUCTIVE);
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
        return new TileEntityCrystalConductive(this);
    }

    /* ======================================== ITileEntityProvider END ===================================== */

    /* ======================================== Block START ===================================== */

    /* @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderOBJBlock.ID;
    } */

    /* ======================================== Block END ===================================== */
}
