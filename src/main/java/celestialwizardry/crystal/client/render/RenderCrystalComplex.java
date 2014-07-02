package celestialwizardry.crystal.client.render;

import celestialwizardry.crystal.client.model.CrystalModels;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalComplex extends RenderCrystal
{
    public RenderCrystalComplex(ResourceLocation texture)
    {
        super(CrystalModels.modelCrystalComplex, texture);
    }

    public RenderCrystalComplex(String texture)
    {
        this(new ResourceLocation(texture));
    }
}
