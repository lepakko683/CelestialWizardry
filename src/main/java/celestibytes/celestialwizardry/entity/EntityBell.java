package celestibytes.celestialwizardry.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.List;

public class EntityBell extends EntityMob
{

    private String playerToFollow = null;
    private int teleCheckDelay = 200;

    public EntityBell(World par1World)
    {
        super(par1World);
    }

    public EntityBell(World par1World, String player)
    {
        super(par1World);
        this.setSize(0.5F, 0.5F);
        this.playerToFollow = player;
        this.tasks.addTask(0, new EntityAILookIdle(this));
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.setVelocity(0.1d, 0d, 0d);
        if (teleCheckDelay <= 0)
        {
            teleportToPlayer();
            teleCheckDelay = 200;
        }
        teleCheckDelay--;

//		this.setVelocity(0d, 0.00d, 0d);
//		System.out.println("I'm following player: " + (playerToFollow == null ? "none" : playerToFollow));
    }

    private void teleportToPlayer()
    {
        if (playerToFollow != null && playerToFollow.length() > 0 && !this.worldObj.isRemote)
        {
            List plrs = this.worldObj.playerEntities;
            if (plrs != null && plrs.size() > 0)
            {
                EntityPlayer targett = null;
                for (int i = 0; i < plrs.size(); i++)
                {
                    targett = ((EntityPlayer) plrs.get(i));
                    if (targett.getDisplayName().equalsIgnoreCase(playerToFollow))
                    {
                        System.out.println("found my target!!! :)");
                        System.out.println("Xdiff: " + Math.abs(this.posX - targett.posX));
                        System.out.println("Ydiff: " + Math.abs(this.posY - targett.posY));
                        System.out.println("Zdiff: " + Math.abs(this.posZ - targett.posZ));

                        try
                        {
                            double dist = Math.sqrt(Math.pow(Math.abs(this.posX - targett.posX), 2) + Math
                                    .pow(Math.abs(this.posY - targett.posY), 2) + Math
                                    .pow(Math.abs(this.posZ - targett.posZ), 2));
                            System.out.println("Dist: " + dist);
                        }
                        catch (Exception e)
                        {
                            System.err.println("Math error!");
                        }
                        return;
                    }


                }

            }
        }
    }

    @Override
    public void onLivingUpdate()
    {
//		System.out.println("update!!!!");
        super.onLivingUpdate();
    }

}
