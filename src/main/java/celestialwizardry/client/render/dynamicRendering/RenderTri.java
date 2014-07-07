package celestialwizardry.client.render.dynamicRendering;

import net.minecraft.client.renderer.Tessellator;
import celestialwizardry.util.LogHelper;

public class RenderTri {
	
	private Vertex[] verts;
	
	public RenderTri(Vertex[] verts) {
		if(verts != null && verts.length == 3) {
			System.arraycopy(verts, 0, this.verts, 0, 3);
		} else {
			LogHelper.err("Invalid amount of verticies in contruction of a RenderTri or array is null...");
		}
	}
	
	public void tessellateVerts(Tessellator tes) {
		for(int i=0;i<verts.length;i++) {
			this.verts[i].addToTes(tes);
		}
	}
	
	
}
