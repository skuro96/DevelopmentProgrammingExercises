import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class ex05_3 extends Application
{
	private TextField[][] tf = new TextField[3][3];
	private Button[][] bt = new Button[2][2];

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

		GridPane sub_gp1 = new GridPane();
		sub_gp1.setHgap(5);
		sub_gp1.setVgap(5);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				sub_gp1.add(tf[i][j], j , i);
		sub_gp1.setAlignment(Pos.CENTER);

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				bt[i][j] = new Button(String.valueOf(i * 2 + j));

		GridPane sub_gp2 = new GridPane();
		sub_gp2.setHgap(15);
		sub_gp2.setVgap(15);
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				sub_gp2.add(bt[i][j], j, i);
		sub_gp2.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setTop(sub_gp1);
		bp.setBottom(sub_gp2);

		Scene sc = new Scene(bp, 200, 250);
		stage.setScene(sc);
		stage.setTitle("ペインの組み合わせ");
		stage.show();
	}
}