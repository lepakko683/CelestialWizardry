package celestialwizardry.api.spellgrammar;

public abstract class RuneCategory extends Rune {

	public RuneCategory(float complexity) {
		super(complexity);
	}
	
	public abstract String getCategoryIDString();

}
