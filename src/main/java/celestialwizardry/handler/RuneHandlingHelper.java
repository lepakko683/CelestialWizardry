package celestialwizardry.handler;

import celestialwizardry.util.Location3D;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;

public class RuneHandlingHelper
{


    /*TODO: I'd like to have a dynamic system for this*/
    public static Entity getNewEntityInstance(String name, World world)
    {
        if (name.startsWith("minecraft:"))
        {
            if (name.equalsIgnoreCase("chicken"))
            {
                return new EntityChicken(world);
            }
            if (name.equalsIgnoreCase("cow"))
            {
                return new EntityCow(world);
            }
            if (name.equalsIgnoreCase("sheep"))
            {
                return new EntitySheep(world);
            }
            if (name.equalsIgnoreCase("mooshroom"))
            {
                return new EntityMooshroom(world);
            }
            if (name.equalsIgnoreCase("bat"))
            {
                return new EntityBat(world);
            }
            if (name.equalsIgnoreCase("ocelot") || name.equalsIgnoreCase("cat"))
            {
                return new EntityOcelot(world);
            }
            if (name.equalsIgnoreCase("wolf") || name.equalsIgnoreCase("dog"))
            {
                return new EntityWolf(world);
            }
            if (name.equalsIgnoreCase("villager") || name.equalsIgnoreCase("testificate"))
            {
                return new EntityVillager(world);
            }
            if (name.equalsIgnoreCase("horse"))
            {
                return new EntityHorse(world);
            }

            if (name.equalsIgnoreCase("zombie"))
            {
                return new EntityZombie(world);
            }
            if (name.equalsIgnoreCase("skeleton"))
            {
                return new EntitySkeleton(world);
            }
            if (name.equalsIgnoreCase("creeper"))
            {
                return new EntityCreeper(world);
            }
            if (name.equalsIgnoreCase("ghast"))
            {
                return new EntityGhast(world);
            }
            if (name.equalsIgnoreCase("enderman") || name.equalsIgnoreCase("endermen"))
            {
                return new EntityEnderman(world);
            }
            if (name.equalsIgnoreCase("slime"))
            {
                return new EntitySlime(world);
            }
            if (name.equalsIgnoreCase("magmacube"))
            {
                return new EntityMagmaCube(world);
            }
            if (name.equalsIgnoreCase("blaze"))
            {
                return new EntityBlaze(world);
            }
            if (name.equalsIgnoreCase("silverfish"))
            {
                return new EntitySilverfish(world);
            }
            if (name.equalsIgnoreCase("spider"))
            {
                return new EntitySpider(world);
            }
            if (name.equalsIgnoreCase("cavespider"))
            {
                return new EntityCaveSpider(world);
            }
            if (name.equalsIgnoreCase("pigzombie") || name.equalsIgnoreCase("zombiepig") || name
                    .equalsIgnoreCase("zombiepigman") || name.equalsIgnoreCase("zisteau")/*;)*/)
            {
                return new EntityPigZombie(world);
            }
        }
        return new EntityPig(world);
    }

    public static Tuple getBlock(String name)
    {
//		return new Tuple(Block.);
        return null;
    }

    public static Location3D getLocationFor(Entity e)
    {
        return new Location3D(e.posX, e.posY, e.posZ);
    }
}
