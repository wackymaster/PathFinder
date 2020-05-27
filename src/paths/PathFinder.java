package paths;
import java.awt.color.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;

public class PathFinder extends JFrame{
	List<Node> nodesArray = new ArrayList<Node>();
	int[] bestPath = new int[0];
	public PathFinder() {
		setTitle("Pathfinder");
//        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Core PC\\eclipse-workspace\\PathFinder\\src\\paths\\img1.jpg")));

        JButton bruteForceButton=new JButton("Brute Force");
        add(bruteForceButton);
        bruteForceButton.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
		        		 long startTime = System.nanoTime();
        	             bestPath = BruteForceFind.findPath(nodesArray);
						 long endTime = System.nanoTime();
						 long totalTime = endTime - startTime;
						 System.out.println("Time elasped (msec.) " + totalTime/1000000.0);
			             if(bestPath != null) {
				             repaint();
			             }
        	        }  
        	    });  
        
        JButton greedyButton =new JButton("Greedy Algorithm");
        add(greedyButton);
        greedyButton.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
        		 long startTime = System.nanoTime();
	             bestPath = GreedyAlgorithm.findPath(nodesArray);
				 long endTime = System.nanoTime();
				 long totalTime = endTime - startTime;
				 System.out.println("Time elasped (msec.) " + totalTime/1000000.0);
	             if(bestPath != null) {
		             repaint();
	             }
	        }  
	    });  
        
        JButton optSwapButton =new JButton("2-Opt Swap");
        add(optSwapButton);
        optSwapButton.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
        		 long startTime = System.nanoTime();
	             bestPath = OptSwap.findPath(nodesArray);
				 long endTime = System.nanoTime();
				 long totalTime = endTime - startTime;
				 System.out.println("Time elasped (msec.) " + totalTime/1000000.0);
	             if(bestPath != null) {
		             repaint();
	             }
	        }  
        });
        
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		
		addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Node n = new Node(e.getX(),e.getY());
            	nodesArray.add(n);
            	repaint();
            }
		});
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);

		for(int i=0; i<nodesArray.size();i++) {
			g.fillRect(nodesArray.get(i).getPosX(), nodesArray.get(i).getPosY(), 10, 10);
			if(i==0) {
				g.drawString("X",nodesArray.get(i).getPosX()+2, nodesArray.get(i).getPosY()-3);
			}
			else {
				g.drawString(""+i,nodesArray.get(i).getPosX()+2, nodesArray.get(i).getPosY()-3);
			}
		}
		// Visualize the Best Path
		for(int i=0; i<bestPath.length-1;i++) {
			g.drawLine(nodesArray.get(bestPath[i]).getPosX(), nodesArray.get(bestPath[i]).getPosY(), nodesArray.get(bestPath[i+1]).getPosX(), nodesArray.get(bestPath[i+1]).getPosY());
		}
		
//		Create an entire visual connected graph (looks awesome)
//		for(int i=0; i<nodesArray.size();i++) {
//			for(int j=0; j<nodesArray.size();j++) {
//				g.drawLine(nodesArray.get(i).getPosX(), nodesArray.get(i).getPosY(), nodesArray.get(j).getPosX(), nodesArray.get(j).getPosY());
//			}
//		}
	}
	
	public static void main(String [] args) {
		PathFinder r = new PathFinder();

	}

}