package game.components;
import enums.EffectEnum;

public class Lands extends Grids{
    private int price;
    private int toll;
    private Players owner;
    public Lands(String gridName, int price){
        super(gridName);
        this.price=price;
        this.toll=0;
        this.owner=null;
    }

    public void setOwner(Players owner) {
    	this.owner=owner;
    }
    public Players getOwner(){
        return this.owner;
    }

    public int getPrice(){
        return this.price;
    }

    public void setToll(int toll){
        this.toll=toll;
    }
    public int getToll(){
        return this.toll;
    }

    @Override
    public EffectEnum effect(Players player){
    	//if ApplyGridEffectServlet enter this, then the owner is first to judge
    	if (this.owner!=null && !this.owner.equals(player)) {
            player.setMoney(player.getMoney()-this.toll);
            this.owner.setMoney(this.owner.getMoney()+this.toll);
            return EffectEnum.PAID_TOLL;
        }
    	else if(this.owner!=null && this.owner.equals(player)) {
    		return EffectEnum.NONE;
    	}
    	
    	//if BuyLandServlet enter this, then the player already confirm yes to buy
    	if (player.getMoney() >= this.getPrice()) {
    		player.setMoney(player.getMoney()-this.getPrice());
            this.setOwner(player);
            this.setToll((int)(this.getPrice() * 0.4));
            return EffectEnum.LAND_PURCHASED;
        }
    	return EffectEnum.CANNOT_AFFORD_LAND;
    }
}
