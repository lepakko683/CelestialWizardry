package celestialwizardry.client.render.crystal;

import celestialwizardry.client.model.OBJModels;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalComplex extends RenderCrystal
{
    public RenderCrystalComplex(ResourceLocation texture)
    {
        super(OBJModels.modelCrystalComplex, texture);
    }

    public RenderCrystalComplex(String texture)
    {
        this(new ResourceLocation(texture));
    }
}
