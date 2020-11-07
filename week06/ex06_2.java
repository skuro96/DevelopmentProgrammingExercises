import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.geometry.*;

public class ex06_2 extends Application
{
	private Label lb;
	private ComboBox<String>[] cb = new ComboBox<String>[3];

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		lb = new Label("いらっしゃいませ。");
		for (int i = 0; i < cb.length; i++)
			cb[i] = new ComboBox<String>();

		ObservableList<String> ol = FXCollections.observableArrayList("乗用車", "トラック", "オープンカー", "タクシー", "スポーツカー", "ミニカー");
		for (int i = 0; i < cb.length; i++)
			cb[i].setItems(ol);

		BorderPane bp = new BorderPane();
		VBox vb = new VBox();
		bp.setTop(lb);
		for (int i = 0; i < cb.length; i++)
		{
			vb.getChildren().add(cb[i]);
			cb[i].setOnAction(new SampleEventHandler());
		}
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);

		Scene sc = new Scene(bp, 300, 200);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}

	class SampleEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < cb.length; i++)
			{
				if (cb[i].getValue() != null)
				{
					sb.append(cb[i].getValue().toString());
					sb.append(", ");
				}
			}
			lb.setText(sb.toString() + "ですね。");
		}
	}
}