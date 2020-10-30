import java.util.*;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;

public class Kadai05_3 extends Application
{
	private static String fname = "input.txt";
	private int[][] value = new int[9][9];
	private TextField[][] tf = new TextField[9][9];
	private Button bt;
	private Label lb;
	private final String msg_default = "空いているマスに1桁の数字を入力してください";
	private final String msg_complete = "ゲームクリア！おめでとうございます！！";

	public static void main(String[] args)
	{
		if (args.length > 0)
			fname = args[0];
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		Scanner scn = new Scanner(new File(fname));
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				value[i][j] = scn.nextInt();
				if (1 <= value[i][j] && value[i][j] <= 9)
				{
					tf[i][j] = new TextField(String.valueOf(value[i][j]));
					tf[i][j].setEditable(false);
					tf[i][j].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
				}
				else
				{
					tf[i][j] = new TextField();
					tf[i][j].setOnAction(new InputEventHandler());
				}
				tf[i][j].setMaxWidth(40);
				tf[i][j].setFont(Font.font("MonoSpace", 20));
			}
		}

		GridPane[][] sub_gp = new GridPane[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				sub_gp[i][j] = new GridPane();
				sub_gp[i][j].setHgap(5);
				sub_gp[i][j].setVgap(5);
				for (int s = 0; s < 3; s++)
					for (int t = 0; t < 3; t++)
						sub_gp[i][j].add(tf[i * 3 + s][j * 3 + t], t, s);
			}
		}

		GridPane gp = new GridPane();
		gp.setHgap(15);
		gp.setVgap(15);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				gp.add(sub_gp[i][j], j, i);
		gp.setAlignment(Pos.CENTER);

		lb = new Label(msg_default);

		bt = new Button("リセット");
		bt.setOnAction(new ResetButtonHandler());
		GridPane gp_bt = new GridPane();
		gp_bt.add(bt, 0, 0);
		gp_bt.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setTop(gp);
		bp.setCenter(lb);
		bp.setBottom(gp_bt);

		Scene sc = new Scene(bp, 500, 500);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}

	public boolean isComplete()
	{
		try
		{
			int[] checkList_x = new int[9];
			int[] checkList_y = new int[9];
			int[] checkList_sqr = new int [9];

			for (int i = 0; i < 9; i++)
			{
				Arrays.fill(checkList_x, 0);
				Arrays.fill(checkList_y, 0);
				for (int j = 0; j < 9; j++)
				{
					checkList_x[Integer.valueOf(tf[i][j].getText()) - 1] = 1;
					checkList_y[Integer.valueOf(tf[j][i].getText()) - 1] = 1;
				}
				if (Arrays.stream(checkList_x).sum() < 9 || Arrays.stream(checkList_y).sum() < 9)
					return false;
			}

			for (int i = 0; i < 3; i ++)
			{
				for (int j = 0; j < 3; j++)
				{
					Arrays.fill(checkList_sqr, 0);
					for (int p = 0; p < 3; p++)
						for (int q = 0; q < 3; q++)
							checkList_sqr[Integer.valueOf(tf[i * 3 + p][j * 3 + q].getText()) - 1] = 1;
					if (Arrays.stream(checkList_sqr).sum() < 9)
						return false;
				}
			}
			return true;
		}
		catch (Exception e){ return false; }
	}

	class InputEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			if (isComplete())
				lb.setText(msg_complete);
			else
				lb.setText(msg_default);
		}
	}

	class ResetButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (!(1 <= value[i][j] && value[i][j] <= 9))
						tf[i][j].clear();
			lb.setText(msg_default);
		}
	}
}