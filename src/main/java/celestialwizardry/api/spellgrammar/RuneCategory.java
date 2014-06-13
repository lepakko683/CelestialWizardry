package celestialwizardry.api.spellgrammar;

public abstract class RuneCategory extends Rune {

	public RuneCategory(float complexity, boolean takesAttribute) {
		super(complexity, takesAttribute);
	}
	
	public abstract String getCategoryIDString();

}
