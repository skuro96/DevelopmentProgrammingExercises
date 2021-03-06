import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

	int getObverse()
	{
		return (obverse);
	}
}

class Board
{
	private Stone[][] board = new Stone[8][8];
	public int num_grid_black;
	public int num_grid_white;

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
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				Point dot_p = new Point(unit_size * 3 + unit_size * 4 * i, unit_size * 3 + unit_size * 4 * j);
				g.fillRect(dot_p.x - dotsize / 2, dot_p.y - dotsize / 2, dotsize, dotsize);
			}
		}

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Point p = new Point((int)(unit_size * (1.5 + i)), (int)(unit_size * (1.5 + j)));
				board[i][j].paint(g, p, (int)(unit_size * 0.4));
			}
		}
	}

	boolean isOnBoard(int x, int y)
	{
		return ((0 <= x && x <= 8) && (0 <= y && y <= 8));
	}

	void setStone(int x, int y, int s)
	{
		if (s == MouseEvent.BUTTON1)
			board[x][y].setObverse(Stone.black);
		else if (s == MouseEvent.BUTTON3)
			board[x][y].setObverse(Stone.white);
	}

	int countStone(int s)
	{
		int cnt = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j].getObverse() == s)
					cnt++;
		return (cnt);
	}

	void evaluateBoard()
	{
		num_grid_black = countStone(Stone.black);
		num_grid_white = countStone(Stone.white);
	}
}

public class Reversi extends JPanel
{
	public final static int UNIT_SIZE = 80;
	Board board = new Board();

	public Reversi()
	{
		setPreferredSize(new Dimension(UNIT_SIZE * 10, UNIT_SIZE * 10));
		addMouseListener(new MouseProc());
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

	void EndMessageDialog()
	{
		String score = "[黒:" + board.num_grid_black + ",白:" + board.num_grid_white + "]で";
		String result;

		if (board.num_grid_black > board.num_grid_white)
			result = "黒の勝ち";
		else if (board.num_grid_black < board.num_grid_white)
			result = "白の勝ち";
		else
			result = "引き分け";

		JOptionPane.showMessageDialog(this, score + result, "ゲーム終了", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	class MouseProc extends MouseAdapter
	{
		public void mouseClicked(MouseEvent me)
		{
			Point point = me.getPoint();
			int btn = me.getButton();

			if ((UNIT_SIZE <= point.x && point.x <= UNIT_SIZE * 9) && (UNIT_SIZE <= point.y && point.y <= UNIT_SIZE * 9))
			{
				int x = point.x / UNIT_SIZE - 1;
				int y = point.y / UNIT_SIZE - 1;
				board.setStone(x, y, btn);
				repaint();
			}
			
			board.evaluateBoard();
			if (board.num_grid_black + board.num_grid_white == 64)
				EndMessageDialog();
		}
	}
}