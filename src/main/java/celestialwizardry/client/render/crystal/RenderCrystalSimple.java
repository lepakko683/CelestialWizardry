package celestialwizardry.client.render.crystal;

import celestialwizardry.block.BlockCrystal;
import celestialwizardry.client.model.OBJModels;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalSimple extends RenderCrystal
{
    public RenderCrystalSimple(ResourceLocation texture)
    {
        super(OBJModels.modelCrystalSimple, texture);
    }

    public RenderCrystalSimple(String texture)
    {
        this(new ResourceLocation(texture));
    }
}
