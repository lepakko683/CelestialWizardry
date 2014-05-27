package celestialwizardry.client.model;

import celestialwizardry.reference.Resources;

import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OBJModels
{

    public static WavefrontObject modelWritingTable;

    @SideOnly(Side.CLIENT)
    public static void init()
    {
        modelWritingTable = new WavefrontObject(Resources.Models.MODEL_WRITING_TABLE);
    }
}
