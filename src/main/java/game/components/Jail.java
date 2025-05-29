package game.components;
import enums.EffectEnum;

public class Jail extends Grids{
	public Jail(String gridName) {
		super(gridName);
	}
	
	@Override
	public EffectEnum effect(Players player) {
		return EffectEnum.VISIT_JAIL;
	}
}
