package celestialwizardry.block;

import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.reference.Names;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * TODO I AM JUST A SMALL LITTLE PURPLE CRYSTAL WHO NEEDS MODEL ;) PROBABLY SOMETHING LIKE THAUMCRAFT CRYSTALS
 */
public class BlockSmallCrystal extends BlockCW implements ITileEntityProvider
{
    public BlockSmallCrystal()
    {
        super(Material.glass);
        this.setBlockName(Names.Blocks.SMALL_CRYSTAL);
        this.setHardness(6.0F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9); // TODO PizzAna magic
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param var1
     * @param var2
     */
    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return null;
    }

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
}
