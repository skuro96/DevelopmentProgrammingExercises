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

public class Kadai05_1 extends Application
{
	private TextField[][] tf = new TextField[9][9];
	private static String fname = "input.txt";

	public static void main(String[] args)
	{
		if (args.length > 0)
			fname = args[0];
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		Scanner s = new Scanner(new File(fname));
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int v = s.nextInt();
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

		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(5);
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				gp.add(tf[i][j], j, i);
		gp.setAlignment(Pos.CENTER);

		Scene sc = new Scene(gp, 500, 500);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}
}