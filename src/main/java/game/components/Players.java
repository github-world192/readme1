package game.components;
import java.util.*;
import enums.EffectEnum;
public class Players {
    private int money;
    private int numOfLandsOwn;
    private String name;
    private int location;
    private String color;
    private boolean isBankrupt;
    private boolean inJail;
    private int inJailTurn;
    private boolean surrendering;
    private int releaseCard;
    
    public Players(String name, String color) {
    	this.money=20000;
    	this.numOfLandsOwn=0;
    	this.name=name;
    	this.color=color;
    	this.location=0;
    	this.inJail=false;
    	this.inJailTurn=0;
    	this.isBankrupt=false;
    	this.surrendering=false;
    }
    
    public void setSurrendering(boolean surrendering) {
    	this.surrendering = surrendering;
    }
    public boolean isSurrendering() {
    	return surrendering;
    }
    
    public void setLocation(int location) {
    	this.location=location;
    }
    public int getLocation() {
    	return this.location;
    }
    
    public String getColor() {
    	return this.color;
    }
    
    
    public int getMoney(){
        return this.money;
    }
    public void setMoney(int money){
        this.money=money;
    }
    
    
    
    public String getName(){
        return this.name;
    }
    public int getNumOfLandsOwn() {
    	return this.numOfLandsOwn;
    }
    public boolean getBankrupt() {
    	return this.isBankrupt;
    }
    
    
    public void goToJail(int turn) {
    	this.inJailTurn=turn;
    	this.inJail=true;
    	this.location=10;
    }
    public void reduceJailTime(int turn) {
    	this.inJailTurn-=turn;
    	if(this.inJailTurn<=0) {
    		this.inJail=false;
    	}
    }
    public boolean isInJail() {
    	return this.inJail;
    }
    public int getInJailTurns() {
    	return this.inJailTurn;
    }
    public void useReleaseCard() {
    	this.releaseCard-=1;
    	this.inJail=false;
    	this.inJailTurn=0;
    }
    
    
    
    public int rollDice() {
    	return (int)(Math.random()*6)+1;
    }
    public EffectEnum move(int step) {
    	 int boardSize = Monopoly.getInstance().getGridsList().size();
    	 int oldPos = this.location;
    	 this.location = (this.location + step) % boardSize;
    	 if (this.location < oldPos) {
    		 return EffectEnum.PASS_START;
    	 } else {
    		 return EffectEnum.NONE;
    	 }
    }
    
    
    public int getNumOfReleaseCard() {
    	return this.releaseCard;
    }
    public void setNumOfReleaseCard(int num) {
    	this.releaseCard=num;
    }
    
    public void payAllOthers(int pay) {
    	Monopoly instance=Monopoly.getInstance();
    	List<Players> allOthers=instance.getPlayersList();
    	this.setMoney(this.getMoney() - pay*allOthers.size());
    	for(Players i : allOthers) {
    		i.setMoney(pay+i.getMoney());
    	}
    }
    
    public void collectFromAll(int collect) {
    	Monopoly instance=Monopoly.getInstance();
    	List<Players> allOthers=instance.getPlayersList();
    	this.setMoney(this.getMoney() + collect*allOthers.size());
    	for(Players i : allOthers) {
    		i.setMoney(-collect+i.getMoney());
    	}
    }
    
    
    public void bankrupt() {
    	this.isBankrupt=true;
    	this.setMoney(-99999999);
    	Monopoly gameInstance=Monopoly.getInstance();
    	List<Grids> gridsList=gameInstance.getGridsList();
    	for(Grids i : gridsList) {
    		if (i instanceof Lands) {
    	        Lands land = (Lands) i;
    	        if (this.equals(land.getOwner())) {
    	            land.setOwner(null);
    	        }
    	    }
    	}
    	gameInstance.numOfBankrupt+=1;
    }
}
