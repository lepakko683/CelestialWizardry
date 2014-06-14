package celestialwizardry.client.render.crystal;

import celestialwizardry.block.BlockCrystal;
import celestialwizardry.client.model.OBJModels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalSimple extends RenderCrystal
{
    public RenderCrystalSimple(BlockCrystal crystal)
    {
        super(OBJModels.modelCrystalSimple, crystal);
    }
}
