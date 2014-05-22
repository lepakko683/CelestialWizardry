package celestialwizardry.api.spellgrammar;

import java.util.List;

public abstract class Rune
{
    public abstract int getRuneType(); //TODO: return type likely not final

    public abstract float getRuneComplexity();

    public abstract List validRuneAttributeTypes();
}
