package celestialwizardry.block;

import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityBell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBell extends BlockCW implements ITileEntityProvider
{

	public BlockBell()
	{
		super(Material.iron);
		this.setHardness(4.0f);
		this.setBlockName(Names.Blocks.BELL);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int unk1, float unk2, float unk3, float unk4)
	{
		if (world.isRemote)
		{
			world.playSoundAtEntity(player, "random.orb", 1f, .5f);
			return true;
		}
		return super.onBlockActivated(world, x, y, z, player, unk1, unk2, unk3, unk4);
	}
	
	@Override
	public int getRenderType()
	{
		return RenderOBJBlock.ID;
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
    

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBell();
	}

}
