package celestibytes.celestialwizardry.crystal.client.model;

import celestibytes.celestialwizardry.crystal.reference.CrystalResources;

import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CrystalModels
{
    public static WavefrontObject modelCrystalSimple = new WavefrontObject(
            CrystalResources.Models.MODEL_CRYSTAL_SIMPLE);
    public static WavefrontObject modelCrystalComplex = new WavefrontObject(
            CrystalResources.Models.MODEL_CRYSTAL_COMPLEX);
}
