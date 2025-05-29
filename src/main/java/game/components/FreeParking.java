package game.components;
import enums.EffectEnum;

public class FreeParking extends Grids{
	public FreeParking(String gridName) {
		super(gridName);
	}
	@Override
	public EffectEnum effect(Players player) {
		return EffectEnum.FREE_PARKING;
	}
}
