package celestialwizardry.block;

import java.util.Random;

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
		this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
		this.setBlockName(Names.Blocks.BELL);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int unk1, float unk2, float unk3, float unk4)
	{
		if (!world.isRemote)
		{
			Random rand = new Random();
			System.out.println("ding!");
			world.playSoundAtEntity(player, "random.orb", 1f, (50+rand.nextInt(11))/100f);
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
