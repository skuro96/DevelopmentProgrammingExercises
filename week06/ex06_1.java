import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;

public class ex06_1 extends Application
{
	private Label lb;
	private ComboBox<String> cb;

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		lb = new Label("いらっしゃいませ。");
		cb = new ComboBox<String>();

		ObservableList<String> ol = FXCollections.observableArrayList("乗用車", "トラック", "オープンカー", "タクシー", "スポーツカー", "ミニカー");
		cb.setItems(ol);

		BorderPane bp = new BorderPane();
		bp.setTop(lb);
		bp.setCenter(cb);
		cb.setOnAction(new SampleEventHandler());

		Scene sc = new Scene(bp, 300, 200);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}

	class SampleEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			String str = cb.getValue().toString();
			lb.setText(str + "ですね。");
		}
	}
}