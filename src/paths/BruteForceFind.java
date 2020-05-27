package paths;

import java.util.ArrayList;
import java.util.List;

public class BruteForceFind {
	// The complexity of BruteForceFind is O(n!)... very inefficient, but finds best solution
    public static int factorial(int n) {
    	int ans = 1;
    	for(int i = n; i>0;i--) {
    		ans *= i;
    	}
    	return ans;
    }
    
    public static int[] createPath(int iter, int len) {
    	int[] path = new int[len+2];
    	List<Integer> availableNodes = new ArrayList<Integer>();
    	int nextNode;
    	// Fill up with available nodes
    	for(int i=0;i<len;i++) {
    		availableNodes.add(i+1);
    	}
    	// Create a path based on the current iteration
    	for(int i=1;i<len+1;i++) {
    		nextNode = availableNodes.get(iter/factorial(len-(i)) % (len - (i-1)));
    		availableNodes.remove(iter/factorial(len-(i)) % (len - (i-1)));
    		path[i] = nextNode;
    	}

    	return path;
    }
    
    public static int[] findPath(List<Node> nodesArray) {
    	if(nodesArray.size() < 2) {
    		System.out.println("More nodes needed");
    		return null;
    	}
    	else {
    		//Declare matrix for path weights based on length
    		int[][] weightMatrix = new int[nodesArray.size()][nodesArray.size()];
    		//Fill up matrix with correct values
    		for(int i=0;i<nodesArray.size();i++) {
        		for(int j=0;j<nodesArray.size();j++) {
        			int disX = (int) Math.pow(nodesArray.get(i).getPosX()-nodesArray.get(j).getPosX(),2);
        			int disY = (int) Math.pow(nodesArray.get(i).getPosY()-nodesArray.get(j).getPosY(),2);
        			int weight = (int) Math.sqrt(disX + disY);
        			weightMatrix[i][j]=weight;
        			
        		}
    		}
    		
    		int minDis = Integer.MAX_VALUE;
    		int currentDistance = 0;
    		int iterations = factorial(nodesArray.size()-1);
    		int[] path = new int[nodesArray.size()+1];
    		int[] bestPath = new int[nodesArray.size()+1];
    		//System.out.println(iterations);
    		for(int i=0;i<iterations;i++) {
    			currentDistance = 0;
    			path = createPath(i, nodesArray.size()-1);
    	    	// Determine distance of path
    	    	for(int n=0; n<path.length-1;n++) {
    	    		currentDistance += weightMatrix[path[n]][path[n+1]];
    	    		//System.out.println("from " + path[n] + " to " + path[n+1] + " is " + weightMatrix[path[n]][path[n+1]]);
    	    	}
    	    	if(currentDistance < minDis) {
    	    		minDis = currentDistance;
    	    		bestPath = path;
    	    	}
    		}
			System.out.println();
	    	System.out.print("(Brute Force) Best Path: ");
	    	for(int p: bestPath) {
	    		System.out.print(p + " ");
	    	}
	    	System.out.println(" Distance: " + minDis);
	    	return bestPath;
    	}
    }
}
