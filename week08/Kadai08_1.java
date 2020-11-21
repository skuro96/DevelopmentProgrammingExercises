import java.awt.*;
import javax.swing.*;

public class Kadai08_1 extends JPanel
{
	final int width = 600;
	final int height = 800;

	public Kadai08_1()
	{
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g)
	{
		Polygon[][] p = new Polygon[4][2];
		int dial_x = width / 20;
		int dial_y = height / 20;

		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.red);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				int mid_x = width/4 + width/2*j;
				int mid_y = height/5 + height/5*i;
				p[i][j] = new Polygon();
				p[i][j].addPoint(mid_x, 			mid_y - dial_y);
				p[i][j].addPoint(mid_x - dial_x, 	mid_y);
				p[i][j].addPoint(mid_x ,			mid_y + dial_y);
				p[i][j].addPoint(mid_x + dial_x,	mid_y);
				g.fillPolygon(p[i][j]);
			}
		}
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new Kadai08_1());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}