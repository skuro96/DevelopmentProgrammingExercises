import java.util.Scanner;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class Kadai05_2 extends Application
{
	private static String fname = "input.txt";
	private TextField[][] tf = new TextField[9][9];
	private Button bt;

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
			{
				for (int j = 0; j < 9; j++)
				{
					int v = scn.nextInt();
					if (1 <= v && v <= 9)
					{
						tf[i][j] = new TextField(String.valueOf(v));
						tf[i][j].setEditable(false);
						tf[i][j].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
					}
					else
						tf[i][j] = new TextField();
					tf[i][j].setMaxWidth(40);
					tf[i][j].setFont(Font.font("MonoSpace", 20));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
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

		bt = new Button("リセット");
		GridPane gp_bt = new GridPane();
		gp_bt.add(bt, 0, 0);
		gp_bt.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setCenter(gp);
		bp.setBottom(gp_bt);

		Scene sc = new Scene(bp, 500, 500);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}
}