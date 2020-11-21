import java.awt.*;
import javax.swing.*;

class Point
{
	int x;
	int y;
}

class Stone
{
	public final static int black = 1;
	public final static int white = 2;
	private int obverse;

	Stone()
	{
		obverse = 0;
	}

	void setObverse(int color)
	{
		if (color == black || color == white)
			obverse = color;
		else
			System.out.println("黒か白でなければいけません");
	}

	void paint(Graphics g, Point center, int radius)
	{
		if (obverse == black)
		{
			g.setColor(Color.black);
			g.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
		}
		else if (obverse == white)
		{
			g.setColor(Color.white);
			g.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
		}
	}
}

class Board
{
	Stone[][] board = new Stone[8][8];

	Board()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				board[i][j] = new Stone();
				if ((i == 3 && j == 3) || (i == 4 && j == 4))
					board[i][j].setObverse(1);
				else if ((i == 3 && j == 4) || (i == 4 && j == 3))
					board[i][j].setObverse(2);
			}
		}
	}

	void paint(Graphics g, int unit_size)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, unit_size * 10, unit_size * 10);
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

		Point p = new Point();
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				p.x = (int)(unit_size * (1.5 + i));
				p.y = (int)(unit_size * (1.5 + j));
				board[i][j].paint(g, p, (int)(unit_size * 0.4));
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