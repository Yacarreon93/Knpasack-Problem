package knapsack;

public class Item {
    
    private int value;
    private int weight;
    
    public Item(int v, int w) {
        value = v;
        weight = w;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }  
    
}
    