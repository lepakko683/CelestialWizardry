package celestibytes.celestialwizardry.init;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBVertexShader;

import celestibytes.celestialwizardry.client.util.Shader;

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
