package celestibytes.celestialwizardry.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CompiledSpell {
	public final double spellcost;
	private SpellAction[] actions;
	
	public CompiledSpell(double cost, SpellAction[] actionss) {
		this.spellcost = cost;
		if(actionss != null) {
			System.arraycopy(actionss, 0, this.actions, 0, actionss.length);
		} else {
			this.actions = null;
		}
	}
	
	public void cast(World world, Entity caster, Object[][] data) {
		for(int i= 0;i < this.actions.length; i++) {
			actions[i].execute(world, caster, data[i]);
		}
			
	}
	
}
