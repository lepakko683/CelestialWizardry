package celestialwizardry.block;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityCrystal;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * TODO I AM JUST A SMALL LITTLE PURPLE CRYSTAL WHO NEEDS MODEL ;) PROBABLY SOMETHING LIKE THAUMCRAFT CRYSTALS
 */
public class BlockConductiveCrystal extends BlockCrystal
{
    public BlockConductiveCrystal()
    {
        super();
        this.setBlockName(Names.Blocks.CONDUCTIVE_CRYSTAL);
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
        return new TileEntityCrystal(this);
    }

    /* ======================================== ITileEntityProvider END ===================================== */

    /* ======================================== ICrystal START ===================================== */

    /**
     * Get the {@link celestialwizardry.api.energy.EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link ICrystal} is
     *
     * @return An array of {@link celestialwizardry.api.energy.EnergyType} this {@link ICrystal} can handle
     */
    @Override
    public EnergyType[] acceptableEnergies(World world)
    {
        return new EnergyType[0];
    }

    /**
     * Can this {@link celestialwizardry.api.crystal.ICrystal} be bounded to given {@link celestialwizardry.api.crystal.ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link celestialwizardry.api.crystal.ICrystal} is
     * @param crystal the {@link celestialwizardry.api.crystal.ICrystal} this {@link celestialwizardry.api.crystal
          * .ICrystal} is going to be bounded with
     *
     * @return can this {@link celestialwizardry.api.crystal.ICrystal} to the {@link celestialwizardry.api.crystal
          * .ICrystal}
     */
    @Override
    public boolean canBoundTo(World world, ICrystal crystal)
    {
        return true;
    }

    /* ======================================== ICrystal END ===================================== */

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
