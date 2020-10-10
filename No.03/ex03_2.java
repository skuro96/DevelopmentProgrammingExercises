import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ex03_2 extends Application
{
	private Label lb;

	public static void main(String[] args)
	{
		launch(args);
	}
	public void start(Stage stage) throws Exception
	{
		lb = new Label();
		lb.setText("こんにちは");

		BorderPane bp = new BorderPane();
		bp.setCenter(lb);
		Scene sc = new Scene(bp, 300, 200);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}
}