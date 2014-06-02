package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneMagic extends RuneCategory
{
	//MAGIC TYPE, MAGIC TYPE TRANSFORM
    public RuneMagic(float complexity) {
		super(complexity);
	}

	@Override
	public String getCategoryIDString() {
		return "magic";
	}
	
	@Override
	public List validRuneAttributeTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
