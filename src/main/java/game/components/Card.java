package game.components;
public class Card {
    private String description;
    private CardEffect cardEffect;

    public Card(String description, CardEffect effect) {
        this.description = description;
        this.cardEffect = effect;
    }

    public void apply(Players player) {
    	cardEffect.apply(player);
    }

    public String getDescription() {
        return description;
    }
}
