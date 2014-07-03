package celestialwizardry.init;

import celestialwizardry.api.spellgrammar.AttributeRune;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.api.spellgrammar.RuneBlock;
import celestialwizardry.api.spellgrammar.RuneEntity;
import celestialwizardry.registry.RuneRegistry;
import celestialwizardry.util.Tuple;

import net.minecraft.init.Blocks;

public class InitRunes
{

//	public static final Rune runeAction = new RuneAction();
//	public static final Rune runeActionTeleport = new RuneAction();

    //Entities
    public static final Rune runeEntityPig = new RuneEntity(1f, "pig");
    public static final Rune runeEntityCow = new RuneEntity(1f, "cow");
    public static final Rune runeEntityChicken = new RuneEntity(1f, "chicken");
    public static final Rune runeEntityBat = new RuneEntity(1f, "bat");
    public static final Rune runeEntityOcelot = new RuneEntity(1f, "ocelot");
    public static final Rune runeEntityWolf = new RuneEntity(1f, "wolf");
    public static final Rune runeEntityMooshroom = new RuneEntity(1f, "mooshroom");
    public static final Rune runeEntityVillager = new RuneEntity(1f, "villager");
    public static final Rune runeEntityHorse = new RuneEntity(1f, "horse");

    public static final Rune runeEntityZombie = new RuneEntity(1f, "zombie");
    public static final Rune runeEntitySkeleton = new RuneEntity(1f, "skeleton");
    public static final Rune runeEntityCreeper = new RuneEntity(1f, "creeper");
    public static final Rune runeEntityGhast = new RuneEntity(1f, "ghast");
    public static final Rune runeEntityEnderman = new RuneEntity(1f, "enderman");
    public static final Rune runeEntitySlime = new RuneEntity(1f, "slime");
    public static final Rune runeEntityMagmacube = new RuneEntity(1f, "magmacube");
    public static final Rune runeEntityBlaze = new RuneEntity(1f, "blaze");
    public static final Rune runeEntitySilverfish = new RuneEntity(1f, "silverfish");
    public static final Rune runeEntitySpider = new RuneEntity(1f, "spider");
    public static final Rune runeEntityCaveSpider = new RuneEntity(1f, "cavespider");
    public static final Rune runeEntityPigZombie = new RuneEntity(1f, "pigzombie");

    //Block attributes
    public static final Rune runeAttrAir = new AttributeRune(1f, null, false); // null?
    public static final Rune runeAttrCobblestone = new AttributeRune(1f, new Tuple(Blocks.cobblestone, 0), false);
    public static final Rune runeAttrBricks = new AttributeRune(1f, new Tuple(Blocks.cobblestone, 0), true)
            .setRequiresAttribute();
    public static final Rune runeAttrMossy = new AttributeRune(1f, new Tuple(Blocks.cobblestone, 0), false);
    public static final Rune runeAttrCracked = new AttributeRune(1f, new Tuple(Blocks.cobblestone, 0), false);
    public static final Rune runeAttrChiseled = new AttributeRune(1f, new Tuple(Blocks.cobblestone, 0), false);


    public static final Rune runeBlockFull = new RuneBlock(1f, RuneBlock.BlockType.FULL_BLOCK, "fullBlock")
            .setRequiresAttribute();
    public static final Rune runeBlockSlab = new RuneBlock(1f, RuneBlock.BlockType.SLAB, "slab");
    public static final Rune runeBlockStairs = new RuneBlock(1f, RuneBlock.BlockType.STAIRS, "stairs");
    public static final Rune runeBlockFence = new RuneBlock(1f, RuneBlock.BlockType.FENCE, "fence");
    public static final Rune runeBlockOther = new RuneBlock(1f, RuneBlock.BlockType.OTHER, "other");
//	public static final Rune runeBlockAir = new RuneBlock(1f, new Tuple(Blocks.air, 0));
//	public static final Rune runeBlockStone = new RuneBlock(1f, new Tuple(Blocks.stone, 0));
//	public static final Rune runeBlockCobblestone = new RuneBlock(1f, new Tuple(Blocks.cobblestone, 0));
//	public static final Rune runeBlockBricksStone = new RuneBlock(1f, new Tuple(Blocks.stonebrick,
// 0)); // Attributes: Mossy:1, Cracked:2, Chiseled:3
//	public static final Rune runeBlockDirt = new RuneBlock(1f, new Tuple(Blocks.dirt, 0));
//	public static final Rune runeBlockGrass = new RuneBlock(1f, new Tuple(Blocks.dirt, 0));


//	@SideOnly(Side.SERVER)
//	public void serverInitRunes() {
//		RuneRegistry.
//	}
//	
//	@SideOnly(Side.CLIENT)
//	public void clientInitRunes(/*RuneConfig rc*/) {
//		
//	}

    public static void init()
    {
        RuneRegistry.registerRune(runeEntityPig);
        RuneRegistry.registerRune(runeEntityCow);
        RuneRegistry.registerRune(runeEntityChicken);
        RuneRegistry.registerRune(runeEntityBat);
        System.out.println("INIT RUNES!!!");
    }
}
