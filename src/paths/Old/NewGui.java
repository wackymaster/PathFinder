package paths.Old;
import java.awt.color.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JFrame;

import paths.Node;

import java.awt.Graphics;
public class NewGui extends JFrame{
	List<Node> nodesArray = new ArrayList<Node>();
	public NewGui() {
		setTitle("Pathfinder");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Node n = new Node(e.getX(),e.getY());
            	nodesArray.add(n);
            	repaint();
            }
		});
		
	}
	public void paint(Graphics g) {
		for(int i=0; i<nodesArray.size();i++) {
			g.fillRect(nodesArray.get(i).getPosX(), nodesArray.get(i).getPosY(), 10, 10);
			g.drawString(""+i,nodesArray.get(i).getPosX()+2, nodesArray.get(i).getPosY()-3);

		}
//		Create an entire visual connected graph (looks awesome)
//		for(int i=0; i<nodesArray.size();i++) {
//			for(int j=0; j<nodesArray.size();j++) {
//				g.drawLine(nodesArray.get(i).getPosX(), nodesArray.get(i).getPosY(), nodesArray.get(j).getPosX(), nodesArray.get(j).getPosY());
//			}
//		}
		
	}

}
