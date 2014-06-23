package celestialwizardry.block;

import celestialwizardry.client.render.RenderOBJBlock;
import celestialwizardry.reference.Names;
import net.minecraft.block.material.Material;

public class BlockContainedCrystal extends BlockCW {
	// Totally not a reference to the desert crystals in Ratchet & Clank 2: Going Commando on planet Tabora

	public BlockContainedCrystal() {
		super(Material.rock);
		this.setBlockName(Names.Blocks.CONTAINED_CRYSTAL);
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

}
