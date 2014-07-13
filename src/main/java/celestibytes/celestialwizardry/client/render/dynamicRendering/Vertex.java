package celestibytes.celestialwizardry.client.render.dynamicRendering;

import net.minecraft.client.renderer.Tessellator;
import celestibytes.core.util.Colour;

public class Vertex {
	
	public float x, y, z;
	public float u, v;
	public Colour color;
	
	public Vertex(float x, float y, float z, float u, float v) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}
	
	public void addToTes(Tessellator tes) {
		tes.addVertexWithUV((double)x, (double)y, (double)z, (double)u, (double)v);
	}
}
