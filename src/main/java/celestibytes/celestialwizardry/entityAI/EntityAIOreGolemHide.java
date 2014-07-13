package celestibytes.celestialwizardry.entityAI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

import java.util.Random;

public class EntityAIOreGolemHide extends EntityAIBase
{

    private EntityCreature entity;

    public EntityAIOreGolemHide(EntityCreature ent)
    {
        this.entity = ent;
    }

    @Override
    public boolean shouldExecute()
    {
        if (entity.getAITarget() != null)
        {
            return false;
        }
        return false;
//		long start = System.currentTimeMillis();
//		List ents = entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(entity
// .posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ));
//		for(int i=0;i<ents.size();i++) {
//			if(!((EntityPlayer)ents.get(i)).capabilities.isCreativeMode){
//				System.out.println("Found a player in " + (System.currentTimeMillis()-start) + " ms");
//				return true;
//			}
//		}
//		
//		return false;
    }

    private Vec3 findHidingHole(EntityCreature ec)
    {
        Random rand = ec.getRNG(); //#RNG!


        return null;
    }

}
