import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.geometry.*;
import java.util.*;

public class ex06_3 extends Application
{
	private Label lb;
	private ArrayList<ComboBox<String>> cb_list = new ArrayList<ComboBox<String>>();

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		lb = new Label("いらっしゃいませ。");

		ObservableList<String> ol = FXCollections.observableArrayList("乗用車", "トラック", "オープンカー", "タクシー", "スポーツカー", "ミニカー");
		for (int i = 0; i < 3; i++)
		{
			ComboBox<String> cb = new ComboBox<String>();
			cb.setItems(ol);
			cb.setOnAction(new SampleEventHandler());
			cb_list.add(cb);
		}

		BorderPane bp = new BorderPane();
		VBox vb = new VBox();
		bp.setTop(lb);
		for (int i = 0; i < cb_list.size(); i++)
			vb.getChildren().add(cb_list.get(i));
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
			for (int i = 0; i < cb_list.size(); i++)
			{
				if (cb_list.get(i).getValue() != null)
				{
					sb.append(cb_list.get(i).getValue().toString());
					sb.append(", ");
				}
			}
			lb.setText(sb.toString() + "ですね。");
		}
	}
}