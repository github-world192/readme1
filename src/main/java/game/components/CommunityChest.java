package game.components;
import java.util.*;
import enums.EffectEnum;

public class CommunityChest extends Grids{
	private static final List<Card> chestCards = new ArrayList<>();
    private static final Random rand = new Random();

    static {
    	chestCards.add(new Card("Doctor’s fees. Pay $1000", player -> player.setMoney(-1000+player.getMoney())));
    	chestCards.add(new Card("From sale of stock you get $2000", player -> player.setMoney(2000+player.getMoney())));
    	chestCards.add(new Card("Grand Opera Night. Collect $1000 from all other players", player -> player.collectFromAll(1000)));
    	chestCards.add(new Card("Income tax refund. Collect $500", player -> player.setMoney(500+player.getMoney())));
    	chestCards.add(new Card("Life insurance matures. Collect $1000", player -> player.setMoney(1000+player.getMoney())));
    	chestCards.add(new Card("Pay hospital fees of $3000", player -> player.setMoney(-3000+player.getMoney())));
    	chestCards.add(new Card("Go to jail for 2 turns", player -> player.goToJail(2)));
    	chestCards.add(new Card("Get a jail release card", player -> player.setNumOfReleaseCard(1+player.getNumOfReleaseCard())));
    }

    public CommunityChest(String gridName) {
        super(gridName);
    }

    @Override
    public EffectEnum effect(Players player) {
    	Monopoly game=Monopoly.getInstance();
        int index = rand.nextInt(chestCards.size());
        Card card = chestCards.get(index);
        game.lastDrawnCard=card;
        card.apply(player);
        return EffectEnum.COMMUNITY_CHEST_CARD;
    }
}