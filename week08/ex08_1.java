import java.awt.*;
import javax.swing.*;

public class ex08_1 extends JPanel
{
	public ex08_1()
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
		g.fillOval(300, 200, 200, 200);
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new ex08_1());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}