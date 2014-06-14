package celestialwizardry.client.render.crystal;

import celestialwizardry.block.BlockCrystal;
import celestialwizardry.client.model.OBJModels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalComplex extends RenderCrystal
{
    public RenderCrystalComplex(BlockCrystal crystal)
    {
        super(OBJModels.modelCrystalComplex, crystal);
    }
}
