import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ex09_2 extends JPanel
{
	int x = 400;
	int y = 300;

	public ex09_2()
	{
		setPreferredSize(new Dimension(800, 600));
		addMouseListener(new MouseProc());
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.white);
		g.fillRect(100, 100, 600, 400);
		g.setColor(Color.red);
		g.fillOval(x - 100, y - 100, 200, 200);
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new ex09_2());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	void MessageDialog()
	{
		String str = "白四角の範囲外です";
		JOptionPane.showMessageDialog(this, str, "メッセージ", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	class MouseProc extends MouseAdapter
	{
		public void mouseClicked(MouseEvent me)
		{
			Point point = me.getPoint();
			int btn = me.getButton();
			System.out.println("(" + point.x + "," + point.y + ")");
			if (btn == MouseEvent.BUTTON1)
			{
				if ((100 <= point.x && point.x <= 700) && (100 <= point.y && point.y <= 500))
				{
					x = point.x;
					y = point.y;
				}
				else
				{
					MessageDialog();
				}
			}
			repaint();
		}
	}
}