package celestialwizardry.client.render.dynamicRendering;

import net.minecraft.client.renderer.Tessellator;
import celestialwizardry.util.LogHelper;

public class RenderQuad {
	
	private Vertex[] verts;
	
	public RenderQuad(Vertex[] verts) {
		if(verts != null && verts.length == 4) {
			System.arraycopy(verts, 0, this.verts, 0, 4);
		} else {
			LogHelper.err("Invalid amount of verticies in contruction of a RenderQuad or array is null...");
		}
	}
	
	public void tessellateVerts(Tessellator tes) {
		for(int i=0;i<verts.length;i++) {
			this.verts[i].addToTes(tes);
		}
	}
	
	public RenderTri[] getInside() {
		
		return null;
	}
	
	public RenderTri[] getOutside() {
		
		return null;
	}
}
