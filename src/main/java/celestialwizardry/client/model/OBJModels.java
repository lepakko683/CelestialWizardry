package celestialwizardry.client.model;

import celestialwizardry.reference.Resources;

import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OBJModels
{
	//BLOCKS
	public static WavefrontObject modelWritingTable = new WavefrontObject(Resources.Models.MODEL_WRITING_TABLE);
	public static WavefrontObject modelBell = new WavefrontObject(Resources.Models.MODEL_BELL); // f 33 46
	
//    public static WavefrontObject modelWritingTable;
    
    //ITEMS
    public static WavefrontObject modelSpellBookOpen;

    //ENTITIES
    public static WavefrontObject modelOreGolem;
    
//    @SideOnly(Side.CLIENT)
//    public static void init()
//    {
//        modelWritingTable = new WavefrontObject(Resources.Models.MODEL_WRITING_TABLE);
//    }
    
}
