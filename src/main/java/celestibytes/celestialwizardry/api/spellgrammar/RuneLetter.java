package celestibytes.celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneLetter extends RuneCategory
{

    public RuneLetter(float complexity)
    {
        super(complexity, false, null);
    }

    @Override
    public String getCategoryIDString()
    {
        return "letter";
    }

    @Override
    public List validRuneAttributeTypes()
    {
        return null;
    }

}
