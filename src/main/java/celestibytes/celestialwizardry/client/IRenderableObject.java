package celestibytes.celestialwizardry.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.WavefrontObject;


public interface IRenderableObject
{
    public WavefrontObject getModel();

    public ResourceLocation getTexture();
}
