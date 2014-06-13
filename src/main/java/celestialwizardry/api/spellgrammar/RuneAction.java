package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneAction extends RuneCategory
{
	//SPAWN, TELEPORT, ACCELERATE
	private String actionString;
	
	public RuneAction(float complexity, String actionString, boolean takesAttribute) {
		super(complexity, takesAttribute);
	}

	@Override
	public List validRuneAttributeTypes() {
		return null;
	}
	
	@Override
	public String getCategoryIDString() {
		return "action";
	}
   
}
