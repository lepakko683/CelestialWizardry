package celestialwizardry.util;

public class Cycler {
	
	private ActiveNumber rotation = new ActiveNumber(ActiveNumber.MODE_KEEP_WITHIN_BOUNDS, 0).setBounds(0, 359);
	
	public Cycler() {
		
	}
	
	public void advanceCycle(double value) {
		rotation.update(value);
	}
}
