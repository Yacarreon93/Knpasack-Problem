package knpasack.aco;

import java.util.Scanner;
import knapsack.Item;

public class TestACO {
    
    private static final float INITIAL_PHEROMONE = 0.01F;
    
    private static int numItems;
    private static Item[] items;
    private static int numAnts;
    private static float[] pheromone;
    
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        numItems = 5;
        initItems();
    }
    
    private static void initItems() {
        int v, w;
        items = new Item[numItems];
        pheromone = new float[numItems];
        for(int i = 0; i < numItems; i++) {
            System.out.println("Object " + (i + 1) + "");
            System.out.print("Insert value: ");
            v = sc.nextInt();            
            System.out.print("Insert weight: ");
            w = sc.nextInt();
            System.out.println("-------------- ");
            items[i] = new Item(i, i);
            pheromone[i] = INITIAL_PHEROMONE;
        }        
    }
    
}
