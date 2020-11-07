import java.util.*;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.collections.*;

public class Kadai06_2 extends Application
{
	private int[][] value = new int[9][9];
	private TextField[][] tf = new TextField[9][9];
	private ArrayList<ComboBox<String>> cb_list = new ArrayList<ComboBox<String>>();

	private static String fname = "input.txt";
	private Label lb;
	private Button bt;

	final String msg_default = "各コンボボックスで数字を選択してください";
	final String msg_complete = "ゲームクリア！おめでとうございます！！";

	public static void main(String[] args)
	{
		if (args.length > 0)
			fname = args[0];
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		Scanner scn = new Scanner(new File(fname));

		try
		{
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					value[i][j] = scn.nextInt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				ComboBox<String> cb = new ComboBox<String>();
				ObservableList<String> ol = FXCollections.observableArrayList();
				ArrayList<String> numList = validNums(i, j);

				for (int s = 0; s < numList.size(); s++)
					ol.add(numList.get(s));

				cb.setItems(ol);
				cb.setOnAction(new SelectEventHandler());
				cb_list.add(cb);

				if (isValid(value[i][j]))
				{
					tf[i][j] = new TextField(String.valueOf(value[i][j]));
					tf[i][j].setEditable(false);
					tf[i][j].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
					tf[i][j].setFont(Font.font("MonoSpace", 18));
					tf[i][j].setMaxWidth(55);
				}
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
				for (int p = 0; p < 3; p++)
				{
					for (int q = 0; q < 3; q++)
					{
						if (isValid(value[i * 3 + p][j * 3 + q]))
							sub_gp[i][j].add(tf[i * 3 + p][j * 3 + q], q, p);
						else
							sub_gp[i][j].add(cb_list.get((i * 3 + p) * 9 + (j * 3 + q)), q, p);
					}
				}
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

		Scene sc = new Scene(bp, 600, 450);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}

	public boolean isValid(int value)
	{
		return (1 <= value && value <= 9);
	}

	public boolean isComplete()
	{
		try
		{
			int[] checkList_x  = new int[9];
			int[] checkList_y  = new int[9];
			int[] checkList_sq = new int[9];

			for (int i = 0; i < 9; i++)
			{
				Arrays.fill(checkList_x, 0);
				Arrays.fill(checkList_y, 0);
				for (int j = 0; j < 9; j++)
				{
					if (isValid(value[i][j]))
					{
						checkList_x[value[i][j] - 1] = 1;
						checkList_y[value[i][j] - 1] = 1;
					}
					else
					{
						checkList_x[Integer.parseInt(cb_list.get(i * 9 + j).getValue().toString()) - 1] = 1;
						checkList_y[Integer.parseInt(cb_list.get(i * 9 + j).getValue().toString()) - 1] = 1;
					}
				}
				if (Arrays.stream(checkList_x).sum() != 9 || Arrays.stream(checkList_y).sum() != 9)
					return false;
			}

			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					Arrays.fill(checkList_sq, 0);
					for (int p = 0; p < 3; p++)
					{
						for (int q = 0; q < 3; q++)
						{
							if (isValid(value[i * 3 + p][j * 3 + q]))
								checkList_sq[value[i * 3 + p][j * 3 + q] - 1] = 1;
							else
								checkList_sq[Integer.parseInt(cb_list.get((i * 3 + p) * 9 + (j * 3 + q)).getValue().toString()) - 1] = 1;
						}
					}
					if (Arrays.stream(checkList_sq).sum() != 9)
						return false;
				}
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public ArrayList<String> validNums(int x, int y)
	{
		ArrayList<String> numList = new ArrayList<String>();
		int[] checkList = new int[9];
		Arrays.fill(checkList, 0);

		for (int i = 0; i < 9; i++)
		{
			try
			{
				if (isValid(value[x][i]))
					checkList[value[x][i] - 1] = 1;
				else
					checkList[Integer.parseInt(cb_list.get(x * 9 + i).getValue().toString()) - 1] = 1;

				if (isValid(value[i][y]))
					checkList[value[i][y] - 1] = 1;
				else
					checkList[Integer.parseInt(cb_list.get(i * 9 + y).getValue().toString()) - 1] = 1;

				int sq_x = (x / 3) * 3;
				int sq_y = (y / 3) * 3;
				int sq_i = sq_x + i / 3;
				int sq_j = sq_y + i % 3;
				if (isValid(value[sq_i][sq_j]))
					checkList[value[sq_i][sq_j] - 1] = 1;
				else
					checkList[Integer.parseInt(cb_list.get(sq_i * 9 + sq_j).getValue().toString()) - 1] = 1;
			}
			catch (Exception e) {}
		}

		for (int i = 0; i < 9; i++)
		{
			if (checkList[i] == 0)
				numList.add(String.valueOf(i + 1));
		}
		return numList;
	}

	class SelectEventHandler implements EventHandler<ActionEvent>
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
					if (!isValid(value[i][j]))
						cb_list.get(i * 9 + j).setValue(null);
			lb.setText(msg_default);
		}
	}
}