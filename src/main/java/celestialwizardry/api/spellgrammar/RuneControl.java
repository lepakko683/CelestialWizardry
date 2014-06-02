package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneControl extends RuneCategory
{
    //AND, IF, NOT, etc
	
	public RuneControl(float complexity) {
		super(complexity);
	}

	@Override
	public String getCategoryIDString() {
		return "control";
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
	
}
