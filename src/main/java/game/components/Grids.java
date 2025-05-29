package game.components;
import enums.EffectEnum;

public abstract class Grids {
    protected String gridName;
    public Grids(String gridName) {
        this.gridName=gridName;
    }
    
    abstract public EffectEnum effect(Players player);
    
    public String getGridName(){
        return this.gridName;
    }
}
