import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ex03_3 extends Application
{
	private Label lb;
	private Button bt;

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		lb = new Label();
		bt = new Button("購入");
		lb.setText("いらっしゃいませ");
		BorderPane bp = new BorderPane();
		bp.setTop(lb);
		bp.setCenter(bt);

		Scene sc = new Scene(bp, 300, 200);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}
}