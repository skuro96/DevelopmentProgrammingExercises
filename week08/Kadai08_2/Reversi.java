import java.awt.*;
import javax.swing.*;

class Board
{
	Board(){}

	void paint(Graphics g, int unit_size)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, unit_size*10, unit_size*10);
		g.setColor(new Color(0, 85, 0));
		g.fillRect(unit_size, unit_size, unit_size*8, unit_size*8);
		g.setColor(Color.black);
		for (int i = 0; i < 9; i++)
			g.drawLine(unit_size, unit_size * (i + 1), unit_size * 9, unit_size * (i + 1));
		for (int i = 0; i < 9; i++)
			g.drawLine(unit_size * (i + 1), unit_size, unit_size * (i + 1), unit_size * 9);
		int dotsize = unit_size / 10;
		Point center = new Point();
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				center.x = unit_size * 3 + unit_size * 4 * i;
				center.y = unit_size * 3 + unit_size * 4 * j;
				g.fillRect(center.x - dotsize / 2, center.y - dotsize / 2, dotsize, dotsize);
			}
		}
	}
}

public class Reversi extends JPanel
{
	public final static int UNIT_SIZE = 80;
	Board board = new Board();

	public Reversi()
	{
		setPreferredSize(new Dimension(UNIT_SIZE*10, UNIT_SIZE*10));
	}

	public void paintComponent(Graphics g)
	{
		board.paint(g, UNIT_SIZE);
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new Reversi());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}