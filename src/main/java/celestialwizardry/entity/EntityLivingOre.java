package celestialwizardry.entity;

import celestialwizardry.entityAI.EntityAIOreGolemHide;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLivingOre extends EntityMob {
	//TODO: iterate over OreDictionary
	public class OreType {
		public static final int ORE_COAL = 0;
		public static final int ORE_LAPIS = 1;
		public static final int ORE_IRON = 2;
		public static final int ORE_GOLD = 3;
		public static final int ORE_REDSTONE = 4;
		public static final int ORE_DIAMOND = 5;
	}
	
	private int oreType;
	
	public EntityLivingOre(World par1World) {
		super(par1World);
		this.oreType = OreType.ORE_COAL;
//		this.getNavigator().setSpeed(0.5D);
		this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIOreGolemHide(this));
		
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setSize(0.9F, 0.9F);
	}
	
	public EntityLivingOre(World par1World, int oreType) {
		super(par1World);
		System.out.println("create");
		this.oreType = oreType>=0 && oreType<6 ? oreType : 0;
		System.out.println("Created Ore Golem with oretype: " + oreType);
//		this.getNavigator().setSpeed(0.5D);
		this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIOreGolemHide(this));
		
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setSize(0.9F, 0.9F);
	}
	
	
	
//	@Override
//	protected void entityInit() {
//		super.entityInit();
//	}
	
	public int getOreType() {
		return this.oreType;
	}
	
	protected boolean isAIEnabled()
    {
        return true;
    }
	
	public void onUpdate()
    {
//        System.out.println("uppdate " + this.getHealth());

        super.onUpdate();
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setInteger("oreType", this.oreType);
		super.writeEntityToNBT(par1nbtTagCompound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		this.oreType = par1nbtTagCompound.getInteger("oreType");
		super.readEntityFromNBT(par1nbtTagCompound);
	}
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
		this.oreType=0;//rand.nextInt(6);
		return super.onSpawnWithEgg(par1EntityLivingData);
	}
}
