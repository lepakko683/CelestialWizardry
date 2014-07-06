package celestialwizardry.api.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

public class Target {
	
	public final boolean multiple;
//	private final Object[] data; TODO
	public final TargetType type;
	
	public Target(Entity ent) {
		this.multiple = false;
		type = TargetType.ENTITY;
	}
	
	public Target(Block block, int x, int y, int z, int meta) {
		this.multiple = false;
		type = TargetType.BLOCK;
	}
	
	public Target(Block[] block, int[] x, int[] y, int[] z, int[] meta) {
		int test = block.length + x.length + y.length + z.length + meta.length;
		test /= 5;
		if(block.length==test && x.length == test && y.length == test && z.length == test && meta.length == test) {
			// Maybe unnecessary
		}
		this.multiple = true;
		type = TargetType.BLOCK;
	}
	
	public float getTargX() {
		switch(type) {
		case BLOCK:
			return getBlockX();
		case ENTITY:
			return getEntityX(null);
		}
		return 0f;
	}
	
	private float getEntityX(Entity e) {
		return (float)e.posX;
	}
	
	private float getEntityY(Entity e) {
		return (float)e.posY;
	}
	
	private float getEntityZ(Entity e) {
		return (float)e.posZ;
	}
	
	private float getBlockX() {
		return 0f;
	}
	
}
