package paths;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithm {
	// Also known as "Nearest Neighbor"
	// The complexity of GreedyAlgorithm is O(n)... very efficient, but also inaccurate
    public static List<Integer> unvisitedNodes = new ArrayList<Integer>();
    static int currentNode;
    static int distance = 0;
    
    // Determine index of next minimum
    public static int findMinIndex(int[] m, List<Integer> uv) {

    	int currentMin = Integer.MAX_VALUE;
    	int loc = 0;
    	// Find the minimum and check to see if visiting is possible
    	for(int i=0;i<m.length;i++) {
    		if(m[i] < currentMin && uv.indexOf(i) >= 0) {
    			currentMin = m[i];
    			loc = i;
    		}
    	}
    	return loc;
    }
    
    public static int[] findPath(List<Node> nodesArray) {
    	int[] bestPath = new int[nodesArray.size()+1];
    	int[][] weightMatrix;
    	bestPath[0] = 0;
    	bestPath[nodesArray.size()] = 0;
    	if(nodesArray.size() < 2) {
    		System.out.println("More nodes needed");
    		return null;
    	}
    	else {

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
    	}
    	// Don't add the start node, because it will not be traveled to
    	for(int i=1;i<nodesArray.size();i++) {
    		unvisitedNodes.add(i);
    	}
    	currentNode = 0;
    	int minIndex;
    	int i=1;
    	while(unvisitedNodes.size() > 0) {
    		minIndex = findMinIndex(weightMatrix[currentNode], unvisitedNodes);
    		currentNode = minIndex;
    		unvisitedNodes.remove(Integer.valueOf(minIndex));
    		bestPath[i]=minIndex;
    		i++;
    	}
    	
    	distance = 0;
    	for(int n=0; n<bestPath.length-1;n++) {
    		distance += weightMatrix[bestPath[n]][bestPath[n+1]];
    		//System.out.println("from " + path[n] + " to " + path[n+1] + " is " + weightMatrix[path[n]][path[n+1]]);
    	}

 
		System.out.println();
    	System.out.print("(Greedy Alg.) Best Path: ");
    	for(int p: bestPath) {
    		System.out.print(p + " ");
    	}
    	System.out.println(" Distance: " + distance);
    	return bestPath;
    }
}
