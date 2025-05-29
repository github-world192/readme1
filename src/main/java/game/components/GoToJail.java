package game.components;
import enums.EffectEnum;

public class GoToJail extends Grids{
	public GoToJail(String gridName) {
		super(gridName);
	}
	
	@Override
	public EffectEnum effect(Players player) {
		player.goToJail(2);
		return EffectEnum.GO_TO_JAIL;
	}
}
