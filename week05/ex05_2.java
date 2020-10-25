import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class ex05_2 extends Application
{
	private TextField[][] tf = new TextField[3][3];

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if ((i * 3 + j) % 2 == 0)
					tf[i][j] = new TextField();
				else
				{
					tf[i][j] = new TextField(String.valueOf(i * 3 + j));
					tf[i][j].setEditable(false);
					tf[i][j].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
				}
				tf[i][j].setMaxWidth(40);
				tf[i][j].setFont(Font.font("MonoSpace", 20));
			}
		}

		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(5);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				gp.add(tf[i][j], j, i);
		gp.setAlignment(Pos.CENTER);

		Scene sc = new Scene(gp, 200, 200);
		stage.setScene(sc);
		stage.setTitle("3x3");
		stage.show();
	}
}