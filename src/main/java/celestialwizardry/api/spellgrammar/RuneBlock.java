package celestialwizardry.api.spellgrammar;

import celestialwizardry.util.Tuple;

import net.minecraft.block.Block;

import java.util.List;


public class RuneBlock extends RuneCategory
{

    /**
     * A = Block, B = Integer
     */
    private Tuple block;
    private Object blockType;

    public RuneBlock(float complexity, Object blockType, String runeName)
    {
        super(complexity, true, null);
//		this.block = block;
    }

    @Override
    public List validRuneAttributeTypes()
    {
        return null;
    }

    public Tuple getBlockAndMeta()
    {
        return block;
    }

    public Block getBlock(IAttributeRune attr)
    {
        if (!(blockType instanceof BlockType))
        {
            return null;
        }
        switch ((BlockType) blockType)
        {
            case FULL_BLOCK:

                break;
            case SLAB:

                break;
            case STAIRS:

                break;
            case FENCE:

                break;
            case OTHER:

                break;
            default:
                break;
        }
        return (Block) block.getA();
    }

    public int getMeta()
    {
        return (Integer) block.getB();
    }

    @Override
    public String getCategoryIDString()
    {
        return "block";
    }

    public static enum BlockType
    {
        FULL_BLOCK,
        SLAB,
        STAIRS,
        FENCE,
        OTHER;

    }

}
