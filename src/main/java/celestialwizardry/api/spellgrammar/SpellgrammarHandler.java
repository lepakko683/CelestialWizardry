package celestialwizardry.api.spellgrammar;

import celestialwizardry.util.Location3D;
import celestialwizardry.util.Tuple;

import net.minecraft.entity.Entity;

public abstract class SpellgrammarHandler
{

    private String modId;

    public SpellgrammarHandler(String modId)
    {
        this.modId = modId;
    }

    public String getModId()
    {
        return modId;
    }

    public abstract Entity getEntityForRune(RuneEntity r);

    /**
     * @return Tuple(Block block, int metadata)
     */
    public abstract Tuple getBlockForRune(RuneBlock r);

    public abstract Location3D getLocationForRune(RuneLocation r);


}
