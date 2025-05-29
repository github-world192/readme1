package game.components;
import java.util.*;
import enums.EffectEnum;

public class Chance extends Grids{
	private static final List<Card> chanceCards = new ArrayList<>();
    private static final Random rand = new Random();

    static {
        chanceCards.add(new Card("Bank pays you dividend of $1500", player -> player.setMoney(1500+player.getMoney())));
        chanceCards.add(new Card("Pay speeding fine $3000", player -> player.setMoney(-3000+player.getMoney())));
        chanceCards.add(new Card("Go to jail for 1 turn", player -> player.goToJail(1)));
        chanceCards.add(new Card("Get a jail release card", player -> player.setNumOfReleaseCard(1+player.getNumOfReleaseCard())));
        chanceCards.add(new Card("Go to jail for 3 turns", player -> player.goToJail(3)));
        chanceCards.add(new Card("Pay $800 to all other players.", player -> player.payAllOthers(800)));
        chanceCards.add(new Card("Pay $1000 to all other players.", player -> player.payAllOthers(1000)));
        chanceCards.add(new Card("Pay poor tax of $500.", player -> player.setMoney(-500+player.getMoney())));
        chanceCards.add(new Card("Pick up a bag of money of $5000.", player -> player.setMoney(5000+player.getMoney())));
    }

    public Chance(String gridName) {
        super(gridName);
    }

    @Override
    public EffectEnum effect(Players player) {
    	Monopoly game=Monopoly.getInstance();
        int index = rand.nextInt(chanceCards.size());
        Card card = chanceCards.get(index);
        game.lastDrawnCard=card;
        card.apply(player);
        return EffectEnum.CHANCE_CARD;
    }
}