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
	public static WavefrontObject modelCrystalItemHolder = new WavefrontObject(Resources.Models.MODEL_CRYS_ITEM_HOLDER);
	
//	public static OBJModelBlock modelCrystalContained = new OBJModelBlock(Resources.Models.MODEL_CRYSTAL_CONTAINED);
	public static OBJModelBlock modelCrystalContained = new OBJModelBlock(Resources.Models.MODEL_CRYSTAL_CONTAINED_QUADS);
	public static OBJModelBlock modelCrystalSimpleQ = new OBJModelBlock(Resources.Models.MODEL_CRYSTAL_SIMPLE_QUADS);

    public static WavefrontObject modelCrystalSimple = new WavefrontObject(Resources.Models.Crystals.MODEL_CRYSTAL_SIMPLE);
    public static WavefrontObject modelCrystalComplex = new WavefrontObject(Resources.Models.Crystals.MODEL_CRYSTAL_COMPLEX);

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
