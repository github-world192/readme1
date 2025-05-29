package game.components;
import enums.EffectEnum;

public class StartGrid extends Grids{
	private int bonus;
	
	public StartGrid(String gridName) {
		super(gridName);
		this.bonus=5000;
	}
	@Override
	public EffectEnum effect(Players player) {
		return EffectEnum.NONE;
	}
	public int getBonus() {
		return this.bonus;
	}
}

