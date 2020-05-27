package paths.Old;

import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import paths.Node;

import javax.swing.*;    
import java.awt.Graphics;

public class createGui{
	public static int nodes = 0;
	static List<Node> nodesArray = new ArrayList<Node>();
	static List<Node> unvisitedNodes = new ArrayList<Node>();
	public static int distance = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Path Finder");
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            	Node n = new Node(e.getX(),e.getY());
            	nodesArray.add(n);

            	// Create label to demonstrate position
                JLabel label=new JLabel(""+nodes);  

            	nodes++;
                label.setBounds(e.getX()-10, e.getY()-40, 20, 20);  
                // Update Frame
                frame.add(label);
                SwingUtilities.updateComponentTreeUI(frame);
                //findPath
                findPath();
            }
        });
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

//    public static int recursionPath(int c, int[][] weightMatrix, List<Integer> unvisitedNodes, int lastNode) {
//    	List<Integer> unvisited = unvisitedNodes;
//    	int curDis = c;
//    	if(unvisited.size()>1) {
//    		
//    		curDis += recursionPath(curDis, weightMatrix, unvisited, lastNode);
//    	}
//    	else {
//    		curDis += weightMatrix[lastNode][unvisited.get(0)];
//    	}
//    	return curDis;
//    }
    
    public static int factorial(int n) {
    	int ans = 1;
    	for(int i =n; i>0;i--) {
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
    
    public static void findPath() {
    	if(nodesArray.size() < 2) {
    		System.out.println("More nodes needed");
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
	    	System.out.print("Best Path: ");
	    	for(int p: bestPath) {
	    		System.out.print(p + " ");
	    	}
	    	System.out.print(" Distance: " + minDis);
    	}
    	
    }

}