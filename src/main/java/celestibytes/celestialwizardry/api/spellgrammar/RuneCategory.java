package celestibytes.celestialwizardry.api.spellgrammar;

public abstract class RuneCategory extends Rune
{

    public RuneCategory(float complexity, boolean takesAttribute, String runeId)
    {
        super(complexity, takesAttribute, runeId);
    }

    public abstract String getCategoryIDString();

}
