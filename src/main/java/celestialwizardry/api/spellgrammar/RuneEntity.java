package celestialwizardry.api.spellgrammar;

import java.util.List;

public class RuneEntity extends RuneCategory
{
	private String entName;
	
	public RuneEntity(float complexity, String entityName) {
		super(complexity, false, entityName);
		this.entName = entityName;
	}
	
	public String getEntityName() {
		return this.entName;
	}
	
	@Override
	public List validRuneAttributeTypes() {
		return null;
	}

	@Override
	public String getCategoryIDString() {
		return "entity";
	}
}
