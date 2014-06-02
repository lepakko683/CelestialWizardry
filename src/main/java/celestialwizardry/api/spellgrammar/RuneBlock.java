package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneBlock extends RuneCategory {

	public RuneBlock(float complexity) {
		super(complexity);
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
	
	@Override
	public String getCategoryIDString() {
		return "block";
	}

}
