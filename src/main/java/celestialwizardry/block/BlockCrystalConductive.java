package celestialwizardry.block;

import celestialwizardry.init.ModBlocks;
import celestialwizardry.reference.Names;
import celestialwizardry.tileentity.TileEntityCrystalConductive;

public class BlockCrystalConductive extends BlockCrystal
{
    public BlockCrystalConductive()
    {
        super("textures/blocks/gold_block.png", RenderStyle.SIMPLE, new TileEntityCrystalConductive(ModBlocks.crystalConductive)); // TODO Blue/purple texture
        this.setBlockName(Names.Blocks.CRYSTAL_CONDUCTIVE);
    }

    /* ======================================== Block START ===================================== */

    /* ======================================== Block END ===================================== */
}
