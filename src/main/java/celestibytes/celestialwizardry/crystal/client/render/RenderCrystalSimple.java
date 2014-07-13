package celestibytes.celestialwizardry.crystal.client.render;

import celestibytes.celestialwizardry.crystal.client.model.CrystalModels;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalSimple extends RenderCrystal
{
    public RenderCrystalSimple(ResourceLocation texture)
    {
        super(CrystalModels.modelCrystalSimple, texture);
    }

    public RenderCrystalSimple(String texture)
    {
        this(new ResourceLocation(texture));
    }
}
