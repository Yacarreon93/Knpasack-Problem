package knpasack.aco;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;   
import knapsack.Item;

public class TestACO {
    
    private static final double INITIAL_PHEROMONE = 0.01F;
    private static final double ALPHA = 4F;
    private static final double BETA = 3F;
    private static final double RHO = 0.95F;
    
    private static int numItems;
    private static Item[] items;
    private static int numAnts;
    private static int[][] tabuList;
    private static double[] pheromone;
    private static int numLoops;
    private static int knapsackMaxCapacity;
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        numItems = 5;
        numAnts = 10;
        numLoops = 10;
        knapsackMaxCapacity = 30;
        initItems();
        for (int i = 0; i < numLoops; i++) {
            initAnts();
            buildSolutions();
        }   
        printSolution();
    }
    
    private static void initItems() {
        int u, w;
        items = new Item[numItems];
        pheromone = new double[numItems];
        for(int i = 0; i < numItems; i++) {
            System.out.println("Object " + (i + 1) + "");
            System.out.print("Insert utility: ");
            u = sc.nextInt();            
            System.out.print("Insert weight: ");
            w = sc.nextInt();
            System.out.println("-------------- ");
            items[i] = new Item(u, w);
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
    
    public static void buildSolutions() {
        double[] probItems;
        double probTotal;
        int selectedItem;
        int localWeight;
        int pointerIndex;
        double[] tempPheromone = new double[numItems];        
        initTempPheromone(tempPheromone, pheromone);
        for (int i = 0; i < numAnts; i++) {
            localWeight = 0;
            pointerIndex = 1;
            while(localWeight < knapsackMaxCapacity && pointerIndex < numItems) {
                probItems = new double[numItems];
                probTotal = getItemsProb(probItems, i);
                normalize(probItems, probTotal);
                selectedItem = selectItem(probItems);
                tabuList[i][pointerIndex] = selectedItem;
                updateTempPheromone(tempPheromone, selectedItem);
                localWeight += items[selectedItem].getWeight();
                pointerIndex++;
            }             
        }
        updatePheromone(pheromone, tempPheromone);
    }   
    
    public static void updatePheromone(double[] pheromone, double[] tempPheromone){
        System.arraycopy(tempPheromone, 0, pheromone, 0, tempPheromone.length);
    }
    
    public static void initTempPheromone(double[] tempPheromone, double[] pheromone) {
        System.arraycopy(pheromone, 0, tempPheromone, 0, pheromone.length);
    }
    
    public static void updateTempPheromone(double[] tempPheromone, int index) {
        tempPheromone[index] = (tempPheromone[index] * RHO) + INITIAL_PHEROMONE;
    }
    
    public static int selectItem(double[] probItems) {
        int selectedItem = 0;        
        Random random = new Random();
        double probAcum = 0;
        for (int i = 0; i < probItems.length; i++) {
            probAcum += probItems[i];
            if(random.nextDouble() <= probAcum) {
                selectedItem = i;
                break;
            }
        }                      
        return selectedItem;
    }
    
    public static double getItemsProb(double[] probItems, int idAnt) {
        double probTotal = 0;
        int localWeight = 0;
        for (int i = 0; i < probItems.length; i++) {
            probItems[i] = -1;
        }
        for (int i = 0; i < probItems.length; i++) {
            if(localWeight + items[i].getWeight() <= knapsackMaxCapacity && isAvialable(i, idAnt)) {
                localWeight += items[i].getWeight();
                probItems[i] = getItemProb(i);
                probTotal += probItems[i];
            } else {
                 continue;
            }
        }
        return probTotal;
    }
    
    public static boolean isAvialable(int idItem, int idAnt) {
        boolean isAvialable = false;
        Integer item = idItem;
        Integer[] list = Arrays.stream(tabuList[idAnt]).boxed().toArray( Integer[]::new );
        isAvialable = Arrays.asList(list).contains(item);;
        return isAvialable;
    }
    
    public static double getItemProb(int idItem) {        
        double prob;
        prob = Math.pow(pheromone[idItem], ALPHA) * Math.pow(items[idItem].getValue(), BETA);
        return prob;
    }
    
    public static void normalize(double[] values, double total) {
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] / total;
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
    
    public static void printSolution() {
        printTabuList();
    }
    
    private static int getRandomItem() {
        Random random = new Random();
        int randomItem = (int)(random.nextDouble() * (numItems + 1));
        return randomItem;
    }
}
