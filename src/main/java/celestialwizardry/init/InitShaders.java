package celestialwizardry.init;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

import celestialwizardry.client.util.Shader;
import net.minecraft.client.renderer.OpenGlHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class InitShaders {
	private static int VERTEX_SHADER = ARBVertexShader.GL_VERTEX_SHADER_ARB;
	private static int FRAGMENT_SHADER = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
	
	public static Shader spellSwitcherCloudShader;
	
	public static void init() {
		
	}
	
	
}
