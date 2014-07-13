package celestibytes.celestialwizardry.client.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

import celestibytes.celestialwizardry.util.LogH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Shader {
	public static final boolean USE_ARB = GLContext.getCapabilities().OpenGL21;
	
	private static List<Integer> toCleanup = new ArrayList<Integer>();
	
	private static boolean shadersEnabled = OpenGlHelper.shadersSupported;
	
	public static int VERTEX_SHADER = ARBVertexShader.GL_VERTEX_SHADER_ARB;
	public static int FRAGMENT_SHADER = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
	
	private int programId = 0;
	
	private int vertShaderId = 0;
	private int fragShaderId = 0;
	
	private boolean programValid = false;
	private boolean canUseShader = true;
	
	public Shader(String vertShaderSrc, String fragShaderSrc) {
		if(shadersEnabled) {
			if(vertShaderSrc != null) {
				vertShaderId = createShader(VERTEX_SHADER);
				
			}
			if(fragShaderSrc != null) {
				fragShaderId = createShader(FRAGMENT_SHADER);
			}
		} else {
			LogH.warn("Shaders aren't enabled, some visual features will be disabled. Your display drivers may be outdated or your graphics card doesn't support shaders.");
		}
		
	}
	
	public int getProgramId() {
		return this.programId;
	}
	
	public int getVertShaderId() {
		return this.vertShaderId;
	}
	
	public int getFragShaderId() {
		return this.fragShaderId;
	}
	
	public void useProgram() {
		if((vertShaderId == 0 && fragShaderId == 0) || !programValid) {
			if(USE_ARB) {
				ARBShaderObjects.glUseProgramObjectARB(programId);
			} else {
				GL20.glUseProgram(programId);
			}
		}
	}
	
	public static void init() {
		if(!USE_ARB) {
			VERTEX_SHADER = GL20.GL_VERTEX_SHADER;
			FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;
		} else {
			VERTEX_SHADER = ARBVertexShader.GL_VERTEX_SHADER_ARB;
			FRAGMENT_SHADER = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
		}
	}
	
	public static boolean areShadersEnabled() {
		return shadersEnabled;
	}
	
	private int createShader(int type) {
		if(USE_ARB) {
			return ARBShaderObjects.glCreateShaderObjectARB(type);
		} else {
			return GL20.glCreateShader(type);
		}
	}
	
	private int createProgram() {
		if(USE_ARB) {
			return ARBShaderObjects.glCreateProgramObjectARB();
		} else {
			return GL20.glCreateProgram();
		}
	}
	
	private void attachShader(int id) {
		if(id == 0) {
			return;
		}
		if(USE_ARB) {
			ARBShaderObjects.glAttachObjectARB(programId, id);
		} else {
			GL20.glAttachShader(programId, id);
		}
	}
	
	private boolean compileShader(int shader) {
		if(USE_ARB) {
			ARBShaderObjects.glCompileShaderARB(shader);
			return ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_TRUE;
		} else {
			GL20.glCompileShader(shader);
			return GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
		}
	}
	
	private boolean linkProgram() {
		if(USE_ARB) {
			ARBShaderObjects.glLinkProgramARB(programId);
			return ARBShaderObjects.glGetObjectParameteriARB(programId, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_TRUE;
		} else {
			GL20.glLinkProgram(programId);
			return GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
		}
	}
	
	private boolean validateProgram() {
		if(USE_ARB) {
			ARBShaderObjects.glValidateProgramARB(programId);
			return ARBShaderObjects.glGetObjectParameteriARB(programId, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_TRUE;
		} else {
			GL20.glValidateProgram(programId);
			return GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == GL11.GL_TRUE;
		}
	}
	
	private void shaderSource(int id, String src) {
		if(USE_ARB) {
			ARBShaderObjects.glShaderSourceARB(id, src);
		} else {
			GL20.glShaderSource(id, src);
		}
		
	}
	
	public void deleteShader() {
		if(USE_ARB) {
			ARBShaderObjects.glDeleteObjectARB(programId);
		} else {
			
		}
	}
}
