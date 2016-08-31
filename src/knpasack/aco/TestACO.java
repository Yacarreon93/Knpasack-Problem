package knpasack.aco;

import java.util.Random;
import java.util.Scanner;
import knapsack.Item;

public class TestACO {
    
    private static final float INITIAL_PHEROMONE = 0.01F;
    
    private static int numItems;
    private static Item[] items;
    private static int numAnts;
    private static int[][] tabuList;
    private static float[] pheromone;
    private static int numLoops;
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        numItems = 5;
        numAnts = 10;
        numLoops = 10;
        initItems();
        for (int i = 0; i < numLoops; i++) {
            initAnts();
            printTabuList();
        }         
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
    
    private static void initAnts() {
        tabuList = new int[numAnts][numItems];
        for (int i = 0; i < numAnts; i++) {
            tabuList[i][0] = getRandomItem();
            for (int j = 1; j < numItems; j++) {
                tabuList[i][j] = -1;
            }
        }
    }  
    
    public static void printTabuList() {
        System.out.println("---------");
        System.out.println("Tabu List");
        System.out.println("---------");
        for (int i = 0; i < numAnts; i++) {
            System.out.print(i + 1 + ":\t");
            for (int j = 0; j < numItems; j++) {
                System.out.print(tabuList[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    private static int getRandomItem() {
        Random random = new Random();
        int randomItem = (int)(random.nextDouble() * (numItems + 1));
        return randomItem;
    }
}
