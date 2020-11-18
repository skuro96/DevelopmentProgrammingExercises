import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class ex07_1 extends Application
{
	private TextField tf;
	private Button[][] bt = new Button[3][4];

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		tf = new TextField();
		tf.setEditable(false);
		tf.setMaxWidth(380);
		tf.setFont(Font.font("MonoSpace", 40));
		tf.setAlignment(Pos.CENTER_RIGHT);

		String[][] bt_str = {
			{"7", "8", "9"},
			{"4", "5", "6"},
			{"1", "2", "3"},
			{"0", "+", "="}
		};
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				bt[i][j] = new Button(bt_str[j][i]);
				bt[i][j].setPrefWidth(95);
				bt[i][j].setPrefHeight(95);
				bt[i][j].setFont(Font.font("MonoSpace", 30));
			}
		}

		GridPane gp = new GridPane();
		gp.setHgap(2);
		gp.setVgap(2);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 4; j++)
				gp.add(bt[i][j], i, j);
		gp.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setTop(tf);
		bp.setAlignment(tf, Pos.CENTER);
		bp.setCenter(gp);

		Scene sc = new Scene(bp, 400, 600);
		stage.setScene(sc);
		stage.setTitle("電卓");
		stage.show();
	}
}