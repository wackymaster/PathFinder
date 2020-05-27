package paths;
import java.awt.Graphics;
public class Node {
	private int xPos;
	private int yPos;
    private int width = 15;
    private int height = 15;
    
	public Node(int x, int y) {
		xPos = x;
		yPos = y;
	}
	public int getPosX() {
		return xPos;
	}
	public int getPosY() {
		return yPos;
	}
    public void paintSquare(Graphics g){
        g.drawRect(xPos,yPos,width,height);  
    }
}
