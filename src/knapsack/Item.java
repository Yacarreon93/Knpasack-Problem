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
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public float getRealValue() {
        float realValue;
        if(weight > 0) {
            realValue = (float)(value / weight);
        } else {
            realValue = value;
        }        
        return realValue;
    }
    
}
    