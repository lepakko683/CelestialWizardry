package celestialwizardry.util;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;

public class TextureLoader {
	
	private static final Map<Object, Object> mapTextureLocations = new HashMap<Object, Object>(); 
	
	public void loadTexture(ResourceLocation loc) {
		Object object = (ITextureObject)mapTextureLocations.get(loc);
		
		if(object == null) {
			object = new SimpleTexture(loc);
			FMLClientHandler.instance().getClient().getTextureManager().loadTexture(loc, (ITextureObject)object);
		}
		
	}
}
