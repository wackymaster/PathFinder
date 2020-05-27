package paths;

import java.util.Arrays;
import java.util.List;

public class OptSwap {
	// 2-Opt Swap starts with nearest neighbor/greedy algorithm
	// After finding, the program does swaps between edges to find better values
	// The complexity of 2-Opt Swap is greater than O(n), but significantly faster than brute force
	// 2-opt swap does not find an absolute minimum, only local/relative minimum

    static int distance = 0;
    
    public static int[] swap(int[] a, int[][] weightMatrix) {
    	int[] swappedArray = a.clone();
    	int[] bestArray = a.clone();
    	int currentBest = determineDistance(a, weightMatrix);
    	int temp, currentScore;
    	for(int i=1;i<bestArray.length-2;i++) {
    		for(int j=i+1;j<bestArray.length-1;j++) {
    			temp = swappedArray[i];
    			swappedArray[i]=swappedArray[j];
    			swappedArray[j]=temp;
    			currentScore = determineDistance(swappedArray,weightMatrix);
    			if(currentScore<currentBest) {
    				currentBest = currentScore;
    				bestArray=swappedArray.clone();
    				i=1;
    				j=2;
    			}
    		}
    	}
    	for(int p: bestArray) {
    		System.out.print(p + " ");
    	}
    	System.out.println(determineDistance(bestArray,weightMatrix));
    	return bestArray;
    }
    
    public static int determineDistance(int[] path, int[][] weightMatrix) {
    	int d = 0;
    	for(int n=0; n<path.length-1;n++) {
    		d += weightMatrix[path[n]][path[n+1]];
    	}
    	return d;
    }
    
    public static int[] findPath(List<Node> nodesArray) {
    	if(nodesArray.size() < 2) {
    		System.out.println("More nodes needed");
    		return null;
    	}
    	else {
        	int[] gaPath = new int[nodesArray.size()+1];
        	int[] bestPath = new int[nodesArray.size()+1];
        	int[][] weightMatrix;
    		//Declare matrix for path weights based on length
    		weightMatrix = new int[nodesArray.size()][nodesArray.size()];
    		//Fill up matrix with correct values
    		for(int i=0;i<nodesArray.size();i++) {
        		for(int j=0;j<nodesArray.size();j++) {
        			int disX = (int) Math.pow(nodesArray.get(i).getPosX()-nodesArray.get(j).getPosX(),2);
        			int disY = (int) Math.pow(nodesArray.get(i).getPosY()-nodesArray.get(j).getPosY(),2);
        			int weight = (int) Math.sqrt(disX + disY);
        			weightMatrix[i][j]=weight;
        		}
    		}
    		gaPath = GreedyAlgorithm.findPath(nodesArray);
    		bestPath = swap(gaPath, weightMatrix);
    		System.out.println();
        	System.out.print("(2-Opt Swap.) Best Path: ");
        	for(int p: bestPath) {
        		System.out.print(p + " ");
        	}
        	System.out.println(" Distance: " + determineDistance(bestPath, weightMatrix));
        	return bestPath;
    	}
    	
    }
}
