package celestialwizardry.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class OBJmodels {
	
	public static WavefrontObject modelWritingTable;
	
	@SideOnly(Side.CLIENT)
	public static void init() {
		modelWritingTable = new WavefrontObject(new ResourceLocation("celestialwizardry", "models/WritingTable3.obj"));
	}
}
