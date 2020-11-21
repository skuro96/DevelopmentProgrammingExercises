import java.awt.*;
import javax.swing.*;

public class ex08_2 extends JPanel
{
	public ex08_2()
	{
		setPreferredSize(new Dimension(800, 600));
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.white);
		g.fillRect(100, 100, 600, 400);
		g.setColor(Color.red);
		Polygon p = new Polygon();
		p.addPoint(400, 200);
		p.addPoint(300, 300);
		p.addPoint(400, 400);
		p.addPoint(500, 300);
		g.fillPolygon(p);
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new ex08_2());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}