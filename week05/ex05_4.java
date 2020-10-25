import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class ex05_4 extends Application
{
	private TextField tf;
	private Label lb;

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		lb = new Label("入力してください");
		tf = new TextField();
		tf.setMaxWidth(180);

		BorderPane bp = new BorderPane();
		bp.setTop(lb);
		bp.setBottom(tf);

		tf.setOnAction(new InputEventHandler());

		Scene sc = new Scene(bp, 200, 100);
		stage.setScene(sc);
		stage.setTitle("テキストフィールドのイベント取得");
		stage.show();
	}

	class InputEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			lb.setText(tf.getText());
		}
	}
}