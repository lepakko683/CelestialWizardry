package celestibytes.celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneMagic extends RuneCategory
{
    //MAGIC TYPE, MAGIC TYPE TRANSFORM
    public RuneMagic(float complexity)
    {
        super(complexity, false, null);
    }

    @Override
    public String getCategoryIDString()
    {
        return "magic";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List validRuneAttributeTypes()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
