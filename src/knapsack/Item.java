package knapsack;

public class Item {
    
    private int utility;
    private int weight;
    
    public Item(int u, int w) {
        utility = u;
        weight = w;
    }
    
    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    } 
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public double getValue() {
        double value;
        if(weight > 0) {
            value = (double)utility / (double)weight;
        } else {
            value = utility;
        }        
        return value;
    }
    
}
    